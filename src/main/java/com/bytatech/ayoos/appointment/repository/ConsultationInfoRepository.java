package com.bytatech.ayoos.appointment.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.bytatech.ayoos.appointment.domain.ConsultationInfo;


/**
 * Spring Data  repository for the ConsultationInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsultationInfoRepository extends JpaRepository<ConsultationInfo, Long> {

}
