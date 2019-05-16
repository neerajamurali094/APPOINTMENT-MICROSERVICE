package com.bytatech.ayoos.appointment.web.rest;

import com.bytatech.ayoos.appointment.AyoosAppointmentApp;
import com.bytatech.ayoos.appointment.domain.Timing;
import com.bytatech.ayoos.appointment.repository.TimingRepository;
import com.bytatech.ayoos.appointment.repository.search.TimingSearchRepository;
import com.bytatech.ayoos.appointment.service.TimingService;
import com.bytatech.ayoos.appointment.service.dto.TimingDTO;
import com.bytatech.ayoos.appointment.service.mapper.TimingMapper;
import com.bytatech.ayoos.appointment.web.rest.TimingResource;
import com.bytatech.ayoos.appointment.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;

import static com.bytatech.ayoos.appointment.web.rest.TestUtil.createFormattingConversionService;
import static com.bytatech.ayoos.appointment.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TimingResource REST controller.
 *
 * @see TimingResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AyoosAppointmentApp.class)
public class TimingResourceIntTest {

    private static final LocalDate DEFAULT_DAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DAY = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_START_FROM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_START_FROM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_END_TO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_END_TO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private TimingRepository timingRepository;

    @Autowired
    private TimingMapper timingMapper;

    @Autowired
    private TimingService timingService;

    /**
     * This repository is mocked in the com.bytatech.ayoos.appointment.repository.search test package.
     *
     * @see com.bytatech.ayoos.appointment.repository.search.TimingSearchRepositoryMockConfiguration
     */
    @Autowired
    private TimingSearchRepository mockTimingSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTimingMockMvc;

