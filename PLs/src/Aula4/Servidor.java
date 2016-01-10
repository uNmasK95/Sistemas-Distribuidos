/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula4;

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
        int i=0;
        try {
            while(true){
                String l = "Bem vindo";
                Socket sock = sSock.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
                while(!l.isEmpty() && i<10){
                    l=in.readLine();
                    out.write(l+"\n");
                    out.flush();
                    i++;
                }
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
