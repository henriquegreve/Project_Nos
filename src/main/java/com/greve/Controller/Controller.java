package com.greve.Controller;

import com.greve.DAO.*;
import com.greve.Database.DatabaseConnection;
import com.greve.Model.*;

import java.sql.SQLException;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Controller {

    public static int logged;

    public static void initConnection(){
        DatabaseConnection.getConnection();
    }

    public static int checkLogin(String username, String password){
        UsersDAO dao = new UsersDAO();
        return dao.checkLogin(username, password);
    }

    public static void checkStatusConnection(){
        JOptionPane.showMessageDialog(null,DatabaseConnection.statusConnection());
    }

    public static void createTables(){
        try{
            DatabaseConnection.createTables();
        } catch (SQLException e) {
            System.out.println("Tabelas não criadas!");
        }
    }

    public static void createUserAdmin() {
        UsersDAO dao = new UsersDAO();
        dao.createUserAdmin();
    }

    public static int createUser(String name, String username, String passwd, int level) {
        UsersDAO dao = new UsersDAO();
        Users user = new Users();

        user.setNome(name);
        user.setUsername(username);
        user.setPassword(passwd);
        user.setLevel(level);

        return dao.createUser(user);
    }

    public static void initSystem(){
        initConnection();
        createTables();
        preencheInfo();
        createUserAdmin();
    }

    private static void preencheInfo() {
        UtilDAO.preencheInfo();
    }

    public static boolean restartConnection(){
        return DatabaseConnection.restartConnection();
    }

    public static int createAddress(String rua, String bairro, String numero, String cep, String complemento, int tipo_end){

        AddressDAO addao = new AddressDAO();
        Address ad = new Address();

        ad.setRua(rua);
        ad.setBairro(bairro);
        ad.setNumero(numero);
        ad.setCep(cep);
        ad.setComplemento(complemento);
        ad.setType(tipo_end);
        return addao.createAddress(ad);
    }

    public static int createDonor(String nome, String sobrenome, String cpf_Cnpj, String email, String contato, int tipo_cad, int idAddress){
        DonorDAO dao = new DonorDAO();
        Donor dn = new Donor();

        dn.setIdAddress(idAddress);
        dn.setfirstName(nome);
        dn.setLastName(sobrenome);
        dn.setCpfCnpj(cpf_Cnpj);
        dn.setEmail(email);
        dn.setContact(contato);
        dn.setType(tipo_cad);

        return dao.createDonor(dn);
    }

    public static void preencheTableUser(JTable table, String username, int level) {

        DefaultTableModel data = new DefaultTableModel();
        data.setNumRows(0);
        data.addColumn("ID");
        data.addColumn("Nome");
        data.addColumn("Username");
        data.addColumn("Level");

        for (Users x : UsersDAO.getAll(username,level)) {
            data.addRow(new Object[]{x.getId(), x.getNome(), x.getUsername(), x.getLevel()});
        }

        table.setModel(data);
    }

    public static void preencheTableDonor(JTable table, String nome, String sobrenome, String cpf_cnpj){
        DefaultTableModel data = new DefaultTableModel();
        data.setNumRows(0);
        data.addColumn("ID");
        data.addColumn("Nome");
        data.addColumn("Sobrenome");
        data.addColumn("CPF/CNPJ");
        data.addColumn("E-mail");
        data.addColumn("Telefone");

        for (Donor x : DonorDAO.getAll(nome,sobrenome, cpf_cnpj)) {
            data.addRow(new Object[]{x.getId(), x.getfirstName(), x.getLastName(),
                    x.getCpfCnpj(), x.getEmail(), x.getContact()});
        }

        table.setModel(data);
    }

    public static Users getUserById(long id){
        UsersDAO dao = new UsersDAO();
        return dao.getById(id);
    }

    public static Donor getDonorById(long id){

        DonorDAO dao = new DonorDAO();
        return dao.getById(id);

    }

    public static Address getAddressById(long idAddress) {

        AddressDAO dao = new AddressDAO();
        return dao.getById(idAddress);

    }

    public static boolean deleteDonorById(int id) {
        int result;
        Donor don = getDonorById(id);
        int idAddress = don.getIdAddress();

        DonorDAO dao = new DonorDAO();
        AddressDAO adDao = new AddressDAO();
        result = dao.deleteById(id);
        result = result + adDao.deleteById(idAddress);

        return result > 1;
    }

    public static long updateUserById(long id, String nome, String username, String passwd, int level){

        long result = 0;
        UsersDAO dao = new UsersDAO();

        Users us = dao.getById(id);

        if(us != null){
            us.setNome(nome);
            us.setUsername(username);
            us.setPassword(passwd);
            us.setLevel(level);

            result = dao.updateById(us);
        }

        if(result > 0) {
            return Objects.requireNonNull(us).getId();
        } else {
            return 0;
        }

    }

    public static int updateDonorById(long id, String nome, String sobrenome, String cpf_Cnpj, String email, String contato, int tipo_cad){

        int result = 0;
        DonorDAO dao = new DonorDAO();

        Donor dn = dao.getById(id);

        if(dn != null){
            dn.setfirstName(nome);
            dn.setLastName(sobrenome);
            dn.setCpfCnpj(cpf_Cnpj);
            dn.setEmail(email);
            dn.setContact(contato);
            dn.setType(tipo_cad);

            result = dao.updateById(dn);
        }

        if(result > 0) {
            return Objects.requireNonNull(dn).getIdAddress();
        } else {
            return 0;
        }
    }

    public static boolean updateAddressById(int id, String rua, String bairro, String numero, String cep, String complemento, int tipo_end){

        int result = 0;
        AddressDAO dao = new AddressDAO();

        Address ad = dao.getById(id);

        if(ad != null){
            ad.setRua(rua);
            ad.setBairro(bairro);
            ad.setNumero(numero);
            ad.setCep(cep);
            ad.setComplemento(complemento);
            ad.setType(tipo_end);

            result = dao.updateById(ad);
        }

        return result > 0;
    }

    public static long createItem(String descricao, boolean ativo, int idSegmento, int idMarca) {
        ItemsDAO dao = new ItemsDAO();
        Items it = new Items();

        it.setDescricao(descricao);
        it.setAtivo(ativo);
        it.setIdSegmento(idSegmento);
        it.setIdMarca(idMarca);

        return dao.createItem(it);
    }

    public static long updateItemById(long id, String descricao, boolean ativo, int idSegmento, int idMarca) {
        long result = 0;
        ItemsDAO dao = new ItemsDAO();

        Items it = dao.getById(id);

        if (it != null) {
            it.setDescricao(descricao);
            it.setAtivo(ativo);
            it.setIdSegmento(idSegmento);
            it.setIdMarca(idMarca);

            result = dao.updateById(it);
        }

        if (result > 0) {
            return Objects.requireNonNull(it).getId();
        } else {
            return 0;
        }
    }

    public static Items getItemById(long id) {
        ItemsDAO dao = new ItemsDAO();
        return dao.getById(id);
    }

    public static void preencheTableItem(JTable jTable1, String descricao, int idSegmento, int idMarca) {

        DefaultTableModel data = new DefaultTableModel();
        data.setNumRows(0);
        data.addColumn("ID");
        data.addColumn("Descricao");
        data.addColumn("Ativo");
        data.addColumn("ID_Segmento");
        data.addColumn("ID_Marca");

        for (Items x : ItemsDAO.getAll(descricao,idSegmento,idMarca)) {
            data.addRow(new Object[]{x.getId(), x.getDescricao(), x.isAtivo(), x.getIdSegmento(), x.getIdMarca()});
        }

        jTable1.setModel(data);
    }

    public static int createSegmento(String descricao, boolean ativo){
        SegmentoDAO dao = new SegmentoDAO();
        Segmento seg = new Segmento();

        seg.setDescricao(descricao);
        seg.setAtivo(ativo);

        return dao.createSegmento(seg);
    }

    public static void preencheTableSegmento(JTable table, String descricao, boolean ativo) {

        DefaultTableModel data = new DefaultTableModel();
        data.setNumRows(0);
        data.addColumn("ID");
        data.addColumn("Descricao");
        data.addColumn("Ativo");

        for (Segmento x : SegmentoDAO.getAll(descricao,ativo)) {
            data.addRow(new Object[]{x.getId(), x.getDescricao(), x.isAtivo()});
        }

        table.setModel(data);
    }

    public static long updateSegmentoById(long id, String descricao, boolean ativo){

        long result = 0;
        SegmentoDAO dao = new SegmentoDAO();

        Segmento seg = dao.getById(id);

        if(seg != null){
            seg.setDescricao(descricao);
            seg.setAtivo(ativo);

            result = dao.updateById(seg);
        }

        if(result > 0) {
            return Objects.requireNonNull(seg).getId();
        } else {
            return 0;
        }
    }

    public static Segmento getSegmentoById(long id){
        SegmentoDAO dao = new SegmentoDAO();
        return dao.getById(id);
    }

    public static int createMarca(String descricao, boolean ativo){
        MarcaDAO dao = new MarcaDAO();
        Marca marc = new Marca();

        marc.setDescricao(descricao);
        marc.setAtivo(ativo);

        return dao.createMarca(marc);
    }

    public static void preencheTableMarca(JTable table, String descricao, boolean ativo) {

        DefaultTableModel data = new DefaultTableModel();
        data.setNumRows(0);
        data.addColumn("ID");
        data.addColumn("Descricao");
        data.addColumn("Ativo");

        for (Marca x : MarcaDAO.getAll(descricao,ativo)) {
            data.addRow(new Object[]{x.getId(), x.getDescricao(), x.isAtivo()});
        }

        table.setModel(data);
    }

    public static long updateMarcaById(long id, String descricao, boolean ativo){

        long result = 0;
        MarcaDAO dao = new MarcaDAO();

        Marca marc = dao.getById(id);

        if(marc != null){
            marc.setDescricao(descricao);
            marc.setAtivo(ativo);

            result = dao.updateById(marc);
        }

        if(result > 0) {
            return Objects.requireNonNull(marc).getId();
        } else {
            return 0;
        }
    }

    public static Marca getMarcaById(long id){
        MarcaDAO dao = new MarcaDAO();
        return dao.getById(id);
    }

    public static boolean deleteMarcaById(int id) {
        int result;
        Marca marc = getMarcaById(id);

        MarcaDAO dao = new MarcaDAO();
        result = dao.deleteById(id);

        return result > 0;
    }

    public static boolean deleteSegmentoById(int id) {
        int result;
        Segmento seg = getSegmentoById(id);

        SegmentoDAO dao = new SegmentoDAO();
        result = dao.deleteById(id);

        return result > 0;
    }
}
