package com.bytatech.ayoos.appointment.service;

import com.bytatech.ayoos.appointment.client.bpmn.model.TaskRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AdditionalInformationRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentConfirmationRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentConfirmationResponse;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.PatientInfo;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.PaymentConfirmationRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.ProcessPayment;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.Slot;
import com.bytatech.ayoos.appointment.domain.Appointment;
import com.bytatech.ayoos.appointment.resource.CommandResource;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.ConsultationDetails;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.DoctorInfo;
import com.bytatech.ayoos.appointment.service.dto.AppointmentDTO;

/**
 * Service Interface for managing Appointment.
 */
public interface AppointmentCommandService {

	/**
	 * Save a appointment.
	 *
	 * @param appointmentDTO the entity to save
	 * @return the persisted entity
	 */
	AppointmentDTO save(AppointmentDTO appointmentDTO);

	/**
	 * Delete the "id" appointment.
	 *
	 * @param id the id of the entity
	 */
	void delete(Long id);

	CommandResource initiateAppointment(AppointmentRequest appointmentRequest);

	CommandResource chooseDoctor(String taskId, DoctorInfo doctorInfo);

	void updateTask(String taskId, TaskRequest taskRequest);

	CommandResource selectSlot(String taskId, Slot slotSelectionRequest);

	CommandResource confirmRegistration(String taskId);

	void updatePatientInfo(PatientInfo patientInfo, String processInstanceId);

	CommandResource sendAppointmentRequest(String taskId, AppointmentConfirmationRequest appointmentConfirmationRequest);

	CommandResource processAppointmentRequest(String taskId, AppointmentConfirmationResponse appointmentConfirmationResponse);

	CommandResource confirmPayment(String taskId, PaymentConfirmationRequest paymentConfirmationRequest);

	CommandResource processPayment(String taskId, ProcessPayment processPayment);

	CommandResource additionalInformationRequest(String taskId, AdditionalInformationRequest additionalInformationRequest);

	CommandResource collectAdditionalDetails(String taskId, ConsultationDetails symptomDetails);
	
	public boolean publishMessageToKafka(Appointment appointment);
}
