/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula1.Aquecimento;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ruifreitas
 */
public class Exercicio1 {
    
    public static void main(String[] arg){
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        String line;
        
        try {
            while((line=input.readLine())!=null){
                System.out.println("Echo: " + line);
            }
        } catch (IOException ex) {
            System.out.println("Erro reading line from System.in!");
        }
   }
}
