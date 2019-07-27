package com.bytatech.ayoos.appointment.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytatech.ayoos.appointment.client.bpmn.model.TaskRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AdditionalInformationRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentConfirmationRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentConfirmationResponse;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.ConsultationDetails;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.DoctorInfo;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.PatientInfo;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.PaymentConfirmationRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.ProcessPayment;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.Slot;
import com.bytatech.ayoos.appointment.domain.Appointment;
import com.bytatech.ayoos.appointment.domain.ConsultationInfo;
import com.bytatech.ayoos.appointment.domain.Symptom;
import com.bytatech.ayoos.appointment.domain.Timing;
import com.bytatech.ayoos.appointment.service.AppointmentCommandService;
import com.bytatech.ayoos.appointment.service.TimingService;
import com.bytatech.ayoos.appointment.service.dto.AppointmentDTO;
import com.bytatech.ayoos.appointment.service.dto.TimingDTO;
import com.bytatech.ayoos.appointment.web.rest.errors.BadRequestAlertException;
import com.bytatech.ayoos.appointment.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;
import com.bytatech.ayoos.appointment.resource.CommandResource;

@RestController
@RequestMapping("/api/command")
public class AppointmentCommandResource {

	private final Logger log = LoggerFactory.getLogger(AppointmentResource.class);

	private static final String ENTITY_NAME = "ayoosAppointmentAppointment";

	private final AppointmentCommandService appointmentCommandService;
	
	@Autowired
	private TimingService timingService;

	public AppointmentCommandResource(AppointmentCommandService appointmentCommandService) {
		
		this.appointmentCommandService = appointmentCommandService;
	}

