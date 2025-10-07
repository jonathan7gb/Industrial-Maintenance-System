package org.maintenancesystem.presentation.view;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.model.enums.MachineStatus;
import org.maintenancesystem.presentation.helpers.InputHelper;
import org.maintenancesystem.presentation.helpers.MessageHelper;

import java.util.List;
import java.util.Scanner;

public class MachineView {

    static Scanner sc = new Scanner(System.in);

    public Long insertID(List<Machine> machines){
        getAllMachines(machines);
        System.out.println("|| ---------------------------------------------");
        return (long) InputHelper.inputInteger("|| Insira o ID da máquina: ", sc);
    }

    public Machine insertMachine(){
        Machine mach = null;
        while(true){
            System.out.println("\n|| ---- Cadastrar Máquina ----- ||");
            String name = InputHelper.inputString("|| Insira o nome da máquina: ", sc);
            String sector = InputHelper.inputString("|| Insira o setor da máquina: ", sc);

            if(!name.isBlank() || !sector.isBlank()){
                mach = new Machine(name, sector, MachineStatus.OPERACIONAL);
                break;
            }else{
                MessageHelper.error("Os dados não podem estar vazios! Obrigatório o preenchimento!");
            }
        }
        return mach;
    }

    public void getAllMachines(List<Machine> machines){
        for(Machine machine : machines){
            System.out.println("|| ---------------------------------------------");
            System.out.printf("|| [%d] %-20s \n|| Setor: %-20s\n|| Status: %-13s \n", machine.getID(), machine.getName(), machine.getSector(), machine.getStatus());
        }
    }
}
