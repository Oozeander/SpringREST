package com.oozeander.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oozeander.model.Information;

public interface InformationRepository extends JpaRepository<Information, Long> {
}