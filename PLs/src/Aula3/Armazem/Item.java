/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula3.Armazem;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Item {
    private int quant;
    private final ReentrantLock lock;
    private final Condition vazio;
    
    public Item(){
        this.quant=0;
        lock = new ReentrantLock();
        this.vazio = lock.newCondition();
    }
    
    public void adiciona(int qt){
        lock.lock();
        try {
            this.quant+=qt;
            System.out.println("Adicionei:"+qt+";");
        } finally {
            lock.unlock();
        }
        vazio.signalAll();
    }
    public void retira(int qt){
        lock.lock();
        try {
            while(quant<qt){
                try {
                    vazio.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.quant-=qt;
        } finally {
            lock.unlock();
        }
    }
    public int consulta(){
        return this.quant;
    }
    
}
