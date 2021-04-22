package com.greve.DAO;

import com.greve.Database.DatabaseConnection;
import com.greve.Model.Items;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ItemsDAO {

    public ItemsDAO(){

    }

    public int createItem(Items it){
        int result = 0;
        String sql = "insert into project_nos.items (descricao, ativo, id_segmento, id_marca) values (?, ?, ?, ?);";
        try {
            Connection db = DatabaseConnection.getConnection();
            if (db != null) {
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, it.getDescricao());
                stmt.setBoolean(2, it.isAtivo());
                stmt.setInt(3, it.getIdSegmento());
                stmt.setInt(4, it.getIdMarca());

                result = stmt.executeUpdate();

                if(result > 0){
                    result = getIdItem(db);
                }
            }
        } catch (SQLException e) {
            result = 0;
        }

        return result;
    }

    private int getIdItem(Connection db) {

        int result = 0;
        String sql = "select id from items where id = (select max(id) from items)";
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

    public Items getById(long id) {

        String sql = "select id, descricao, ativo, id_segmento, id_marca where id = "+id;
        Connection db = DatabaseConnection.getConnection();
        Items it = new Items();

        try {
            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    it.setId(rs.getInt("id"));
                    it.setDescricao(rs.getString("descricao"));
                    it.setAtivo(rs.getBoolean("ativo"));
                    it.setIdSegmento(rs.getInt("id_segmento"));
                    it.setIdMarca(rs.getInt("id_marca"));
                }

                if (it.getId() == 0){
                    it = null;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return it;
    }

    public long updateById(Items it) {
        long result = 0;
        String sql = "update project_nos.items set descricao = ?, ativo = ?, id_segmento = ?, id_marca = ? where id = ?";

        Connection db = DatabaseConnection.getConnection();

        try {
            if (db != null) {
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, it.getDescricao());
                stmt.setBoolean(2, it.isAtivo());
                stmt.setInt(3, it.getIdSegmento());
                stmt.setInt(4, it.getIdMarca());
                stmt.setLong(5, it.getId());

                result = stmt.executeUpdate();

                if(result > 0){
                    result = it.getId();
                } else {
                    result = 0;
                }

                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static List<Items> getAll(String descricao, int idSegmento, int idMarca){

        int count = 0;
        List<Items> list = new LinkedList<>();
        String sql = "select id, descricao, ativo, id_segmento, id_marca from project_nos.items";

        if(!descricao.equals("") || idSegmento > 0 || idMarca > 0){
            sql = sql.concat(" where ");

            if(!descricao.equals("")){
                sql = sql.concat(" descricao = '"+descricao+"'");
                count++;
            }

            if(count > 0){
                sql = sql.concat(" and ");
            }

            if(idSegmento > 0){
                sql = sql.concat(" id_segmento = "+idSegmento);
                count++;
            }

            if (count > 1){
                sql = sql.concat(" and ");
            }

            if(idMarca > 0){
                sql = sql.concat(" id_marca = "+idMarca);
            }

        }

        try {
            Connection db = DatabaseConnection.getConnection();

            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    Items it = new Items();
                    it.setId(rs.getInt("id"));
                    it.setDescricao(rs.getString("descricao"));
                    it.setAtivo(rs.getBoolean("ativo"));
                    it.setIdSegmento(rs.getInt("id_segmento"));
                    it.setIdMarca(rs.getInt("id_marca"));

                    list.add(it);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar usuarios");
        }
        return list;
    }
}
