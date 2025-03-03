package com.example.medicationservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medication_types")
public class MedicationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeName;
    private boolean active = true;

    public MedicationType() {
    }

    public MedicationType(String typeName) {
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
        result = prime * result + (active ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MedicationType other = (MedicationType) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (typeName == null) {
            if (other.typeName != null)
                return false;
        } else if (!typeName.equals(other.typeName))
            return false;
        if (active != other.active)
            return false;
        return true;
    }

}