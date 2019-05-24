package com.bytatech.ayoos.appointment.service.impl;

import com.bytatech.ayoos.appointment.service.AppointmentCommandService;
import com.bytatech.ayoos.appointment.service.AppointmentQueryService;
import com.bytatech.ayoos.appointment.service.UserService;
import com.bytatech.ayoos.appointment.avro.ConsultationInfo;
import com.bytatech.ayoos.appointment.avro.Symptom;
import com.bytatech.ayoos.appointment.avro.Timing;
import com.bytatech.ayoos.appointment.client.bpmn.api.FormsApi;
import com.bytatech.ayoos.appointment.client.bpmn.api.HistoryApi;
import com.bytatech.ayoos.appointment.client.bpmn.api.ProcessInstancesApi;
import com.bytatech.ayoos.appointment.client.bpmn.api.TasksApi;
import com.bytatech.ayoos.appointment.client.bpmn.model.DataResponse;
import com.bytatech.ayoos.appointment.client.bpmn.model.ProcessInstanceCreateRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.ProcessInstanceResponse;
import com.bytatech.ayoos.appointment.client.bpmn.model.RestFormProperty;
import com.bytatech.ayoos.appointment.client.bpmn.model.RestVariable;
import com.bytatech.ayoos.appointment.client.bpmn.model.SubmitFormRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.TaskActionRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.TaskRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.TaskResponse;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AdditionalInformationRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentConfirmationRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentConfirmationResponse;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentDetails;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.DoctorInfo;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.PatientInfo;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.PaymentConfirmationRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.PaymentInfo;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.ProcessPayment;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.Settings;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.Slot;
import com.bytatech.ayoos.appointment.client.doctor.api.DoctorResourceApi;
import com.bytatech.ayoos.appointment.client.doctor.model.DoctorAggregateDTO;
import com.bytatech.ayoos.appointment.client.doctor.model.DoctorDTO;
import com.bytatech.ayoos.appointment.config.MessageBindersConfiguration;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.ConsultationDetails;
import com.bytatech.ayoos.appointment.domain.Appointment;
import com.bytatech.ayoos.appointment.repository.AppointmentRepository;
import com.bytatech.ayoos.appointment.repository.search.AppointmentSearchRepository;
import com.bytatech.ayoos.appointment.resource.assembler.AppointmentCommandResourceAssembler;
import com.bytatech.ayoos.appointment.security.SecurityUtils;
import com.bytatech.ayoos.appointment.service.dto.AppointmentDTO;
import com.bytatech.ayoos.appointment.service.dto.UserDTO;
import com.bytatech.ayoos.appointment.service.mapper.AppointmentMapper;
import com.bytatech.ayoos.appointment.resource.CommandResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Appointment.
 */
@Service
@Transactional
@SuppressWarnings("unchecked")

public class AppointmentCommandServiceImpl implements AppointmentCommandService {

	private final Logger log = LoggerFactory.getLogger(AppointmentCommandServiceImpl.class);

	private static final String BPM_PROCESSDEFINITION_ID = "booking-prototype-key:7:20326";

	@Autowired
	private DoctorResourceApi doctorApi;
	
	@Autowired
	private AppointmentCommandResourceAssembler assembler;

	private final MessageBindersConfiguration messageChannel;

	private final AppointmentRepository appointmentRepository;

	private final AppointmentMapper appointmentMapper;

	private final AppointmentSearchRepository appointmentSearchRepository;

	private final AppointmentQueryService queryService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProcessInstancesApi processInstanceApi;

	@Autowired
	private FormsApi formsApi;

	@Autowired
	private TasksApi tasksApi;

	@Autowired
	private HistoryApi histroyApi;

