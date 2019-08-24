/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.mpesa.tx;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

/**
 *
 * @author BADEMBA
 */
public class Auth {

    String consumerKey;
    String consumerSecret;
    String sandboxHost= "https://sandbox.safaricom.co.ke";
    String productionHost= "https://api.safaricom.co.ke";

//    public Auth(String app_key, String app_secret) {
//        consumerKey = app_key;
//        consumerSecret = app_secret;
//    }

    public String generateAccessToken() throws IOException {
        String app_key = "J1AzjYt6AWZTgPozOxuiJ6vtZj7K1rVx";
        String app_secret = "8NwRt4CtHGHTtxCg";
        String appKeySecret = app_key + ":" + app_secret;
        byte[] bytes = appKeySecret.getBytes("ISO-8859-1");
        String encoded = Base64.getEncoder().encodeToString(bytes);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(sandboxHost+"/oauth/v1/generate?grant_type=client_credentials")
                .get()
                .addHeader("authorization", "Basic " + encoded)
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        System.out.println("AcessToken Response:" + jsonObject.toString());
        return jsonObject.getString("access_token");
    }
    
    public String lnmSecurityCredential(String businessShortCode , String passKey,  String timeStamp) throws UnsupportedEncodingException{
    
      String securityCredential =  businessShortCode+passKey+timeStamp;
      byte[] bytes = securityCredential.getBytes("ISO-8859-1");
        String encoded = Base64.getEncoder().encodeToString(bytes);
        return encoded;
    }
}
