package org.maintenancesystem.application.service;

import org.maintenancesystem.domain.model.entities.Part;
import org.maintenancesystem.domain.port.PartRepositoryPort;
import org.maintenancesystem.infrastructure.adapter.PartRepositoryAdapter;
import org.maintenancesystem.presentation.helpers.MessageHelper;
import org.maintenancesystem.presentation.view.PartView;

import java.sql.SQLException;

public class PartManager {

    private final PartRepositoryPort partRepository;
    private final PartView partView =  new PartView();

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
}
