package org.maintenancesystem.application.service;

import org.maintenancesystem.domain.model.entities.MaintenanceRequest;
import org.maintenancesystem.domain.model.entities.Part;
import org.maintenancesystem.domain.port.PartOrderRepositoryPort;
import org.maintenancesystem.domain.port.PartRepositoryPort;
import org.maintenancesystem.infrastructure.adapter.MaintenanceRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.PartRepositoryAdapter;
import org.maintenancesystem.presentation.helpers.MessageHelper;
import org.maintenancesystem.presentation.view.MaintenanceRequestView;
import org.maintenancesystem.presentation.view.PartView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartManager {

    private final PartRepositoryPort partRepository;
    private final PartOrderRepositoryPort partOrderRepository;
    private final PartView partView =  new PartView();

    MaintenanceRequestView maintenanceRequestView = new MaintenanceRequestView();

    public PartManager(PartRepositoryPort partRepository, PartOrderRepositoryPort partOrderRepository) {
        this.partRepository = partRepository;
        this.partOrderRepository = partOrderRepository;
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
        List<Part> parts = new ArrayList<>();
        MaintenanceRepositoryAdapter maintenanceRepositoryAdapter = new MaintenanceRepositoryAdapter();
        PartRepositoryAdapter partRepositoryAdapter = new PartRepositoryAdapter();

        MaintenanceRequest mr = null;
        Part part = null;

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
        Long partID;
        double quantityUse;

        while(true){
            try{
                parts = partRepositoryAdapter.getAllPartsStockIsMoreThan0();
                if(parts.isEmpty()){
                    MessageHelper.error("Nenhuma Peça encontrada!\n");
                    return;
                }else{
                    while(true){
                        partView.getAllParts(parts);
                        partID = partView.insertID();
                        if(partID == 0){
                            return;
                        }else{
                            part = partRepositoryAdapter.getPartByIdStockIsMoreThan0(partID);
                            if(part == null){
                                MessageHelper.error("Nenhuma Peça encontrada!\n");
                            }else{
                                quantityUse = partView.insertPartQuantity();
                                if(quantityUse <= 0 || quantityUse > part.getQuantityInStock()){
                                    MessageHelper.error("Quantidade insuficiente!\n");
                                }else{
                                    partOrderRepository.registerPartOrder(mr.getID(), part.getID(), quantityUse);
                                    partRepositoryAdapter.updatePartStock(partID, quantityUse);
                                    MessageHelper.success("Peça Associada com sucesso!\n");
                                }
                            }
                        }
                    }
                }
            }catch (SQLException e){
                MessageHelper.error(e.getMessage());
            }
        }
    }
}
