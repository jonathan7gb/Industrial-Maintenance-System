package org.maintenancesystem.domain.model.entities;

import org.maintenancesystem.domain.model.MaintenanceRequestStatus;
import org.maintenancesystem.domain.model.Technical;

import java.time.LocalDate;

public class MaintenanceRequest {

    private Long ID;
    private org.maintenancesystem.domain.model.entities.Machine machine;
    private Technical technical;
    private LocalDate requestDate;
    private MaintenanceRequestStatus status;

    public MaintenanceRequest(Long ID, org.maintenancesystem.domain.model.entities.Machine machine, org.maintenancesystem.domain.model.entities.Technical technical, LocalDate requestDate, org.maintenancesystem.domain.model.enums.MaintenanceRequestStatus status) {
        this.ID = ID;
        this.machine = machine;
        this.technical = technical;
        this.requestDate = requestDate;
        this.status = status;
    }

    public MaintenanceRequest(org.maintenancesystem.domain.model.entities.Machine machine, org.maintenancesystem.domain.model.entities.Technical technical, LocalDate requestDate, org.maintenancesystem.domain.model.enums.MaintenanceRequestStatus status) {
        this.machine = machine;
        this.technical = technical;
        this.requestDate = requestDate;
        this.status = status;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public org.maintenancesystem.domain.model.entities.Machine getMachine() {
        return machine;
    }

    public void setMachine(org.maintenancesystem.domain.model.entities.Machine machine) {
        this.machine = machine;
    }

    public org.maintenancesystem.domain.model.entities.Technical getTechnical() {
        return technical;
    }

    public void setTechnical(org.maintenancesystem.domain.model.entities.Technical technical) {
        this.technical = technical;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public org.maintenancesystem.domain.model.enums.MaintenanceRequestStatus getStatus() {
        return status;
    }

    public void setStatus(org.maintenancesystem.domain.model.enums.MaintenanceRequestStatus status) {
        this.status = status;
    }
}
