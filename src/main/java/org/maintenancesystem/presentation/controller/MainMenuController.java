package org.maintenancesystem.presentation.controller;

import org.maintenancesystem.application.service.MachineManager;
import org.maintenancesystem.application.service.TechnicianManager;
import org.maintenancesystem.domain.repository.MachineRepositoryPort;
import org.maintenancesystem.domain.repository.TechnicianRepositoryPort;
import org.maintenancesystem.infrastructure.persistence.MachineRepositoryImplements;
import org.maintenancesystem.infrastructure.persistence.TechnicianRepositoryImplements;
import org.maintenancesystem.presentation.helpers.MessageHelper;
import org.maintenancesystem.presentation.view.MainMenuView;

public class MainMenuController {

    MainMenuView mainMenuView = new MainMenuView();

    TechnicianRepositoryPort t = new TechnicianRepositoryImplements();
    TechnicianManager technicianManager = new TechnicianManager(t);

    MachineRepositoryPort m = new MachineRepositoryImplements();
    MachineManager machineManager = new MachineManager(m);

    public void mainMenuController(){
        int option = 0;

        while(true){
            option = mainMenuView.mainMenuView();
            switch (option){
                case 1 -> machineManager.registerMachine();
                case 2 -> technicianManager.registerTechnician();
                case 0 -> {
                    System.out.println("\n|| ---- Saindo do Sistema ---- ||\n");
                    return;
                }
                default -> MessageHelper.invalidIntInput();
            }
        }
    }
}
