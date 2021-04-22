package com.greve.Database;

import java.util.ArrayList;

public class CreateTables {

    public static ArrayList<String> tables(){

        ArrayList<String> tables = new ArrayList<>();

        tables.add("CREATE TABLE IF NOT EXISTS USERS ("
                + "ID INTEGER NOT NULL AUTO_INCREMENT, "
                + "NAME VARCHAR(100) NOT NULL, "
                + "USERNAME VARCHAR(30) NOT NULL, "
                + "PASSWD VARCHAR(200) NOT NULL, "
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
                + "NUMERO VARCHAR(8) NOT NULL, "
                + "BAIRRO VARCHAR(100) NOT NULL, "
                + "CEP VARCHAR(10) NOT NULL, "
                + "TIPO VARCHAR(15) NOT NULL, " //0 - Casa || 1 - Apartment
                + "COMPLEMENTO VARCHAR(30), "
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
                + "ID INTEGER NOT NULL AUTO_INCREMENT, "
                + "FIRST_NAME VARCHAR(100) NOT NULL, "
                + "LAST_NAME VARCHAR(50) NOT NULL, "
                + "CPF_CNPJ VARCHAR(25) NOT NULL, "
                + "ID_ADDRESS INTEGER NOT NULL, "
                + "EMAIL VARCHAR(120), "
                + "CONTACT VARCHAR(30), "
                + "TYPE VARCHAR(10) NOT NULL, "
                + "DATE_REG DATE, "
                + "PRIMARY KEY(ID) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS FAMILY( "
                + "ID INTEGER NOT NULL AUTO_INCREMENT, "
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
                + "ATIVO BOOLEAN NOT NULL, "
                + "ID_SEGMENTO INTEGER NOT NULL, "
                + "ID_MARCA INTEGER NOT NULL, "
                + "PRIMARY KEY(ID) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS VERSION( "
                + "DB_VERSION VARCHAR(10) NOT NULL, "
                + "SOFTWARE_VERSION VARCHAR(15) NOT NULL, "
                + "PRIMARY KEY(DB_VERSION) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS SEGMENTO( "
                + "ID INTEGER NOT NULL AUTO_INCREMENT, "
                + "DESCRICAO VARCHAR(100) NOT NULL, "
                + "ATIVO BOOLEAN NOT NULL, "
                + "PRIMARY KEY(ID) );"
        );

        tables.add("CREATE TABLE IF NOT EXISTS MARCA( "
                + "ID INTEGER NOT NULL AUTO_INCREMENT, "
                + "DESCRICAO VARCHAR(100) NOT NULL, "
                + "ATIVO BOOLEAN NOT NULL, "
                + "ID_SEGMENTO INTEGER NOT NULL, "
                + "PRIMARY KEY(ID) );"
        );

        return tables;
    }
}
