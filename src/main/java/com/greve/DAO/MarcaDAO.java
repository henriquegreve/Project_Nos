package com.greve.DAO;

import com.greve.Database.DatabaseConnection;
import com.greve.Model.Donor;
import com.greve.Model.Marca;
import com.greve.Model.Segmento;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MarcaDAO {

    public MarcaDAO(){

    }

    public int createMarca(Marca marc){

        int result = 0;
        String sql = "insert into project_nos.marca (descricao, ativo) values (?,?);";

        try{
            Connection db = DatabaseConnection.getConnection();
            if(db != null){
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, marc.getDescricao());
                stmt.setBoolean(2, marc.isAtivo());

                result = stmt.executeUpdate();

                if(result > 0){
                    result = getIdMarca(db);
                }

            }
        } catch (SQLException throwables) {
            result = 0;
        }

        return result;
    }

    private int getIdMarca(Connection db) {

        int result = 0;
        String sql = "select id from marca where id = (select max(id) from marca)";
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

    public static List<Marca> getAll(String descricao, boolean ativo){

        int count = 0;
        List<Marca> list = new LinkedList<>();
        String sql = "select id, descricao, ativo, id_segmento from project_nos.marca";

        if(!descricao.equals("") || ativo){
            sql = sql.concat(" where ");

            if(!descricao.equals("")){
                sql = sql.concat(" descricao like '%"+descricao+"%'");
                count++;
            }

            if(ativo){

                if(count > 0){
                    sql = sql.concat(" and ");
                }

                sql = sql.concat(" ativo = "+ativo);
            }
        }

        try {
            Connection db = DatabaseConnection.getConnection();

            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    Marca marc = new Marca();
                    marc.setId(rs.getInt("id"));
                    marc.setDescricao(rs.getString("descricao"));
                    marc.setAtivo(rs.getBoolean("ativo"));

                    list.add(marc);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar Marcas");
        }
        return list;
    }

    public int deleteById(int id) {
        int result = 0;
        String sql = "delete from project_nos.marca where id = "+id;
        Connection db = DatabaseConnection.getConnection();

        try{
            if(db != null){
                Statement stmt = db.createStatement();
                result = stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            result = 0;
        }
        return result;
    }

    public Marca getById(long id) {

        String sql = "select id, descricao, ativo from project_nos.marca where id = "+id;
        Connection db = DatabaseConnection.getConnection();
        Marca marc = new Marca();

        try {
            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    marc.setId(rs.getInt("id"));
                    marc.setDescricao(rs.getString("descricao"));
                    marc.setAtivo(rs.getBoolean("ativo"));
                }

                if (marc.getId() == 0){
                    marc = null;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return marc;
    }

    public long updateById(Marca marc) {
        long result = 0;
        String sql = "update project_nos.marca set descricao = ?, ativo = ? where id = ?";

        Connection db = DatabaseConnection.getConnection();

        try {
            if (db != null) {
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, marc.getDescricao());
                stmt.setBoolean(2, marc.isAtivo());
                stmt.setLong(3, marc.getId());

                result = stmt.executeUpdate();

                if(result > 0){
                    result = marc.getId();
                } else {
                    result = 0;
                }

                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

}
