package teste2015;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket Ssock;
	private Leilao l;
	
	public Server(int port) {
		try {
			Ssock = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l = new Leilao();
	}
	
	public void startServer(){
		while(true){
			Socket sock;
			try {
				sock = Ssock.accept();
				Thread t = new Thread(new ServerConnection(sock,l));
				t.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] agrs){
		Server sev = new Server(6969);
		sev.startServer();
	}
}

class ServerConnection implements Runnable{
	
	private Leilao l;
	private Socket sock;
	private BufferedReader br;
	private BufferedWriter bw;
	
	public ServerConnection(Socket sock, Leilao l){
		this.l = l; 
		this.sock = sock;
		try {
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String line = "";
		while(sock.isConnected()){
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (line) {
				case "ANUNCIAR" : {
					try {
						line = br.readLine();
						String[] array = line.split(":");
						bw.write(l.anunciar(array[0], Double.parseDouble(array[1])));
						bw.newLine();
						bw.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				case "LISTAR" : {
					try {
						bw.write(l.listar());
						bw.newLine();
						bw.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				case "OFERECER" : {
					try {
						line = br.readLine();
						String[] array = line.split(":");
						bw.write(l.oferecer(array[0], Double.parseDouble(array[1])));
						bw.newLine();
						bw.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
}
