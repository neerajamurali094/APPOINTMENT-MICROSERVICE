package com.bytatech.ayoos.appointment.web.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
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
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.Slot;
import com.bytatech.ayoos.appointment.domain.Appointment;
import com.bytatech.ayoos.appointment.service.AppointmentQueryService;
import com.bytatech.ayoos.appointment.service.dto.AppointmentDTO;
import com.bytatech.ayoos.appointment.service.impl.AppointmentServiceImpl;
import com.bytatech.ayoos.appointment.web.rest.util.PaginationUtil;

import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/query")
public class AppointmentQueryResource {

	private final AppointmentQueryService appointmentQueryService;

	private final Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);

	/**
	 * SEARCH /_search/appointments?query=:query : search for the appointment
	 * corresponding to the query.
	 *
	 * @param query    the query of the appointment search
	 * @param pageable the pagination information
	 * @return the result of the search
	 */
	@GetMapping("/_search/appointments")
	@Timed
	public ResponseEntity<List<AppointmentDTO>> searchAppointments(@RequestParam String query, Pageable pageable) {
		log.debug("REST request to search for a page of Appointments for query {}", query);
		Page<AppointmentDTO> page = appointmentQueryService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page,
				"/api/_search/appointments");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	public AppointmentQueryResource(AppointmentQueryService appointmentQueryService) {
		this.appointmentQueryService = appointmentQueryService;
	}

	@GetMapping("/tasks")
	public ResponseEntity<DataResponse> getTasks(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "nameLike", required = false) String nameLike,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "priority", required = false) String priority,
			@RequestParam(value = "minimumPriority", required = false) String minimumPriority,
			@RequestParam(value = "maximumPriority", required = false) String maximumPriority,
			@RequestParam(value = "assignee", required = false) String assignee,
			@RequestParam(value = "assigneeLike", required = false) String assigneeLike,
			@RequestParam(value = "owner", required = false) String owner,
			@RequestParam(value = "ownerLike", required = false) String ownerLike,
			@RequestParam(value = "unassigned", required = false) String unassigned,
			@RequestParam(value = "delegationState", required = false) String delegationState,
			@RequestParam(value = "candidateUser", required = false) String candidateUser,
			@RequestParam(value = "candidateGroup", required = false) String candidateGroup,
			@RequestParam(value = "candidateGroups", required = false) String candidateGroups,
			@RequestParam(value = "involvedUser", required = false) String involvedUser,
			@RequestParam(value = "taskDefinitionKey", required = false) String taskDefinitionKey,
			@RequestParam(value = "taskDefinitionKeyLike", required = false) String taskDefinitionKeyLike,
			@RequestParam(value = "processInstanceId", required = false) String processInstanceId,
			@RequestParam(value = "processInstanceBusinessKey", required = false) String processInstanceBusinessKey,
			@RequestParam(value = "processInstanceBusinessKeyLike", required = false) String processInstanceBusinessKeyLike,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with the given id.") @Valid @RequestParam(value = "processDefinitionId", required = false) String processDefinitionId,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with the given key.") @Valid @RequestParam(value = "processDefinitionKey", required = false) String processDefinitionKey,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with a key like the given value.") @Valid @RequestParam(value = "processDefinitionKeyLike", required = false) String processDefinitionKeyLike,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with the given name.") @Valid @RequestParam(value = "processDefinitionName", required = false) String processDefinitionName,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with a name like the given value.") @Valid @RequestParam(value = "processDefinitionNameLike", required = false) String processDefinitionNameLike,
			@ApiParam(value = "Only return tasks which are part of the execution with the given id.") @Valid @RequestParam(value = "executionId", required = false) String executionId,
			@ApiParam(value = "Only return tasks which are created on the given date.") @Valid @RequestParam(value = "createdOn", required = false) String createdOn,
			@ApiParam(value = "Only return tasks which are created before the given date.") @Valid @RequestParam(value = "createdBefore", required = false) String createdBefore,
			@ApiParam(value = "Only return tasks which are created after the given date.") @Valid @RequestParam(value = "createdAfter", required = false) String createdAfter,
			@ApiParam(value = "Only return tasks which are due on the given date.") @Valid @RequestParam(value = "dueOn", required = false) String dueOn,
			@ApiParam(value = "Only return tasks which are due before the given date.") @Valid @RequestParam(value = "dueBefore", required = false) String dueBefore,
			@ApiParam(value = "Only return tasks which are due after the given date.") @Valid @RequestParam(value = "dueAfter", required = false) String dueAfter,
			@ApiParam(value = "Only return tasks which don�t have a due date. The property is ignored if the value is false.") @Valid @RequestParam(value = "withoutDueDate", required = false) Boolean withoutDueDate,
			@ApiParam(value = "Only return tasks that are not a subtask of another task.") @Valid @RequestParam(value = "excludeSubTasks", required = false) Boolean excludeSubTasks,
			@ApiParam(value = "If true, only return tasks that are not suspended (either part of a process that is not suspended or not part of a process at all). If false, only tasks that are part of suspended process instances are returned.") @Valid @RequestParam(value = "active", required = false) Boolean active,
			@ApiParam(value = "Indication to include task local variables in the result.") @Valid @RequestParam(value = "includeTaskLocalVariables", required = false) Boolean includeTaskLocalVariables,
			@ApiParam(value = "Indication to include process variables in the result.") @Valid @RequestParam(value = "includeProcessVariables", required = false) Boolean includeProcessVariables,
			@ApiParam(value = "Only return tasks with the given tenantId.") @Valid @RequestParam(value = "tenantId", required = false) String tenantId,
			@ApiParam(value = "Only return tasks with a tenantId like the given value.") @Valid @RequestParam(value = "tenantIdLike", required = false) String tenantIdLike,
			@ApiParam(value = "If true, only returns tasks without a tenantId set. If false, the withoutTenantId parameter is ignored.") @Valid @RequestParam(value = "withoutTenantId", required = false) Boolean withoutTenantId,
			@ApiParam(value = "Select tasks that has been claimed or assigned to user or waiting to claim by user (candidate user or groups).") @Valid @RequestParam(value = "candidateOrAssigned", required = false) String candidateOrAssigned,
			@ApiParam(value = "Select tasks with the given category. Note that this is the task category, not the category of the process definition (namespace within the BPMN Xml). ") @Valid @RequestParam(value = "category", required = false) String category) {

		return appointmentQueryService.getTasks(name, nameLike, description, priority, minimumPriority, maximumPriority,
				assignee, assigneeLike, owner, ownerLike, unassigned, delegationState, candidateUser, candidateGroup,
				candidateGroups, involvedUser, taskDefinitionKey, taskDefinitionKeyLike, processInstanceId,
				processInstanceBusinessKey, processInstanceBusinessKeyLike, processDefinitionId, processDefinitionKey,
				processDefinitionKeyLike, processDefinitionName, processDefinitionNameLike, executionId, createdOn,
				createdBefore, createdAfter, dueOn, dueBefore, dueAfter, withoutDueDate, excludeSubTasks, active,
				includeTaskLocalVariables, includeProcessVariables, tenantId, tenantIdLike, withoutTenantId,
				candidateOrAssigned, category);
	}

	@PostMapping("/tasks")
	public ResponseEntity<DataResponse> getQueryResult(@RequestBody TaskQueryRequest taskQueryRequest) {
		return appointmentQueryService.getQueryresult(taskQueryRequest);
	}

	@GetMapping("/tasks/{taskId}")
	public ResponseEntity<TaskResponse> getTask(@PathVariable String taskId) {
		return appointmentQueryService.getTask(taskId);
	}

	@GetMapping("/tasks/{taskId}/{variableName}")
	public ResponseEntity<RestVariable> getTaskInstanceVariable(@PathVariable String taskId,
			@PathVariable String variableName, @RequestParam(required = false) String scope) {

		return appointmentQueryService.getTaskInstanceVariable(taskId, variableName, scope);

	}

	@GetMapping("/tasks/{taskId}/variables")
	public ResponseEntity<List<RestVariable>> getTaskVariables(@PathVariable String taskId) {

		return appointmentQueryService.getTaskVariables(taskId);

	}

	@GetMapping("/history")
	public ResponseEntity<DataResponse> getTaskFormProperties(@RequestParam(required = false) String id,
			@RequestParam(required = false) String processInstanceId,
			@RequestParam(required = false) String executionId,
			@RequestParam(required = false) String activityInstanceId, @RequestParam(required = false) String taskId,
			@RequestParam(required = false) Boolean selectOnlyFormProperties,
			@RequestParam(required = false) Boolean selectOnlyVariableUpdates) {
		return appointmentQueryService.getTaskFormProperties(id, processInstanceId, executionId, activityInstanceId,
				taskId, selectOnlyFormProperties, selectOnlyVariableUpdates);
	}

	@GetMapping("/appointmentAttributes/{processInstanceId}")
	public ResponseEntity<List<RestVariable>> getAllProcessAttributes(@PathVariable String processInstanceId,
			@RequestParam(required = false) String scope) {
		ResponseEntity<List<RestVariable>> variables = appointmentQueryService.getAllProcesAttributes(processInstanceId,
				scope);
		return variables;
	}

	@GetMapping("/appointment-details/{processInstanceId}")
	public AppointmentDetails getAppointmentDetails(@PathVariable String processInstanceId) {

		return appointmentQueryService.getAppointmentDetails(processInstanceId);
	}

	@GetMapping("/symptoms/{taskId}")
	public ConsultationDetails getSymptoms(@PathVariable String taskId) {

		return appointmentQueryService.getSymptoms(taskId);
	}

	@GetMapping("/paymentInfo/{taskId}")
	public PaymentInfo getPaymentInfo(@PathVariable String taskId) {

		// return appointmentQueryService.getPaymentInfo(taskId);
		return null;
	}

	/**
	 * GET /appointments : get all the appointments.
	 *
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of appointments
	 *         in body
	 */
	@GetMapping("/appointments")
	@Timed
	public ResponseEntity<List<AppointmentDTO>> getAllAppointments(Pageable pageable) {
		log.debug("REST request to get a page of Appointments");
		Page<AppointmentDTO> page = appointmentQueryService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/appointments");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * GET /appointments/:id : get the "id" appointment.
	 *
	 * @param id the id of the appointmentDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         appointmentDTO, or with status 404 (Not Found)
	 */
	@GetMapping("/appointments/{id}")
	@Timed
	public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable Long id) {
		log.debug("REST request to get Appointment : {}", id);
		Optional<AppointmentDTO> appointmentDTO = appointmentQueryService.findOne(id);
		return ResponseUtil.wrapOrNotFound(appointmentDTO);
	}

	@PostMapping("/getHistoricTask")
	public ResponseEntity<DataResponse> getHistoricTask(@RequestBody HistoricTaskInstanceQueryRequest request) {
		System.out.println("++++++++++++++++++++++++++++##########" + request.getProcessInstanceId()
				+ "^^^^^^^^^^^^^^^^^^^^^^^^^" + request.getTaskName());
		return appointmentQueryService.getHistoricTask(request);
	}

	@GetMapping("/myAppointments")
	public List<OpenAppointmentResponse> getMyAppointments(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "nameLike", required = false) String nameLike,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "priority", required = false) String priority,
			@RequestParam(value = "minimumPriority", required = false) String minimumPriority,
			@RequestParam(value = "maximumPriority", required = false) String maximumPriority,
			@RequestParam(value = "assignee", required = false) String assignee,
			@RequestParam(value = "assigneeLike", required = false) String assigneeLike,
			@RequestParam(value = "owner", required = false) String owner,
			@RequestParam(value = "ownerLike", required = false) String ownerLike,
			@RequestParam(value = "unassigned", required = false) String unassigned,
			@RequestParam(value = "delegationState", required = false) String delegationState,
			@RequestParam(value = "candidateUser", required = false) String candidateUser,
			@RequestParam(value = "candidateGroup", required = false) String candidateGroup,
			@RequestParam(value = "candidateGroups", required = false) String candidateGroups,
			@RequestParam(value = "involvedUser", required = false) String involvedUser,
			@RequestParam(value = "taskDefinitionKey", required = false) String taskDefinitionKey,
			@RequestParam(value = "taskDefinitionKeyLike", required = false) String taskDefinitionKeyLike,
			@RequestParam(value = "processInstanceId", required = false) String processInstanceId,
			@RequestParam(value = "processInstanceBusinessKey", required = false) String processInstanceBusinessKey,
			@RequestParam(value = "processInstanceBusinessKeyLike", required = false) String processInstanceBusinessKeyLike,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with the given id.") @Valid @RequestParam(value = "processDefinitionId", required = false) String processDefinitionId,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with the given key.") @Valid @RequestParam(value = "processDefinitionKey", required = false) String processDefinitionKey,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with a key like the given value.") @Valid @RequestParam(value = "processDefinitionKeyLike", required = false) String processDefinitionKeyLike,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with the given name.") @Valid @RequestParam(value = "processDefinitionName", required = false) String processDefinitionName,
			@ApiParam(value = "Only return tasks which are part of a process instance which has a process definition with a name like the given value.") @Valid @RequestParam(value = "processDefinitionNameLike", required = false) String processDefinitionNameLike,
			@ApiParam(value = "Only return tasks which are part of the execution with the given id.") @Valid @RequestParam(value = "executionId", required = false) String executionId,
			@ApiParam(value = "Only return tasks which are created on the given date.") @Valid @RequestParam(value = "createdOn", required = false) String createdOn,
			@ApiParam(value = "Only return tasks which are created before the given date.") @Valid @RequestParam(value = "createdBefore", required = false) String createdBefore,
			@ApiParam(value = "Only return tasks which are created after the given date.") @Valid @RequestParam(value = "createdAfter", required = false) String createdAfter,
			@ApiParam(value = "Only return tasks which are due on the given date.") @Valid @RequestParam(value = "dueOn", required = false) String dueOn,
			@ApiParam(value = "Only return tasks which are due before the given date.") @Valid @RequestParam(value = "dueBefore", required = false) String dueBefore,
			@ApiParam(value = "Only return tasks which are due after the given date.") @Valid @RequestParam(value = "dueAfter", required = false) String dueAfter,
			@ApiParam(value = "Only return tasks which don�t have a due date. The property is ignored if the value is false.") @Valid @RequestParam(value = "withoutDueDate", required = false) Boolean withoutDueDate,
			@ApiParam(value = "Only return tasks that are not a subtask of another task.") @Valid @RequestParam(value = "excludeSubTasks", required = false) Boolean excludeSubTasks,
			@ApiParam(value = "If true, only return tasks that are not suspended (either part of a process that is not suspended or not part of a process at all). If false, only tasks that are part of suspended process instances are returned.") @Valid @RequestParam(value = "active", required = false) Boolean active,
			@ApiParam(value = "Indication to include task local variables in the result.") @Valid @RequestParam(value = "includeTaskLocalVariables", required = false) Boolean includeTaskLocalVariables,
			@ApiParam(value = "Indication to include process variables in the result.") @Valid @RequestParam(value = "includeProcessVariables", required = false) Boolean includeProcessVariables,
			@ApiParam(value = "Only return tasks with the given tenantId.") @Valid @RequestParam(value = "tenantId", required = false) String tenantId,
			@ApiParam(value = "Only return tasks with a tenantId like the given value.") @Valid @RequestParam(value = "tenantIdLike", required = false) String tenantIdLike,
			@ApiParam(value = "If true, only returns tasks without a tenantId set. If false, the withoutTenantId parameter is ignored.") @Valid @RequestParam(value = "withoutTenantId", required = false) Boolean withoutTenantId,
			@ApiParam(value = "Select tasks that has been claimed or assigned to user or waiting to claim by user (candidate user or groups).") @Valid @RequestParam(value = "candidateOrAssigned", required = false) String candidateOrAssigned,
			@ApiParam(value = "Select tasks with the given category. Note that this is the task category, not the category of the process definition (namespace within the BPMN Xml). ") @Valid @RequestParam(value = "category", required = false) String category) {
		log.info("Assignee is " + assignee);
		return appointmentQueryService.getOpenAppointments(name, nameLike, description, priority, minimumPriority,
				maximumPriority, assignee, assigneeLike, owner, ownerLike, unassigned, delegationState, candidateUser,
				candidateGroup, candidateGroups, involvedUser, taskDefinitionKey, taskDefinitionKeyLike,
				processInstanceId, processInstanceBusinessKey, processInstanceBusinessKeyLike, processDefinitionId,
				processDefinitionKey, processDefinitionKeyLike, processDefinitionName, processDefinitionNameLike,
				executionId, createdOn, createdBefore, createdAfter, dueOn, dueBefore, dueAfter, withoutDueDate,
				excludeSubTasks, active, includeTaskLocalVariables, includeProcessVariables, tenantId, tenantIdLike,
				withoutTenantId, candidateOrAssigned, category);
	}

	@GetMapping("/doctoInfo/{processInstanceId}")
	public DoctorInfo getDoctorInfo(@PathVariable String processInstanceId) {

		return appointmentQueryService.getDoctorInfo(processInstanceId);

	}

	@GetMapping("/slotInfo/{processInstanceId}")
	public Slot getSlotInfo(@PathVariable String processInstanceId) {

		return appointmentQueryService.getSlotInfo(processInstanceId);

	}

	@GetMapping("/appointmentInfo/{processInstanceId}")
	public Appointment getAppointmentInfo(@PathVariable String processInstanceId) {
		return appointmentQueryService.getCompletedAppointment(processInstanceId);
	}
}
