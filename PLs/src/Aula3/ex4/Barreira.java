package Aula3.ex4;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Aula3.Barreira.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Barreira {
    
    private final int esp;
    private int n;
    private int etapa;
    
    public Barreira(int n){
        esp=n;
        n=0;
        etapa=0;
    }
    
    public synchronized void esperar(){
        
        n++;
        while(n<esp){
            try {
                System.out.println("Estou em espera"+Thread.currentThread());
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Barreira.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.notifyAll();
        
    }
}
