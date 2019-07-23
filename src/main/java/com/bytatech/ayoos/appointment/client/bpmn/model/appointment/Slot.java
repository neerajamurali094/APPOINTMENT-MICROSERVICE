package com.bytatech.ayoos.appointment.client.bpmn.model.appointment;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Slot {
	
	@JsonFormat(pattern = "MM-dd-yyyy" ,timezone="IST")
	private Date day;
	
	@JsonFormat(pattern = "hh:mm")
	private Date startTime;
	
	@JsonFormat(pattern = "hh:mm")
	private Date endTime;

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	
	
}
