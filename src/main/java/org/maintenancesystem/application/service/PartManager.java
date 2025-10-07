package org.maintenancesystem.application.service;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.model.entities.MaintenanceRequest;
import org.maintenancesystem.domain.model.entities.Part;
import org.maintenancesystem.domain.model.entities.Technician;
import org.maintenancesystem.domain.port.PartRepositoryPort;
import org.maintenancesystem.infrastructure.adapter.MachineRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.MaintenanceRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.PartRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.TechnicianRepositoryAdapter;
import org.maintenancesystem.presentation.helpers.MessageHelper;
import org.maintenancesystem.presentation.view.MaintenanceRequestView;
import org.maintenancesystem.presentation.view.PartView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartManager {

    private final PartRepositoryPort partRepository;
    private final PartView partView =  new PartView();

    MaintenanceRequestView maintenanceRequestView = new MaintenanceRequestView();

    public PartManager(PartRepositoryPort partRepository) {
        this.partRepository = partRepository;
    }

    public  void registerPart(){
        Part part = partView.insertPart();
        PartRepositoryAdapter partRepository = new PartRepositoryAdapter();
        try{
            boolean exists = partRepository.verifyPartIfNameAlreadyExists(part.getName());
            if(exists){
                MessageHelper.error("Já existe uma peça com esse nome cadastrada!\n");
            }else{
                partRepository.registerPart(part);
                MessageHelper.success("Peça registrada com sucesso!\n");
            }
        }catch(SQLException e){
            MessageHelper.error(e.getMessage());
        }
    }

    public void associatePartsToAOrder(){
        List<MaintenanceRequest> maintenanceRequests = new ArrayList<>();
        MaintenanceRepositoryAdapter maintenanceRepositoryAdapter = new MaintenanceRepositoryAdapter();

        MaintenanceRequest mr = null;

        while(true){
            try{
                System.out.println("\n|| ------- Associar Peças a uma Ordem ------ ||");
                maintenanceRequests = maintenanceRepositoryAdapter.getAllPendingMaintenanceRequests();

                if(maintenanceRequests.isEmpty()){
                    MessageHelper.error("Nenhuma ordem encontrada!\n");
                    return;
                }else{
                    Long maintenanceID = maintenanceRequestView.insertID(maintenanceRequests);
                    mr = maintenanceRepositoryAdapter.getPendingMaintenanceById(maintenanceID);
                    if(mr == null){
                        MessageHelper.error("Nenhuma ordem encontrada com esse ID!");
                    }else{
                        System.out.println();
                        break;
                    }
                }
            }catch (SQLException e){
                MessageHelper.error(e.getMessage());
            }
        }
    }
}
