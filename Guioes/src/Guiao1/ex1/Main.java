/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao1.ex1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Main {
    
    public static void main(String[] args){
        int n = 10;
            
        NThreads t = new NThreads(1000);
        
        Thread[] lista = new Thread[n];
            
        for(int i=0;i<n;i++){
            lista[i]=new Thread(t);
        }
        
        for(int i=0;i<n;i++){
            lista[i].start();
        }
        
        for(int i=0;i<n;i++){
            try {
                lista[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
    }
    
}
