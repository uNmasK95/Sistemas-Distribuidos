package g4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {

	private ReentrantLock lock;
	private Condition cheio;
	private Condition vazio;
	private int[] buffer;
	private int size;
	private int tam;
	
	public BoundedBuffer(int tam) {
		lock = new ReentrantLock();
		buffer = new int[tam];
		this.size=0;
		this.tam=tam;
		vazio = lock.newCondition();
		cheio = lock.newCondition();
	}
	
	public void put(int val){
		try{
			lock.lock();
			while(size==tam){
				System.out.println("Buffer cheio vou fazer wait");
				cheio.await();
				System.out.println("saio do wait cheio vou tentar outra vez");
			}
			System.out.println("Buffer nao está cheio");
			buffer[size]=val;
			size++;
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			vazio.signal();
			lock.unlock();
			
		}
	}
	
	public int get(){
		int val=-1;
		try {
			lock.lock();
			while(size==0){
				System.out.println("Buffer vazio vou fazer wait");
				vazio.await();
				System.out.println("saio do wait vazio vou tentar outra vez");
			}
			System.out.println("Buffer tem elemtentos para retirar");
			size--;
			val = buffer[size];
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			cheio.signal();
			lock.unlock();
		}
		return val;
	}

	public void geral(){
		for(int i =0;i<tam;i++){
			System.out.println("geral: " + i + "val: " +buffer[i]);
		}
		System.out.println("Size: " + size);
	}
	public static void main(String[] args){
		BoundedBuffer b = new BoundedBuffer(10);
		
		Thread tg = new Thread(new Runner(b,'G'));
		Thread tp = new Thread(new Runner(b,'P'));
		
		tg.start();
		tp.start();
		
		try{
			tg.join();
			tp.join();
		}catch(Exception e){
			e.printStackTrace();
		}
		b.geral();
		System.out.println("Acabou");
	}
}
