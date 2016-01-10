/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula2.Ex2;

/**
 *
 * @author ruifreitas
 */
public class Banco {
    
    private int saldo;
    
    public Banco(){
        saldo=0;
    }
    
    public int consulta(){
        return saldo;
    }
    
    public synchronized void credito(int add){
        saldo+=add;
    }
    
    public synchronized void debito(int deb){
        saldo-=deb;
    }
    
}
