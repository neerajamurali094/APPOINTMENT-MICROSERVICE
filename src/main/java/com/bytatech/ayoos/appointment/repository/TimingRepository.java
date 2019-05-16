package com.bytatech.ayoos.appointment.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.bytatech.ayoos.appointment.domain.Timing;


/**
 * Spring Data  repository for the Timing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TimingRepository extends JpaRepository<Timing, Long> {

}
