package org.maintenancesystem;

import org.maintenancesystem.domain.repository.TechnicianRepositoryPort;
import org.maintenancesystem.infrastructure.persistence.TechnicianRepositoryImplements;
import org.maintenancesystem.presentation.controller.MainMenuController;

public class Main {
    public static void main(String[] args) {

        MainMenuController mainMenuController = new MainMenuController();

        mainMenuController.mainMenuController();
    }
}