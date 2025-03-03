package com.example.medicationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.medicationservice.model.MedicationType;

public interface MedicationTypeRepository extends JpaRepository<MedicationType, Long> {
}