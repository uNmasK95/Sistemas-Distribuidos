/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula2.Ex4;

/**
 *
 * @author ruifreitas
 */
public class Conta {

    private final int id;
    private int saldo;
    
    public Conta(int i, int inicio){
        id=i;
        saldo = inicio;
    }
    
    public int getID(){
        return id;
    }
 
    public synchronized int consulta(){
        return saldo;
    }
    
    public synchronized void credito(int add){
        saldo+=add;
    }
    
    public synchronized void debito(int deb){
        saldo-=deb;
    }
}
