package com.greve.Database;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DatabaseConnection {


    public static String status = "STATUS ----> ...";

    public DatabaseConnection() {

    }

    //Método de conexão
    public static Connection getConnection(){

        Connection conn;

        try{
            //Carregando o JDBC Driver Padrão
            String driverName = "com.mysql.cj.jdbc.Driver";
            Class.forName(driverName);

            //Configuração de conexão com o Banco de Dados
            String serverName = "127.0.0.1:3306";
            String myDataBase = "project_nos";
            String url = "jdbc:mysql://"+ serverName +"/"+ myDataBase;
            String comp = "? allowPublicKeyRetrieval=true&useSSL=false&useTimezone=true&serverTimezone=UTC";
            String username = "hgreve";
            String password = "yBYhc6gx5aq4";
            conn = DriverManager.getConnection(url+comp,username,password);

            if (conn != null){
                status = ("STATUS ----> Conexao OK.");
            } else {
                status = ("STATUS ----> Erro na comunicaçao com o Banco de Dados");
                JOptionPane.showMessageDialog(null, status);
            }

            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    //Método que fecha sua conexão//
    public static void fecharConexao() {
        try {
            Objects.requireNonNull(DatabaseConnection.getConnection()).close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexao!");
        }
    }

    //Método para reiniciar conexão
    public static boolean restartConnection(){
        fecharConexao();
        return DatabaseConnection.getConnection() != null;
    }

    //Método retorna status da conexão
    public static String statusConnection(){
        return status;
    }

    public static void createTables() throws SQLException {

        ArrayList<String> lista = CreateTables.tables();
        Statement stmt = getConnection().createStatement();

        if(stmt != null) for (String i : lista) {
            stmt.executeUpdate(i);
        }

    }
}
