/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula4.Multi;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Sworker implements Runnable{
    
    private Socket sock;
    
    public Sworker (Socket sock){
        this.sock=sock;
        
    }
    
    @Override
    public void run() {
        try {
            int i=0;
            String l = "Bem vindo";
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
            while(!l.isEmpty() && i<10){
                l = in.readLine();
                out.write(l+"\n");
                out.flush();
                i++;
            }   in.close();
            out.close();
            in.close();
            sock.close();
        } catch (IOException ex) {
            Logger.getLogger(Sworker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
