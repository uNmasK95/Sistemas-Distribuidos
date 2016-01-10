package Aula6;

import java.util.concurrent.locks.ReentrantLock;

public class Conta {

	private double valor;
	ReentrantLock lock;

	Conta() {
		this.valor = 0;
		this.lock = new ReentrantLock();
	}

	public double consultar() {
		return this.valor;
	}

	public void depositar(double valor) {
		this.valor = this.valor + valor;
	}

	public void levantar(double valor) {
		this.valor = this.valor - valor;
	}

}
