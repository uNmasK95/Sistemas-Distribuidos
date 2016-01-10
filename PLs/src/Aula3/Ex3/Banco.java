/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula3.Ex3;

import java.util.ArrayList;

/**
 *
 * @author ruifreitas
 */
public class Banco {
    
    public ArrayList<Conta> contas;
    
    public Banco(int n){
        contas =  new ArrayList<>(n);
        for(int i=0;i<n;i++){
            contas.add(new Conta(i,0));
        }
    }
    
    public void credito(int id,int add){
        this.contas.get(id).credito(add);
    }
    
    public void debito(int id, int deb){
        this.contas.get(id).debito(deb);
    }
    
    public int consulta(int id){
        return this.contas.get(id).consulta();
    }    
    
    public void transferencia(int conta1, int conta2, int valor){
        
        int menor,maior;
        if(conta1<conta2){
            menor=conta1;
            maior=conta2;
        }else{
            menor=conta2;
            maior=conta1;
        }
        synchronized(this.contas.get(menor)){
            synchronized(this.contas.get(maior)){
                this.contas.get(conta1).debito(valor);
                this.contas.get(conta2).credito(valor);

            }
        }
    }   
}