	/**
	 * POST /appointments : Create a new appointment.
	 *
	 * @param appointmentDTO the appointmentDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         appointmentDTO, or with status 400 (Bad Request) if the appointment
	 *         has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/appointments")
	@Timed
	public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO)
			throws URISyntaxException {
		log.debug("REST request to save Appointment : {}", appointmentDTO);
		if (appointmentDTO.getId() != null) {
			throw new BadRequestAlertException("A new appointment cannot already have an ID", ENTITY_NAME, "idexists");
		}
		AppointmentDTO result = appointmentCommandService.save(appointmentDTO);
		if (result.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		AppointmentDTO result2 = appointmentCommandService.save(result);
		return ResponseEntity.created(new URI("/api/appointments/" + result2.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result2.getId().toString())).body(result);
	}

	/**
	 * PUT /appointments : Updates an existing appointment.
	 *
	 * @param appointmentDTO the appointmentDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         appointmentDTO, or with status 400 (Bad Request) if the
	 *         appointmentDTO is not valid, or with status 500 (Internal Server
	 *         Error) if the appointmentDTO couldn't be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/appointments")
	@Timed
	public ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody AppointmentDTO appointmentDTO)
			throws URISyntaxException {
		log.debug("REST request to update Appointment : {}", appointmentDTO);
		if (appointmentDTO.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		AppointmentDTO result = appointmentCommandService.save(appointmentDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, appointmentDTO.getId().toString()))
				.body(result);
	}

	/**
	 * DELETE /appointments/:id : delete the "id" appointment.
	 *
	 * @param id the id of the appointmentDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/appointments/{id}")
	@Timed
	public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
		log.debug("REST request to delete Appointment : {}", id);
		appointmentCommandService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	@PostMapping("/initiateAppointment")
	public ResponseEntity<CommandResource> initiateAppointment(@RequestBody AppointmentRequest appointementRequest) {
		log.info("Initiating Appointment +++++ " + appointementRequest);
		CommandResource resource=appointmentCommandService.initiateAppointment(appointementRequest);
		return new ResponseEntity<CommandResource>(resource, HttpStatus.OK);
	}

	
	@PostMapping("/chooseDoctor/{taskId}")
	public CommandResource chooseDoctor(@PathVariable String taskId, @RequestBody DoctorInfo doctorInfo) {

		return appointmentCommandService.chooseDoctor(taskId, doctorInfo);
	}

	@PostMapping("/chooseSlot/{taskId}")
	public CommandResource selectSlot(@PathVariable String taskId, @RequestBody Slot slotSelectionRequest) {

		appointmentCommandService.selectSlot(taskId, slotSelectionRequest);
		return new CommandResource();
	}

	@PostMapping("/confirmRegistartion/{taskId}")
	public CommandResource confirmRegistration(@PathVariable String taskId) {
		return appointmentCommandService.confirmRegistration(taskId);
	}

	@PostMapping("/sendAppointmentRequest/{taskId}")
	public CommandResource sendAppointmentRequest(@PathVariable String taskId,
			@RequestBody AppointmentConfirmationRequest appointmentConfirmationRequest) {
		return appointmentCommandService.sendAppointmentRequest(taskId, appointmentConfirmationRequest);
	}

	@PostMapping("/processAppointmentRequest/{taskId}")
	public CommandResource processAppointmentRequest(@PathVariable String taskId,
			@RequestBody AppointmentConfirmationResponse appointmentConfirmationResponse) {
		return appointmentCommandService.processAppointmentRequest(taskId, appointmentConfirmationResponse);
	}

	@PostMapping("/confirmPayment/{taskId}")
	public CommandResource confirmPayment(@PathVariable String taskId,
			@RequestBody PaymentConfirmationRequest paymentConfirmationRequest) {

		return appointmentCommandService.confirmPayment(taskId, paymentConfirmationRequest);

	}

	@PostMapping("/processPayment/{taskId}")
	public CommandResource processPayment(@PathVariable String taskId, @RequestBody ProcessPayment processPayment) {

		return appointmentCommandService.processPayment(taskId, processPayment);
	}

	@PostMapping("/additionalInformationRequest/{taskId}")
	public CommandResource additionalInformationRequest(@PathVariable String taskId,
			@RequestBody AdditionalInformationRequest additionalInformationRequest) {
		return appointmentCommandService.additionalInformationRequest(taskId, additionalInformationRequest);
	}

	@PostMapping("/collectAdditionalDetails/{taskId}")
	public CommandResource collectAdditionalDetails(@PathVariable String taskId, @RequestBody ConsultationDetails symptomDetails) {

		return appointmentCommandService.collectAdditionalDetails(taskId, symptomDetails);

	}

	@PutMapping("/updateTask/{taskId}")
	public void updateTask(@PathVariable String taskId, @RequestBody TaskRequest taskRequest) {

		appointmentCommandService.updateTask(taskId, taskRequest);
	}

	@PutMapping("/updatePatientInfo/{processInstanceId}")
	public void updatePatientInfo(@RequestBody PatientInfo patientInfo, @PathVariable String processInstanceId) {
		appointmentCommandService.updatePatientInfo(patientInfo, processInstanceId);
	}

	@PostMapping("/publishAppointment")
	public boolean publishMessageToKafka() {

		/* Test Publish To Kafka*/
		
		Appointment appointment = new Appointment();
		appointment.setAppointmentDateAndTime(ZonedDateTime.now());
		appointment.setAppointmentId("App-2016");
		appointment.setChronicDiseaseRef("Ref-Dis");
		appointment.setTrackingId("65676787878989");
		appointment.setDoctorId("jowin");
		appointment.setPatientId("abilash");
		appointment.setNote("sample note");
		appointment.setConsultationInfo(new ConsultationInfo().age(23).height(17.0f)
				.symptoms(new HashSet<Symptom>(Arrays.asList(new Symptom().ref("Fev").numberOfDaysSuffering(12),
						new Symptom().ref("Cough").numberOfDaysSuffering(8)))));
		appointment
				.setTiming(new Timing().day(LocalDate.now()).startFrom(ZonedDateTime.now()).endTo(ZonedDateTime.now()));
		return appointmentCommandService.publishMessageToKafka(appointment);
	}
}
