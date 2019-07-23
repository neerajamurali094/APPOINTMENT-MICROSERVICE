package com.bytatech.ayoos.appointment.client.doctor.api;

import org.springframework.cloud.openfeign.FeignClient;

import com.bytatech.ayoos.appointment.client.doctor.ClientConfiguration;

@FeignClient(name="${doctor.name:doctor}", url="${doctor.url:34.68.84.96:8083}", configuration = ClientConfiguration.class)
public interface DoctorResourceApiClient extends DoctorResourceApi {
}