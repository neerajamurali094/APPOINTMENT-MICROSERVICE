 package com.bytatech.ayoos.appointment.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytatech.ayoos.appointment.client.bpmn.api.HistoryApi;
import com.bytatech.ayoos.appointment.client.bpmn.api.ProcessInstancesApi;
import com.bytatech.ayoos.appointment.client.bpmn.api.TasksApi;
import com.bytatech.ayoos.appointment.client.bpmn.model.DataResponse;

import com.bytatech.ayoos.appointment.client.bpmn.model.HistoricTaskInstanceQueryRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.RestVariable;
import com.bytatech.ayoos.appointment.client.bpmn.model.TaskQueryRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.TaskResponse;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentDetails;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.ConsultationDetails;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.DoctorInfo;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.OpenAppointmentResponse;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.PatientInfo;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.PaymentInfo;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.Settings;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.Slot;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.Syndrome;
import com.bytatech.ayoos.appointment.domain.Appointment;
import com.bytatech.ayoos.appointment.domain.ConsultationInfo;
import com.bytatech.ayoos.appointment.domain.Status;
import com.bytatech.ayoos.appointment.domain.Symptom;
import com.bytatech.ayoos.appointment.domain.Timing;
import com.bytatech.ayoos.appointment.repository.AppointmentRepository;
import com.bytatech.ayoos.appointment.repository.search.AppointmentSearchRepository;
import com.bytatech.ayoos.appointment.service.AppointmentQueryService;
import com.bytatech.ayoos.appointment.service.dto.AppointmentDTO;
import com.bytatech.ayoos.appointment.service.mapper.AppointmentMapper;


@Service
@SuppressWarnings("unchecked")
public class AppointmentQueryServiceImpl implements AppointmentQueryService {

