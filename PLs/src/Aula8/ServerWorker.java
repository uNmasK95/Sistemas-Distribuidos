package Aula8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerWorker implements Runnable {

	private Socket sock;
	private BufferedReader br;
	private BufferedWriter bw;
	private Servidor server;
	private int idUser;

	public ServerWorker(Socket s, Servidor server) {
		this.sock = s;
		this.server = server;
		try {
			this.bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			this.br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (IOException e) {
			System.out.println("Não foi possivel criar o ServerWorker");
		}
		this.idUser = server.addUser(bw);
	}

	@Override
	public void run() {
		String line = "";
		while (sock.isConnected()) {
			try {
				while ((line = br.readLine()) != null) {
					this.server.multicast(line,idUser);
				}
				this.server.remUser(idUser);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
