/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula2.Ex4;

/**
 *
 * @author ruifreitas
 */
public class Cliente2 implements Runnable{
    
    private Banco b;
    private int idConta;
    
    public Cliente2(Banco ban,int id){
        b=ban;
        idConta=id;
    }
    
    @Override
    public void run() {
        b.transferencia(2, 1, 200);
        //System.out.println(b.consulta(idConta));
    }
    
}
