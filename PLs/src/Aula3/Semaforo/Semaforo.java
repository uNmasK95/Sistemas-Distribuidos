/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula3.Semaforo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Semaforo {
    
    int nEsp;
    int n;
    
    public Semaforo(int n){
        nEsp=n;
        n=0;
    }
    
    public synchronized void P(){
        while(n>=nEsp){
            try {
                System.out.println(Thread.currentThread()+"nao consigo o lock;");
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Semaforo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        n++;
    }
    
    public synchronized void V(){
        if(n>0){
            n--;
            this.notify();
            System.out.println(Thread.currentThread()+"sai do lock;");
        } 
    }
    
    
    
}


