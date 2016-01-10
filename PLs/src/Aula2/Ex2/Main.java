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
public class Main {
    
    public static void main (String[] args){
        Banco b = new Banco();
        Cliente c1 = new Cliente(b, 1, 5, 1000000000);
        Cliente c2 = new Cliente(b, 0, 5, 1000000000);
        
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
        
        System.out.println("Saldo da conta: " + b.consulta());
        
                
    }    
}
