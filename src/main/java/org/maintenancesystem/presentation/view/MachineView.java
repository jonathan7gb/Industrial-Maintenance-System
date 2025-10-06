package org.maintenancesystem.presentation.view;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.model.enums.MachineStatus;
import org.maintenancesystem.presentation.helpers.InputHelper;
import org.maintenancesystem.presentation.helpers.MessageHelper;

import java.util.Scanner;

public class MachineView {

    static Scanner sc = new Scanner(System.in);

    public static Long insertID(){
        return (long) InputHelper.inputInteger("|| Insira o ID da máquina: ", sc);
    }

    public static Machine insertMachine(){
        Machine mach = null;
        while(true){
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
}
