package com.greve.DAO;

import com.greve.Database.DatabaseConnection;
import com.greve.Model.Donor;
import com.greve.Model.Users;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DonorDAO {

    public int createDonor(Donor dn) {

        int resultado = 0;
        String sql = "insert into project_nos.donor (first_name, last_name, cpf_cnpj, id_address, email, contact, type) values (?, ?, ?, ?, ?, ?, ?)";
        try{
            Connection db = DatabaseConnection.getConnection();
            if(db != null){
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, dn.getfirstName());
                stmt.setString(2, dn.getLastName());
                stmt.setString(3, dn.getCpfCnpj());
                stmt.setInt(4, dn.getIdAddress());
                stmt.setString(5, dn.getEmail());
                stmt.setString(6, dn.getContact());
                stmt.setInt(7, dn.getType());

                resultado = stmt.executeUpdate();

                if(resultado > 0){
                    resultado = getIdDonor(db);
                }
            }
        } catch (SQLException e) {
            resultado = 0;
        }
        return resultado;
    }

    private int getIdDonor(Connection db) {
        int result = 0;
        String sql = "select id from donor where id = (select max(id) from donor)";

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

    public static List<Donor> getAll(String nome, String sobrenome, String cpf_cnpj){

        int count = 0;
        List<Donor> list = new LinkedList<>();
        String sql = "select id, first_name, last_name, cpf_cnpj, email, contact from project_nos.donor";

        if(!nome.equals("") || !sobrenome.equals("") || !cpf_cnpj.equals("")){
            sql = sql.concat(" where ");

            if(!nome.equals("")){
                sql = sql.concat(" first_name like '%"+nome+"%'");
                count++;
            }

            if(!sobrenome.equals("")){

                if(count > 0){
                    sql = sql.concat(" and ");
                }

                sql = sql.concat(" last_name like '%"+sobrenome+"%'");
                count++;
            }

            if(!cpf_cnpj.equals("")){

                if(count > 0){
                    sql = sql.concat(" and ");
                }

                sql = sql.concat(" cpf_cnpj = '"+cpf_cnpj+"'");
            }
        }

        try {
            Connection db = DatabaseConnection.getConnection();

            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    Donor donor = new Donor();
                    donor.setId(rs.getInt("id"));
                    donor.setfirstName(rs.getString("first_name"));
                    donor.setLastName(rs.getString("last_name"));
                    donor.setCpfCnpj(rs.getString("cpf_cnpj"));
                    donor.setEmail(rs.getString("email"));
                    donor.setContact(rs.getString("contact"));

                    list.add(donor);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar doadores");
        }
        return list;
    }

    public Donor getById(long id) {

        String sql = "select id, first_name, last_name, cpf_cnpj, id_address, email, contact, type "
                +"from project_nos.donor where id = "+id;

        Connection db = DatabaseConnection.getConnection();
        Donor donor = new Donor();

        try {
            if (db != null) {
                Statement stmt = db.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    donor.setId(rs.getInt("id"));
                    donor.setfirstName(rs.getString("first_name"));
                    donor.setLastName(rs.getString("last_name"));
                    donor.setCpfCnpj(rs.getString("cpf_cnpj"));
                    donor.setIdAddress(rs.getInt("id_address"));
                    donor.setEmail(rs.getString("email"));
                    donor.setContact(rs.getString("contact"));
                    donor.setType(rs.getInt("type"));
                }

                if(donor.getId() == 0){
                    donor = null;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return donor;
    }

    public int deleteById(int id) {
        int result = 0;
        String sql = "delete from project_nos.donor where id = "+id;
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

    public int updateById(Donor dn){

        int result = 0;
        String sql = "update project_nos.donor set first_name = ?, last_name = ?, cpf_cnpj = ?, email = ?, contact = ?, type = ? where id = ?";

        Connection db = DatabaseConnection.getConnection();

        try {
            if (db != null) {
                PreparedStatement stmt = db.prepareStatement(sql);

                stmt.setString(1, dn.getfirstName());
                stmt.setString(2, dn.getLastName());
                stmt.setString(3, dn.getCpfCnpj());
                stmt.setString(4, dn.getEmail());
                stmt.setString(5, dn.getContact());
                stmt.setInt(6, dn.getType());
                stmt.setLong(7, dn.getId());

                result = stmt.executeUpdate();

                if(result > 0){
                    result = dn.getIdAddress();
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
