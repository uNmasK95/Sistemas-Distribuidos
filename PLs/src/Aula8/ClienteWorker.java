package Aula8;

import java.io.BufferedReader;
import java.io.IOException;

public class ClienteWorker implements Runnable{
	private BufferedReader br;
	
	public ClienteWorker(BufferedReader br) {
		this.br = br;
	}

	@Override
	public void run() {
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
