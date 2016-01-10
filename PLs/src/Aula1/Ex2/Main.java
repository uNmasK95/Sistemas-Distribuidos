/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula1.Ex2;

import Aula1.Ex2.Contador;
import Aula1.Ex2.Incrementador;
import java.util.ArrayList;

/**
 *
 * @author ruifreitas
 */
public class Main {
   
    public static void main(String[] args){
        
        Incrementador inc = new Incrementador(new Contador(),10000000);
        
        ArrayList<Thread> list = new ArrayList(10);
        Thread t;
        int i;
        try{
            
        for(i=0; i<10; i++){  
            list.add(new Thread(inc));
        }
        
        for(i=0; i<10; i++){  
            list.get(i).start();
        }
       
        for(i=0; i<10; i++){  
            list.get(i).join();
        }
        
        
            
        }catch (InterruptedException e){
            System.out.println("Error no join");
            e.printStackTrace();
        }
        
    }
    
}
