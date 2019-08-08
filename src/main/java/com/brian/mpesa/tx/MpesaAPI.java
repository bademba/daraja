/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.mpesa.tx;

import com.brian.db.DBConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;

/**
 *
 * @author BADEMBA
 */
public class MpesaAPI {

    @Test
    public void B2C() throws MalformedURLException, IOException {

        List<B2CUtils> list = new ArrayList<B2CUtils>();
 
        Connection con = null;
        try {
            con = DBConnector.getMysqlDBConnection();
            String b2cTransaction = " SELECT d.initiatorname, d.securitycredential ,d.commandid, d.amount, d.partya, d.partyb, d.remarks,d.queuetimeouturl , d.resulturl, d.occassion FROM  daraja_b2c d  where id =12345;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(b2cTransaction);
            while (rs.next()) {
                B2CUtils b2cUtils = new B2CUtils();
                b2cUtils.InitiatorName = rs.getString("initiatorname");
                b2cUtils.SecurityCredential = rs.getString("securitycredential");
                b2cUtils.CommandID = rs.getString("commandid");
                b2cUtils.Amount = rs.getInt("amount");
                b2cUtils.PartyA = rs.getString("partya");
                b2cUtils.PartyB = rs.getString("partyb");
                b2cUtils.Remarks = rs.getString("remarks");
                b2cUtils.QueueTimeOutURL = rs.getString("queuetimeouturl");
                b2cUtils.ResultURL = rs.getString("resulturl");
                b2cUtils.Occassion = rs.getString("occassion");
                list.add(b2cUtils);

                Gson gson = new Gson();
                String getB2cJson = null;
                getB2cJson = gson.toJson(b2cUtils);
                System.out.println("Request:" + getB2cJson);
                ObjectMapper mapper = new ObjectMapper();
 
                String accessToken = "CwYIlxG8e3A2hBVP0HDgT6j8K3hN";
                URL obj = new URL("https://sandbox.safaricom.co.ke/mpesa/b2c/v1/paymentrequest");
                HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
                postConnection.setRequestMethod("POST");
                postConnection.setRequestProperty("Authorization", "Bearer " + accessToken);
                postConnection.setRequestProperty("Content-Type", "application/json");
                postConnection.setDoOutput(true);
                OutputStream os = postConnection.getOutputStream();
                os.write(getB2cJson.getBytes());
                os.flush();
                os.close();

                int responseCode = postConnection.getResponseCode();
                System.out.println("ResponseCode:  " + responseCode);
                System.out.println("ResponseMessage: " + postConnection.getResponseMessage());
                System.out.println("ContentType:"+postConnection.getContentType());
                if (responseCode == 200) { //success
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            postConnection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    // print result
                    System.out.println("Response:"+response.toString());
                } else {
                    System.out.println("Transaction Failed");
                }
            }
        } catch (Exception e) {
        }
        //return list;
    }
}
