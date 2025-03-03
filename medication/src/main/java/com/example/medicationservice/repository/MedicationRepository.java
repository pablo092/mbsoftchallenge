package com.example.medicationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.medicationservice.model.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    @Query("SELECT m FROM Medication m WHERE LOWER(m.type.typeName) LIKE LOWER(CONCAT('%', :typeName, '%'))")
    List<Medication> findByTypeTypeName(@Param("typeName") String typeName);

    @Query("SELECT m FROM Medication m WHERE LOWER(m.commercialName) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Medication> findByCommercialNameStartingWith(@Param("prefix") String prefix);

}