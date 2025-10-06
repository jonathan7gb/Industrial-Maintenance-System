package org.maintenancesystem.application.service;

import org.maintenancesystem.domain.model.entities.Technician;
import org.maintenancesystem.infrastructure.persistence.TechnicianRepositoryImplements;
import org.maintenancesystem.presentation.helpers.MessageHelper;
import org.maintenancesystem.presentation.view.TechnicianView;

import java.sql.SQLException;

public class TechnicianManager {

    public static void registerTechnician(){
        Technician technician = TechnicianView.insertTechnical();
        TechnicianRepositoryImplements technicianRepository = new TechnicianRepositoryImplements();
        try{
            boolean exists = technicianRepository.verifyTechnicalIfNameAlreadyExists(technician.getName());
            if(exists){
                MessageHelper.error("Já existe um técnico com esse nome cadastrado!");
            }else{
                technicianRepository.registerTechnical(technician);
                MessageHelper.success("Técnico registrado com sucesso!");
            }
        }catch(SQLException e){
            MessageHelper.error(e.getMessage());
        }
    }
}
