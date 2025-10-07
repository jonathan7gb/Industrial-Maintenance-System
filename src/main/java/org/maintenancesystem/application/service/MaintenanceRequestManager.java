package org.maintenancesystem.application.service;

import org.maintenancesystem.domain.port.MachineRepositoryPort;
import org.maintenancesystem.domain.port.MaintenanceRepositoryPort;
import org.maintenancesystem.presentation.view.MachineView;
import org.maintenancesystem.presentation.view.MaintenanceRequestView;

public class MaintenanceRequestManager {

    private final MaintenanceRepositoryPort maintenanceRepository;
    private final MaintenanceRequestView maintenanceRequestView =  new MaintenanceRequestView();

    public MaintenanceRequestManager(MaintenanceRepositoryPort maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }
}
