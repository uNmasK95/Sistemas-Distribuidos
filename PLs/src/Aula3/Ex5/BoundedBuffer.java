package Aula3.Ex5;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/**
 *
 * @author ruifreitas
 */
public class BoundedBuffer {
    private int[]valeus;
    int size;
    int poswrite;
    private ReentrantLock lock = new ReentrantLock();;
    private Condition vazio = lock.newCondition();
    private Condition cheio = lock.newCondition();
     
    
    public BoundedBuffer(int n){
        valeus = new int[n];
        size=n;
        poswrite=0;
    }
  
    public void put(int v){
       
        lock.lock();
        try {
            while(poswrite==size){
                System.out.println("Buffer cheio.");
                try {
                    cheio.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(BoundedBuffer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            valeus[poswrite]=v;
            System.out.println("Foi inserido na pos: " + poswrite + " o valor: "+ v + ";");
            poswrite++;
            vazio.signalAll();
        } finally {
            lock.unlock();
        }
    }
    
    public int get(){
        int i = -1;
        lock.lock();
        try {
            while(poswrite==0){
                System.out.println("Buffer vazio.");
                try {
                    vazio.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(BoundedBuffer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            i = valeus[poswrite-1];
            System.out.println("Foi retirado na pos: " + (poswrite-1) + " o valor: "+ i + ";");
            poswrite--;
            cheio.signalAll();
        } finally {
            lock.unlock();
        }
        return i;
    }
}
