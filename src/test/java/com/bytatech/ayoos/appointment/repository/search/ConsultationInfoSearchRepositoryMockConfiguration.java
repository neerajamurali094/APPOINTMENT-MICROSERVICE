package com.bytatech.ayoos.appointment.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;


/**
 * Configure a Mock version of ConsultationInfoSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ConsultationInfoSearchRepositoryMockConfiguration {

    @MockBean
    private ConsultationInfoSearchRepository mockConsultationInfoSearchRepository;

}
