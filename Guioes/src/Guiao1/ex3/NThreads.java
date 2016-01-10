/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao1.ex3;

import Guiao1.ex2.*;

/**
 *
 * @author ruifreitas
 */
public class NThreads implements Runnable {
    
    private int fim;
    private Counter c;
    
    public NThreads(Counter cn, int i){
        this.c=cn;
        this.fim=i;
    }

    public void run() {
        for(int i=1;i<=fim;i++){
            c.incrementa();
            System.out.println(c.get());
        }
    }
}
