package org.maintenancesystem.presentation.controller;

import org.maintenancesystem.application.service.TechnicianManager;
import org.maintenancesystem.presentation.helpers.MessageHelper;
import org.maintenancesystem.presentation.view.MainMenuView;

public class MainMenuController {

    public static void mainMenuController(){
        int option = MainMenuView.mainMenuView();

        switch (option){
            case 2 -> TechnicianManager.registerTechnician();
            case 0 -> { return; }
            default -> MessageHelper.invalidIntInput();
        }
    }
}
