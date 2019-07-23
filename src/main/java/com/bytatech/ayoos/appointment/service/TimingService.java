package com.bytatech.ayoos.appointment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bytatech.ayoos.appointment.service.dto.TimingDTO;

import java.util.Optional;

/**
 * Service Interface for managing Timing.
 */
public interface TimingService {

    /**
     * Save a timing.
     *
     * @param timingDTO the entity to save
     * @return the persisted entity
     */
    TimingDTO save(TimingDTO timingDTO);

    /**
     * Get all the timings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TimingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" timing.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TimingDTO> findOne(Long id);

    /**
     * Delete the "id" timing.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the timing corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TimingDTO> search(String query, Pageable pageable);
}
