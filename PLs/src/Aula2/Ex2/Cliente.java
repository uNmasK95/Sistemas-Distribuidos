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
public class Cliente implements Runnable{
    
    private Banco banco;
    private int tipo; // se for 0 é para decontar 1 adicionar
    private int valor;
    private int nAc;
    
    public Cliente(Banco b, int t, int v, int ac){
        banco=b;
        tipo=t;
        valor=v;
        nAc=ac;
    }
    
    @Override
    public void run() {
        int i=0;
        while(i<nAc){
            if(tipo==0){
                banco.debito(valor);
            }
            if(tipo==1){
                banco.credito(valor);
            }
            i++;
        }
    }
    
}
