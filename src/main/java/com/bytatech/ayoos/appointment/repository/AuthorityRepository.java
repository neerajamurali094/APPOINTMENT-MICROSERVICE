package com.bytatech.ayoos.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bytatech.ayoos.appointment.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
