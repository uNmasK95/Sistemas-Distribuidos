package g4;

import java.util.Random;

public class Runner implements Runnable{

	private BoundedBuffer buffer;
	private char tipo;

	public Runner(BoundedBuffer b,char tipo) {
		this.tipo=tipo;
		this.buffer = b;
	}

	@Override
	public void run() {
		if(tipo=='G'){
			int i = 0;
			while(i<10){
				System.out.println("Get " + i +": " + buffer.get());
				i++;
			}
		}else{
			int i = 0;
			Random rand = new Random();
			int r = rand.nextInt();
			while(i<10){
				System.out.println("Put " + i + ": " + r);
				buffer.put(r);
				i++;
			}
		}
	}
	
	

}
