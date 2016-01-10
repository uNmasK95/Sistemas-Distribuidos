/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula3.Ex2;

import Aula3.Ex2.*;
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
    
        
        read.start();
        prod.start();
        try {
            prod.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            prod.join();
            read.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
