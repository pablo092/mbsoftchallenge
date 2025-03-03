package com.example.medicationservice.config;

import com.example.medicationservice.model.Medication;
import com.example.medicationservice.model.MedicationType;
import com.example.medicationservice.repository.MedicationRepository;
import com.example.medicationservice.repository.MedicationTypeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner initDatabase(MedicationTypeRepository typeRepository, MedicationRepository medicationRepository) {
        return args -> {
            if (typeRepository.count() == 0) {
                List<MedicationType> types = Arrays.asList(
                        new MedicationType("Analgesico"),
                        new MedicationType("Antiinflamatorio"),
                        new MedicationType("Antibiótico"),
                        new MedicationType("Antihistamínico"),
                        new MedicationType("Sedante"));
                typeRepository.saveAll(types);
                logger.info("📌 Tipos de medicamentos insertados correctamente.");
            }

            if (medicationRepository.count() == 0) {
                List<MedicationType> types = typeRepository.findAll();

                List<Medication> medications = Arrays.asList(
                        new Medication("PCT-100-1", "Paracetamol", "Paracetamol", findType(types, "Analgesico")),
                        new Medication("IBU-200-2", "Ibuprofeno", "Ibuprofeno", findType(types, "Antiinflamatorio")),
                        new Medication("AMX-300-3", "Amoxicilina", "Amoxicilina", findType(types, "Antibiótico")),
                        new Medication("DIP-4004", "Dipirona", "Metamizol sódico", findType(types, "Analgesico")),
                        new Medication("ASP-500-5", "Aspirina", "Ácido acetilsalicílico",
                                findType(types, "Analgesico")),
                        new Medication("LOR-6006", "Loratadina", "Loratadina", findType(types, "Antihistamínico")),
                        new Medication("RAN-700-7", "Ranitidina", "Ranitidina", findType(types, "Sedante")),
                        new Medication("MEL-800-8", "Meloxicam", "Meloxicam", findType(types, "Antiinflamatorio")),
                        new Medication("CLV-9009", "Clonazepam", "Clonazepam", findType(types, "Sedante")));
                medicationRepository.saveAll(medications);
            }
            List<Medication> medications = medicationRepository.findAll();
            logger.info("📌 Medicamentos en la base de datos:");
            medications.forEach(med -> logger.info("🔹 {} ({})-{}", med.getCommercialName(), med.getCode(),
                    med.getType().getTypeName()));
        };
    }

    private MedicationType findType(List<MedicationType> types, String typeName) {
        return types.stream()
                .filter(type -> type.getTypeName().equalsIgnoreCase(typeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tipo de medicamento no encontrado: " + typeName));
    }
}
