package teste2015;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Item {
	
	private String item;
	private double valor;
	private int propostas;
	private double valorProposto;
	private Condition itemAnunciado;
	
	public Item(String item,double valor,ReentrantLock lock) {
		this.item=item;
		this.valor=valor;
		this.propostas = -1;
		this.itemAnunciado = lock.newCondition();
	}
	
	public synchronized String getItem(){
		return item;
	}
	public synchronized double getValor(){
		return valor;
	}
	
	public synchronized int getPropostas(){
		return this.propostas;
	}
	public synchronized void addproposta(){
		this.propostas++;
	}

	public synchronized double getValorProposto() {
		return valorProposto;
	}

	public synchronized void setValorProposto(double valorProposto) {
		this.valorProposto = valorProposto;
	}
	
	public synchronized String toString(){
		return item + ":" + valor; 
	}

	public Condition getItemAnunciado() {
		return itemAnunciado;
	}

	public void setItemAnunciado(Condition itemAnunciado) {
		this.itemAnunciado = itemAnunciado;
	}

}
