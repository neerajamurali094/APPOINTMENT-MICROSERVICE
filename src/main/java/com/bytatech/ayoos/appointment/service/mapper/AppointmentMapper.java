package com.bytatech.ayoos.appointment.service.mapper;

import com.bytatech.ayoos.appointment.domain.*;
import com.bytatech.ayoos.appointment.service.dto.AppointmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Appointment and its DTO AppointmentDTO.
 */
@Mapper(componentModel = "spring", uses = {ConsultationInfoMapper.class, TimingMapper.class, StatusMapper.class})
public interface AppointmentMapper extends EntityMapper<AppointmentDTO, Appointment> {

    @Mapping(source = "consultationInfo.id", target = "consultationInfoId")
    @Mapping(source = "timing.id", target = "timingId")
    @Mapping(source = "status.id", target = "statusId")
    AppointmentDTO toDto(Appointment appointment);

    @Mapping(source = "consultationInfoId", target = "consultationInfo")
    @Mapping(source = "timingId", target = "timing")
    @Mapping(source = "statusId", target = "status")
    Appointment toEntity(AppointmentDTO appointmentDTO);

    default Appointment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setId(id);
        return appointment;
    }
}
