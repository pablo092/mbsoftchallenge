package com.example.medicationservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.medicationservice.model.Medication;
import com.example.medicationservice.model.MedicationType;
import com.example.medicationservice.repository.MedicationRepository;
import com.example.medicationservice.repository.MedicationTypeRepository;
import com.example.medicationservice.service.MedicationService;

@SpringBootTest
class MedicationServiceTest {

    @InjectMocks
    private MedicationService medicationService;

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private MedicationTypeRepository typeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateType() {
        MedicationType type = new MedicationType("Aerosol");
        when(typeRepository.save(any())).thenReturn(type);
        assertEquals("Aerosol", medicationService.createType("Aerosol").getTypeName());
    }

    @Test
    void testGetAllMedicationTypes() {
        List<MedicationType> types = Arrays.asList(
                new MedicationType("Aerosol"),
                new MedicationType("Crema"));

        when(typeRepository.findAll()).thenReturn(types);

        List<MedicationType> result = medicationService.getAllMedicationTypes();
        assertEquals(2, result.size());
        assertEquals("Aerosol", result.get(0).getTypeName());
        assertEquals("Crema", result.get(1).getTypeName());
    }

    @Test
    void testDeleteTypeSuccess() {
        MedicationType type = new MedicationType("Aerosol");
        when(typeRepository.findById(anyLong())).thenReturn(Optional.of(type));

        medicationService.deleteType(1L);
        assertFalse(type.isActive());

        verify(typeRepository, times(1)).save(type);
    }

    @Test
    void testDeleteTypeNotFound() {
        when(typeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            medicationService.deleteType(1L);
        });

        assertEquals("El tipo de medicamento con ID 1 no existe.", exception.getMessage());
    }

    @Test
    void testCreateMedicationSuccess() {
        MedicationType type = new MedicationType("Aerosol");
        when(typeRepository.findById(anyLong())).thenReturn(Optional.of(type));

        Medication med = new Medication("123", "Amoxil", "Amoxicilina", type);
        when(medicationRepository.save(any())).thenReturn(med);

        Medication result = medicationService.createMedication("123", "Amoxil", "Amoxicilina", 1L);

        assertNotNull(result);
        assertEquals("Amoxil", result.getCommercialName());
    }

    @Test
    void testCreateMedicationTypeNotFound() {
        when(typeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            medicationService.createMedication("123", "Amoxil", "Amoxicilina", 99L);
        });

        assertEquals("No se encontró el tipo de medicamento con ID: 99", exception.getMessage());
    }

    @Test
    void testGetByType() {
        when(medicationRepository.findByTypeTypeName("Aerosol"))
                .thenReturn(
                        Arrays.asList(new Medication("123", "Amoxil", "Amoxicilina", new MedicationType("Aerosol"))));

        assertFalse(medicationService.getByType("Aerosol").isEmpty());
    }

    @Test
    void testGetByCommercialNameStartingWith() {
        when(medicationRepository.findByCommercialNameStartingWith("Par"))
                .thenReturn(Arrays.asList(
                        new Medication("101", "Paracetamol", "Paracetamol", new MedicationType("Analgesico"))));

        List<Medication> result = medicationService.getByCommercialNameStartingWith("Par");
        assertEquals(1, result.size());
        assertEquals("Paracetamol", result.get(0).getCommercialName());
    }

    @Test
    void testGetByCommercialNameStartingWithEmpty() {
        when(medicationRepository.findByCommercialNameStartingWith("XYZ")).thenReturn(Collections.emptyList());

        List<Medication> result = medicationService.getByCommercialNameStartingWith("XYZ");
        assertTrue(result.isEmpty());
    }
}
