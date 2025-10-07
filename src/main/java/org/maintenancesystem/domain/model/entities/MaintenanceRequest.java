package org.maintenancesystem.domain.model.entities;

import org.maintenancesystem.domain.model.enums.MaintenanceRequestStatus;
import org.maintenancesystem.domain.model.entities.Technician;

import java.time.LocalDate;

public class MaintenanceRequest {

    private Long ID;
    private org.maintenancesystem.domain.model.entities.Machine machine;
    private Technician technical;
    private LocalDate requestDate;
    private MaintenanceRequestStatus status;

    public MaintenanceRequest(Long ID, org.maintenancesystem.domain.model.entities.Machine machine, Technician technician, LocalDate requestDate, org.maintenancesystem.domain.model.enums.MaintenanceRequestStatus status) {
        this.ID = ID;
        this.machine = machine;
        this.technical = technician;
        this.requestDate = requestDate;
        this.status = status;
    }

    public MaintenanceRequest(org.maintenancesystem.domain.model.entities.Machine machine, Technician technician, LocalDate requestDate, org.maintenancesystem.domain.model.enums.MaintenanceRequestStatus status) {
        this.machine = machine;
        this.technical = technician;
        this.requestDate = requestDate;
        this.status = status;
    }

    public MaintenanceRequest(Machine machine, Technician technical) {
        this.machine = machine;
        this.technical = technical;
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

    public Technician getTechnical() {
        return technical;
    }

    public void setTechnical(Technician technician) {
        this.technical = technician;
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
