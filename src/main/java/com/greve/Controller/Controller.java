package com.greve.Controller;

import com.greve.Database.DatabaseConnection;


public class Controller {

    public static void initConnection(){
        DatabaseConnection.getConnection();
    }

    public static boolean checkLogin(String username, String password){
        return false;
    }

    public static void checkDataBase(){
        System.out.println(DatabaseConnection.statusConnection());
    }
    
}
