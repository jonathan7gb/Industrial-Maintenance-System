package org.maintenancesystem.application.service;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.model.entities.MaintenanceRequest;
import org.maintenancesystem.domain.model.entities.Technician;
import org.maintenancesystem.domain.port.MachineRepositoryPort;
import org.maintenancesystem.domain.port.MaintenanceRepositoryPort;
import org.maintenancesystem.infrastructure.adapter.MachineRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.TechnicianRepositoryAdapter;
import org.maintenancesystem.presentation.helpers.MessageHelper;
import org.maintenancesystem.presentation.view.MachineView;
import org.maintenancesystem.presentation.view.MaintenanceRequestView;
import org.maintenancesystem.presentation.view.TechnicianView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceRequestManager {

    private final MaintenanceRepositoryPort maintenanceRepository;
    private final MaintenanceRequestView maintenanceRequestView =  new MaintenanceRequestView();
    private final MachineView machineView =  new MachineView();
    private final TechnicianView technicianView =  new TechnicianView();

    public MaintenanceRequestManager(MaintenanceRepositoryPort maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public void insertMaintenanceRequest() {
        List<Machine> machineList = new ArrayList<>();
        List<Technician> technicianList = new ArrayList<>();
        MachineRepositoryAdapter machineRepositoryAdapter = new MachineRepositoryAdapter();
        TechnicianRepositoryAdapter technicianRepositoryAdapter = new TechnicianRepositoryAdapter();

        while(true){
            try{
                System.out.println("\n|| ------- Criar ordem de manutenção  ------- ||");
                machineList = machineRepositoryAdapter.getAllMachines();
                if(machineList.isEmpty()){
                    MessageHelper.error("Nenhuma máquina encontrada.");
                }else{
                   Long machineID = machineView.insertID(machineList);
                   Machine mac = machineRepositoryAdapter.getMachineById(machineID);

                   if(mac == null){
                       MessageHelper.error("Máquina não encontrada!");
                   }else{
                       System.out.println();
                       break;
                   }
                }
            }catch(SQLException e){
                MessageHelper.error(e.getMessage());
                return;
            }
        }

        while(true){
            try{
                technicianList = technicianRepositoryAdapter.getAllTechnicians();
                if(technicianList.isEmpty()){
                    MessageHelper.error("Nenhuma técnico encontrado.");
                }else{
                    Long technicianID = technicianView.insertID(technicianList);
                    Technician tech = technicianRepositoryAdapter.getTechnicianById(technicianID);

                    if(tech == null){
                        MessageHelper.error("Técnico não encontrado!\n");
                    }else{
                        System.out.println();
                        break;
                    }
                }
            }catch (SQLException e){
                MessageHelper.error(e.getMessage());
                return;
            }
        }

    }
}