	public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository,
			AppointmentMapper appointmentMapper, AppointmentSearchRepository appointmentSearchRepository,
			AppointmentQueryService appointmentQueryService, MessageBindersConfiguration messageChannel) {
		this.appointmentRepository = appointmentRepository;
		this.appointmentMapper = appointmentMapper;
		this.appointmentSearchRepository = appointmentSearchRepository;
		this.queryService = appointmentQueryService;
		this.messageChannel = messageChannel;
	}

	/**
	 * Save a appointment.
	 *
	 * @param appointmentDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public AppointmentDTO save(AppointmentDTO appointmentDTO) {
		log.debug("Request to save Appointment : {}", appointmentDTO);

		Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
		appointment = appointmentRepository.save(appointment);
		AppointmentDTO result = appointmentMapper.toDto(appointment);
		appointmentSearchRepository.save(appointment);
		return result;
	}

	/**
	 * Delete the appointment by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Appointment : {}", id);
		appointmentRepository.deleteById(id);
		appointmentSearchRepository.deleteById(id);
	}

	public String generateTrackingID() {
		String prefix = null;
		UUID uniqueKey = UUID.randomUUID();
		Long least = uniqueKey.getMostSignificantBits();
		if (least.toString().charAt(0) == '-') {
			prefix = "AY";
			System.out.println("if");
		} else {
			System.out.println("else");
			prefix = "AY-";
		}
		return prefix + least.toString();
	}

	@Override
	public CommandResource initiateAppointment(AppointmentRequest appointmentRequest) {
		log.info("Initiating Appointment +++++ In service IMPL");
		ProcessInstanceCreateRequest processInstanceCreateRequest = new ProcessInstanceCreateRequest();
		processInstanceCreateRequest.setProcessDefinitionId(BPM_PROCESSDEFINITION_ID);
		List<RestVariable> variables = new ArrayList<RestVariable>();
		RestVariable appointmentDetailsVariable = new RestVariable();
		appointmentDetailsVariable.setName("appointmentDetails");
		appointmentDetailsVariable.setScope("global");
		AppointmentDetails appointmentDetails = getAppointmentDetails();
		appointmentDetailsVariable.setValue(appointmentDetails);
		variables.add(appointmentDetailsVariable);
		log.info("Appointment Deatils are " + appointmentDetails);

		processInstanceCreateRequest.setVariables(variables);

		ResponseEntity<ProcessInstanceResponse> processInstanceResponse = processInstanceApi
				.createProcessInstance(processInstanceCreateRequest);
		String processInstanceId = processInstanceResponse.getBody().getId();
		log.info("ProcessInstanceId is " + processInstanceId);

		ResponseEntity<DataResponse> taskResponseDoctor = tasksApi.getTasks("Choose Doctor", null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, processInstanceId, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null);
		String chooseDoctorTaskId = ((List<LinkedHashMap<String, String>>) taskResponseDoctor.getBody().getData())
				.get(0).get("id");
		log.info("Task ID of Choose Doctor task ID is " + chooseDoctorTaskId);
		chooseDoctor(chooseDoctorTaskId, TestgetDoctorDetails(appointmentRequest.getDoctorId()));

		ResponseEntity<DataResponse> taskResponseSlot = tasksApi.getTasks("Choose Slot", null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, processInstanceId, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null);
		String chooseSlotTaskId = ((List<LinkedHashMap<String, String>>) taskResponseSlot.getBody().getData()).get(0)
				.get("id");
		log.info("Task ID of Choose Slot task ID is " + chooseSlotTaskId);

		selectSlot(chooseSlotTaskId, appointmentRequest.getSlot());
		CommandResource commandResource = assembler.toResource(processInstanceId);
		commandResource.setStatus(taskResponseSlot.getStatusCode().name());
		return commandResource;
	}

	public AppointmentDetails getAppointmentDetails() {
		AppointmentDetails appointmentDetails = new AppointmentDetails();
		appointmentDetails.setAppointmentDateAndTime(ZonedDateTime.now(ZoneId.systemDefault()).toString());
		appointmentDetails.setTrackingID(generateTrackingID());
		UserDTO currentUser = null;
		if (SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2Authentication) {
			currentUser = userService.getUserFromAuthentication(
					(OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication());
		}
		appointmentDetails.setPatientInfo(getPatientDetails(currentUser));
		log.info("Appointment Details are " + appointmentDetails);
		return appointmentDetails;
	}

	public PatientInfo getPatientDetails(UserDTO userDTO) {
		// User principal call to get patient Details here
		if (userDTO == null) {

			return null;

		} else {
			PatientInfo patientInfo = new PatientInfo();
			patientInfo.setEmail(userDTO.getEmail());
			patientInfo.setPhoneNumber(Long.parseLong(SecurityUtils.getPhoneNumberFromAuthentication().get()));
			patientInfo.setFirstName(userDTO.getFirstName());
			patientInfo.setPatientId(userDTO.getLogin());
			log.info("Patient Details " + patientInfo);
			return patientInfo;
		}
	}

	@Override
	public CommandResource chooseDoctor(String taskId, DoctorInfo doctorInfo) {
		TaskActionRequest taskActionRequest = new TaskActionRequest();
		taskActionRequest.setAction("complete");
		List<RestVariable> variables = new ArrayList<RestVariable>();

		RestVariable doctorInfoVariable = new RestVariable();
		doctorInfoVariable.setName("doctorInfo");
		doctorInfoVariable.setScope("global");
		doctorInfoVariable.setValue(doctorInfo);
		variables.add(doctorInfoVariable);

		taskActionRequest.setVariables(variables);
		log.info("DoctorInfo " + doctorInfo);
		String processInstanceId=tasksApi.getTask(taskId).getBody().getProcessInstanceId();
		CommandResource commandResource = assembler.toResource(processInstanceId);
		ResponseEntity<Void> response=tasksApi.executeTaskAction(taskId, taskActionRequest);
		commandResource.setStatus(response.getStatusCode().name());
		return commandResource;
	}

	public DoctorInfo TestgetDoctorDetails(String doctorId) {
		// Rest call to get doctor service for Details here
		
		DoctorAggregateDTO doctorDTO=doctorApi.getDoctorByDoctorIdUsingGET(doctorId).getBody();
		
		
		DoctorInfo doctorInfo = new DoctorInfo();
		doctorInfo.setEmail(doctorDTO.getEmail());
		doctorInfo.setPhoneNumber(doctorDTO.getPhoneNumber());
		doctorInfo.setFirstName(doctorDTO.getFirstName());
		doctorInfo.setDoctorId(doctorId);

		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setAmount(doctorDTO.getPaymentSettings().getAmount());
		paymentInfo.setCurrency(doctorDTO.getPaymentSettings().getCurrency());
		paymentInfo.setNote_to_payer(doctorDTO.getPaymentSettings().getNoteToPayer());
		paymentInfo.setPaymentGatewayCredentials("abilash.s-facilitator@lxisoft.com");
		paymentInfo.setPaymentGatewayProvider("paypal");
		paymentInfo.setPaymentMethod("paypal");
		paymentInfo.setIsPaymentEnabled(doctorDTO.getPaymentSettings().isIsPaymentEnabled());

		Settings settings = new Settings();
		settings.setApprovalType(doctorDTO.getDoctorSettings().getApprovalType());
		settings.setIsMailNotificationsEnabled(doctorDTO.getDoctorSettings().isIsMailNotificationsEnabled());
		settings.setIsSMSNotificationsEnabled(doctorDTO.getDoctorSettings().isIsSMSNotificationsEnabled());
		settings.setPaymentSettings(paymentInfo);
		doctorInfo.setSettings(settings);
		return doctorInfo;

	}

	@Override
	public void updateTask(String taskId, TaskRequest taskRequest) {

		tasksApi.updateTask(taskId, taskRequest);

	}

	@Override
	public CommandResource selectSlot(String taskId, Slot slotSelectionRequest) {
		List<RestFormProperty> formProperties = new ArrayList<RestFormProperty>();
		SubmitFormRequest formRequest = new SubmitFormRequest();
		formRequest.setAction("completed");
		formRequest.setTaskId(taskId); /* Publishing avro messages to Kafka */

		log.info("Slot Details " + slotSelectionRequest);
		RestFormProperty dayFormProperty = new RestFormProperty();
		dayFormProperty.setId("day");
		dayFormProperty.setName("day");
		dayFormProperty.setType("date");
		dayFormProperty.setReadable(true);
		dayFormProperty.setWritable(true);
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		String day = formatter.format(slotSelectionRequest.getDay());
		dayFormProperty.setValue(day);
		formProperties.add(dayFormProperty);

		RestFormProperty startTimeFormProperty = new RestFormProperty();
		startTimeFormProperty.setId("startTime");
		startTimeFormProperty.setName("startTime");
		startTimeFormProperty.setType("date");
		startTimeFormProperty.setReadable(true);
		startTimeFormProperty.setWritable(true);
		SimpleDateFormat startTimeformatter = new SimpleDateFormat("hh:mm");
		String startTime = startTimeformatter.format(slotSelectionRequest.getStartTime());
		startTimeFormProperty.setValue(startTime);
		formProperties.add(startTimeFormProperty);

		RestFormProperty endTimeFormProperty = new RestFormProperty();
		endTimeFormProperty.setId("endTime");
		endTimeFormProperty.setName("endTime");
		endTimeFormProperty.setType("date");
		endTimeFormProperty.setReadable(true);
		endTimeFormProperty.setWritable(true);
		SimpleDateFormat endTimeformatter = new SimpleDateFormat("hh:mm");
		String endTime = endTimeformatter.format(slotSelectionRequest.getEndTime());
		endTimeFormProperty.setValue(endTime);
		formProperties.add(endTimeFormProperty);

		formRequest.setProperties(formProperties);
		String processInstanceId=tasksApi.getTask(taskId).getBody().getProcessInstanceId();
		CommandResource commandResource = assembler.toResource(processInstanceId);
		ResponseEntity<ProcessInstanceResponse> response=formsApi.submitForm(formRequest);
		commandResource.setStatus(response.getStatusCode().name());
		return commandResource;
	}

	@Override
	public CommandResource confirmRegistration(String taskId) {
		String processInstanceId=tasksApi.getTask(taskId).getBody().getProcessInstanceId();
		ResponseEntity<Void> response=null;
		UserDTO currentUser = null;
		if (SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2Authentication) {
			currentUser = userService.getUserFromAuthentication(
					(OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication());
		}
		if (getPatientDetails(currentUser) != null) {
			TaskActionRequest taskActionRequest = new TaskActionRequest();
			taskActionRequest.setAction("complete");
			List<RestVariable> variables = new ArrayList<RestVariable>();
			taskActionRequest.setVariables(variables);
			ResponseEntity<TaskResponse> taskReposponse = queryService.getTask(taskId);
			updatePatientInfo(getPatientDetails(currentUser), taskReposponse.getBody().getProcessInstanceId()); // refactor
			response=tasksApi.executeTaskAction(taskId, taskActionRequest);
		} else {
			log.info("The user is not registered");
		}
		CommandResource commandResource = assembler.toResource(processInstanceId);
		commandResource.setStatus(response.getStatusCode().name());
		return commandResource;
	}

	@Override
	public void updatePatientInfo(PatientInfo patientInfo, String processInstanceId) {
		RestVariable variable = new RestVariable();
		variable.setName("appointmentDetails");
		AppointmentDetails appointmentDetails = updateAppointmentDetails(processInstanceId);
		appointmentDetails.setPatientInfo(patientInfo);
		variable.setValue(appointmentDetails);
		processInstanceApi.updateProcessInstanceVariable(variable, processInstanceId, "appointmentDetails");
	}

	private AppointmentDetails updateAppointmentDetails(String processInstanceId) {
		AppointmentDetails appointmentDetailsOld = queryService.getAppointmentDetails(processInstanceId);
		AppointmentDetails appointmentDetailsUpdated = new AppointmentDetails();
		appointmentDetailsUpdated.setAppointmentDateAndTime(appointmentDetailsOld.getAppointmentDateAndTime());
		appointmentDetailsUpdated.setAppointmentID(appointmentDetailsOld.getAppointmentID());
		appointmentDetailsUpdated.setTrackingID(appointmentDetailsOld.getTrackingID());
		return appointmentDetailsUpdated;
	}

	@Override
	public CommandResource sendAppointmentRequest(String taskId,
			AppointmentConfirmationRequest appointmentConfirmationRequest) {
		String processInstanceId=tasksApi.getTask(taskId).getBody().getProcessInstanceId();

		List<RestFormProperty> formProperties = new ArrayList<RestFormProperty>();
		SubmitFormRequest formRequest = new SubmitFormRequest();
		formRequest.setAction("completed");
		formRequest.setTaskId(taskId);
		/* Publishing avro messages to Kafka */

		RestFormProperty requestConfirmationProperty = new RestFormProperty();
		requestConfirmationProperty.setId("requestConfirmation");
		requestConfirmationProperty.setName("requestConfirmation");
		requestConfirmationProperty.setType("string");
		requestConfirmationProperty.setReadable(true);
		requestConfirmationProperty.setWritable(true);
		requestConfirmationProperty.setValue(appointmentConfirmationRequest.getRequestConfirmation());
		formProperties.add(requestConfirmationProperty);
		formRequest.setProperties(formProperties);
		ResponseEntity<ProcessInstanceResponse> response=formsApi.submitForm(formRequest);
		CommandResource commandResource = assembler.toResource(processInstanceId);
		commandResource.setStatus(response.getStatusCode().name());
		return commandResource;
	}

	@Override
	public CommandResource processAppointmentRequest(String taskId,
			AppointmentConfirmationResponse appointmentConfirmationResponse) {
		String processInstanceId=tasksApi.getTask(taskId).getBody().getProcessInstanceId();

		List<RestFormProperty> formProperties = new ArrayList<RestFormProperty>();
		SubmitFormRequest formRequest = new SubmitFormRequest();
		formRequest.setAction("completed");
		formRequest.setTaskId(taskId);

		RestFormProperty appointmentConfirmationProperty = new RestFormProperty();
		appointmentConfirmationProperty.setId("appointmentConfirmation");
		appointmentConfirmationProperty.setName("appointmentConfirmation");
		appointmentConfirmationProperty.setType("string");
		appointmentConfirmationProperty.setReadable(true);
		appointmentConfirmationProperty.setWritable(true);
		appointmentConfirmationProperty.setValue(appointmentConfirmationResponse.getAppointmentConfirmation());
		formProperties.add(appointmentConfirmationProperty);

		RestFormProperty isSuggetionEnabledProperty = new RestFormProperty();
		isSuggetionEnabledProperty.setId("isSuggetionEnabled");
		isSuggetionEnabledProperty.setName("motivation");
		isSuggetionEnabledProperty.setType("boolean");
		isSuggetionEnabledProperty.setReadable(true);
		isSuggetionEnabledProperty.setWritable(true);
		isSuggetionEnabledProperty.setValue("" + appointmentConfirmationResponse.getIsSuggetionEnabled());
		formProperties.add(isSuggetionEnabledProperty);

		RestFormProperty messageProperty = new RestFormProperty();
		messageProperty.setId("message");
		messageProperty.setName("message");
		messageProperty.setType("string");
		messageProperty.setReadable(true);
		messageProperty.setWritable(true);
		messageProperty.setValue("" + appointmentConfirmationResponse.getMessage());
		formProperties.add(messageProperty);

		formRequest.setProperties(formProperties);
		ResponseEntity<ProcessInstanceResponse> response=formsApi.submitForm(formRequest);
		CommandResource commandResource = assembler.toResource(processInstanceId);
		commandResource.setStatus(response.getStatusCode().name());
		return commandResource;

	}

	@Override
	public CommandResource confirmPayment(String taskId, PaymentConfirmationRequest paymentConfirmationRequest) {
		String processInstanceId=tasksApi.getTask(taskId).getBody().getProcessInstanceId();

		List<RestFormProperty> formProperties = new ArrayList<RestFormProperty>();
		SubmitFormRequest formRequest = new SubmitFormRequest();
		formRequest.setAction("completed");
		formRequest.setTaskId(taskId);

		RestFormProperty paymentDecisionProperty = new RestFormProperty();
		paymentDecisionProperty.setId("paymentDecision");
		paymentDecisionProperty.setName("paymentDecision");
		paymentDecisionProperty.setType("string");
		paymentDecisionProperty.setReadable(true);
		paymentDecisionProperty.setWritable(true);
		paymentDecisionProperty.setValue(paymentConfirmationRequest.getPaymentDecision());
		formProperties.add(paymentDecisionProperty);
		formRequest.setProperties(formProperties);
		ResponseEntity<ProcessInstanceResponse> response=formsApi.submitForm(formRequest);
		CommandResource commandResource = assembler.toResource(processInstanceId);
		commandResource.setStatus(response.getStatusCode().name());
		return commandResource;
	}

	@Override
	public CommandResource processPayment(String taskId, ProcessPayment processPayment) {
		String processInstanceId=tasksApi.getTask(taskId).getBody().getProcessInstanceId();

		List<RestFormProperty> formProperties = new ArrayList<RestFormProperty>();
		SubmitFormRequest formRequest = new SubmitFormRequest();
		formRequest.setAction("completed");
		formRequest.setTaskId(taskId);

		RestFormProperty paymentStatusProperty = new RestFormProperty();
		paymentStatusProperty.setId("paymentStatus");
		paymentStatusProperty.setName("paymentStatus");
		paymentStatusProperty.setType("string");
		paymentStatusProperty.setReadable(true);
		paymentStatusProperty.setWritable(true);
		paymentStatusProperty.setValue(processPayment.getPaymentStatus());
		formProperties.add(paymentStatusProperty);
		formRequest.setProperties(formProperties);
		ResponseEntity<ProcessInstanceResponse> response=formsApi.submitForm(formRequest);
		CommandResource commandResource = assembler.toResource(processInstanceId);
		commandResource.setStatus(response.getStatusCode().name());
		return commandResource;
	}

	@Override
	public CommandResource additionalInformationRequest(String taskId,
			AdditionalInformationRequest additionalInformationRequest) {
		String processInstanceId=tasksApi.getTask(taskId).getBody().getProcessInstanceId();

		List<RestFormProperty> formProperties = new ArrayList<RestFormProperty>();
		SubmitFormRequest formRequest = new SubmitFormRequest();
		formRequest.setAction("completed");
		formRequest.setTaskId(taskId);

		RestFormProperty decisionProperty = new RestFormProperty();
		decisionProperty.setId("decision");
		decisionProperty.setName("decision");
		decisionProperty.setType("string");
		decisionProperty.setReadable(true);
		decisionProperty.setWritable(true);
		decisionProperty.setValue(additionalInformationRequest.getDecision());
		formProperties.add(decisionProperty);
		formRequest.setProperties(formProperties);
		ResponseEntity<ProcessInstanceResponse> response=formsApi.submitForm(formRequest);
		CommandResource commandResource = assembler.toResource(processInstanceId);
		commandResource.setStatus(response.getStatusCode().name());
		return commandResource;
	}

	@Override
	public CommandResource collectAdditionalDetails(String taskId, ConsultationDetails consultationDetails) {
		String processInstanceId=tasksApi.getTask(taskId).getBody().getProcessInstanceId();

		TaskActionRequest taskActionRequest = new TaskActionRequest();
		taskActionRequest.setAction("complete");
		List<RestVariable> variables = new ArrayList<RestVariable>();

		RestVariable symptomDetailsVariable = new RestVariable();
		symptomDetailsVariable.setName("consultationDetails");
		symptomDetailsVariable.setScope("global");
		symptomDetailsVariable.setValue(consultationDetails);
		variables.add(symptomDetailsVariable);
		taskActionRequest.setVariables(variables);
		log.info("SymptomsList " + symptomDetailsVariable);
		ResponseEntity<Void> response=tasksApi.executeTaskAction(taskId, taskActionRequest);
		ResponseEntity<DataResponse> taskReponse = histroyApi.listHistoricTaskInstances(taskId, null, null, null, null,
				null, null, null, null, null, null, "Collect Informations", null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null);
		List<LinkedHashMap<String, String>> map = (List<LinkedHashMap<String, String>>) taskReponse.getBody().getData();
		Appointment appointment = queryService.getCompletedAppointment(map.get(0).get("processInstanceId"));
		log.info("Appointment is " + appointment);

		/* Publishing avro messages to Kafka */
		Boolean status=publishMessageToKafka(appointment);
		appointmentRepository.save(appointment);
		appointmentSearchRepository.save(appointment);
		CommandResource commandResource = assembler.toResource(processInstanceId);
		if(response.getStatusCode().name().equalsIgnoreCase("OK")&&status)
		commandResource.setStatus(response.getStatusCode().name());
		return commandResource;
	}

	@Override
	public boolean publishMessageToKafka(Appointment appointment) {

		com.bytatech.ayoos.appointment.avro.Appointment appointmentAvro = com.bytatech.ayoos.appointment.avro.Appointment
				.newBuilder()
				.setAppointmentDateAndTime(appointment.getAppointmentDateAndTime().toInstant().toEpochMilli())
				.setAppointmentId(appointment.getAppointmentId())
				.setChronicDiseaseRef(appointment.getChronicDiseaseRef()).setDoctorId(appointment.getDoctorId())
				.setPatientId(appointment.getPatientId()).setNote(appointment.getNote())
				.setTrackingId(appointment.getTrackingId())
				.setConsultationInfo(ConsultationInfo.newBuilder().setAge(appointment.getConsultationInfo().getAge())
						.setHeight(appointment.getConsultationInfo().getHeight())
						.setWeight(appointment.getConsultationInfo().getWeight())
						.setSymptoms(appointment.getConsultationInfo().getSymptoms().stream().map(this::toAvroSymptom)
								.collect(Collectors.toList()))
						.build())
				.setTiming(Timing.newBuilder().setDay(appointment.getTiming().getDay().toEpochDay())
						.setEndTime(appointment.getTiming().getEndTo().toInstant().toEpochMilli())
						.setStartTime(appointment.getTiming().getStartFrom().toInstant().toEpochMilli()).build())
				.build();
		log.info("Avro Message appointment is " + appointmentAvro);
		return messageChannel.appointmentOut().send(MessageBuilder.withPayload(appointmentAvro).build());

	}

	private Symptom toAvroSymptom(com.bytatech.ayoos.appointment.domain.Symptom symptom) {

		return new Symptom(symptom.getRef(), symptom.getNumberOfDaysSuffering());

	}

}
