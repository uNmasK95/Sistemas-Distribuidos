/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao3.ex1;

import Guiao2.ex4.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Main {
    public static void main(String[] args){
        
        Banco b = new Banco(10);
        
        Thread t0 = new Thread(new Cliente(b,0,"C"));
        Thread t1 = new Thread(new Cliente(b,1,"T"));
        Thread t2 = new Thread(new Cliente(b,2,"T"));
        Thread t3 = new Thread(new Cliente(b,2,"T"));
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        
        try {
        	t0.join();
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        b.geral();
        
    }
}
