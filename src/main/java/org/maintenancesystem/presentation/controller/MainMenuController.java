package org.maintenancesystem.presentation.controller;

import org.maintenancesystem.application.service.TechnicianManager;
import org.maintenancesystem.presentation.view.MainMenuView;

public class MainMenuController {

    public static void mainMenuController(){
        int option = MainMenuView.mainMenuView();

        switch (option){
            case 1 -> TechnicianManager.registerTechnician();

        }
    }
}
