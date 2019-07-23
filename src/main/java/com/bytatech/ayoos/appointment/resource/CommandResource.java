package com.bytatech.ayoos.appointment.resource;

import org.springframework.hateoas.ResourceSupport;

public class CommandResource extends ResourceSupport{

	private String status;
	
	private String trackingId;
	private String nextTaskId;
	private String taskName;

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the trackingId
	 */
	public String getTrackingId() {
		return trackingId;
	}

	/**
	 * @param trackingId the trackingId to set
	 */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	

	@Override
	public String toString() {
		return String.format("CommandResource [status=%s,\n trackingId=%s,\n nextTaskId=%s,\n taskName=%s]", status,
				trackingId, nextTaskId, taskName);
	}

	/**
	 * @return the nextTaskId
	 */
	public String getNextTaskId() {
		return nextTaskId;
	}

	/**
	 * @param nextTaskId the nextTaskId to set
	 */
	public void setNextTaskId(String nextTaskId) {
		this.nextTaskId = nextTaskId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
