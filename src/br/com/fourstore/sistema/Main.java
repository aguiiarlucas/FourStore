package br.com.fourstore.sistema;

import br.com.fourstore.sistema.communication.MainMenu;
import br.com.fourstore.sistema.controller.ProductController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        MainMenu mainMenu = new MainMenu();
        mainMenu.mainMenu();

    }



}


