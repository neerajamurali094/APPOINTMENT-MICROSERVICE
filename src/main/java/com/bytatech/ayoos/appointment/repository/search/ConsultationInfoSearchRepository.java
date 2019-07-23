package com.bytatech.ayoos.appointment.repository.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bytatech.ayoos.appointment.domain.ConsultationInfo;

/**
 * Spring Data Elasticsearch repository for the ConsultationInfo entity.
 */
public interface ConsultationInfoSearchRepository extends ElasticsearchRepository<ConsultationInfo, Long> {
}
