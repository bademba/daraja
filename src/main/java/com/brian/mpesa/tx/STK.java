/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.mpesa.tx;

import com.brian.db.DBConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 *
 * @author BADEMBA
 */
public class STK {

    public void lipaNaMpesaOnline() {
        Connection dbConnection = null;
        CloseableHttpClient httpclient = null;
        List<STKPushUtils> stkList = null;
        try {
            dbConnection = DBConnector.getMysqlDBConnection();
            stkList = new ArrayList<STKPushUtils>();
            String lnmQuery = " SELECT d.businessshortcode, d.securitycredential,d.commandid , d.amount, d.msisdn,d.resulturl, d.occassion,d.remarks FROM  daraja d  where transactiontype =\"lnmo\";";
            Statement statement = dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(lnmQuery);
            while (resultSet.next()) {
                STKPushUtils stkPushUtils = new STKPushUtils();
                ObjectMapper mapper = new ObjectMapper();
                Auth auth = new Auth();

                stkPushUtils.BusinessShortCode = resultSet.getString("businessshortcode");
                stkPushUtils.Password = auth.lnmSecurityCredential(resultSet.getString("businessshortcode"), resultSet.getString("securitycredential"), stkPushUtils.getTimestamp());
                stkPushUtils.Timestamp = stkPushUtils.getTimestamp();
                stkPushUtils.TransactionType = resultSet.getString("commandid");
                stkPushUtils.Amount = resultSet.getInt("amount");
                stkPushUtils.PartyA = resultSet.getString("msisdn");
                stkPushUtils.PartyB = resultSet.getString("businessshortcode");
                stkPushUtils.PhoneNumber = resultSet.getString("msisdn");
                stkPushUtils.CallBackURL = resultSet.getString("resulturl");
                stkPushUtils.AccountReference = resultSet.getString("occassion");
                stkPushUtils.TransactionDesc = resultSet.getString("remarks");
                stkList.add(stkPushUtils);
                Gson gson = new Gson();
                String stkJson = null;
                stkJson = gson.toJson(stkPushUtils);

                System.out.println("STK Request:" + stkJson);
                httpclient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost("https://sandbox.safaricom.co.ke/mpesa/stkpush/v1/processrequest");
                StringEntity stringEntity = new StringEntity(stkJson);
                httpPost.setEntity(stringEntity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");
                httpPost.setHeader("Authorization", "Bearer " + auth.generateAccessToken());
                CloseableHttpResponse response = httpclient.execute(httpPost);
                System.out.println(response);
                HttpEntity entity = response.getEntity();
                String content = EntityUtils.toString(entity);
                JSONObject jsonObject = new JSONObject(content);
                //http status code
                int httpStatusCode = response.getStatusLine().getStatusCode();
                if (httpStatusCode == 200) {

                    String merchantRequestId = jsonObject.getString("MerchantRequestID");
                    String checkoutRequestID = jsonObject.getString("CheckoutRequestID");
                    int responseCode = jsonObject.getInt("ResponseCode");
                    String responseDescription = jsonObject.getString("ResponseDescription");
                    String customerMessage = jsonObject.getString("CustomerMessage");
                    PreparedStatement ps = null;
                    String updateStk = "UPDATE daraja set merchantrequestid = ?,checkoutrequestid = ?,responsecode = ? where occassion = ?";
                    ps = dbConnection.prepareStatement(updateStk);
                    ps.setString(1, merchantRequestId);
                    ps.setString(2, checkoutRequestID);
                    ps.setInt(3, responseCode);
                    ps.setString(4, resultSet.getString("occassion"));
                    ps.executeUpdate();
                    ps.close();
                } else if (httpStatusCode == 404) {
                    System.out.println(jsonObject.getString("errorMessage"));
                } else if (httpStatusCode == 500) {
                    System.out.println("Server error");
                } else {
                    System.out.println("Something went wrong");
                }

                System.out.println("Response:" + content);
                httpclient.close();
            }
        } catch (Exception e) {
        }
    }
}
