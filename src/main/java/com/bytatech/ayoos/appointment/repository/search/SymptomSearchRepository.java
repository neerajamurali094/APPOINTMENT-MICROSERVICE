package com.bytatech.ayoos.appointment.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bytatech.ayoos.appointment.domain.Symptom;

/**
 * Spring Data Elasticsearch repository for the Symptom entity.
 */
public interface SymptomSearchRepository extends ElasticsearchRepository<Symptom, Long> {
}
