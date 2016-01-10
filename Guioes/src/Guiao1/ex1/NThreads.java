/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao1.ex1;

/**
 *
 * @author ruifreitas
 */
public class NThreads implements Runnable {
    
    private int fim;
    
    public NThreads(int i){
        this.fim=i;
    }

    public void run() {
        for(int i=1;i<=fim;i++){
            System.out.println(i);
        }
    }
}
