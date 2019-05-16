package com.bytatech.ayoos.appointment.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bytatech.ayoos.appointment.service.dto.ConsultationInfoDTO;

import java.util.Optional;

/**
 * Service Interface for managing ConsultationInfo.
 */
public interface ConsultationInfoService {

    /**
     * Save a consultationInfo.
     *
     * @param consultationInfoDTO the entity to save
     * @return the persisted entity
     */
    ConsultationInfoDTO save(ConsultationInfoDTO consultationInfoDTO);

    /**
     * Get all the consultationInfos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConsultationInfoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" consultationInfo.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ConsultationInfoDTO> findOne(Long id);

    /**
     * Delete the "id" consultationInfo.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the consultationInfo corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConsultationInfoDTO> search(String query, Pageable pageable);
}
