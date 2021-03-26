package com.greve.Database;

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
            String comp = "? useSSL=false&useTimezone=true&serverTimezone=UTC";
            String username = "hgreve";
            String password = "yBYhc6gx5aq4";
            conn = DriverManager.getConnection(url+comp,username,password);

            if (conn != null){
                status = ("STATUS ----> Conexão OK.");
            } else {
                status = ("STATUS ----> Erro na comunicação com o Banco de Dados");
                System.out.println(status);
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

    public static void createTables() throws SQLException {

        ArrayList<String> lista = CreateTables.tables();

        for (String i : lista){
            Statement stmt = Objects.requireNonNull(getConnection()).createStatement();
            stmt.executeUpdate(i);
        }

    }

    public static int verifyTables(){

        int i = 0;
        String sql = "USE project_nos; SHOW TABLES; SELECT FOUND_ROWS();";

        try {
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                i = rs.getInt("FOUND_ROWS()");
            }

            return i;

        } catch (SQLException e) {
            return i;
        }
    }
}
