package com.greve.View;

import com.greve.Controller.Controller;

import java.util.Scanner;

public class Main {

    public Main(){

    }

    public static void main(String[] args){

        Controller.initSystem();

        String username;
        String pass;
        Scanner sc = new Scanner(System.in);

        System.out.println("1 --> Sign In || 2 --> Sign Up  ");
        int choice = sc.nextInt();

        switch(choice){
            case 1:
                System.out.println("\nUsername: ");
                username = sc.nextLine();
                System.out.println("\nPassword: ");
                pass = sc.nextLine();

                if (Controller.checkLogin(username, pass)) {
                    System.out.println("\nAcess granted!");
                } else {
                    System.out.println("\nAccess Denied");
                }
                break;

            case 2:
                System.out.println("\nUsername: ");
                username = sc.nextLine();
                System.out.println("\nPassword: ");
                pass = sc.nextLine();

                int level = 5;
                if (username.trim().equals("") && pass.trim().equals("")){
                    System.out.println("Preencheu serto!");
                } else {
                    if (Controller.createUser(username, pass, level)) {
                        System.out.println("Usuario cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar o usuario!");
                    }
                }
                break;

            default:
                System.out.println("Puff...");
                break;
        }




    }


}
