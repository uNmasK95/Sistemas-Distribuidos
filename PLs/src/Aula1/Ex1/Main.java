/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula1.Ex1;

/**
 *
 * @author ruifreitas
 */
public class Main {
   
    public static void main(String[] args){
        
        Incrementador inc = new Incrementador(new Contador());
        Echo ec = new Echo();
        Thread t1 = new Thread(inc);
        Thread t2 = new Thread(ec);
        
        t1.start();
        t2.start();
        
        
        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            System.out.println("Error no join");
            e.printStackTrace();
        }
        
    }
    
}
