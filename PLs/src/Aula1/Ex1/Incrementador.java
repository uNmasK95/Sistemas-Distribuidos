/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula1.Ex1;

import Aula1.Ex1.Contador;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Incrementador implements Runnable{
    
    private Contador c;
    
    public Incrementador(Contador cont){
        c=cont;
    }
    
    public void run() {
        while(c!=null){
            c.incContador();
            System.out.println(c.getContador());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) { 
                System.out.println("Nao foi possivel esperar!");
            }
        }
    }
    
}
