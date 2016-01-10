/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao3.ex1;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author ruifreitas
 */
public class Conta {
	
	private ReentrantLock lock;
	private int num;
    private int saldo;
    
    public Conta(int num){
    	this.lock = new ReentrantLock();
    	this.num=num;
        saldo=0;
    }
    
    public int getNum(){
    	return this.num;
    }
    
    public ReentrantLock getLock(){
    	return lock;
    }
    
    public int consulta(){
    	int val;
    	lock.lock();
    	val=saldo;
    	lock.unlock();
    	return val;
    }
    public synchronized void credito(int val){
    	lock.lock();
    	this.saldo+=val;
    	lock.unlock();
    }
    public synchronized void debito(int val){
    	lock.lock();
    	this.saldo-=val;
    	lock.unlock();
    }
    
}
