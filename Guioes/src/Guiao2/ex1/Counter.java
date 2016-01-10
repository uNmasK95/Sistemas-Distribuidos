/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao2.ex1;

import Guiao1.ex3.*;
import Guiao1.ex2.*;

/**
 *
 * @author ruifreitas
 */
public class Counter {
    private int i;
   
    public Counter(){
        int i = 0;
    }
    
    public synchronized void incrementa(){i++;}
    public synchronized int get(){return i;}
}
