package com.greve.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DatabaseConnection {


    public static String status = "Connection Failed...";

    public DatabaseConnection() {

    }

    //Método de conexão
    public static Connection getConnection(){

        Connection conn;

        try{
            //Carregando o JDBC Driver Padrão
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);

            //COnfiguração de conexão com o Banco de Dados
            String serverName = "127.0.0.1:3306";
            String myDataBase = "project_nos";
            String url = "jdbc:mysql://"+ serverName +"/"+ myDataBase;
            String comp = "? useSSL=false&useTimezone=true&serverTimezone=UTC";
            String username = "hgreve";
            String password = "yBYhc6gx5aq4";
            conn = DriverManager.getConnection(url+comp,username,password);

            if (conn != null){
                status = ("STATUS ----> Conexão OK.");
            } else {
                status = ("STATUS ----> Erro na comunicação com o Banco de Dados");
            }

            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Não foi possível conectar ao Banco de Dados");
            return null;
        }
    }

    //Método que fecha sua conexão//
    public static boolean fecharConexao() {
        try {
            Objects.requireNonNull(DatabaseConnection.getConnection()).close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    //Método para reiniciar conexão
    public static Connection restartConnection(){
        fecharConexao();

        return DatabaseConnection.getConnection();
    }

    //Método retorna status da conexão
    public static String statusConnection(){

        return status;

    }
}
