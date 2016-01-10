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
public class Cliente implements Runnable{
    private Banco b;
    private int nConta;
    private String tipo;
    
    public Cliente(Banco c, int nC, String tipo){
        this.b=c;
        this.nConta=nC;
        this.tipo=tipo;
    }
    
    public void run(){
        if(tipo.equals("C")){
            b.creditoConta(nConta, 25);
        }
        else{
            b.debitoConta(nConta, 25);
        }
        
    }
}
