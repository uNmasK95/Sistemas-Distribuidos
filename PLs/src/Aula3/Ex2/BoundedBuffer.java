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
public class BoundedBuffer {
    private int[]valeus;
    int size;
    int poswrite;
    
    
    public BoundedBuffer(int n){
        valeus = new int[n];
        size=n;
        poswrite=0;
    }
  
    public synchronized void put(int v){
        while(poswrite==size){
            System.out.println("Buffer cheio.");
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(BoundedBuffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        valeus[poswrite]=v;
        System.out.println("Foi inserido na pos: " + poswrite + " o valor: "+ v + ";");
        poswrite++;
        this.notify();
    }
    
    public synchronized int get(){
        int i = -1;
        while(poswrite==0){
            System.out.println("Buffer vazio.");
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(BoundedBuffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
        i = valeus[poswrite-1];
        System.out.println("Foi retirado na pos: " + (poswrite-1) + " o valor: "+ i + ";");
        poswrite--;
        this.notify();
 
        return i;
    }
}
