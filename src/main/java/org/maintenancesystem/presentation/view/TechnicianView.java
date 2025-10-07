package org.maintenancesystem.presentation.view;

import org.maintenancesystem.domain.model.entities.Machine;
import org.maintenancesystem.domain.model.entities.Technician;
import org.maintenancesystem.presentation.helpers.InputHelper;
import org.maintenancesystem.presentation.helpers.MessageHelper;

import java.util.List;
import java.util.Scanner;

public class TechnicianView {

    static Scanner sc = new Scanner(System.in);

    public Long insertID(List<Technician> technician){
        getAllTechnicians(technician);
        System.out.println("|| ---------------------------------------------");
        return (long) InputHelper.inputInteger("|| Insira o ID do técnico: ", sc);
    }

    public Technician insertTechnical(){
        Technician tec = null;
        while(true){
            System.out.println("\n|| ---- Cadastrar Técnico ----- ||");
            String name = InputHelper.inputString("|| Insira o nome do técnico: ", sc);

            String specialty = InputHelper.inputString("|| Insira a especialidade do técnico: ", sc);

            if(!name.isBlank() || !specialty.isBlank()){
                tec = new Technician(name, specialty);
                break;
            }else{
                MessageHelper.error("Os dados não podem estar vazios! Obrigatório o preenchimento!");
            }
        }
        return tec;
    }

    public void getAllTechnicians(List<Technician> technicians){
        for(Technician technician : technicians){
            System.out.println("|| ---------------------------------------------");
            System.out.printf("|| [%d] %-20s \n|| Especialidade: %-20s \n", technician.getID(), technician.getName(), technician.getSpecialty());
        }
    }
}
