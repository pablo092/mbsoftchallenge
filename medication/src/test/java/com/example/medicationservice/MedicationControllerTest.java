package com.example.medicationservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import com.example.medicationservice.model.Medication;
import com.example.medicationservice.model.MedicationType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings("null")
class MedicationControllerTest {

        @Autowired
        private TestRestTemplate restTemplate;

        @Test
        void testCreateTypeEndpoint() {
                ResponseEntity<MedicationType> response = restTemplate.postForEntity(
                                "/api/medications/type?typeName=Aerosol",
                                null,
                                MedicationType.class);

                assertNotNull(response.getBody());
                assertEquals("Aerosol", Objects.requireNonNull(response.getBody()).getTypeName());
        }

        @Test
        void testGetAllTypesEndpoint() {
                ResponseEntity<MedicationType[]> response = restTemplate.getForEntity("/api/medications/types",
                                MedicationType[].class);

                assertNotNull(response.getBody());
                assertTrue(response.getBody().length > 0);
        }

        @Test
        void testDeleteTypeEndpoint() {
                ResponseEntity<MedicationType> createResponse = restTemplate.postForEntity(
                                "/api/medications/type?typeName=Prueba",
                                null,
                                MedicationType.class);

                assertNotNull(createResponse.getBody());

                Long typeId = createResponse.getBody().getId();
                restTemplate.delete("/api/medications/type/" + typeId);

                ResponseEntity<MedicationType[]> getResponse = restTemplate.getForEntity("/api/medications/types",
                                MedicationType[].class);

                assertNotNull(getResponse.getBody());
                Optional<Object> deletedType = Arrays.asList(getResponse.getBody()).stream()
                                .filter(type -> Objects.equals(((MedicationType) type).getId(), typeId)).findFirst();
                assertTrue(deletedType.isPresent());
                MedicationType mt = (MedicationType) deletedType.get();
                assertFalse(mt.isActive());
        }

        @Test
        void testCreateMedicationEndpoint() {
                ResponseEntity<MedicationType[]> response = restTemplate.getForEntity("/api/medications/types",
                                MedicationType[].class);
                assertNotNull(response.getBody());
                assertTrue(response.getBody().length > 0);

                Long typeId = response.getBody()[0].getId();

                ResponseEntity<Medication> medResponse = restTemplate.postForEntity(
                                "/api/medications/?code=123&commercialName=Amoxil&drugName=Amoxicilina&typeId="
                                                + typeId,
                                null,
                                Medication.class);

                assertNotNull(medResponse.getBody());
                assertEquals("Amoxil", Objects.requireNonNull(medResponse.getBody()).getCommercialName());
        }

        @Test
        void testCreateMedicationWithInvalidType() {
                ResponseEntity<String> medResponse = restTemplate.postForEntity(
                                "/api/medications/?code=999&commercialName=Invalid&drugName=Drug&typeId=99999",
                                null,
                                String.class);

                assertEquals(HttpStatus.BAD_REQUEST, medResponse.getStatusCode());
        }

        @Test
        void testGetMedicationsByType() {
                ResponseEntity<Medication[]> response = restTemplate.getForEntity(
                                "/api/medications/byType?typeName=Analgesico",
                                Medication[].class);

                assertNotNull(response.getBody());
                assertTrue(response.getBody().length > 0);
        }

        @Test
        void testGetMedicationsByNamePrefix() {
                ResponseEntity<Medication[]> response = restTemplate.getForEntity(
                                "/api/medications/byName?prefix=P",
                                Medication[].class);

                assertNotNull(response.getBody());
                assertTrue(response.getBody().length > 0);
        }

        @Test
        void testGetMedicationsByNamePrefixNotFound() {
                ResponseEntity<Medication[]> response = restTemplate.getForEntity(
                                "/api/medications/byName?prefix=XYZ",
                                Medication[].class);

                assertNotNull(response.getBody());
                assertEquals(0, response.getBody().length);
        }
}
