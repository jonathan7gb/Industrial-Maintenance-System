package org.maintenancesystem.presentation.view;

import org.maintenancesystem.domain.model.entities.Part;
import org.maintenancesystem.presentation.helpers.InputHelper;
import org.maintenancesystem.presentation.helpers.MessageHelper;

import java.util.Scanner;

public class PartView {

    static Scanner sc = new Scanner(System.in);

    public static Long insertID(){
        return (long) InputHelper.inputInteger("|| Insira o ID da peça: ", sc);
    }

    public static Part insertPart(){
        Part part = null;
        while(true){
            String name = InputHelper.inputString("|| Insira o nome da peça: ", sc);
            int quantityInStock = InputHelper.inputInteger("|| Insira a quantidade em estoque da peça: ", sc);

            if(!name.isBlank() || quantityInStock >= 0){
                part = new Part(name, quantityInStock);
                break;
            }else{
                MessageHelper.error("Os dados não podem estar vazios ou negativos! Obrigatório o preenchimento correto!");
            }
        }
        return part;
    }
}
