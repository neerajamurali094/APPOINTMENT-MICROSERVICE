package com.bytatech.ayoos.appointment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.bytatech.ayoos.appointment.client.bpmn.model.DataResponse;
import com.bytatech.ayoos.appointment.client.bpmn.model.HistoricTaskInstanceQueryRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.RestVariable;
import com.bytatech.ayoos.appointment.client.bpmn.model.TaskQueryRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.TaskResponse;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentDetails;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.ConsultationDetails;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.DoctorInfo;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.OpenAppointmentResponse;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.Slot;
import com.bytatech.ayoos.appointment.domain.Appointment;
import com.bytatech.ayoos.appointment.service.dto.AppointmentDTO;

public interface AppointmentQueryService {

	ResponseEntity<DataResponse> getTasks(String name, String nameLike, String description, String priority,
			String minimumPriority, String maximumPriority, String assignee, String assigneeLike, String owner,
			String ownerLike, String unassigned, String delegationState, String candidateUser, String candidateGroup,
			String candidateGroups, String involvedUser, String taskDefinitionKey, String taskDefinitionKeyLike,
			String processInstanceId, String processInstanceBusinessKey, String processInstanceBusinessKeyLike,
			String processDefinitionId, String processDefinitionKey, String processDefinitionKeyLike,
			String processDefinitionName, String processDefinitionNameLike, String executionId, String createdOn,
			String createdBefore, String createdAfter, String dueOn, String dueBefore, String dueAfter,
			Boolean withoutDueDate, Boolean excludeSubTasks, Boolean active, Boolean includeTaskLocalVariables,
			Boolean includeProcessVariables, String tenantId, String tenantIdLike, Boolean withoutTenantId,
			String candidateOrAssigned, String category);

	ResponseEntity<DataResponse> getQueryresult(TaskQueryRequest taskQueryRequest);

	ResponseEntity<TaskResponse> getTask(String taskId);

	ResponseEntity<RestVariable> getTaskInstanceVariable(String taskId, String variableName, String scope);

	ResponseEntity<List<RestVariable>> getTaskVariables(String taskId);

	Page<AppointmentDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" appointment.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	Optional<AppointmentDTO> findOne(Long id);

	/**
	 * Search for the appointment corresponding to the query.
	 *
	 * @param query    the query of the search
	 * 
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	Page<AppointmentDTO> search(String query, Pageable pageable);

	ResponseEntity<DataResponse> getTaskFormProperties(String id, String processInstanceId, String executionId,
			String activityInstanceId, String taskId, Boolean selectOnlyFormProperties,
			Boolean selectOnlyVariableUpdates);

	ResponseEntity<List<RestVariable>> getAllProcesAttributes(String processInstanceId, String scope);

	AppointmentDetails getAppointmentDetails(String processInstanceId);

	ConsultationDetails getSymptoms(String taskId);

	// PaymentInfo getPaymentInfo(String taskId);

	List<OpenAppointmentResponse> getOpenAppointments(String name, String nameLike, String description, String priority,
			String minimumPriority, String maximumPriority, String assignee, String assigneeLike, String owner,
			String ownerLike, String unassigned, String delegationState, String candidateUser, String candidateGroup,
			String candidateGroups, String involvedUser, String taskDefinitionKey, String taskDefinitionKeyLike,
			String processInstanceId, String processInstanceBusinessKey, String processInstanceBusinessKeyLike,
			String processDefinitionId, String processDefinitionKey, String processDefinitionKeyLike,
			String processDefinitionName, String processDefinitionNameLike, String executionId, String createdOn,
			String createdBefore, String createdAfter, String dueOn, String dueBefore, String dueAfter,
			Boolean withoutDueDate, Boolean excludeSubTasks, Boolean active, Boolean includeTaskLocalVariables,
			Boolean includeProcessVariables, String tenantId, String tenantIdLike, Boolean withoutTenantId,
			String candidateOrAssigned, String category);

	ResponseEntity<DataResponse> getHistoricTask(HistoricTaskInstanceQueryRequest request);

	DoctorInfo getDoctorInfo(String processInstanceId);

	Slot getSlotInfo(String processInstanceId);

	Appointment getCompletedAppointment(String processInstanceId);

	public ResponseEntity<DataResponse> getHistoricTaskusingProcessInstanceIdAndName(String processInstanceId,
			String name);

}
