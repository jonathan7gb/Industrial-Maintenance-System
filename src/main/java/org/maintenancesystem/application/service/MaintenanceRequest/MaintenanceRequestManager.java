package org.maintenancesystem.application.service.MaintenanceRequest;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.model.entities.MaintenanceRequest;
import org.maintenancesystem.domain.model.entities.Part;
import org.maintenancesystem.domain.model.entities.Technician;
import org.maintenancesystem.domain.model.enums.MachineStatus;
import org.maintenancesystem.domain.model.enums.MaintenanceRequestStatus;
import org.maintenancesystem.domain.port.MaintenanceRequest.MaintenanceRepositoryPort;
import org.maintenancesystem.infrastructure.adapter.Machine.MachineRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.MaintenanceRequest.MaintenanceRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.Part.PartRepositoryAdapter;
import org.maintenancesystem.infrastructure.adapter.Technician.TechnicianRepositoryAdapter;
import org.maintenancesystem.presentation.helpers.InputHelper;
import org.maintenancesystem.presentation.helpers.MessageHelper;
import org.maintenancesystem.presentation.view.MachineView;
import org.maintenancesystem.presentation.view.MaintenanceRequestView;
import org.maintenancesystem.presentation.view.TechnicianView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        Machine mac = null;
        Technician tech = null;

        while(true){
            try{
                System.out.println("\n|| ------- Criar ordem de manutenção  ------- ||");
                machineList = machineRepositoryAdapter.getAllOperationalMachines();
                if(machineList.isEmpty()){
                    MessageHelper.error("Nenhuma máquina em operação encontrada.\n");
                    return;
                }else{
                   Long machineID = machineView.insertID(machineList);
                   mac = machineRepositoryAdapter.getOperationalMachineById(machineID);

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
                    tech = technicianRepositoryAdapter.getTechnicianById(technicianID);

                    if(tech == null){
                        MessageHelper.error("Técnico não encontrado!\n");
                    }else{
                        break;
                    }
                }
            }catch (SQLException e){
                MessageHelper.error(e.getMessage());
                return;
            }
        }

        try{
            MaintenanceRequest maintenanceRequest = new MaintenanceRequest(mac, tech);
            maintenanceRepository.registerMaintenanceRequest(maintenanceRequest);
            machineRepositoryAdapter.updateMachineStatus(mac.getID(), MachineStatus.EM_MANUTENCAO);
            MessageHelper.success("Ordem de manutenção criada com sucesso!\n");
        }catch (SQLException e){
            MessageHelper.error(e.getMessage());
        }
    }

    public void executeMaintenanceRequest(){
        while(true){
            List<MaintenanceRequest> maintenanceRequests = new ArrayList<>();
            MaintenanceRepositoryAdapter maintenanceRepositoryAdapter = new MaintenanceRepositoryAdapter();
            MachineRepositoryAdapter machineRepositoryAdapter = new MachineRepositoryAdapter();

            MaintenanceRequest mr = null;
            try{
                System.out.println("\n|| --------- --Executar Manutenção ---------- ||");
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
                        System.out.println("\n|| ---------------------------------------------");
                        System.out.println(mr);
                        System.out.println("|| ---------------------------------------------");
                        String choice = InputHelper.inputString("\n|| Você tem certeza que quer executar a manutenção?\n|| S - Sim\n|| N - Não\n|| Sua escolha: ", new Scanner(System.in));

                        if(choice.equalsIgnoreCase("S")){
                            maintenanceRepositoryAdapter.updateMaintenanceStatus(mr.getID(), MaintenanceRequestStatus.EXECUTADA);
                            machineRepositoryAdapter.updateMachineStatus(mr.getMachine().getID(), MachineStatus.OPERACIONAL);
                            MessageHelper.success("Manutenção executada com sucesso!\n");
                            break;
                        }else if(choice.equalsIgnoreCase("N")){
                            MessageHelper.info("Manutenção cancelada com sucesso!\n");
                            break;
                        }else{
                            MessageHelper.error("Entrada inválida!");
                        }
                    }
                }
            }catch (SQLException e){
                MessageHelper.error(e.getMessage());
            }
        }

    }
}
