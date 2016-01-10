/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula3.ex4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Main {
    public static void main(String[] args){
        int i;
        Barreira b = new Barreira(10);
        Runner r = new Runner(b);
        Thread[] lista = new Thread[20];
        for(i=0;i<20;i++){
            lista[i]= new Thread(r); 
        }
        
        for(i=0;i<20;i++){
            lista[i].start();
        }
        for(i=0;i<20;i++){
            lista[i].start();
        }
        
        for(i=0;i<20;i++){
            try {
                lista[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
