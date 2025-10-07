package org.maintenancesystem.application.service;

import org.maintenancesystem.domain.model.entities.Part;
import org.maintenancesystem.domain.repository.PartRepositoryPort;
import org.maintenancesystem.infrastructure.persistence.PartRepositoryImplements;
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
        PartRepositoryImplements partRepository = new PartRepositoryImplements();
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
