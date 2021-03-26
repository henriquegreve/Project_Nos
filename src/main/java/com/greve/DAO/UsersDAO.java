package com.greve.DAO;

import com.greve.Database.DatabaseConnection;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class UsersDAO {

    public UsersDAO(){

    }

    public int createUser(String username, String password, int level){
        int result = 0;
        String sql = "insert into project_nos.users (username, passwd, level) values (?, ?, ?);";
        try {
            Connection db = DatabaseConnection.getConnection();
            if (db != null) {
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setInt(3, level);

                result = stmt.executeUpdate();
            }
        } catch (SQLException e) {
            result = 0;
        }
        return result;
    }

    public int createUserAdmin(){
        int result = 0;
        String sql = "insert into project_nos.users (username, passwd, level) values (?, ?, ?);";
        try {
            Connection db = DatabaseConnection.getConnection();
            if (db != null) {
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, "Administrador");
                stmt.setString(2, "Secur3@Nos");
                stmt.setInt(3, 1);

                result = stmt.executeUpdate();
            }
        } catch (SQLException e) {
            result = 0;
        }
        return result;
    }

    public int checkLogin(String username, String password) {
        int count = 0;
        try {
            String sql = "SELECT * FROM USERS WHERE USERNAME = '"+username
                    +"' AND PASSWD = '"+password
                    +"';";

            Connection db = DatabaseConnection.getConnection();

            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()){
                    count++;
                }
            }
            return count;
        } catch (SQLException ignored) {
            System.out.println("Impossivel verificar login!");
        }
        return count;
    }
}
