/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula6;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */
public class Runner implements Runnable {

	final static int CONSULTAR = 0;
	final static int CONSULTAR_TOTAL = 1;
	final static int LEVANTAR = 2;
	final static int DEPOSITAR = 3;
	final static int TRANSFERENCIA = 4;
	final static int OK = 5;
	final static int KO = 6;

	private Socket sock;
	private Banco b;
	private ObjectInputStream in;	//DataInputStream
	private ObjectOutputStream out; //DataOutputStream

	public Runner(Socket sock, Banco banco) {
		this.sock = sock;
		this.b = banco;
		try {
			this.in = new ObjectInputStream(sock.getInputStream());
			this.out = new ObjectOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		int op;
		try {
			while (sock.isConnected()) {
				op = in.readInt();
				switch (op) {
				case CONSULTAR:
					out.writeDouble(this.b.consultar(in.readInt()));
					out.flush();
					break;
				case CONSULTAR_TOTAL:
					try {
						out.writeDouble(this.b.consultartotal((int[]) in.readObject()));
						out.flush();
					} catch (ClassNotFoundException e) {
						System.out.println("Erro");
					}
					break;
				case LEVANTAR:
					try {
						this.b.levantar(in.readInt(), in.readDouble());
					} catch (DinheiroIndisponivel e) {
						System.out.println(e.getMessage());
					}
					break;
				case DEPOSITAR:
					this.b.depositar(in.readInt(), in.readDouble());
					break;
				case TRANSFERENCIA:
					try {
						this.b.transferir(in.readInt(), in.readInt(), in.readDouble());
					} catch (DinheiroIndisponivel e) {
						out.writeInt(KO);
						out.flush();
					}
					break;
				default:
					break;
				}
			}
		} catch (IOException ex) {
			System.out.println("O cliente saiu por isso vou deixar de executar");
		}
	}

}
