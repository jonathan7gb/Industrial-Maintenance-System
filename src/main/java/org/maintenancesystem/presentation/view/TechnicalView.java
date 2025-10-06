package org.maintenancesystem.presentation.view;

import org.maintenancesystem.presentation.helpers.InputHelper;

import java.util.Scanner;

public class TechnicalView {

    static Scanner sc = new Scanner(System.in);

    public static Long insertID(){
        return (long) InputHelper.inputInteger("|| Insira o ID do técnico: ", sc);
    }
}
