package org.maintenancesystem.presentation.view;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.model.entities.MaintenanceRequest;
import org.maintenancesystem.presentation.helpers.InputHelper;

import java.util.List;
import java.util.Scanner;

public class MaintenanceRequestView {

    static Scanner sc = new Scanner(System.in);

    public Long insertID(List<MaintenanceRequest> maintenanceRequests){
        getAllMaintenanceRequests(maintenanceRequests);
        System.out.println("|| ---------------------------------------------");
        return (long) InputHelper.inputInteger("|| Insira o ID da ordem: ", sc);
    }

    public void getAllMaintenanceRequests(List<MaintenanceRequest> maintenanceRequests){
        for(MaintenanceRequest mr : maintenanceRequests){
            System.out.println("|| ---------------------------------------------");
            System.out.printf("|| [%d] %s \n|| Máquina: %s\n|| Técnico: %s \n|| Status: %s", mr.getID(), mr.getRequestDate(), mr.getMachine().getName(), mr.getTechnical().getName(), mr.getStatus());
        }
    }
}
