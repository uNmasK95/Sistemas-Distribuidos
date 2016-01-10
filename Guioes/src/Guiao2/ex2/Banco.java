/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao2.ex2;

/**
 *
 * @author ruifreitas
 */
public class Banco {
    
    private Conta[] lista;
    private int n;
    
    public Banco(int n){
        lista = new Conta[n];
        for(int i=0;i<n;i++){
            lista[i]=new Conta();
        }
        this.n=n;
    }
    
    public synchronized int consulta(int nConta){return lista[nConta].consulta();}
    public synchronized void creditoConta(int nConta, int val){lista[nConta].credito(val);}
    public synchronized void debitoConta(int nConta, int val){lista[nConta].debito(val);}
    
    
    public void geral(){
        for(int i=0;i<n;i++){
            System.out.println("Conta Nº"+ i + ":" + lista[i].consulta() + "\n");
        }
    }

}
