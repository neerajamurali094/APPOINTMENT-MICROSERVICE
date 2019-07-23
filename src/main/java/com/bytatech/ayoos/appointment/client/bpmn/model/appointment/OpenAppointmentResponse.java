package com.bytatech.ayoos.appointment.client.bpmn.model.appointment;

public class OpenAppointmentResponse {

	private Slot slot;
	private AppointmentDetails appointmentDetails;
	public Slot getSlot() {
		return slot;
	}
	public void setSlot(Slot slot) {
		this.slot = slot;
	}
	public AppointmentDetails getAppointmentDetails() {
		return appointmentDetails;
	}
	public void setAppointmentDetails(AppointmentDetails appointmentDetails) {
		this.appointmentDetails = appointmentDetails;
	}
	
	
	
}
