/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula3.Ex3;

import Aula2.Ex4.*;

/**
 *
 * @author ruifreitas
 * FAZER ESTES exercicio com locks ao banco array com 0 e uns para saber se a conta está a ser utilizada
 * 
 */
public class Main {
    
    public static void main (String[] args){
        Banco b = new Banco(3);
        
        Cliente1 c1 = new Cliente1(b, 1);
        Cliente2 c2 = new Cliente2(b, 2);
        
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            System.out.println("Error no join");
        }
        
        for(Conta c : b.contas){
            System.out.println("Saldos conta " + c.getID() + ":"+c.consulta());
        }
                
    }    
}
