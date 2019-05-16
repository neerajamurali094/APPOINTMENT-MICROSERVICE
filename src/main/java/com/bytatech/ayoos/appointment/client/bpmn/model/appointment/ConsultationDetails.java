package com.bytatech.ayoos.appointment.client.bpmn.model.appointment;

import java.util.List;

import org.activiti.engine.impl.variable.ByteArrayType;

public class ConsultationDetails  extends ByteArrayType{

	List<Syndrome> symptoms;
	
	public List<Syndrome> getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(List<Syndrome> symptoms) {
		this.symptoms = symptoms;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	private Float height;
	private Integer weight;
	private String note;
	private Integer age;
	
}
