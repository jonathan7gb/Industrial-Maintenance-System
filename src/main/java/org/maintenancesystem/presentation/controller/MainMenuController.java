package org.maintenancesystem.presentation.controller;

import org.maintenancesystem.application.service.MachineManager;
import org.maintenancesystem.application.service.MaintenanceRequestManager;
import org.maintenancesystem.application.service.PartManager;
import org.maintenancesystem.application.service.TechnicianManager;
import org.maintenancesystem.domain.port.MachineRepositoryPort;
import org.maintenancesystem.domain.port.MaintenanceRepositoryPort;
import org.maintenancesystem.domain.port.PartRepositoryPort;
import org.maintenancesystem.domain.port.TechnicianRepositoryPort;
import org.maintenancesystem.infrastructure.adapter.MachineRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.MaintenanceRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.PartRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.TechnicianRepositoryAdapter;
import org.maintenancesystem.presentation.helpers.MessageHelper;
import org.maintenancesystem.presentation.view.MainMenuView;

public class MainMenuController {

    MainMenuView mainMenuView = new MainMenuView();

    TechnicianRepositoryPort t = new TechnicianRepositoryAdapter();
    TechnicianManager technicianManager = new TechnicianManager(t);

    MachineRepositoryPort m = new MachineRepositoryAdapter();
    MachineManager machineManager = new MachineManager(m);

    PartRepositoryPort p = new PartRepositoryAdapter();
    PartManager partManager = new PartManager(p);

    MaintenanceRepositoryPort mr = new MaintenanceRepositoryAdapter();
    MaintenanceRequestManager maintenanceRequestManager = new MaintenanceRequestManager(mr);

    public void mainMenuController(){
        int option = 0;

        while(true){
            option = mainMenuView.mainMenuView();
            switch (option){
                case 1 -> machineManager.registerMachine();
                case 2 -> technicianManager.registerTechnician();
                case 3 -> partManager.registerPart();
                case 4 -> maintenanceRequestManager.insertMaintenanceRequest();
                case 0 -> {
                    System.out.println("\n|| ---- Saindo do Sistema ---- ||\n");
                    return;
                }
                default -> MessageHelper.invalidIntInput();
            }
        }
    }
}
