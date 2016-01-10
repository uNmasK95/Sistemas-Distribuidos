/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula3.Ex5;

import Aula3.Semaforo.Semaforo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Runner implements Runnable{
    
    private Semaforo s;
    
    public Runner(Semaforo f){
        s=f;
    }

    public void run() {
        s.P();
        System.out.println(Thread.currentThread()+" está com lock;");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        s.V();
        
        System.out.println(Thread.currentThread()+"já tive lock");
    }
}
