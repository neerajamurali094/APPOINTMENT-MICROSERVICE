package com.bytatech.ayoos.appointment.web.rest;

import com.bytatech.ayoos.appointment.AyoosAppointmentApp;
import com.bytatech.ayoos.appointment.domain.ConsultationInfo;
import com.bytatech.ayoos.appointment.repository.ConsultationInfoRepository;
import com.bytatech.ayoos.appointment.repository.search.ConsultationInfoSearchRepository;
import com.bytatech.ayoos.appointment.service.ConsultationInfoService;
import com.bytatech.ayoos.appointment.service.dto.ConsultationInfoDTO;
import com.bytatech.ayoos.appointment.service.mapper.ConsultationInfoMapper;
import com.bytatech.ayoos.appointment.web.rest.ConsultationInfoResource;
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
import java.util.Collections;
import java.util.List;


import static com.bytatech.ayoos.appointment.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ConsultationInfoResource REST controller.
 *
 * @see ConsultationInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AyoosAppointmentApp.class)
public class ConsultationInfoResourceIntTest {

    private static final Float DEFAULT_HEIGHT = 1F;
    private static final Float UPDATED_HEIGHT = 2F;

    private static final Float DEFAULT_WEIGHT = 1F;
    private static final Float UPDATED_WEIGHT = 2F;

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    @Autowired
    private ConsultationInfoRepository consultationInfoRepository;

    @Autowired
    private ConsultationInfoMapper consultationInfoMapper;

    @Autowired
    private ConsultationInfoService consultationInfoService;

    /**
     * This repository is mocked in the com.diviso.appointment.repository.search test package.
     *
     * @see com.diviso.appointment.repository.search.ConsultationInfoSearchRepositoryMockConfiguration
     */
    @Autowired
    private ConsultationInfoSearchRepository mockConsultationInfoSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConsultationInfoMockMvc;

