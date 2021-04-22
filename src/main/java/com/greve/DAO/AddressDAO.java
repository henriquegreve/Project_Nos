package com.greve.DAO;

import com.greve.Database.DatabaseConnection;
import com.greve.Model.Address;
import com.greve.Model.Donor;

import javax.swing.*;
import java.sql.*;

public class AddressDAO {

    public int createAddress(Address ad){

        int resultado = 0;
        String sql = "insert into project_nos.address (rua, cep, bairro, tipo, complemento, numero) values (?, ?, ?, ?, ?, ?)";
        try {
            Connection db = DatabaseConnection.getConnection();
            if (db != null) {
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, ad.getRua());
                stmt.setString(2, ad.getCep());
                stmt.setString(3, ad.getBairro());
                stmt.setInt(4, ad.getType());
                stmt.setString(5, ad.getComplemento());
                stmt.setString(6, ad.getNumero());

                resultado = stmt.executeUpdate();

                if (resultado > 0) {
                    resultado = getIdAddress();
                }
            }
        } catch(SQLException e) {
            resultado = 0;
        }
            return resultado;
    }

    public int getIdAddress(){
        int id = 0;
        String sql = "select id from address where id = (select max(id) from address)";
        try {
            Connection db = DatabaseConnection.getConnection();
            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()){
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar ID do Endereço!");
        }

        return id;
    }

    public Address getById(long id) {

        String sql = "select id, rua, numero, bairro, cep, tipo, complemento from project_nos.address where id = "+id;
        Connection db = DatabaseConnection.getConnection();
        Address ad = new Address();

        try{
            if(db != null){
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    ad.setId(rs.getInt("id"));
                    ad.setRua(rs.getString("rua"));
                    ad.setNumero(rs.getString("numero"));
                    ad.setBairro(rs.getString("bairro"));
                    ad.setCep(rs.getString("cep"));
                    ad.setType(rs.getInt("tipo"));
                    ad.setComplemento(rs.getString("complemento"));
                }

                if(ad.getId() == 0){
                    ad = null;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ad;
    }

    public int deleteById(int id) {
        int result = 0;
        String sql = "delete from project_nos.address where id = "+id;
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

    public int updateById(Address ad){

        int result = 0;
        String sql = "update project_nos.address set rua = ?, numero = ?, bairro = ?, cep = ?, tipo = ?, complemento = ? where id = ?";

        Connection db = DatabaseConnection.getConnection();

        try {
            if (db != null) {
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, ad.getRua());
                stmt.setString(2, ad.getNumero());
                stmt.setString(3, ad.getBairro());
                stmt.setString(4, ad.getCep());
                stmt.setInt(5, ad.getType());
                stmt.setString(6, ad.getComplemento());
                stmt.setLong(7, ad.getId());

                result = stmt.executeUpdate();

                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
