/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula3.ex4;

/**
 *
 * @author ruifreitas
 */
public class Runner implements Runnable{
    
    private Barreira b;
    
    public Runner(Barreira br){
        this.b=br;
    }

    @Override
    public void run() {
        System.out.println("Vou entrar na barreira"+ Thread.currentThread());
        b.esperar();
        System.out.println("Sai da barreira." + Thread.currentThread());
    }
    
    
}
