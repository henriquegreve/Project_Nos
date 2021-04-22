package com.greve.DAO;

import com.greve.Database.DatabaseConnection;
import com.greve.Model.Segmento;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SegmentoDAO {

    public SegmentoDAO(){

    }

    public int createSegmento(Segmento seg){

        int result = 0;
        String sql = "insert into project_nos.segmento (descricao, ativo) values (?,?);";

        try{
            Connection db = DatabaseConnection.getConnection();
            if(db != null){
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, seg.getDescricao());
                stmt.setBoolean(2, seg.isAtivo());

                result = stmt.executeUpdate();

                if(result > 0){
                    result = getIdSegmento(db);
                }

            }
        } catch (SQLException throwables) {
            result = 0;
        }

        return result;
    }

    private int getIdSegmento(Connection db) {

        int result = 0;
        String sql = "select id from segmento where id = (select max(id) from segmento)";
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

    public static List<Segmento> getAll(String descricao, boolean ativo){

        int count = 0;
        List<Segmento> list = new LinkedList<>();
        String sql = "select id, descricao, ativo from project_nos.segmento";

        if(!descricao.equals("") || ativo){
            sql = sql.concat(" where ");

            if(!descricao.equals("")){
                sql = sql.concat(" descricao = '"+descricao+"'");
                count++;
            }

            if(count > 0){
                sql = sql.concat(" and ");
            }

            if(ativo){
                sql = sql.concat(" ativo = "+ativo+"'");
            }
        }

        try {
            Connection db = DatabaseConnection.getConnection();

            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    Segmento seg = new Segmento();
                    seg.setId(rs.getInt("id"));
                    seg.setDescricao(rs.getString("descricao"));
                    seg.setAtivo(rs.getBoolean("ativo"));

                    list.add(seg);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar Segmentos");
        }
        return list;
    }

    public int deleteById(int id) {
        int result = 0;
        String sql = "delete from project_nos.segmento where id = "+id;
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

    public Segmento getById(long id) {

        String sql = "select id, descricao, ativo from project_nos.segmento where id = "+id;
        Connection db = DatabaseConnection.getConnection();
        Segmento seg = new Segmento();

        try {
            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    seg.setId(rs.getInt("id"));
                    seg.setDescricao(rs.getString("descricao"));
                    seg.setAtivo(rs.getBoolean("ativo"));
                }

                if (seg.getId() == 0){
                    seg = null;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return seg;
    }

    public long updateById(Segmento seg) {
        long result = 0;
        String sql = "update project_nos.segmento set descricao = ?, ativo = ? where id = ?";

        Connection db = DatabaseConnection.getConnection();

        try {
            if (db != null) {
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, seg.getDescricao());
                stmt.setBoolean(2, seg.isAtivo());
                stmt.setLong(3, seg.getId());

                result = stmt.executeUpdate();

                if(result > 0){
                    result = seg.getId();
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
