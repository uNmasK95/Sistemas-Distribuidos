/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula2.Ex1;

/**
 *
 * @author ruifreitas
 */
public class Contador {
    
    private int contador;
    
    public Contador(){this.contador=0;}
    
    public synchronized void incContador(){this.contador++;}
    
    public int getContador(){return this.contador;}
    
}
