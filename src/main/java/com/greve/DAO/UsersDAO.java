package com.greve.DAO;

import com.greve.Database.DatabaseConnection;
import com.greve.Model.Users;
import com.greve.Util.CypherPunk;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UsersDAO {

    public UsersDAO(){

    }

    public int createUser(Users user){
        int result = 0;
        String sql = "insert into project_nos.users (name, username, passwd, level) values (?, ?, ?, ?);";
        try {
            Connection db = DatabaseConnection.getConnection();
            if (db != null) {
                if(!(verifyUser(db, user.getUsername()) > 0)) {
                    PreparedStatement stmt = db.prepareStatement(sql);

                    stmt.setString(1, user.getNome());
                    stmt.setString(2, user.getUsername());
                    stmt.setString(3, CypherPunk.newCypher(user.getPassword()));
                    stmt.setInt(4, user.getLevel());

                    result = stmt.executeUpdate();

                    if(result > 0){
                        result = getIdUser(db);
                    }
                }
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            result = 0;
        }

        return result;
    }

    private int getIdUser(Connection db) {

        int result = 0;
        String sql = "select id from users where id = (select max(id) from users)";
        try {
            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                   result = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            return 0;
        }
        return result;
    }

    public void createUserAdmin(){
        String sql = "insert into project_nos.users (name, username, passwd, level) values (?, ?, ?, ?);";
        try {
            Connection db = DatabaseConnection.getConnection();
            if (db != null) {
                if(!(verifyUser(db, "Admin") > 0)) {
                    PreparedStatement stmt = db.prepareStatement(sql);

                    stmt.setString(1, "Administrator");
                    stmt.setString(2, "Admin");
                    stmt.setString(3, CypherPunk.newCypher("Secur3@Nos"));
                    stmt.setInt(4, 3);

                    stmt.executeUpdate();
                }
            }
        } catch (SQLException | NoSuchAlgorithmException ignored) {

        }
    }

    public int verifyUser(Connection db, String username){
        String sql = "select username from users where username = '"+username+"'";
        int result = 0;
        try {
            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                result++;
            }

            return result;
        } catch (SQLException e) {
            return result;
        }
    }

    public int checkLogin(String username, String password) {
        int level = 0;
        try {
            String passwd = CypherPunk.newCypher(password);
            String sql = "SELECT USERNAME, PASSWD, LEVEL FROM USERS WHERE USERNAME = '"+username+"';";

            Connection db = DatabaseConnection.getConnection();

            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()){
                    String pass = rs.getString("PASSWD");
                    if(pass.equals(passwd)) {
                        level = rs.getInt("LEVEL");
                    }
                }
            }
            return level;
        } catch (SQLException | NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "Impossivel verificar login!");
        }
        return level;
    }

    public static List<Users> getAll(String username, int level){

        int count = 0;
        List<Users> list = new LinkedList<>();
        String sql = "select id, name, username, level from project_nos.users";

        if(!username.equals("") || level > 0){
            sql = sql.concat(" where ");

            if(!username.equals("")){
                sql = sql.concat(" username = '"+username+"'");
                count++;
            }

            if(count > 0){
                sql = sql.concat(" and ");
            }

            if(level > 0){
                sql = sql.concat(" level = "+level+"'");
            }
        }

        try {
            Connection db = DatabaseConnection.getConnection();

            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    Users us = new Users();
                    us.setId(rs.getInt("id"));
                    us.setNome(rs.getString("name"));
                    us.setUsername(rs.getString("username"));
                    us.setLevel(rs.getInt("level"));

                    list.add(us);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar usuarios");
        }
        return list;
    }

    public Users getById(long id) {

        String sql = "select id, name, username, passwd, level from project_nos.users where id = "+id;
        Connection db = DatabaseConnection.getConnection();
        Users user = new Users();

        try {
            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    user.setId(rs.getInt("id"));
                    user.setNome(rs.getString("name"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("passwd"));
                    user.setLevel(rs.getInt("level"));
                }

                if (user.getId() == 0){
                    user = null;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    public long updateById(Users us) {
        long result = 0;
        String sql = "update project_nos.users set name = ?, username = ?, passwd = ?, level = ? where id = ?";

        Connection db = DatabaseConnection.getConnection();

        try {
            if (db != null) {
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, us.getNome());
                stmt.setString(2, us.getUsername());
                stmt.setString(3, CypherPunk.newCypher(us.getPassword()));
                stmt.setInt(4, us.getLevel());
                stmt.setLong(5, us.getId());

                result = stmt.executeUpdate();

                if(result > 0){
                    result = us.getId();
                } else {
                    result = 0;
                }

                stmt.close();
            }
        } catch (SQLException | NoSuchAlgorithmException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
