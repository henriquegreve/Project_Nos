package com.greve.Controller;

import com.greve.DAO.UsersDAO;
import com.greve.Database.DatabaseConnection;

import java.sql.SQLException;


public class Controller {

    public static void initConnection(){
        DatabaseConnection.getConnection();
    }

    public static boolean checkLogin(String username, String password){
        UsersDAO dao = new UsersDAO();
        return dao.checkLogin(username, password) > 0;
    }

    public static void checkStatusConnection(){
        System.out.println(DatabaseConnection.statusConnection());
    }

    public static void createTables(){
        try{
            DatabaseConnection.createTables();
        } catch (SQLException e) {
            System.out.println("Tabelas não criadas!");
        }
    }

    public static void createUserAdmin() {
        UsersDAO dao = new UsersDAO();
        dao.createUserAdmin();
    }

    public static boolean createUser(String username, String pass, int level) {
        UsersDAO dao = new UsersDAO();
        return dao.createUser(username, pass, level) > 0;
    }

    public static void initSystem(){
        initConnection();
        //if(!checkDatabase()){ createTables(); }
        createTables();
        createUserAdmin();
    }

    public static boolean checkDatabase() {
        return DatabaseConnection.verifyTables() > 0;
    }


}
