package com.bytatech.ayoos.appointment.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

import com.bytatech.ayoos.appointment.repository.search.TimingSearchRepository;

/**
 * Configure a Mock version of TimingSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class TimingSearchRepositoryMockConfiguration {

    @MockBean
    private TimingSearchRepository mockTimingSearchRepository;

}
