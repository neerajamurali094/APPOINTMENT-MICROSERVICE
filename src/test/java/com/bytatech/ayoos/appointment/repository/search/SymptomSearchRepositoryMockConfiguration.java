package com.bytatech.ayoos.appointment.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

import com.bytatech.ayoos.appointment.repository.search.SymptomSearchRepository;

/**
 * Configure a Mock version of SymptomSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class SymptomSearchRepositoryMockConfiguration {

    @MockBean
    private SymptomSearchRepository mockSymptomSearchRepository;

}
