package com.greve.Database;

import java.util.ArrayList;

public class CreateTables {

    public static ArrayList<String> tables(){

        ArrayList<String> tables = new ArrayList<>();

        tables.add("CREATE TABLE IF NOT EXISTS USERS ("
                + "ID INTEGER NOT NULL AUTO_INCREMENT, "
                + "USERNAME VARCHAR(30) NOT NULL, "
                + "PASSWD VARCHAR(16) NOT NULL, "
                + "LEVEL INT NOT NULL, "
                + "PRIMARY KEY (ID) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS DONATION_RECEIVED ("
                + "ID INTEGER NOT NULL AUTO_INCREMENT, "
                + "ID_DONOR INTEGER NOT NULL, "
                + "DATE DATE NOT NULL, "
                + "PRIMARY KEY(ID) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS DONATION_MADE ("
                + "ID INTEGER NOT NULL AUTO_INCREMENT, "
                + "ID_FAMILY INTEGER NOT NULL, "
                + "ID_CAMPAIGN INTEGER NOT NULL, "
                + "OBSERVATION VARCHAR(150) NOT NULL, "
                + "PRIMARY KEY (ID) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS FAMILY_CHILDS ("
                + "ID INTEGER NOT NULL AUTO_INCREMENT, "
                + "ID_FAMILY INTEGER NOT NULL, "
                + "NOME VARCHAR(100) NOT NULL, "
                + "IDADE INTEGER NOT NULL, "
                + "PRIMARY KEY(ID) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS ADDRESS ("
                + "ID INTEGER NOT NULL AUTO_INCREMENT, "
                + "RUA VARCHAR(100) NOT NULL, "
                + "CEP VARCHAR(10) NOT NULL, "
                + "BAIRRO VARCHAR(100) NOT NULL, "
                + "TIPO INTEGER NOT NULL, "
                + "COMPLEMENTO VARCHAR(30) NOT NULL, "
                + "BLOCO VARCHAR(8) NOT NULL, "
                + "NUMERO VARCHAR(8) NOT NULL, "
                + "PRIMARY KEY(ID) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS DONATION_ITEMS ("
                + "ID INTEGER NOT NULL AUTO_INCREMENT, "
                + "ID_ITEM INTEGER NOT NULL, "
                + "ID_DONATION INTEGER NOT NULL, "
                + "QUANTITY FLOAT NOT NULL, "
                + "TYPE INTEGER NOT NULL, "
                + "PRIMARY KEY (ID) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS DONOR ("
                + "ID INTEGER NOT NULL, "
                + "FIRST_NAME VARCHAR(100) NOT NULL, "
                + "LAST_NAME VARCHAR(50) NOT NULL, "
                + "CPF_CNPJ VARCHAR(25) NOT NULL, "
                + "ID_ADDRESS INTEGER NOT NULL, "
                + "EMAIL VARCHAR(120), "
                + "CONTACT VARCHAR(30), "
                + "TYPE INTEGER NOT NULL, "
                + "DATE DATE NOT NULL, "
                + "PRIMARY KEY(ID) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS FAMILY( "
                + "ID INTEGER NOT NULL, "
                + "FIRST_NAME VARCHAR(100) NOT NULL, "
                + "LAST_NAME VARCHAR(50) NOT NULL, "
                + "DOCUMENT VARCHAR(20) NOT NULL, "
                + "IDADE INTEGER NOT NULL, "
                + "ESTADO_CIVIL VARCHAR(20) NOT NULL, "
                + "QUANTITY_RESIDENTS INTEGER NOT NULL, "
                + "ID_ADDRESS INTEGER NOT NULL, "
                + "PROFISSION VARCHAR(50) NOT NULL, "
                + "RENDA_MENSAL FLOAT NOT NULL, "
                + "QUANTITY_CHILDS INT NOT NULL, "
                + "PRIMARY KEY(ID) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS ITEMS ( "
                + "ID INTEGER NOT NULL, "
                + "DESCRICAO VARCHAR(100) NOT NULL, "
                + "SEGMENTO VARCHAR(50) NOT NULL, "
                + "MARCA VARCHAR(50), "
                + "PRIMARY KEY(ID) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS VERSION( "
                + "ID INTEGER NOT NULL AUTO_INCREMENT, "
                + "DB_VERSION VARCHAR(10) NOT NULL, "
                + "SOFTWARE_VERSION VARCHAR(15) NOT NULL, "
                + "PRIMARY KEY(ID) );"
        );

        return tables;
    }
}
