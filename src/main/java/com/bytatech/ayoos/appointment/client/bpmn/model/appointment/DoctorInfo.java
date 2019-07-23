package com.bytatech.ayoos.appointment.client.bpmn.model.appointment;

import org.activiti.engine.impl.variable.ByteArrayType;

public class DoctorInfo extends ByteArrayType{

	
	@Override
	public String toString() {
		return String.format("DoctorInfo [doctorId=%s,\n phoneNumber=%s,\n email=%s,\n firstName=%s,\n settings=%s]",
				doctorId, phoneNumber, email, firstName, settings);
	}
	private String doctorId;
	private Long phoneNumber;
	private String email;
	private String firstName;
	private Settings settings;
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
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
	public Settings getSettings() {
		return settings;
	}
	public void setSettings(Settings settings) {
		this.settings = settings;
	}
}
