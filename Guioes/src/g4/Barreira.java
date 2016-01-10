package g4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barreira {
	
	private Map<Integer,ArrayList<Integer>> etapas;
	private ReentrantLock lock;
	private Condition bar;
	private int tam;
	private int size;
	private int et;
	
	public Barreira(int n) {
		etapas = new HashMap<>();
		etapas.put(1, new ArrayList<>(tam));
		lock = new ReentrantLock();
		bar = lock.newCondition();
		tam = n;
		size = 0;
		et=1;
		
	}
	public void espera(){
		try {
			lock.lock();
			System.out.println("Vou entrar na barreira thread" + Thread.currentThread().getId());
			etapas.get(et).add((int) Thread.currentThread().getId());
			boolean res = false;
			while(size<tam-1 && !res){
				size++;
				bar.await();
				res = false;
				System.out.println("Sai do wait vou testar se estou na minha estapa");
				for(Integer t : etapas.keySet()){
					if(t!=et && etapas.get(t).contains((int) Thread.currentThread().getId())){
						res = true;
						System.out.println(res);
					}					
				}
			}
			if(size==tam-1){
				System.out.println("Foi completada a etapa" + et);
				et++;
				etapas.put(et, new ArrayList<>());
				size = 0;
				bar.signalAll();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		Barreira b = new Barreira(10);
		int n = 0;
		while(n<21){
			Thread t = new Thread(new Runnerbar(b));
			n++;
			t.start();
		}
	}
}