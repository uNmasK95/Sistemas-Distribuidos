/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula2.Ex1;

import Aula2.Ex1.Contador;

/**
 *
 * @author ruifreitas
 */
public class Incrementador implements Runnable{
    
    private Contador c;
    private int last;
    
    public Incrementador(Contador cont){
        c=cont;
    }
    
    public Incrementador(Contador cont, int i){
        c=cont;
        last=i;
    }
    
    @Override
    public void run() {
        int f=0;
        while(c!=null && f<last){
            c.incContador();
            f++;
        }
    }
    
}
