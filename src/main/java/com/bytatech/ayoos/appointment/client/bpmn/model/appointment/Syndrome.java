package com.bytatech.ayoos.appointment.client.bpmn.model.appointment;

import org.activiti.engine.impl.variable.ByteArrayType;

public class Syndrome extends ByteArrayType{

	private String symptomRef;
	private String numberOfDaysSuffering;
	public String getSymptomRef() {
		return symptomRef;
	}
	public void setSymptomRef(String symptomRef) {
		this.symptomRef = symptomRef;
	}
	public String getNumberOfDaysSuffering() {
		return numberOfDaysSuffering;
	}
	public void setNumberOfDaysSuffering(String numberOfDaysSuffering) {
		this.numberOfDaysSuffering = numberOfDaysSuffering;
	}
	
	
}
