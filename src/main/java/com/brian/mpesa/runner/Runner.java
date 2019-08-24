/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.mpesa.runner;

import com.brian.mpesa.security.EncryptionDecryption;
import com.brian.mpesa.tx.Auth;
import com.brian.mpesa.tx.B2C;
import com.brian.mpesa.tx.STK;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BADEMBA
 */
public class Runner {

    public static void main(String[] args) throws Exception {
        //B2C b2c = new B2C();
        STK stk = new STK();
        stk.lipaNaMpesaOnline();
    }

}