    private final Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);

	@Autowired
	private TasksApi tasksApi;

	private final AppointmentMapper appointmentMapper;

	private final AppointmentSearchRepository appointmentSearchRepository;

	private final AppointmentRepository appointmentRepository;

	@Autowired
	private ProcessInstancesApi processInstanceApi;

	@Autowired
	private HistoryApi historyApi;

	public AppointmentQueryServiceImpl(AppointmentMapper appointmentMapper,
			AppointmentSearchRepository appointmentSearchRepository, AppointmentRepository appointmentrepository) {

		this.appointmentMapper = appointmentMapper;
		this.appointmentSearchRepository = appointmentSearchRepository;
		this.appointmentRepository = appointmentrepository;
	}

	@Override
	public ResponseEntity<DataResponse> getTasks(String name, String nameLike, String description, String priority,
			String minimumPriority, String maximumPriority, String assignee, String assigneeLike, String owner,
			String ownerLike, String unassigned, String delegationState, String candidateUser, String candidateGroup,
			String candidateGroups, String involvedUser, String taskDefinitionKey, String taskDefinitionKeyLike,
			String processInstanceId, String processInstanceBusinessKey, String processInstanceBusinessKeyLike,
			String processDefinitionId, String processDefinitionKey, String processDefinitionKeyLike,
			String processDefinitionName, String processDefinitionNameLike, String executionId, String createdOn,
			String createdBefore, String createdAfter, String dueOn, String dueBefore, String dueAfter,
			Boolean withoutDueDate, Boolean excludeSubTasks, Boolean active, Boolean includeTaskLocalVariables,
			Boolean includeProcessVariables, String tenantId, String tenantIdLike, Boolean withoutTenantId,
			String candidateOrAssigned, String category) {
		return tasksApi.getTasks(name, nameLike, description, priority, minimumPriority, maximumPriority, assignee,
				assigneeLike, owner, ownerLike, unassigned, delegationState, candidateUser, candidateGroup,
				candidateGroups, involvedUser, taskDefinitionKey, taskDefinitionKeyLike, processInstanceId,
				processInstanceBusinessKey, processInstanceBusinessKeyLike, processDefinitionId, processDefinitionKey,
				processDefinitionKeyLike, processDefinitionName, processDefinitionNameLike, executionId, createdOn,
				createdBefore, createdAfter, dueOn, dueBefore, dueAfter, withoutDueDate, excludeSubTasks, active,
				includeTaskLocalVariables, includeProcessVariables, tenantId, tenantIdLike, withoutTenantId,
				candidateOrAssigned, category);
	}

	@Override
	public ResponseEntity<DataResponse> getQueryresult(TaskQueryRequest taskQueryRequest) {
		return tasksApi.getQueryResult(taskQueryRequest);
	}

	@Override
	public ResponseEntity<TaskResponse> getTask(String taskId) {
		return tasksApi.getTask(taskId);
	}

	@Override
	public ResponseEntity<RestVariable> getTaskInstanceVariable(String taskId, String variableName, String scope) {
		return tasksApi.getTaskInstanceVariable(taskId, variableName, scope);
	}

	@Override
	public ResponseEntity<List<RestVariable>> getTaskVariables(String taskId) {
		return tasksApi.listTaskVariables(taskId);
	}

	/**
     * Search for the appointment corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AppointmentDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Appointments for query {}", query);
        return appointmentSearchRepository.search(queryStringQuery(query), pageable)
            .map(appointmentMapper::toDto);
    }

	/**
     * Get all the appointments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AppointmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Appointments");
        return appointmentRepository.findAll(pageable)
            .map(appointmentMapper::toDto);
    }


    /**
     * Get one appointment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AppointmentDTO> findOne(Long id) {
        log.debug("Request to get Appointment : {}", id);
        return appointmentRepository.findById(id)
            .map(appointmentMapper::toDto);
    }

	@Override
	public ResponseEntity<DataResponse> getTaskFormProperties(String id, String processInstanceId, String executionId,
			String activityInstanceId, String taskId, Boolean selectOnlyFormProperties,
			Boolean selectOnlyVariableUpdates) {
		return historyApi.getHistoricDetailInfo(id, processInstanceId, executionId, activityInstanceId, taskId,
				selectOnlyFormProperties, selectOnlyVariableUpdates);
	}

	@Override
	public ResponseEntity<List<RestVariable>> getAllProcesAttributes(String processInstanceId, String scope) {
		return processInstanceApi.getVariables(processInstanceId, scope);
	}

	@Override
	public AppointmentDetails getAppointmentDetails(String processInstanceId) {
		ResponseEntity<byte[]> response = historyApi.getHistoricProcessInstanceVariableData(processInstanceId,
				"appointmentDetails");
		LinkedHashMap<Object, Object> appointmentDetailsMap = (LinkedHashMap<Object, Object>) SerializationUtils
				.deserialize(response.getBody());
		AppointmentDetails appointmentDetails=new AppointmentDetails();
		appointmentDetails.setAppointmentID("APP-"+processInstanceId);
		appointmentDetails.setAppointmentDateAndTime((String)appointmentDetailsMap.get("appointmentDateAndTime"));
		appointmentDetails.setTrackingID((String)appointmentDetailsMap.get("trackingID"));
		appointmentDetails.setStatus("Ref");
	
		LinkedHashMap<Object, Object> patientInfoMap=(LinkedHashMap<Object, Object>)appointmentDetailsMap.get("patientInfo"); 
		PatientInfo patientInfo=new PatientInfo();
		if(patientInfoMap!=null) {
		patientInfo.setEmail((String)patientInfoMap.get("email"));
		patientInfo.setFirstName((String)patientInfoMap.get("firstName"));
		patientInfo.setPhoneNumber((Long)patientInfoMap.get("phoneNumber"));
		patientInfo.setPatientId((String)patientInfoMap.get("patientId"));
		appointmentDetails.setPatientInfo(patientInfo);
		}
		return appointmentDetails;

	}

	@Override
	public ConsultationDetails getSymptoms(String taskId) {
		ResponseEntity<byte[]> response = historyApi.getHistoricTaskInstanceVariableData(taskId, "consultationDetails",
				"global");
		LinkedHashMap<Object, Object> consultationInfoMap = (LinkedHashMap<Object, Object>) SerializationUtils
				.deserialize(response.getBody());
		List<Object> values = (List<Object>) consultationInfoMap.get("symptoms");
		Double height = (Double) consultationInfoMap.get("height");
		Integer age=(Integer)consultationInfoMap.get("age");
		ConsultationDetails consultationDetails = new ConsultationDetails();
		consultationDetails.setHeight(height.floatValue());
		consultationDetails.setWeight((Integer) consultationInfoMap.get("weight"));
		consultationDetails.setNote((String) consultationInfoMap.get("note"));
		consultationDetails.setAge(age);
		List<Syndrome> symptoms = new ArrayList<Syndrome>();
		for (Object value : values) {
			LinkedHashMap<Object, Object> symptomsMap = (LinkedHashMap<Object, Object>) value;
			Syndrome symptom = new Syndrome();
			symptom.setSymptomRef((String) symptomsMap.get("symptomRef"));
			symptom.setNumberOfDaysSuffering((String) symptomsMap.get("numberOfDaysSuffering"));
			symptoms.add(symptom);
		}
		consultationDetails.setSymptoms(symptoms);
		return consultationDetails;
	}

	@Override
	public List<OpenAppointmentResponse> getOpenAppointments(String name, String nameLike, String description, String priority,
			String minimumPriority, String maximumPriority, String assignee, String assigneeLike, String owner,
			String ownerLike, String unassigned, String delegationState, String candidateUser, String candidateGroup,
			String candidateGroups, String involvedUser, String taskDefinitionKey, String taskDefinitionKeyLike,
			String processInstanceId, String processInstanceBusinessKey, String processInstanceBusinessKeyLike,
			String processDefinitionId, String processDefinitionKey, String processDefinitionKeyLike,
			String processDefinitionName, String processDefinitionNameLike, String executionId, String createdOn,
			String createdBefore, String createdAfter, String dueOn, String dueBefore, String dueAfter,
			Boolean withoutDueDate, Boolean excludeSubTasks, Boolean active, Boolean includeTaskLocalVariables,
			Boolean includeProcessVariables, String tenantId, String tenantIdLike, Boolean withoutTenantId,
			String candidateOrAssigned, String category) {

		ResponseEntity<DataResponse> response = tasksApi.getTasks(name, nameLike, description, priority,
				minimumPriority, maximumPriority, assignee, assigneeLike, owner, ownerLike, unassigned, delegationState,
				candidateUser, candidateGroup, candidateGroups, involvedUser, taskDefinitionKey, taskDefinitionKeyLike,
				processInstanceId, processInstanceBusinessKey, processInstanceBusinessKeyLike, processDefinitionId,
				processDefinitionKey, processDefinitionKeyLike, processDefinitionName, processDefinitionNameLike,
				executionId, createdOn, createdBefore, createdAfter, dueOn, dueBefore, dueAfter, withoutDueDate,
				excludeSubTasks, active, includeTaskLocalVariables, includeProcessVariables, tenantId, tenantIdLike,
				withoutTenantId, candidateOrAssigned, category);
		List<LinkedHashMap<String, String>> taskResponses = (List<LinkedHashMap<String, String>>) response.getBody()
				.getData();
		
		List<OpenAppointmentResponse> myAppointments = new ArrayList<OpenAppointmentResponse>();
		for (LinkedHashMap<String, String> taskResponse : taskResponses) {
			OpenAppointmentResponse myAppointment = new OpenAppointmentResponse();
			String taskProcessInstanceId = taskResponse.get("processInstanceId");
			log.info("Process Instance id of open appointment is "+taskProcessInstanceId);
			
			myAppointment.setAppointmentDetails(getAppointmentDetails(taskProcessInstanceId));
			myAppointment.setSlot(getSlotInfo(taskProcessInstanceId));
			myAppointments.add(myAppointment);
		}
		return myAppointments;
	}

	@Override
	public ResponseEntity<DataResponse> getHistoricTask(HistoricTaskInstanceQueryRequest request) {
		return historyApi.queryHistoricTaskInstance(request);
	}

	@Override
	public Appointment getCompletedAppointment(String processInstanceId) {
		Appointment appointment = new Appointment();
		AppointmentDetails appointmentDetails=getAppointmentDetails(processInstanceId);
		appointment.setPatientId(appointmentDetails.getPatientInfo().getPatientId());
		appointment.setDoctorId(getDoctorInfo(processInstanceId).getDoctorId());
		Slot slot=getSlotInfo(processInstanceId);
		Timing timing =new Timing();
		timing.setDay(slot.getDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		timing.setStartFrom(ZonedDateTime.ofInstant(slot.getStartTime().toInstant(),
                                          ZoneId.systemDefault()));
		timing.setEndTo(ZonedDateTime.ofInstant(slot.getEndTime().toInstant(),
                                          ZoneId.systemDefault()));
		appointment.setTiming(timing);
		appointment.setTrackingId(appointmentDetails.getTrackingID());
		appointment.setAppointmentDateAndTime(ZonedDateTime.parse(appointmentDetails.getAppointmentDateAndTime()));
		appointment.setStatus(new Status());
		appointment.setAppointmentId("APP-"+processInstanceId);
		
		ConsultationInfo consultationInfo = new ConsultationInfo();
		List<LinkedHashMap<String, String>> taskResponseCollectInfo = (List<LinkedHashMap<String, String>>) getHistoricTaskusingProcessInstanceIdAndName(
				processInstanceId, "Collect Informations").getBody().getData();
		String taskId = taskResponseCollectInfo.get(0).get("id");
		log.info("Collect Informations TaskID is "+taskId);
		ConsultationDetails consultationdetails = getSymptoms(taskId);
		consultationInfo.setHeight(consultationdetails.getHeight());
		consultationInfo.setWeight(consultationdetails.getWeight().floatValue());
		consultationInfo.setAge(consultationdetails.getAge());
		List<Symptom> symtoms = new ArrayList<Symptom>();
		for (Syndrome syndrome : consultationdetails.getSymptoms()) {
			Symptom symptom = new Symptom();
			symptom.setRef((syndrome.getSymptomRef()));
			symptom.setNumberOfDaysSuffering(Integer.parseInt(syndrome.getNumberOfDaysSuffering()));
			symtoms.add(symptom);
		}
		consultationInfo.setSymptoms(new HashSet<Symptom>(symtoms));
		log.info("ConsultationInfo is "+consultationInfo);
		appointment.setConsultationInfo(consultationInfo);
		
		return appointment;
	}

	@Override
	public Slot getSlotInfo(String processInstanceId) {
		
		Slot slot = new Slot();
		List<LinkedHashMap<String, String>> taskResponseSlot = (List<LinkedHashMap<String, String>>) getHistoricTaskusingProcessInstanceIdAndName(
				processInstanceId, "Choose Slot").getBody().getData();
		
		Long taskId=taskResponseSlot.stream().mapToLong(obj -> Long.parseLong(obj.get("id"))).max().getAsLong();
		ResponseEntity<DataResponse> slotDetails = historyApi.getHistoricDetailInfo(null, processInstanceId, null, null,
				taskId.toString(), true, false);
		List<LinkedHashMap<String, String>> slotFormProperties = (List<LinkedHashMap<String, String>>) slotDetails
				.getBody().getData();
		log.info("Number of slots in the collection "+taskResponseSlot.size());
		log.info("Task Id of the slot is "+taskId);
		for (LinkedHashMap<String, String> slotMap : slotFormProperties) {
			String day = null;
			String endTime = null;
			String startTime = null;
			String propertyId = slotMap.get("propertyId");
			if (propertyId.equals("day")) {
				day = slotMap.get("propertyValue");
				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
				try {
					Date parsedDay = formatter.parse(day);
					log.info("Parsed day " + parsedDay);
					slot.setDay(parsedDay);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else if (propertyId.equals("startTime")) {
				startTime = slotMap.get("propertyValue");
				SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
				try {
					Date parsedStartTime = formatter.parse(startTime);
					log.info("Parsed StartTime "+startTime);
					slot.setStartTime(parsedStartTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else if (propertyId.equals("endTime")) {
				endTime = slotMap.get("propertyValue");
				SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
				try {
					Date parsedEndTime = formatter.parse(endTime);
					log.info("Parsed End Time "+endTime);
					slot.setEndTime(parsedEndTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return slot;
	}

	@Override
	public DoctorInfo getDoctorInfo(String processInstanceId) {
		List<LinkedHashMap<String, String>> doctorInfoResponse = (List<LinkedHashMap<String, String>>) getHistoricTaskusingProcessInstanceIdAndName(
				processInstanceId, "Choose Doctor").getBody().getData();
		String taskId = doctorInfoResponse.get(0).get("id");
		log.info("Task id for Choose Doctor Is "+taskId);
		ResponseEntity<byte[]> response = historyApi.getHistoricTaskInstanceVariableData(taskId, "doctorInfo",
				"global");
		LinkedHashMap<Object, Object> doctorInfoMap = (LinkedHashMap<Object, Object>) SerializationUtils
				.deserialize(response.getBody());
		DoctorInfo doctorInfo = new DoctorInfo();
		doctorInfo.setEmail((String) doctorInfoMap.get("email"));
		doctorInfo.setPhoneNumber((Long) doctorInfoMap.get("phoneNumber"));
		doctorInfo.setDoctorId((String) doctorInfoMap.get("doctorId"));
		doctorInfo.setFirstName((String) doctorInfoMap.get("firstName"));
		
		Settings settings=new Settings();
		LinkedHashMap<Object, Object> settingsMap=(LinkedHashMap<Object, Object>)doctorInfoMap.get("settings");
		settings.setApprovalType((String)settingsMap.get("approvalType"));
		settings.setIsMailNotificationsEnabled((Boolean)settingsMap.get("isMailNotificationsEnabled"));
		settings.setIsSMSNotificationsEnabled((Boolean)settingsMap.get("isSMSNotificationsEnabled"));
		
		PaymentInfo paymentInfo=new PaymentInfo();
		LinkedHashMap<Object, Object> paymentSettingsMap=(LinkedHashMap<Object, Object>)settingsMap.get("paymentSettings");
		paymentInfo.setAmount((Double) paymentSettingsMap.get("amount"));
		paymentInfo.setIsPaymentEnabled((Boolean) paymentSettingsMap.get("isPaymentEnabled"));
		paymentInfo.setCurrency((String) paymentSettingsMap.get("currency"));
		paymentInfo.setPaymentGatewayCredentials((String) paymentSettingsMap.get("paymentGatewayCredentials"));
		paymentInfo.setIntent("sale");
		paymentInfo.setPaymentGatewayProvider((String) paymentSettingsMap.get("paymentGatewayProvider"));
		paymentInfo.setPaymentMethod((String) paymentSettingsMap.get("paymentMethod"));
		
		settings.setPaymentSettings(paymentInfo);
		doctorInfo.setSettings(settings);
		return doctorInfo;

	}

	public ResponseEntity<DataResponse> getHistoricTaskusingProcessInstanceIdAndName(String processInstanceId,
			String name) {
		return historyApi.listHistoricTaskInstances(null, processInstanceId, null, null, null, null, null, null, null,
				null, null, name, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

	}

	
}