    private Timing timing;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TimingResource timingResource = new TimingResource(timingService);
        this.restTimingMockMvc = MockMvcBuilders.standaloneSetup(timingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Timing createEntity(EntityManager em) {
        Timing timing = new Timing()
            .day(DEFAULT_DAY)
            .startFrom(DEFAULT_START_FROM)
            .endTo(DEFAULT_END_TO);
        return timing;
    }

    @Before
    public void initTest() {
        timing = createEntity(em);
    }

    @Test
    @Transactional
    public void createTiming() throws Exception {
        int databaseSizeBeforeCreate = timingRepository.findAll().size();

        // Create the Timing
        TimingDTO timingDTO = timingMapper.toDto(timing);
        restTimingMockMvc.perform(post("/api/timings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timingDTO)))
            .andExpect(status().isCreated());

        // Validate the Timing in the database
        List<Timing> timingList = timingRepository.findAll();
        assertThat(timingList).hasSize(databaseSizeBeforeCreate + 1);
        Timing testTiming = timingList.get(timingList.size() - 1);
        assertThat(testTiming.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testTiming.getStartFrom()).isEqualTo(DEFAULT_START_FROM);
        assertThat(testTiming.getEndTo()).isEqualTo(DEFAULT_END_TO);

        // Validate the Timing in Elasticsearch
        verify(mockTimingSearchRepository, times(1)).save(testTiming);
    }

    @Test
    @Transactional
    public void createTimingWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = timingRepository.findAll().size();

        // Create the Timing with an existing ID
        timing.setId(1L);
        TimingDTO timingDTO = timingMapper.toDto(timing);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTimingMockMvc.perform(post("/api/timings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Timing in the database
        List<Timing> timingList = timingRepository.findAll();
        assertThat(timingList).hasSize(databaseSizeBeforeCreate);

        // Validate the Timing in Elasticsearch
        verify(mockTimingSearchRepository, times(0)).save(timing);
    }

    @Test
    @Transactional
    public void getAllTimings() throws Exception {
        // Initialize the database
        timingRepository.saveAndFlush(timing);

        // Get all the timingList
        restTimingMockMvc.perform(get("/api/timings?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(timing.getId().intValue())))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY.toString())))
            .andExpect(jsonPath("$.[*].startFrom").value(hasItem(sameInstant(DEFAULT_START_FROM))))
            .andExpect(jsonPath("$.[*].endTo").value(hasItem(sameInstant(DEFAULT_END_TO))));
    }
    
    @Test
    @Transactional
    public void getTiming() throws Exception {
        // Initialize the database
        timingRepository.saveAndFlush(timing);

        // Get the timing
        restTimingMockMvc.perform(get("/api/timings/{id}", timing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(timing.getId().intValue()))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY.toString()))
            .andExpect(jsonPath("$.startFrom").value(sameInstant(DEFAULT_START_FROM)))
            .andExpect(jsonPath("$.endTo").value(sameInstant(DEFAULT_END_TO)));
    }

    @Test
    @Transactional
    public void getNonExistingTiming() throws Exception {
        // Get the timing
        restTimingMockMvc.perform(get("/api/timings/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTiming() throws Exception {
        // Initialize the database
        timingRepository.saveAndFlush(timing);

        int databaseSizeBeforeUpdate = timingRepository.findAll().size();

        // Update the timing
        Timing updatedTiming = timingRepository.findById(timing.getId()).get();
        // Disconnect from session so that the updates on updatedTiming are not directly saved in db
        em.detach(updatedTiming);
        updatedTiming
            .day(UPDATED_DAY)
            .startFrom(UPDATED_START_FROM)
            .endTo(UPDATED_END_TO);
        TimingDTO timingDTO = timingMapper.toDto(updatedTiming);

        restTimingMockMvc.perform(put("/api/timings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timingDTO)))
            .andExpect(status().isOk());

        // Validate the Timing in the database
        List<Timing> timingList = timingRepository.findAll();
        assertThat(timingList).hasSize(databaseSizeBeforeUpdate);
        Timing testTiming = timingList.get(timingList.size() - 1);
        assertThat(testTiming.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testTiming.getStartFrom()).isEqualTo(UPDATED_START_FROM);
        assertThat(testTiming.getEndTo()).isEqualTo(UPDATED_END_TO);

        // Validate the Timing in Elasticsearch
        verify(mockTimingSearchRepository, times(1)).save(testTiming);
    }

    @Test
    @Transactional
    public void updateNonExistingTiming() throws Exception {
        int databaseSizeBeforeUpdate = timingRepository.findAll().size();

        // Create the Timing
        TimingDTO timingDTO = timingMapper.toDto(timing);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTimingMockMvc.perform(put("/api/timings")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(timingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Timing in the database
        List<Timing> timingList = timingRepository.findAll();
        assertThat(timingList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Timing in Elasticsearch
        verify(mockTimingSearchRepository, times(0)).save(timing);
    }

    @Test
    @Transactional
    public void deleteTiming() throws Exception {
        // Initialize the database
        timingRepository.saveAndFlush(timing);

        int databaseSizeBeforeDelete = timingRepository.findAll().size();

        // Get the timing
        restTimingMockMvc.perform(delete("/api/timings/{id}", timing.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Timing> timingList = timingRepository.findAll();
        assertThat(timingList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Timing in Elasticsearch
        verify(mockTimingSearchRepository, times(1)).deleteById(timing.getId());
    }

    @Test
    @Transactional
    public void searchTiming() throws Exception {
        // Initialize the database
        timingRepository.saveAndFlush(timing);
        when(mockTimingSearchRepository.search(queryStringQuery("id:" + timing.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(timing), PageRequest.of(0, 1), 1));
        // Search the timing
        restTimingMockMvc.perform(get("/api/_search/timings?query=id:" + timing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(timing.getId().intValue())))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY.toString())))
            .andExpect(jsonPath("$.[*].startFrom").value(hasItem(sameInstant(DEFAULT_START_FROM))))
            .andExpect(jsonPath("$.[*].endTo").value(hasItem(sameInstant(DEFAULT_END_TO))));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Timing.class);
        Timing timing1 = new Timing();
        timing1.setId(1L);
        Timing timing2 = new Timing();
        timing2.setId(timing1.getId());
        assertThat(timing1).isEqualTo(timing2);
        timing2.setId(2L);
        assertThat(timing1).isNotEqualTo(timing2);
        timing1.setId(null);
        assertThat(timing1).isNotEqualTo(timing2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TimingDTO.class);
        TimingDTO timingDTO1 = new TimingDTO();
        timingDTO1.setId(1L);
        TimingDTO timingDTO2 = new TimingDTO();
        assertThat(timingDTO1).isNotEqualTo(timingDTO2);
        timingDTO2.setId(timingDTO1.getId());
        assertThat(timingDTO1).isEqualTo(timingDTO2);
        timingDTO2.setId(2L);
        assertThat(timingDTO1).isNotEqualTo(timingDTO2);
        timingDTO1.setId(null);
        assertThat(timingDTO1).isNotEqualTo(timingDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(timingMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(timingMapper.fromId(null)).isNull();
    }
}
