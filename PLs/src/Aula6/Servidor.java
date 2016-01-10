/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula6;

import java.io.IOException;

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
    private Banco b;

    public Servidor(int port, int nClientes){
        try {
            this.sSock = new ServerSocket(port,nClientes);
            this.b = new Banco(20);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startServer(){
        try {
            while(true){
                Socket sock = sSock.accept();
                Runner ligClient = new Runner(sock,b);
                Thread t = new Thread(ligClient);
                t.start();
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
