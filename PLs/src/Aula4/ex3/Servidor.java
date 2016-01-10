/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula4.ex3;

import Aula4.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Servidor {
    
    private ServerSocket sSock;

    public Servidor(int port, int nClientes){
        try {
            sSock = new ServerSocket(port,nClientes);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startServer(){
        int i=1;
        int sum=0;
        double b;
        try {
            while(true){
                String l = "Bem vindo";
                Socket sock = sSock.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
                while(l!=null){
                    l=in.readLine();
                    if(l!=null){
                    sum+=Integer.parseInt(l);
                    out.write("Sumatorio: " + sum + "\n");
                    out.flush();
                    i++;
                    }
                }
                b=(sum/i + sum%i);
                System.out.print(b+","+sum+","+i);
                
                out.write("Média:" + b + "\n");
                out.flush();
                in.close();
                out.close();
                sock.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args){
        Servidor server = new Servidor(12346,10);
        server.startServer();
    }  
}
