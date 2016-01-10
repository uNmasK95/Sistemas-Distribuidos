package Aula8;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Servidor {

	private ServerSocket sSock;
	private HashMap<Integer, BufferedWriter> utilizadores;

	public Servidor() {
		try {
			this.sSock = new ServerSocket(12346);
			this.utilizadores = new HashMap<>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startServer() {
		try {
			while (true) {
				Socket sock = sSock.accept();
				ServerWorker sw = new ServerWorker(sock, this);
				Thread t = new Thread(sw);
				ser t.start();
			}
		} catch (IOException e) {
			System.out.println("Erro no startServer");
			e.printStackTrace();
		}
	}

	public synchronized void multicast(String m, int user) {
		System.out.println("Vou fazer multicast");
		try {
			String chat = user + " : " + m;
			for (Integer idUser : this.utilizadores.keySet()) {
				if (idUser != user) {
					this.utilizadores.get(idUser).write(chat);
				}
			}
		} catch (IOException e) {
			System.out.println("Erro no multicast");
			e.printStackTrace();
		}
	}

	public synchronized void remUser(int user) {
		utilizadores.remove(user);
	}

	public synchronized int addUser(BufferedWriter osw) {
		int user = utilizadores.size()+1;
		utilizadores.put(user, osw);
		return user;
	}
	
	public static void main(String[] agrs){
		Servidor s = new Servidor();
		s.startServer();
	}
}
