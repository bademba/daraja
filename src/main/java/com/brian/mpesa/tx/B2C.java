/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.mpesa.tx;

import com.brian.db.DBConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class B2C {

    @Test
    public List<B2CUtils> B2CAInitiator() {

        List<B2CUtils> list = new ArrayList<B2CUtils>();
        B2CUtils b2cUtils = null;
        Connection con = null;
        try {
            con = DBConnector.getMysqlDBConnection();
            String b2cTransaction = " SELECT d.initiatorname, d.securitycredential ,d.commandid, d.amount, d.partya, d.partyb, d.remarks,d.queuetimeouturl , d.resulturl, d.occassion FROM  daraja_b2c d  where id =12345;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(b2cTransaction);
            while (rs.next()) {
                b2cUtils = new B2CUtils();
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
                ObjectMapper mapper = new ObjectMapper();
                String b2cJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(b2cUtils);
                System.out.print(b2cJson);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
