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
 */
public class Cliente1 implements Runnable{
    
    private Banco b;
    private int idConta;
    
    public Cliente1(Banco ban,int id){
        b=ban;
        idConta=id;
    }
    
    public void run() {
        b.transferencia(1, 2, 200);
        //System.out.println(b.consulta(idConta));
    }
    
}