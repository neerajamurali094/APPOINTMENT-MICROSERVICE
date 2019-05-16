package com.bytatech.ayoos.appointment.service.impl;

import com.bytatech.ayoos.appointment.domain.ConsultationInfo;
import com.bytatech.ayoos.appointment.repository.ConsultationInfoRepository;
import com.bytatech.ayoos.appointment.repository.search.ConsultationInfoSearchRepository;
import com.bytatech.ayoos.appointment.service.ConsultationInfoService;
import com.bytatech.ayoos.appointment.service.dto.ConsultationInfoDTO;
import com.bytatech.ayoos.appointment.service.mapper.ConsultationInfoMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing ConsultationInfo.
 */
@Service
@Transactional
public class ConsultationInfoServiceImpl implements ConsultationInfoService {

    private final Logger log = LoggerFactory.getLogger(ConsultationInfoServiceImpl.class);

    private final ConsultationInfoRepository consultationInfoRepository;

    private final ConsultationInfoMapper consultationInfoMapper;

    private final ConsultationInfoSearchRepository consultationInfoSearchRepository;

    public ConsultationInfoServiceImpl(ConsultationInfoRepository consultationInfoRepository, ConsultationInfoMapper consultationInfoMapper, ConsultationInfoSearchRepository consultationInfoSearchRepository) {
        this.consultationInfoRepository = consultationInfoRepository;
        this.consultationInfoMapper = consultationInfoMapper;
        this.consultationInfoSearchRepository = consultationInfoSearchRepository;
    }

    /**
     * Save a consultationInfo.
     *
     * @param consultationInfoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ConsultationInfoDTO save(ConsultationInfoDTO consultationInfoDTO) {
        log.debug("Request to save ConsultationInfo : {}", consultationInfoDTO);

        ConsultationInfo consultationInfo = consultationInfoMapper.toEntity(consultationInfoDTO);
        consultationInfo = consultationInfoRepository.save(consultationInfo);
        ConsultationInfoDTO result = consultationInfoMapper.toDto(consultationInfo);
        consultationInfoSearchRepository.save(consultationInfo);
        return result;
    }

    /**
     * Get all the consultationInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConsultationInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ConsultationInfos");
        return consultationInfoRepository.findAll(pageable)
            .map(consultationInfoMapper::toDto);
    }


    /**
     * Get one consultationInfo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConsultationInfoDTO> findOne(Long id) {
        log.debug("Request to get ConsultationInfo : {}", id);
        return consultationInfoRepository.findById(id)
            .map(consultationInfoMapper::toDto);
    }

    /**
     * Delete the consultationInfo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConsultationInfo : {}", id);
        consultationInfoRepository.deleteById(id);
        consultationInfoSearchRepository.deleteById(id);
    }

    /**
     * Search for the consultationInfo corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConsultationInfoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ConsultationInfos for query {}", query);
        return consultationInfoSearchRepository.search(queryStringQuery(query), pageable)
            .map(consultationInfoMapper::toDto);
    }
}
