package com.bytatech.ayoos.appointment.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bytatech.ayoos.appointment.domain.Appointment;

/**
 * Spring Data Elasticsearch repository for the Appointment entity.
 */
public interface AppointmentSearchRepository extends ElasticsearchRepository<Appointment, Long> {
}
