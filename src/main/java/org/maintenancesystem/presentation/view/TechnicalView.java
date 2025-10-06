package org.maintenancesystem.presentation.view;

import org.maintenancesystem.domain.model.entities.Technical;
import org.maintenancesystem.presentation.helpers.InputHelper;
import org.maintenancesystem.presentation.helpers.MessageHelper;

import java.util.Scanner;

public class TechnicalView {

    static Scanner sc = new Scanner(System.in);

    public static Long insertID(){
        return (long) InputHelper.inputInteger("|| Insira o ID do técnico: ", sc);
    }

    public static Technical insertTechnical(){
        Technical tec = null;
        while(true){
            String name = InputHelper.inputString("|| Insira o nome do técnico: ", sc);

            String specialty = InputHelper.inputString("|| Insira a especialidade do técnico: ", sc);

            if(!name.isBlank() || !specialty.isBlank()){
                tec = new Technical(name, specialty);
                break;
            }else{
                MessageHelper.error("Os dados não podem estar vazios! Obrigatório o preenchimento!");
            }
        }
        return tec;
    }
}
