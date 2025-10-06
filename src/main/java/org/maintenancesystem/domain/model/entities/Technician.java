package org.maintenancesystem.domain.model.entities;

public class Technician {

    private Long ID;
    private String name;
    private String specialty;

    public Technician(Long ID, String name, String specialty) {
        this.ID = ID;
        this.name = name;
        this.specialty = specialty;
    }

    public Technician(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
