/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.mpesa.runner;

import com.brian.mpesa.tx.MpesaAPI;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BADEMBA
 */
public class Runner {
    public static void main(String[] args) {
        MpesaAPI  mpesa = new MpesaAPI("J1AzjYt6AWZTgPozOxuiJ6vtZj7K1rVx","8NwRt4CtHGHTtxCg");
        try {
            mpesa.B2C();
        } catch (IOException ex) {
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
