package com.bytatech.ayoos.appointment.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bytatech.ayoos.appointment.domain.Status;

/**
 * Spring Data Elasticsearch repository for the Status entity.
 */
public interface StatusSearchRepository extends ElasticsearchRepository<Status, Long> {
}
