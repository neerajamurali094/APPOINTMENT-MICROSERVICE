package com.bytatech.ayoos.appointment.client.bpmn.model.appointment;


public class AppointmentRequest {
	
	private String doctorId;
	private Slot slot;
	
	public Slot getSlot() {
		return slot;
	}
	public void setSlot(Slot slot) {
		this.slot = slot;
	}
	
	@Override
	public String toString() {
		return String.format("InitiateAppointmentRequest [doctorId=%s, slot=%s]", doctorId, slot);
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	
	

	
	
}
