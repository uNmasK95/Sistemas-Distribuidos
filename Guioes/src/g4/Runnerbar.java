package g4;

public class Runnerbar implements Runnable{

	private Barreira b;
	public Runnerbar(Barreira b) {
		this.b = b;
	}
	@Override
	public void run() {
		b.espera();
		System.out.println("Sai da barreira thread" + Thread.currentThread().getId());
	}
	
	

}
