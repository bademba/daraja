/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brian.mpesa.runner;

import com.brian.mpesa.tx.B2C;

/**
 *
 * @author BADEMBA
 */
public class Runner {
    public static void main(String[] args) {
        B2C  b2c = new B2C();
        b2c.B2CAInitiator();
    }
    
}
