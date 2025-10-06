package org.maintenancesystem.presentation.view;

import org.maintenancesystem.presentation.helpers.InputHelper;

import java.util.Scanner;

public class MainMenuView {

    static Scanner sc = new Scanner(System.in);

    public static int mainMenuView(){
        System.out.println("""
                || --- Sistema de Manutenção Industrial --- ||
                || 1 - Cadastrar Máquina
                || 2 - Cadastrar Técnico
                || 3 - Cadastrar Peça
                || 4 - Criar Ordem de Manutenção
                || 5 - Associar Peças à Ordem
                || 6 - Executar Manutenção
                || --------------------------------------- ||
                || 0 - Sair do Sistema
                || --------------------------------------- ||
                """
        );
        return InputHelper.inputInteger("|| Sua escolha: ", sc);
    }
}
