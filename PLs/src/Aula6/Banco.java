package Aula6;

import java.util.ArrayList;
import java.util.Arrays;

public class Banco implements BancoIface {

	private Conta[] contas;

	public Banco(int n) {
		contas = new Conta[n];
		while (n > 0) {
			contas[n - 1] = new Conta();
			n--;
		}
	}

	public double consultar(int nr_conta) {

		double ret = 0;
		this.contas[nr_conta].lock.lock();

		try {
			ret = this.contas[nr_conta].consultar();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			this.contas[nr_conta].lock.unlock();
		}
		return ret;
	}

	// Lock com ciclo for para consultar
	// Aqui é preciso algum mecanismo para:
	// Adquirir locks por ordem
	// Libertar locks adquiridos caso ocorra uma excepção
	public double consultartotal1(int[] nr_contas) {

		Arrays.sort(nr_contas);

		double ret = 0;
		ArrayList<Integer> contas_locked = new ArrayList<Integer>();

		try {

			for (int i = 0; i < nr_contas.length; i++) {
				System.out.println("Locking conta " + nr_contas[i]);
				contas_locked.add(nr_contas[i]);
				this.contas[nr_contas[i]].lock.lock();
				/*
				 * if(i==1){ throw new Exception(); }
				 */

			}

			for (int i = 0; i < nr_contas.length; i++) {
				ret += this.contas[nr_contas[i]].consultar();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			for (int i = contas_locked.size() - 1; i >= 0; i--) {
				System.out.println("UnLocking conta " + nr_contas[i]);
				this.contas[nr_contas[i]].lock.unlock();
			}
		}

		return ret;
	}

	public double consultar_recurs(int[] nr_contas, int pos) {

		double total = 0;
		if (pos < nr_contas.length) {

			System.out.println("Locking conta " + nr_contas[pos]);
			this.contas[nr_contas[pos]].lock.lock();

			try {

				/*
				 * if(pos==2){ throw new Exception(); }
				 */

				total = consultar_recurs(nr_contas, pos + 1);
				total += this.contas[nr_contas[pos]].consultar();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.contas[nr_contas[pos]].lock.unlock();
				System.out.println("UNLocking conta " + nr_contas[pos]);
			}
		}
		return total;
	}

	// Recursão
	public double consultartotal(int[] nr_contas) {

		double total = 0;
		Arrays.sort(nr_contas);

		total = this.consultar_recurs(nr_contas, 0);

		return total;
	}

	// Também dava com synchronized
	/*
	 * public double consultar_recurs_sync(int[] nr_contas,int pos){
	 * 
	 * double total=0; if(pos<nr_contas.length){
	 * synchronized(this.contas[nr_contas[pos]]){
	 * total=consultar_recurs(nr_contas,pos+1);
	 * total+=this.contas[nr_contas[pos]].consultar();
	 * 
	 * 
	 * } } return total; }
	 */

	public void levantar(int nr_conta, double valor) throws DinheiroIndisponivel {

		this.contas[nr_conta].lock.lock();
		try {

			if (this.contas[nr_conta].consultar() < valor) {
				throw new DinheiroIndisponivel("Não tem dinheiro na conta");
			}
			
			System.out.println("a levantar");
			this.contas[nr_conta].levantar(valor);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			System.out.println("a fazer unlock");
			this.contas[nr_conta].lock.unlock();
		}
	}

	public void depositar(int nr_conta, double valor) {

		this.contas[nr_conta].lock.lock();
		try {
			this.contas[nr_conta].depositar(valor);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			this.contas[nr_conta].lock.unlock();
		}

	}

	// Usa diretamente as contas
	// Aqui try catchs encadeados também
	public void transferir(int conta_origem, int conta_destino, double valor) throws DinheiroIndisponivel {

		Integer conta_min = Math.min(conta_origem, conta_destino);
		Integer conta_max = Math.max(conta_origem, conta_destino);

		this.contas[conta_min].lock.lock();
		try {
			this.contas[conta_max].lock.lock();
			try {

				if (this.contas[conta_origem].consultar() < valor) {
					throw new DinheiroIndisponivel();
				}
				this.contas[conta_origem].levantar(valor);
				this.contas[conta_destino].depositar(valor);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("unlock in");
				this.contas[conta_max].lock.unlock();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("unlock out");
			this.contas[conta_min].lock.unlock();
		}

	}

}

class DinheiroIndisponivel extends Exception {

	// Parameterless Constructor
	public DinheiroIndisponivel() {
	}

	// Constructor that accepts a message
	public DinheiroIndisponivel(String message) {
		super(message);
	}
}
