package com.bytatech.ayoos.appointment.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

import com.bytatech.ayoos.appointment.repository.search.StatusSearchRepository;

/**
 * Configure a Mock version of StatusSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class StatusSearchRepositoryMockConfiguration {

    @MockBean
    private StatusSearchRepository mockStatusSearchRepository;

}
