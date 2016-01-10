package Aula3.Barreira;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Barreira {
    
    private final int nPesp;
    private int n;
    
    public Barreira(int n){
        nPesp=n;
        n=0;
    }
    
    public synchronized void esperar(){
        if(n==nPesp){
            this.notifyAll();
            n=0;
        }
        if(n<nPesp){
            try {
                n++;
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Barreira1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
