package com.bytatech.ayoos.appointment.service.mapper;

import com.bytatech.ayoos.appointment.domain.ConsultationInfo;
import com.bytatech.ayoos.appointment.service.dto.ConsultationInfoDTO;


import org.mapstruct.*;

/**
 * Mapper for the entity ConsultationInfo and its DTO ConsultationInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConsultationInfoMapper extends EntityMapper<ConsultationInfoDTO, ConsultationInfo> {


    @Mapping(target = "symptoms", ignore = true)
    ConsultationInfo toEntity(ConsultationInfoDTO consultationInfoDTO);

    default ConsultationInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConsultationInfo consultationInfo = new ConsultationInfo();
        consultationInfo.setId(id);
        return consultationInfo;
    }
}
