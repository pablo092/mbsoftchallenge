package com.example.medicationservice.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicationservice.model.Medication;
import com.example.medicationservice.model.MedicationType;
import com.example.medicationservice.service.MedicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/medications")
@Tag(name = "Medication API", description = "API para gestionar medicamentos")
public class MedicationController {
    @Autowired
    private MedicationService medicationService;

    @PostMapping("/type")
    @Operation(summary = "Crea un nuevo tipo de medicamento")
    public ResponseEntity<?> createType(@RequestParam String typeName) {
        if (typeName == null || typeName.isEmpty()) {
            return ResponseEntity.badRequest().body("⚠️Tipo no puede ser vacío.");
        }
        return ResponseEntity.ok(medicationService.createType(typeName));
    }

    @GetMapping("/types")
    @Operation(summary = "Lista los tipos de medicamento")
    public ResponseEntity<List<MedicationType>> getAll() {
        return ResponseEntity.ok(medicationService.getAllMedicationTypes());
    }

    @DeleteMapping("/type/{id}")
    @Operation(summary = "Elimina lógicamente un tipo de medicamento")
    public ResponseEntity<?> deleteType(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("⚠️ ID de tipo de medicamento no puede ser nulo.");
        }
        medicationService.deleteType(id);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/")
    @Operation(summary = "Crea un nuevo medicamento")
    public ResponseEntity<?> createMedication(
            @RequestParam String code,
            @RequestParam String commercialName,
            @RequestParam String drugName,
            @RequestParam Long typeId) {

        if (code == null || !code.matches("\\d+")) {
            return ResponseEntity.badRequest().body("⚠️El código del medicamento debe ser numérico.");
        }
        if (commercialName == null || commercialName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("⚠️El nombre comercial no puede estar vacío.");
        }
        if (drugName == null || drugName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("⚠️El nombre de la droga no puede estar vacío.");
        }
        if (typeId == null || typeId <= 0) {
            return ResponseEntity.badRequest().body("⚠️Debe especificar un tipo de medicamento válido.");
        }

        return ResponseEntity.ok(medicationService.createMedication(code, commercialName, drugName, typeId));
    }

    @GetMapping("/byType")
    @Operation(summary = "Lista medicamentos por tipo")
    public ResponseEntity<?> getByType(@RequestParam String typeName) {
        if (typeName == null || typeName.isEmpty()) {
            return ResponseEntity.badRequest().body("⚠️ El Nombre del tipo no puede estar vacío.");
        }
        return ResponseEntity.ok(medicationService.getByType(typeName));
    }

    @GetMapping("/byName")
    @Operation(summary = "Lista medicamentos cuyo nombre comercial comienza con una letra")
    public ResponseEntity<?> getByCommercialNameStartingWith(@RequestParam String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return ResponseEntity.badRequest().body("⚠️ El prefijo no puede estar vacío.");
        }
        return ResponseEntity.ok(medicationService.getByCommercialNameStartingWith(prefix));
    }
}