package com.bytatech.ayoos.appointment.web.rest;

import com.bytatech.ayoos.appointment.service.ConsultationInfoService;
import com.bytatech.ayoos.appointment.service.dto.ConsultationInfoDTO;
import com.bytatech.ayoos.appointment.web.rest.errors.BadRequestAlertException;
import com.bytatech.ayoos.appointment.web.rest.util.HeaderUtil;
import com.bytatech.ayoos.appointment.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing ConsultationInfo.
 */
@RestController
@RequestMapping("/api")
public class ConsultationInfoResource {

    private final Logger log = LoggerFactory.getLogger(ConsultationInfoResource.class);

    private static final String ENTITY_NAME = "ayoosAppointmentConsultationInfo";

    private final ConsultationInfoService consultationInfoService;

    public ConsultationInfoResource(ConsultationInfoService consultationInfoService) {
        this.consultationInfoService = consultationInfoService;
    }

    /**
     * POST  /consultation-infos : Create a new consultationInfo.
     *
     * @param consultationInfoDTO the consultationInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new consultationInfoDTO, or with status 400 (Bad Request) if the consultationInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/consultation-infos")
    @Timed
    public ResponseEntity<ConsultationInfoDTO> createConsultationInfo(@RequestBody ConsultationInfoDTO consultationInfoDTO) throws URISyntaxException {
        log.debug("REST request to save ConsultationInfo : {}", consultationInfoDTO);
        if (consultationInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new consultationInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConsultationInfoDTO result = consultationInfoService.save(consultationInfoDTO);
        return ResponseEntity.created(new URI("/api/consultation-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /consultation-infos : Updates an existing consultationInfo.
     *
     * @param consultationInfoDTO the consultationInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated consultationInfoDTO,
     * or with status 400 (Bad Request) if the consultationInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the consultationInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/consultation-infos")
    @Timed
    public ResponseEntity<ConsultationInfoDTO> updateConsultationInfo(@RequestBody ConsultationInfoDTO consultationInfoDTO) throws URISyntaxException {
        log.debug("REST request to update ConsultationInfo : {}", consultationInfoDTO);
        if (consultationInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConsultationInfoDTO result = consultationInfoService.save(consultationInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, consultationInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /consultation-infos : get all the consultationInfos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of consultationInfos in body
     */
    @GetMapping("/consultation-infos")
    @Timed
    public ResponseEntity<List<ConsultationInfoDTO>> getAllConsultationInfos(Pageable pageable) {
        log.debug("REST request to get a page of ConsultationInfos");
        Page<ConsultationInfoDTO> page = consultationInfoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/consultation-infos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /consultation-infos/:id : get the "id" consultationInfo.
     *
     * @param id the id of the consultationInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the consultationInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/consultation-infos/{id}")
    @Timed
    public ResponseEntity<ConsultationInfoDTO> getConsultationInfo(@PathVariable Long id) {
        log.debug("REST request to get ConsultationInfo : {}", id);
        Optional<ConsultationInfoDTO> consultationInfoDTO = consultationInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(consultationInfoDTO);
    }

    /**
     * DELETE  /consultation-infos/:id : delete the "id" consultationInfo.
     *
     * @param id the id of the consultationInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/consultation-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteConsultationInfo(@PathVariable Long id) {
        log.debug("REST request to delete ConsultationInfo : {}", id);
        consultationInfoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/consultation-infos?query=:query : search for the consultationInfo corresponding
     * to the query.
     *
     * @param query the query of the consultationInfo search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/consultation-infos")
    @Timed
    public ResponseEntity<List<ConsultationInfoDTO>> searchConsultationInfos(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of ConsultationInfos for query {}", query);
        Page<ConsultationInfoDTO> page = consultationInfoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/consultation-infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
