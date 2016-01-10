/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula3.Semaforo;

import Aula3.Ex5.Runner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class MainS {   
    
    public static void main(String[] args){
        
        Semaforo s = new Semaforo(5);
        
        Thread t;
        int i=0;
        while(i<10){
            t=new Thread(new Runner(s));
            t.start();
            try {
                t.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(MainS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            i++;
        }
        
        
        
    }
    
}

    
