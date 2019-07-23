package com.bytatech.ayoos.appointment.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessageBindersConfiguration {
	
	String APPOINTMENT="appointment";
	
	//@Output(APPOINTMENT)
	MessageChannel appointmentOut();

}
