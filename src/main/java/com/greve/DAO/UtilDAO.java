package com.greve.DAO;

import com.greve.Database.DatabaseConnection;
import com.greve.Util.Version;

import java.sql.*;

public class UtilDAO {

    public UtilDAO(){

    }

    public static int checkVersion(Connection db){

        int result = 0;
        String sql = "select db_version, software_version from version;";
        try {
            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                result++;
            }
        }catch (SQLException e) {
            return result;
        }

        return result;
    }

    public static void preencheInfo() {

        String sql = "insert into project_nos.version (db_version, software_version) values (?, ?);";

        try {
            Connection db = DatabaseConnection.getConnection();

            if (db != null) {
                if(!(checkVersion(db) > 0)) {
                    PreparedStatement stmt = db.prepareStatement(sql);

                    stmt.setString(1, Version.DB_VERSION);
                    stmt.setString(2, Version.SOFTWARE_VERSION);

                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Não foi possivel cadastrar versão!");
        }
    }

}
