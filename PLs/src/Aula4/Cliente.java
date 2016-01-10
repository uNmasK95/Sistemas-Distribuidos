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
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Cliente {
    private Socket sock;
    
    public Cliente(String remotehost,int port){
        try {
            sock = new Socket(remotehost,port);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startCliente(){
        String l="1";
        String r;
        while(sock.isConnected()){
            try {
                BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
                while(!l.isEmpty()){
                    l=sysin.readLine();
                    out.write(l);
                    out.newLine();
                    out.flush();
                    System.out.println(in.readLine());
                }
                sock.shutdownInput();
                sock.shutdownOutput();
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        }
    }
    public static void main(String[] args){
        Cliente c1 = new Cliente("localhost",12346);
        c1.startCliente();
    }
}
