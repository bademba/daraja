/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.mpesa.tx;

 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author BADEMBA
 */
public class STKPushUtils {

    public String BusinessShortCode;
    public String Password;
    public String Timestamp;
    public String TransactionType;
    public int Amount;
    public String PartyA;
    public String PartyB;
    public String PhoneNumber;
    public String CallBackURL;
    public String AccountReference;
    public String TransactionDesc;

    
    public STKPushUtils(){}
    public String getBusinessShortCode() {
        return BusinessShortCode;
    }

    public void setBusinessShortCode(String BusinessShortCode) {
        this.BusinessShortCode = BusinessShortCode;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	Date date = new Date();
        Timestamp =  dateFormat.format(date);
        return Timestamp ;
    }

    public void setTimestamp(String Timestamp) {
        this.Timestamp = Timestamp;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String TransactionType) {
        this.TransactionType = TransactionType;
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

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getCallBackURL() {
        return CallBackURL;
    }

    public void setCallBackURL(String CallBackURL) {
        this.CallBackURL = CallBackURL;
    }

    public String getAccountReference() {
        return AccountReference;
    }

    public void setAccountReference(String AccountReference) {
        this.AccountReference = AccountReference;
    }

    public String getTransactionDesc() {
        return TransactionDesc;
    }

    public void setTransactionDesc(String TransactionDesc) {
        this.TransactionDesc = TransactionDesc;
    }
    
    
}
