package com.bytatech.ayoos.appointment.resource;

import org.springframework.hateoas.ResourceSupport;

public class CommandResource extends ResourceSupport{

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format(
				"CommandResource [status=%s,\n getStatus()=%s,\n getId()=%s,\n hasLinks()=%s,\n getLinks()=%s,\n toString()=%s,\n hashCode()=%s,\n getClass()=%s]",
				status, getStatus(), getId(), hasLinks(), getLinks(), super.toString(), hashCode(), getClass());
	}
	
}
