/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula3.ex6.RWLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class RWLock {
    
    private int nrWrite;
    private int nrRead;
    private ReentrantLock lock;
    private Condition cond;
    
    public RWLock(){
        nrWrite=0;
        nrRead=0;
        lock = new ReentrantLock();
        cond = lock.newCondition();
    }
    
    public void readLock(){
        lock.lock();
        try {
            while(nrWrite>0){
                System.out.println(Thread.currentThread() +  "Alguem está a escrever");
                cond.await();
            }
            System.out.println(Thread.currentThread() +  "Tenho o lock para ler");
            nrRead++;
        } catch (InterruptedException ex) {
            Logger.getLogger(RWLock.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
        lock.unlock();
        }
    }
    public void readUnlock(){
        lock.lock();
        try {
            nrRead--;
        } finally {
            if(nrRead==0){
                cond.signalAll();
            }
            System.out.println(Thread.currentThread() +  "Libertei o lock de leitura");
            lock.unlock();
            
        }
    }
    
    public void writeLock(){
        lock.lock();
        try{
            while(nrRead>0 || nrWrite>0){
                System.out.println(Thread.currentThread() +  "Alguem está a escrever ou existem leitores ativos");
                cond.await();
            }
            System.out.println(Thread.currentThread() +  "Tenho o lock para excrita");
        } catch (InterruptedException ex) {
            Logger.getLogger(RWLock.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.unlock();
            
        }
        
    }
    
    public void writeUnlock(){
        lock.lock();
        try{
            nrWrite--;
        }finally {
            cond.signalAll();
            System.out.println(Thread.currentThread() +  "Libertei o lock de escrita");
            lock.unlock();
        }
    }
    
    
}
