package com.mycompany.refactoslog;

import java.util.Scanner;

public class RefactosLog {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ApplicationStarter app = new ApplicationStarter();

        System.out.println("Quer rodar o sistema ou quer debugar com o metodo demo?");
        System.out.println("[1] - Rodar o sistema - [2] - Debugar com o metodo demo");

        while (true) {
            int option = s.nextInt();
            if (option == 1) {
                app.startSystem();
                break;
            } else if (option == 2) {
                app.startDemo();
                break;
            } else {
                System.out.println("Opção inválida, tente novamente");
            }
        }

        s.close();
    }
}
