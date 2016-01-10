/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula3.Ex5;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Main {
    
    
    public static void main(String[] args){
        BoundedBuffer bf = new BoundedBuffer(10);
    
        Thread prod = new Thread(new Produtor(bf));
        Thread read = new Thread(new Read(bf));
    
        prod.start();
        read.start();
        
        
        try {
            prod.join();
            read.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
