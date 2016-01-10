package teste2015;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements leilaoInterface{

	private Socket sock;
	private static Scanner input = new Scanner(System.in);
	private BufferedReader br;
	private BufferedWriter bw;
	
	public Cliente(String host, int port) {
		try {
			sock = new Socket(host,port);
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String anunciar(String item, double valor) {
		String res = "";
		try {
			bw.write("ANUNCIAR");
			bw.newLine();
			bw.flush();
			bw.write(item + ":" + valor);
			bw.newLine();
			bw.flush();
			res = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String listar() {
		String res = "";
		try {
			bw.write("LISTAR");
			bw.newLine();
			bw.flush();
			res = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String oferecer(String item, double valor) {
		String res = "";
		try {
			bw.write("OFERECER");
			bw.newLine();
			bw.flush();
			bw.write(item +":" + valor);
			bw.newLine();
			bw.flush();
			res = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	
	public void startCliente(){
		while(sock.isConnected()){
			int op = input.nextInt();
			
			switch (op){
				case  1: {
					anunciar(input.nextLine(),input.nextInt());
					break;
				}
				case 2 : {
					listar();
					break;
				}
				case 3 :{
					oferecer(input.nextLine(),input.nextInt());
					break;
				}
			}
			
			
			
		}
	}
	
	public static void main(String[] args){
		Cliente c = new Cliente("localhost",6969);
		c.startCliente();
	}
}
