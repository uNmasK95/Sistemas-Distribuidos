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
public class Produtor implements Runnable{

    private BoundedBuffer b;
    
    public Produtor(BoundedBuffer bf){
        this.b=bf;
    }
    
    public void run() {
        
        for(int i=1;i<100;i++){
            try {
                b.put(i);
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
}
