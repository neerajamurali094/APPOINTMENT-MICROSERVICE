package com.bytatech.ayoos.appointment.service.impl;

import com.bytatech.ayoos.appointment.domain.Appointment;
import com.bytatech.ayoos.appointment.repository.AppointmentRepository;
import com.bytatech.ayoos.appointment.repository.search.AppointmentSearchRepository;
import com.bytatech.ayoos.appointment.service.AppointmentService;
import com.bytatech.ayoos.appointment.service.dto.AppointmentDTO;
import com.bytatech.ayoos.appointment.service.mapper.AppointmentMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Appointment.
 */
@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    private final AppointmentRepository appointmentRepository;

    private final AppointmentMapper appointmentMapper;

    private final AppointmentSearchRepository appointmentSearchRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, AppointmentSearchRepository appointmentSearchRepository) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.appointmentSearchRepository = appointmentSearchRepository;
    }

    

    

    

    

    
}
