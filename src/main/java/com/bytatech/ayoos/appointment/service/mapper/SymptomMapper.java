package com.bytatech.ayoos.appointment.service.mapper;

import com.bytatech.ayoos.appointment.domain.*;
import com.bytatech.ayoos.appointment.service.dto.SymptomDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Symptom and its DTO SymptomDTO.
 */
@Mapper(componentModel = "spring", uses = {ConsultationInfoMapper.class})
public interface SymptomMapper extends EntityMapper<SymptomDTO, Symptom> {

    @Mapping(source = "consultationInfo.id", target = "consultationInfoId")
    SymptomDTO toDto(Symptom symptom);

    @Mapping(source = "consultationInfoId", target = "consultationInfo")
    Symptom toEntity(SymptomDTO symptomDTO);

    default Symptom fromId(Long id) {
        if (id == null) {
            return null;
        }
        Symptom symptom = new Symptom();
        symptom.setId(id);
        return symptom;
    }
}
