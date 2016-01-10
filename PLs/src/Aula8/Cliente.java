package Aula8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	private Socket sock;
	private BufferedReader br;
	private BufferedWriter bw;
	private static final Scanner input = new Scanner(System.in);

	public Cliente(String remotehost, int port) {
		try {
			this.sock = new Socket(remotehost, port);
			this.br = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
			this.bw = new BufferedWriter( new OutputStreamWriter(this.sock.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startCliente() {
		Thread receber = new Thread(new ClienteWorker(br));
		receber.start();
		String line;
		System.out.println("Mensagem a Enviar:");
		while ((line = input.nextLine())!=null) {
			try {
				bw.write(line);
				System.out.println("Mensagem a Enviar:");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	public static void main(String[] args){
		Cliente c1 = new Cliente("localhost",12346);
		c1.startCliente();
		
	}
}
