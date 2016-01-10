/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao2.ex4;


/**
 *
 * @author ruifreitas
 */
public class Conta {
    private int saldo;
    
    public Conta(){
        saldo=0;
    }
    
    public synchronized int consulta(){return saldo;}
    public synchronized void credito(int val){this.saldo+=val;}
    public synchronized void debito(int val){this.saldo-=val;}
    
}
