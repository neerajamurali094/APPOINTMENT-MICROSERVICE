package com.bytatech.ayoos.appointment.client.bpmn.model.appointment;

public class AppointmentConfirmationResponse {

	private String appointmentConfirmation;
	private Boolean isSuggetionEnabled;
	private String message;
	
	public Boolean getIsSuggetionEnabled() {
		return isSuggetionEnabled;
	}
	public void setIsSuggetionEnabled(Boolean isSuggetionEnabled) {
		this.isSuggetionEnabled = isSuggetionEnabled;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAppointmentConfirmation() {
		return appointmentConfirmation;
	}
	public void setAppointmentConfirmation(String appointmentConfirmation) {
		this.appointmentConfirmation = appointmentConfirmation;
	}
	
}
