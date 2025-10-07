package org.maintenancesystem.application.service;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.repository.MachineRepositoryPort;
import org.maintenancesystem.infrastructure.persistence.MachineRepositoryImplements;
import org.maintenancesystem.presentation.helpers.MessageHelper;
import org.maintenancesystem.presentation.view.MachineView;

import java.sql.SQLException;

public class MachineManager {

    private final MachineRepositoryPort machineRepository;
    private final MachineView machineView =  new MachineView();

    public MachineManager(MachineRepositoryPort machineRepository) {
        this.machineRepository = machineRepository;
    }

    public  void registerMachine(){
        Machine machine = machineView.insertMachine();
        MachineRepositoryImplements machineRepository = new MachineRepositoryImplements();
        try{
            boolean exists = machineRepository.verifyMachineIfNameAlreadyExists(machine.getName());
            if(exists){
                MessageHelper.error("Já existe uma máquina com esse nome cadastrada!\n");
            }else{
                machineRepository.registerMachine(machine);
                MessageHelper.success("Máquina registrada com sucesso!\n");
            }
        }catch(SQLException e){
            MessageHelper.error(e.getMessage());
        }
    }
}
