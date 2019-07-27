package com.bytatech.ayoos.appointment.service.impl;

import com.bytatech.ayoos.appointment.domain.Timing;
import com.bytatech.ayoos.appointment.repository.TimingRepository;
import com.bytatech.ayoos.appointment.repository.search.TimingSearchRepository;
import com.bytatech.ayoos.appointment.service.TimingService;
import com.bytatech.ayoos.appointment.service.dto.TimingDTO;
import com.bytatech.ayoos.appointment.service.mapper.TimingMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Timing.
 */
@Service
@Transactional
public class TimingServiceImpl implements TimingService {

    private final Logger log = LoggerFactory.getLogger(TimingServiceImpl.class);

    private final TimingRepository timingRepository;

    private final TimingMapper timingMapper;

    private final TimingSearchRepository timingSearchRepository;

    public TimingServiceImpl(TimingRepository timingRepository, TimingMapper timingMapper, TimingSearchRepository timingSearchRepository) {
        this.timingRepository = timingRepository;
        this.timingMapper = timingMapper;
        this.timingSearchRepository = timingSearchRepository;
    }

    /**
     * Save a timing.
     *
     * @param timingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    @Transactional
    public TimingDTO save(TimingDTO timingDTO) {
        log.debug("Request to save Timing : {}", timingDTO);

        Timing timing = timingMapper.toEntity(timingDTO);
        log.info("++++++++++++++++ entity is "+timing);
        timing = timingRepository.save(timing);
        log.info("++++++++++++++++ entity after saving is "+timing);
        TimingDTO result = timingMapper.toDto(timing);
        log.info("++++++++++++++++ After dto conversion "+result);
        timingSearchRepository.save(timing);
        return result;
    }

    /**
     * Get all the timings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TimingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Timings");
        return timingRepository.findAll(pageable)
            .map(timingMapper::toDto);
    }


    /**
     * Get one timing by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TimingDTO> findOne(Long id) {
        log.debug("Request to get Timing : {}", id);
        return timingRepository.findById(id)
            .map(timingMapper::toDto);
    }

    /**
     * Delete the timing by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Timing : {}", id);
        timingRepository.deleteById(id);
        timingSearchRepository.deleteById(id);
    }

    /**
     * Search for the timing corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TimingDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Timings for query {}", query);
        return timingSearchRepository.search(queryStringQuery(query), pageable)
            .map(timingMapper::toDto);
    }
}
