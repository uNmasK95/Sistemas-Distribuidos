/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao2.ex3;

import Guiao2.ex2.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Main {
    public static void main(String[] args){
        
        Banco b = new Banco(10);
        
        Thread t1 = new Thread(new Cliente(b,1,"T"));
        Thread t2 = new Thread(new Cliente(b,2,"T"));
        
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        b.geral();
        
    }
}
