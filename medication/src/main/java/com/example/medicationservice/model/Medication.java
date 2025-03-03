package com.example.medicationservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "commercial_name")
    private String commercialName;
    @Column(name = "drug_name")
    private String drugName;
    @ManyToOne
    private MedicationType type;

    public Medication() {
    }

    public Medication(String code, String commercialName, String drugName, MedicationType type) {
        this.code = code;
        this.commercialName = commercialName;
        this.drugName = drugName;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public MedicationType getType() {
        return type;
    }

    public void setType(MedicationType type) {
        this.type = type;
    }
}