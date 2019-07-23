package com.bytatech.ayoos.appointment.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bytatech.ayoos.appointment.domain.Timing;

/**
 * Spring Data Elasticsearch repository for the Timing entity.
 */
public interface TimingSearchRepository extends ElasticsearchRepository<Timing, Long> {
}
