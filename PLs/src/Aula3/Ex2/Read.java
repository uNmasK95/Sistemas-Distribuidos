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
public class Read implements Runnable{

    private BoundedBuffer b;
    
    public Read(BoundedBuffer bf){
        this.b=bf;
    }
    
    public void run() {
        int i=0;
        while(i<100){
            b.get();
            i++;
        }   
    }
}
