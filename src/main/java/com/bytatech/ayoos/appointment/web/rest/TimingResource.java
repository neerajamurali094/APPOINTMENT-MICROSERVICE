package com.bytatech.ayoos.appointment.web.rest;

import com.bytatech.ayoos.appointment.service.TimingService;
import com.bytatech.ayoos.appointment.service.dto.TimingDTO;
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
 * REST controller for managing Timing.
 */
@RestController
@RequestMapping("/api")
public class TimingResource {

    private final Logger log = LoggerFactory.getLogger(TimingResource.class);

    private static final String ENTITY_NAME = "ayoosAppointmentTiming";

    private final TimingService timingService;

    public TimingResource(TimingService timingService) {
        this.timingService = timingService;
    }

    /**
     * POST  /timings : Create a new timing.
     *
     * @param timingDTO the timingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new timingDTO, or with status 400 (Bad Request) if the timing has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/timings")
    @Timed
    public ResponseEntity<TimingDTO> createTiming(@RequestBody TimingDTO timingDTO) throws URISyntaxException {
        log.debug("REST request to save Timing : {}", timingDTO);
        if (timingDTO.getId() != null) {
            throw new BadRequestAlertException("A new timing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TimingDTO result = timingService.save(timingDTO);
        return ResponseEntity.created(new URI("/api/timings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /timings : Updates an existing timing.
     *
     * @param timingDTO the timingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated timingDTO,
     * or with status 400 (Bad Request) if the timingDTO is not valid,
     * or with status 500 (Internal Server Error) if the timingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/timings")
    @Timed
    public ResponseEntity<TimingDTO> updateTiming(@RequestBody TimingDTO timingDTO) throws URISyntaxException {
        log.debug("REST request to update Timing : {}", timingDTO);
        if (timingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TimingDTO result = timingService.save(timingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, timingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /timings : get all the timings.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of timings in body
     */
    @GetMapping("/timings")
    @Timed
    public ResponseEntity<List<TimingDTO>> getAllTimings(Pageable pageable) {
        log.debug("REST request to get a page of Timings");
        Page<TimingDTO> page = timingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/timings");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /timings/:id : get the "id" timing.
     *
     * @param id the id of the timingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the timingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/timings/{id}")
    @Timed
    public ResponseEntity<TimingDTO> getTiming(@PathVariable Long id) {
        log.debug("REST request to get Timing : {}", id);
        Optional<TimingDTO> timingDTO = timingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(timingDTO);
    }

    /**
     * DELETE  /timings/:id : delete the "id" timing.
     *
     * @param id the id of the timingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/timings/{id}")
    @Timed
    public ResponseEntity<Void> deleteTiming(@PathVariable Long id) {
        log.debug("REST request to delete Timing : {}", id);
        timingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/timings?query=:query : search for the timing corresponding
     * to the query.
     *
     * @param query the query of the timing search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/timings")
    @Timed
    public ResponseEntity<List<TimingDTO>> searchTimings(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Timings for query {}", query);
        Page<TimingDTO> page = timingService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/timings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
