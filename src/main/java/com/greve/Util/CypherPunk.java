package com.greve.Util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CypherPunk {

    public static String newCypher(String passwd) throws NoSuchAlgorithmException {
        //Hash de passwd
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = algorithm.digest(passwd.getBytes(StandardCharsets.UTF_8));

        //Transforma para hexadecimal
        StringBuilder hexString = new StringBuilder();

        for(byte b : messageDigest){
            hexString.append(String.format("%02X", 0xFF & b));
        }

        return hexString.toString();
    }

}
