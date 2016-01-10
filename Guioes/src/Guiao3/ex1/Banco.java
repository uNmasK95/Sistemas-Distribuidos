/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao3.ex1;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author ruifreitas
 */
public class Banco implements Bank{
	private ReentrantLock lock;
    private ArrayList<Conta> contas;
    
    public Banco(int n){
    	lock = new ReentrantLock();
        contas = new ArrayList<>();
        for(int i=0;i<n;i++){
            contas.add(new Conta(i));
        }
    }
    
    public int consulta(int nConta){
    	return contas[nConta].consulta();
    }
    public void creditoConta(int nConta, int val){
    	contas[nConta].credito(val);
    }
    public void debitoConta(int nConta, int val){
    	contas[nConta].debito(val);
    	}
    public void transferencia(int orig, int dest, int val){
        int max, min;
        if(orig<dest){
            min=orig;
            max=dest;
        }else{
            min=dest;
            max=orig;
        }
        contas[min].getLock().lock();
        	contas[max].getLock().lock();
                debitoConta(orig,val);
                creditoConta(dest,val);
            contas[max].getLock().unlock();
        contas[min].getLock().unlock();
    }
    
    public void geral(){
    	for (Conta c : contas){
    		System.out.println("Conta: " + c.getNum() + " saldo: " + this.consulta(c.getNum()));
    	}
    }

		
    @Override
	public int createAccount(float initialBalance) {
    	lock.lock();
    		
    	lock.unlock();
    	return 0;
	}

	@Override
	public float closeAccount(int id) throws InvalidAccount {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void transfer(int from, int to, float amount) throws InvalidAccount, NotEnoughFunds {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float totalBalance(int[] accounts) {
		// TODO Auto-generated method stub
		return 0;
	}
}
