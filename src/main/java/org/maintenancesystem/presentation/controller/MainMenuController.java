package org.maintenancesystem.presentation.controller;

import org.maintenancesystem.application.service.Machine.MachineManager;
import org.maintenancesystem.application.service.MaintenanceRequest.MaintenanceRequestManager;
import org.maintenancesystem.application.service.Part.PartManager;
import org.maintenancesystem.application.service.Technician.TechnicianManager;
import org.maintenancesystem.domain.port.Machine.MachineRepositoryPort;
import org.maintenancesystem.domain.port.MaintenanceRequest.MaintenanceRepositoryPort;
import org.maintenancesystem.domain.port.Part.PartOrderRepositoryPort;
import org.maintenancesystem.domain.port.Part.PartRepositoryPort;
import org.maintenancesystem.domain.port.Technician.TechnicianRepositoryPort;
import org.maintenancesystem.infrastructure.adapter.Machine.MachineRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.MaintenanceRequest.MaintenanceRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.Part.PartOrderRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.Part.PartRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.Technician.TechnicianRepositoryAdapter;
import org.maintenancesystem.presentation.helpers.MessageHelper;
import org.maintenancesystem.presentation.view.MainMenuView;

public class MainMenuController {

    MainMenuView mainMenuView = new MainMenuView();

    TechnicianRepositoryPort t = new TechnicianRepositoryAdapter();
    TechnicianManager technicianManager = new TechnicianManager(t);

    MachineRepositoryPort m = new MachineRepositoryAdapter();
    MachineManager machineManager = new MachineManager(m);

    PartRepositoryPort p = new PartRepositoryAdapter();
    PartOrderRepositoryPort po = new PartOrderRepositoryAdapter();
    PartManager partManager = new PartManager(p, po);

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
                case 5 -> partManager.associatePartsToAOrder();
                case 6 -> maintenanceRequestManager.executeMaintenanceRequest();
                case 0 -> {
                    System.out.println("\n|| ---- Saindo do Sistema ---- ||\n");
                    return;
                }
                default -> MessageHelper.invalidIntInput();
            }
        }
    }
}
