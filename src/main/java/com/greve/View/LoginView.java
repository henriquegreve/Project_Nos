package com.greve.View;

import com.greve.Controller.Controller;

import java.util.Scanner;

public class LoginView {

    public static void main(String[] args){

        Controller.initConnection();

        String username;
        String pass;
        Scanner sc = new Scanner(System.in);

        System.out.println("\nUsername: ");
        username = sc.nextLine();
        System.out.println("\nPassword: ");
        pass = sc.nextLine();

        if (Controller.checkLogin(username, pass)){
            System.out.println("\nAcess granted!");
        } else {
            System.out.println("\nAccess Denied");
        }

    }


}
