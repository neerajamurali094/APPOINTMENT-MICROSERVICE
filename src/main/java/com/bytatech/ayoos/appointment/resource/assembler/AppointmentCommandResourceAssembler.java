package com.bytatech.ayoos.appointment.resource.assembler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AdditionalInformationRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentConfirmationRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.AppointmentConfirmationResponse;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.ConsultationDetails;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.DoctorInfo;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.PaymentConfirmationRequest;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.ProcessPayment;
import com.bytatech.ayoos.appointment.client.bpmn.model.appointment.Slot;
import com.bytatech.ayoos.appointment.client.bpmn.util.BPMUtils;
import com.bytatech.ayoos.appointment.service.AppointmentQueryService;
import com.bytatech.ayoos.appointment.web.rest.AppointmentCommandResource;
import com.bytatech.ayoos.appointment.web.rest.AppointmentQueryResource;
import com.bytatech.ayoos.appointment.resource.CommandResource;

@Component
@SuppressWarnings("unchecked")
public class AppointmentCommandResourceAssembler extends ResourceAssemblerSupport<String, CommandResource> {

	@Autowired
	private AppointmentQueryService appointmentQueryService;

	public AppointmentCommandResourceAssembler() {
		super(AppointmentCommandResource.class, CommandResource.class);
	}

	@Override
	public CommandResource toResource(String processInstanceId) {
		CommandResource appointmentCommandResource = new CommandResource();
		List<Link> links = new ArrayList<Link>();
		Link selfLink = linkTo(methodOn(AppointmentQueryResource.class).getAppointmentInfo(processInstanceId))
				.withSelfRel();
		appointmentCommandResource.add(selfLink);
		List<LinkedHashMap<String, String>> list = ((List<LinkedHashMap<String, String>>) appointmentQueryService
				.getTasks(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, processInstanceId, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null)
				.getBody().getData());
		String taskId = null;
		String taskName = null;
		if (list.size() != 0) {
			taskId = list.get(0).get("id");
			taskName = list.get(0).get("name");
			Link next = createNextRel(taskId, taskName);
			//links.add(next);

			if (next != null) {
				appointmentCommandResource.add(next);
			}
		}
		appointmentCommandResource.add(links);
		appointmentCommandResource.setNextTaskId(taskId);
		appointmentCommandResource.setTaskName(taskName);
		return appointmentCommandResource;
	}

	private Link createNextRel(String taskId,String taskName) {
		
		Link next = null;
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% "+taskName+"%%%%%%%%%%%%%%%%%%%%%%%%");

			if (taskName.equals(BPMUtils.CHOOSE_SLOT)) {
				next = linkTo(methodOn(AppointmentCommandResource.class).selectSlot(taskId, new Slot()))
						.withRel("next");
			} else if (taskName.equals(BPMUtils.REGISTER_PATIENT)) {
				next = linkTo(methodOn(AppointmentCommandResource.class).confirmRegistration(taskId)).withRel("next");
			} else if (taskName.equals(BPMUtils.CHOOSE_DOCTOR)) {
				next = linkTo(methodOn(AppointmentCommandResource.class).chooseDoctor(taskId, new DoctorInfo()))
						.withRel("next");
			} else if (taskName.equals(BPMUtils.SEND_APPOINTMENT_REQUEST)) {
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				next = linkTo(methodOn(AppointmentCommandResource.class).sendAppointmentRequest(taskId,
						new AppointmentConfirmationRequest())).withRel("next");
			} else if (taskName.equals(BPMUtils.PROCESS_APPOINTMENT_REQUEST)) {
				next = linkTo(methodOn(AppointmentCommandResource.class).processAppointmentRequest(taskId,
						new AppointmentConfirmationResponse())).withRel("next");
			} else if (taskName.equals(BPMUtils.PROCEEDTO_PAY)) {
				next = linkTo(methodOn(AppointmentCommandResource.class).confirmPayment(taskId,
						new PaymentConfirmationRequest())).withRel("next");
			} else if (taskName.equals(BPMUtils.PROCESS_PAYMENT)) {
				next = linkTo(methodOn(AppointmentCommandResource.class).processPayment(taskId, new ProcessPayment()))
						.withRel("next");
			} else if (taskName.equals(BPMUtils.ADD_ADDITIONAL_DETAILS)) {
				System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ in add additional");
				next = linkTo(methodOn(AppointmentCommandResource.class).additionalInformationRequest(taskId,
						new AdditionalInformationRequest())).withRel("next");
			} else if (taskName.equals(BPMUtils.COLLECT_INFORMATIONS)) {
				System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ in add additional");
				next = linkTo(methodOn(AppointmentCommandResource.class).collectAdditionalDetails(taskId,
						new ConsultationDetails())).withRel("next");
			}
		

	return next;
}

}
