package com.bytatech.ayoos.appointment.client.bpmn.model.appointment;

import org.activiti.engine.impl.variable.ByteArrayType;


public class PatientInfo extends ByteArrayType  {
	
	private String patientId;
	private Long phoneNumber;
	private String email;
	private String firstName;
	@Override
	public String toString() {
		return String.format("PatientInfo [patientId=%s,\n phoneNumber=%s,\n email=%s,\n firstName=%s]", patientId,
				phoneNumber, email, firstName);
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	
}
