package org.maintenancesystem.domain.model.entities;

import org.maintenancesystem.domain.model.enums.MachineStatus;

public class Machine {

    public Long ID;
    public String name;
    public String sector;
    public MachineStatus status;

    public Machine(Long ID, String name, String sector, org.maintenancesystem.domain.model.enums.MachineStatus status) {
        this.ID = ID;
        this.name = name;
        this.sector = sector;
        this.status = status;
    }

    public Machine(String name, String sector, org.maintenancesystem.domain.model.enums.MachineStatus status) {
        this.name = name;
        this.sector = sector;
        this.status = status;
    }

    public Machine (String name){
        this.name = name;
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

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public org.maintenancesystem.domain.model.enums.MachineStatus getStatus() {
        return status;
    }

    public void setStatus(org.maintenancesystem.domain.model.enums.MachineStatus status) {
        this.status = status;
    }
}
