package org.maintenancesystem.application.service;

import org.maintenancesystem.domain.model.entities.Technician;
import org.maintenancesystem.domain.repository.TechnicianRepositoryPort;
import org.maintenancesystem.infrastructure.persistence.TechnicianRepositoryImplements;
import org.maintenancesystem.presentation.helpers.MessageHelper;
import org.maintenancesystem.presentation.view.TechnicianView;

import java.sql.SQLException;

public class TechnicianManager {

    private final TechnicianRepositoryPort technicianRepository;
    private TechnicianView technicianView =  new TechnicianView();
    public TechnicianManager(TechnicianRepositoryPort technicianRepository) {
        this.technicianRepository = technicianRepository;
    }

    public void registerTechnician(){
        Technician technician = technicianView.insertTechnical();
        try{
            boolean exists = technicianRepository.verifyTechnicianIfNameAlreadyExists(technician.getName());
            if(exists){
                MessageHelper.error("Já existe um técnico com esse nome cadastrado!\n");
            }else{
                technicianRepository.registerTechnician(technician);
                MessageHelper.success("Técnico registrado com sucesso!\n");
            }
        }catch(SQLException e){
            MessageHelper.error(e.getMessage());
        }
    }
}
