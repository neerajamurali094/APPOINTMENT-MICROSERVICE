package com.bytatech.ayoos.appointment.client.bpmn.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bytatech.ayoos.appointment.client.bpmn.ClientConfiguration;

@FeignClient(name="${bpm.name:bpm}", url="${bpm.url}", configuration = ClientConfiguration.class)
public interface HistoryApiClient extends HistoryApi {
}