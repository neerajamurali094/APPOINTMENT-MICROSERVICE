package com.bytatech.ayoos.appointment.client.bpmn.model.appointment;


import org.activiti.engine.impl.variable.ByteArrayType;

public class AppointmentDetails extends ByteArrayType {

	
	private String trackingID;
	private String appointmentID;
    private String appointmentDateAndTime;
    private String status;
    private PatientInfo patientInfo;
	public PatientInfo getPatientInfo() {
		return patientInfo;
	}
	@Override
	public String toString() {
		return String.format(
				"AppointmentDetails [trackingID=%s,\n appointmentID=%s,\n appointmentDateAndTime=%s,\n status=%s,\n patientInfo=%s]",
				trackingID, appointmentID, appointmentDateAndTime, status, patientInfo);
	}
	public void setPatientInfo(PatientInfo patientInfo) {
		this.patientInfo = patientInfo;
	}
	public String getTrackingID() {
		return trackingID;
	}
	public void setTrackingID(String trackingID) {
		this.trackingID = trackingID;
	}
	public String getAppointmentID() {
		return appointmentID;
	}
	public void setAppointmentID(String appointmentID) {
		this.appointmentID = appointmentID;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAppointmentDateAndTime() {
		return appointmentDateAndTime;
	}
	public void setAppointmentDateAndTime(String appointmentDateAndTime) {
		this.appointmentDateAndTime = appointmentDateAndTime;
	}
	
	
}
