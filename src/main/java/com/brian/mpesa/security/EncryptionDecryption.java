/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.mpesa.security;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author BADEMBA
 */
public class EncryptionDecryption {

    public static PublicKey getPublicKey(String filename) throws Exception {

        byte[] keyBytes = Files.readAllBytes(Paths.get("cert.key"));

        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        System.out.println("KEY:"+ kf.generatePublic(spec));
        return kf.generatePublic(spec);
    }

//    public static byte[] encrypt() throws Exception {
//        //Get Cipher Instance RSA With ECB Mode and OAEPWITHSHA-512ANDMGF1PADDING Padding
//        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
//        
//        String plainText = "testapi";
//        File keyFile = new File("cert.cer");
//        FileInputStream fis = new FileInputStream(keyFile);
//        DataInputStream dis = new DataInputStream(fis);
//        //Initialize Cipher for ENCRYPT_MODE
//        byte[] keyBytes1 = new byte[(int) keyFile.length()];
//        dis.readFully(keyBytes1);
//        dis.close();
//
//        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(fis.toString()));
//
//        //Perform EncryptionDecryption
//        byte[] cipherText = cipher.doFinal(plainText.getBytes());
//        System.out.println("CIPHER TEXT:" + cipherText);
//
//        return cipherText;
//    }
//
//    public static String decrypt(byte[] cipherTextArray, PrivateKey privateKey) throws Exception {
//        //Get Cipher Instance RSA With ECB Mode and OAEPWITHSHA-512ANDMGF1PADDING Padding
//        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
//
//        //Initialize Cipher for DECRYPT_MODE
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//
//        //Perform Decryption
//        byte[] decryptedTextArray = cipher.doFinal(cipherTextArray);
//
//        return new String(decryptedTextArray);
//    }

    
    
    public static byte[] encrypt2(String data, String publicKey) throws BadPaddingException,   InvalidKeyException,   NoSuchAlgorithmException,    NoSuchPaddingException,    Exception {
	
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        System.out.println(cipher.doFinal(data.getBytes()));
	return cipher.doFinal(data.getBytes());
}
}
