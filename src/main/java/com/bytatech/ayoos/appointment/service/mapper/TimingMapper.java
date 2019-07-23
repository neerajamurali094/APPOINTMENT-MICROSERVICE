package com.bytatech.ayoos.appointment.service.mapper;

import com.bytatech.ayoos.appointment.domain.*;
import com.bytatech.ayoos.appointment.service.dto.TimingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Timing and its DTO TimingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TimingMapper extends EntityMapper<TimingDTO, Timing> {



    default Timing fromId(Long id) {
        if (id == null) {
            return null;
        }
        Timing timing = new Timing();
        timing.setId(id);
        return timing;
    }
}