    private ConsultationInfo consultationInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConsultationInfoResource consultationInfoResource = new ConsultationInfoResource(consultationInfoService);
        this.restConsultationInfoMockMvc = MockMvcBuilders.standaloneSetup(consultationInfoResource)
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
    public static ConsultationInfo createEntity(EntityManager em) {
        ConsultationInfo consultationInfo = new ConsultationInfo()
            .height(DEFAULT_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .age(DEFAULT_AGE);
        return consultationInfo;
    }

    @Before
    public void initTest() {
        consultationInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createConsultationInfo() throws Exception {
        int databaseSizeBeforeCreate = consultationInfoRepository.findAll().size();

        // Create the ConsultationInfo
        ConsultationInfoDTO consultationInfoDTO = consultationInfoMapper.toDto(consultationInfo);
        restConsultationInfoMockMvc.perform(post("/api/consultation-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultationInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the ConsultationInfo in the database
        List<ConsultationInfo> consultationInfoList = consultationInfoRepository.findAll();
        assertThat(consultationInfoList).hasSize(databaseSizeBeforeCreate + 1);
        ConsultationInfo testConsultationInfo = consultationInfoList.get(consultationInfoList.size() - 1);
        assertThat(testConsultationInfo.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testConsultationInfo.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testConsultationInfo.getAge()).isEqualTo(DEFAULT_AGE);

        // Validate the ConsultationInfo in Elasticsearch
        verify(mockConsultationInfoSearchRepository, times(1)).save(testConsultationInfo);
    }

    @Test
    @Transactional
    public void createConsultationInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = consultationInfoRepository.findAll().size();

        // Create the ConsultationInfo with an existing ID
        consultationInfo.setId(1L);
        ConsultationInfoDTO consultationInfoDTO = consultationInfoMapper.toDto(consultationInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConsultationInfoMockMvc.perform(post("/api/consultation-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultationInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsultationInfo in the database
        List<ConsultationInfo> consultationInfoList = consultationInfoRepository.findAll();
        assertThat(consultationInfoList).hasSize(databaseSizeBeforeCreate);

        // Validate the ConsultationInfo in Elasticsearch
        verify(mockConsultationInfoSearchRepository, times(0)).save(consultationInfo);
    }

    @Test
    @Transactional
    public void getAllConsultationInfos() throws Exception {
        // Initialize the database
        consultationInfoRepository.saveAndFlush(consultationInfo);

        // Get all the consultationInfoList
        restConsultationInfoMockMvc.perform(get("/api/consultation-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consultationInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)));
    }
    
    @Test
    @Transactional
    public void getConsultationInfo() throws Exception {
        // Initialize the database
        consultationInfoRepository.saveAndFlush(consultationInfo);

        // Get the consultationInfo
        restConsultationInfoMockMvc.perform(get("/api/consultation-infos/{id}", consultationInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(consultationInfo.getId().intValue()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE));
    }

    @Test
    @Transactional
    public void getNonExistingConsultationInfo() throws Exception {
        // Get the consultationInfo
        restConsultationInfoMockMvc.perform(get("/api/consultation-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConsultationInfo() throws Exception {
        // Initialize the database
        consultationInfoRepository.saveAndFlush(consultationInfo);

        int databaseSizeBeforeUpdate = consultationInfoRepository.findAll().size();

        // Update the consultationInfo
        ConsultationInfo updatedConsultationInfo = consultationInfoRepository.findById(consultationInfo.getId()).get();
        // Disconnect from session so that the updates on updatedConsultationInfo are not directly saved in db
        em.detach(updatedConsultationInfo);
        updatedConsultationInfo
            .height(UPDATED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .age(UPDATED_AGE);
        ConsultationInfoDTO consultationInfoDTO = consultationInfoMapper.toDto(updatedConsultationInfo);

        restConsultationInfoMockMvc.perform(put("/api/consultation-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultationInfoDTO)))
            .andExpect(status().isOk());

        // Validate the ConsultationInfo in the database
        List<ConsultationInfo> consultationInfoList = consultationInfoRepository.findAll();
        assertThat(consultationInfoList).hasSize(databaseSizeBeforeUpdate);
        ConsultationInfo testConsultationInfo = consultationInfoList.get(consultationInfoList.size() - 1);
        assertThat(testConsultationInfo.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testConsultationInfo.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testConsultationInfo.getAge()).isEqualTo(UPDATED_AGE);

        // Validate the ConsultationInfo in Elasticsearch
        verify(mockConsultationInfoSearchRepository, times(1)).save(testConsultationInfo);
    }

    @Test
    @Transactional
    public void updateNonExistingConsultationInfo() throws Exception {
        int databaseSizeBeforeUpdate = consultationInfoRepository.findAll().size();

        // Create the ConsultationInfo
        ConsultationInfoDTO consultationInfoDTO = consultationInfoMapper.toDto(consultationInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConsultationInfoMockMvc.perform(put("/api/consultation-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultationInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsultationInfo in the database
        List<ConsultationInfo> consultationInfoList = consultationInfoRepository.findAll();
        assertThat(consultationInfoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ConsultationInfo in Elasticsearch
        verify(mockConsultationInfoSearchRepository, times(0)).save(consultationInfo);
    }

    @Test
    @Transactional
    public void deleteConsultationInfo() throws Exception {
        // Initialize the database
        consultationInfoRepository.saveAndFlush(consultationInfo);

        int databaseSizeBeforeDelete = consultationInfoRepository.findAll().size();

        // Get the consultationInfo
        restConsultationInfoMockMvc.perform(delete("/api/consultation-infos/{id}", consultationInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConsultationInfo> consultationInfoList = consultationInfoRepository.findAll();
        assertThat(consultationInfoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ConsultationInfo in Elasticsearch
        verify(mockConsultationInfoSearchRepository, times(1)).deleteById(consultationInfo.getId());
    }

    @Test
    @Transactional
    public void searchConsultationInfo() throws Exception {
        // Initialize the database
        consultationInfoRepository.saveAndFlush(consultationInfo);
        when(mockConsultationInfoSearchRepository.search(queryStringQuery("id:" + consultationInfo.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(consultationInfo), PageRequest.of(0, 1), 1));
        // Search the consultationInfo
        restConsultationInfoMockMvc.perform(get("/api/_search/consultation-infos?query=id:" + consultationInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consultationInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsultationInfo.class);
        ConsultationInfo consultationInfo1 = new ConsultationInfo();
        consultationInfo1.setId(1L);
        ConsultationInfo consultationInfo2 = new ConsultationInfo();
        consultationInfo2.setId(consultationInfo1.getId());
        assertThat(consultationInfo1).isEqualTo(consultationInfo2);
        consultationInfo2.setId(2L);
        assertThat(consultationInfo1).isNotEqualTo(consultationInfo2);
        consultationInfo1.setId(null);
        assertThat(consultationInfo1).isNotEqualTo(consultationInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsultationInfoDTO.class);
        ConsultationInfoDTO consultationInfoDTO1 = new ConsultationInfoDTO();
        consultationInfoDTO1.setId(1L);
        ConsultationInfoDTO consultationInfoDTO2 = new ConsultationInfoDTO();
        assertThat(consultationInfoDTO1).isNotEqualTo(consultationInfoDTO2);
        consultationInfoDTO2.setId(consultationInfoDTO1.getId());
        assertThat(consultationInfoDTO1).isEqualTo(consultationInfoDTO2);
        consultationInfoDTO2.setId(2L);
        assertThat(consultationInfoDTO1).isNotEqualTo(consultationInfoDTO2);
        consultationInfoDTO1.setId(null);
        assertThat(consultationInfoDTO1).isNotEqualTo(consultationInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(consultationInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(consultationInfoMapper.fromId(null)).isNull();
    }
}
