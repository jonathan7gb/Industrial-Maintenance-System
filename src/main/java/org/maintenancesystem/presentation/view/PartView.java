package org.maintenancesystem.presentation.view;

import org.maintenancesystem.domain.model.entities.Part;
import org.maintenancesystem.domain.model.entities.Part;
import org.maintenancesystem.presentation.helpers.InputHelper;
import org.maintenancesystem.presentation.helpers.MessageHelper;

import java.util.List;
import java.util.Scanner;

public class PartView {

    static Scanner sc = new Scanner(System.in);

    public Long insertID(){
        return (long) InputHelper.inputInteger("|| Insira o ID da peça (0 para encerrar): ", sc);
    }

    public double insertPartQuantity(){
        return InputHelper.inputDouble("|| Insira a quantidade que você vai usar: ", sc);
    }

    public Part insertPart(){
        Part part = null;
        while(true){
            System.out.println("\n|| ----- Cadastrar Peça ------ ||");
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

    public void getAllParts(List<Part> parts){
        System.out.println("|| ------------------ Peças --------------------");
        for(Part part : parts){
            System.out.println("|| ---------------------------------------------");
            System.out.printf("|| [%d] %-20s \n|| Quantidade em Estoque: %.2f\n", part.getID(), part.getName(), part.getQuantityInStock());
        }
        System.out.println("|| ---------------------------------------------");
    }

}
