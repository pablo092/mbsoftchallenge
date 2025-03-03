package com.example.medicationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicationservice.model.Medication;
import com.example.medicationservice.model.MedicationType;
import com.example.medicationservice.repository.MedicationRepository;
import com.example.medicationservice.repository.MedicationTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class MedicationService {

    @Autowired
    private MedicationTypeRepository typeRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    public MedicationType createType(String typeName) {
        return typeRepository.save(new MedicationType(typeName));
    }

    public List<MedicationType> getAllMedicationTypes() {
        return typeRepository.findAll();
    }

    @Transactional
    public void deleteType(Long id) {
        MedicationType type = typeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El tipo de medicamento con ID " + id + " no existe."));

        type.deactivate();
        typeRepository.save(type);
    }

    public Medication createMedication(String code, String commercialName, String drugName, Long typeId) {
        MedicationType type = typeRepository.findById(typeId)
                .orElseThrow(
                        () -> new IllegalArgumentException("No se encontró el tipo de medicamento con ID: " + typeId));

        return medicationRepository.save(new Medication(code, commercialName, drugName, type));
    }

    public List<Medication> getByType(String typeName) {
        return medicationRepository.findByTypeTypeName(typeName);
    }

    public List<Medication> getByCommercialNameStartingWith(String prefix) {
        return medicationRepository.findByCommercialNameStartingWith(prefix);
    }
}