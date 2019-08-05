/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.mpesa.tx;

/**
 *
 * @author BADEMBA
 */
public class B2CUtils {
    
    public String InitiatorName;
    public String SecurityCredential;
    public String CommandID;
    public int Amount;
    public String PartyA;
    public String PartyB;
    public String Remarks;
    public String QueueTimeOutURL;
    public String ResultURL;
    public String Occassion;

    
    public B2CUtils(){}
     public B2CUtils(String InitiatorName , String SecurityCredential , String CommandID,  int Amount, String PartyA,String PartyB
     ,String Remarks , String QueueTimeOutURL , String ResultURL, String Occassion){
         this.InitiatorName = InitiatorName;
         this.SecurityCredential = SecurityCredential;
         this.CommandID= CommandID;
         this.Amount =  Amount;
         this.PartyA =  PartyA;
         this.PartyB =  PartyB;
         this.Remarks =  Remarks;
         this.QueueTimeOutURL =  QueueTimeOutURL;
         this.ResultURL =  ResultURL;
         this.Occassion = Occassion;
     }
     
    public String getInitiatorName() {
        return InitiatorName;
    }

    public void setInitiatorName(String InitiatorName) {
        this.InitiatorName = InitiatorName;
    }

    public String getSecurityCredential() {
        return SecurityCredential;
    }

    public void setSecurityCredential(String SecurityCredential) {
        this.SecurityCredential = SecurityCredential;
    }

    public String getCommandID() {
        return CommandID;
    }

    public void setCommandID(String CommandID) {
        this.CommandID = CommandID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public String getPartyA() {
        return PartyA;
    }

    public void setPartyA(String PartyA) {
        this.PartyA = PartyA;
    }

    public String getPartyB() {
        return PartyB;
    }

    public void setPartyB(String PartyB) {
        this.PartyB = PartyB;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public String getQueueTimeOutURL() {
        return QueueTimeOutURL;
    }

    public void setQueueTimeOutURL(String QueueTimeOutURL) {
        this.QueueTimeOutURL = QueueTimeOutURL;
    }

    public String getResultURL() {
        return ResultURL;
    }

    public void setResultURL(String ResultURL) {
        this.ResultURL = ResultURL;
    }

    public String getOccassion() {
        return Occassion;
    }

    public void setOccassion(String Occassion) {
        this.Occassion = Occassion;
    }
    
    
}
