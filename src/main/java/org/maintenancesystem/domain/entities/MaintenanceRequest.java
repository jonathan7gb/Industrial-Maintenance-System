package org.maintenancesystem.domain.entities;

import org.maintenancesystem.domain.enums.MaintenanceRequestStatus;

import java.time.LocalDate;

public class MaintenanceRequest {

    private Long ID;
    private Machine machine;
    private Technical technical;
    private LocalDate requestDate;
    private MaintenanceRequestStatus status;

    public MaintenanceRequest(Long ID, Machine machine, Technical technical, LocalDate requestDate, MaintenanceRequestStatus status) {
        this.ID = ID;
        this.machine = machine;
        this.technical = technical;
        this.requestDate = requestDate;
        this.status = status;
    }

    public MaintenanceRequest(Machine machine, Technical technical, LocalDate requestDate, MaintenanceRequestStatus status) {
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

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Technical getTechnical() {
        return technical;
    }

    public void setTechnical(Technical technical) {
        this.technical = technical;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public MaintenanceRequestStatus getStatus() {
        return status;
    }

    public void setStatus(MaintenanceRequestStatus status) {
        this.status = status;
    }
}
