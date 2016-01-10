/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula3.ex6.RWLock;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Runner implements Runnable{
    
    private RWLock rw;
    private int tipo;
    
    public Runner(RWLock r, int t){
        rw = r;
        tipo=t;
    }
    
    public void run(){
        if(tipo==1){
            rw.readLock();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            }
            rw.readUnlock();
        }
        if(tipo==2){
            rw.writeLock();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Runner.class.getName()).log(Level.SEVERE, null, ex);
            }
            rw.writeUnlock();
        }
    }
    
}
