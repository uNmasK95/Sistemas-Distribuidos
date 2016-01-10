/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ruifreitas
 */

public class Cliente implements BancoIface {

	private static Scanner input = new Scanner(System.in);;
	private Socket sock;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	public Cliente(String remotehost, int port) {
		try {
			sock = new Socket(remotehost, port);
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
		} catch (IOException ex) {
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public double consultar(int nr_conta) {
		try {
			out.writeInt(CONSULTAR);
			out.writeInt(nr_conta);
			out.flush();
			System.out.println("Foi enviado o numero da conta");
			double valor = in.readDouble();
			return valor;
		} catch (IOException ex) {
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
		}
		return -1;
	}

	@Override
	public double consultartotal(int[] nr_contas) {
		try {
			out.writeInt(CONSULTAR_TOTAL);
			out.writeObject(nr_contas);
			out.flush();
			System.out.println("Foi enviado os numeros das contas");
			double valor = in.readDouble();
			return valor;
		} catch (IOException ex) {
			Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
		}
		return -1;
	}

	@Override
	public void levantar(int nr_conta, double valor) throws DinheiroIndisponivel {
		try {
			out.writeInt(LEVANTAR);
			out.writeInt(nr_conta);
			out.writeDouble(valor);
			out.flush();
			System.out.println("Foi enviado o numero de conta e o valor");
		} catch (IOException ex) {
			System.out.println("Ocorreu um erro no envio dos dados na operação de levantamento");
		}
	}

	@Override
	public void depositar(int nr_conta, double valor) {
		try {
			out.writeInt(DEPOSITAR);
			out.writeInt(nr_conta);
			out.writeDouble(valor);
			out.flush();
			System.out.println("Foi enviado o numero de conta e o valor");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void transferir(int conta_origem, int conta_destino, double valor) throws DinheiroIndisponivel {
		try {
			out.writeInt(TRANSFERENCIA);
			out.writeInt(conta_origem);
			out.writeInt(conta_destino);
			out.writeDouble(valor);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int n;
		Cliente c1 = new Cliente("localhost", 12346);
		
		while ((n = input.nextInt()) != -1) {
			switch (n) {
			case 0: {
				System.out.println("Operação de consultar conta");
				System.out.print("Diga o numero da conta: ");
				int nr_conta = input.nextInt();
				double valor = c1.consultar(nr_conta);
				System.out.println("A conta " + nr_conta + " tem disponivel " + valor);
				break;
			}
			case 1: {
				int[] contas = new int[100];
				int nrc, i = 0;
				System.out.println("Operação de consultar contas");
				System.out.print("Diga os numeros das contas(-1 STOP): ");
				while ((nrc = input.nextInt()) != -1) {
					contas[i++] = nrc;
				}
				double valor = c1.consultartotal(contas);
				System.out.println("As contas tem disponivel " + valor);
				break;
			}
			case 2: {
				System.out.println("Operação de levantar");
				System.out.print("Diga o numero da conta: ");
				int nr_conta = input.nextInt();
				System.out.print("Diga o valor: ");
				double valor = input.nextDouble();
				try {
					c1.levantar(nr_conta, valor);
				} catch (DinheiroIndisponivel dinheiroIndisponivel) {
					System.out.println(dinheiroIndisponivel.getMessage());
				}
				System.out.println("Foi efetuada a operação de levantamento");
				break;
			}
			case 3: {
				System.out.println("Operação depositar");
				System.out.print("Diga o numero da conta: ");
				int nr_conta = input.nextInt();
				System.out.print("Diga o valor: ");
				double valor = input.nextDouble();
				c1.depositar(nr_conta, valor);
				System.out.println("Foi efetuada a operação de deposito");
				break;
			}
			case 4: {
				System.out.println("Operação de transferencia");
				System.out.print("Diga o numero da conta de origem:");
				int nr_contaOrigem = input.nextInt();
				System.out.print("Diga o numero da conta de destino:");
				int nr_contaDestino = input.nextInt();
				System.out.print("Diga o valor: ");
				double valor = input.nextDouble();
				try {
					c1.transferir(nr_contaOrigem, nr_contaDestino, valor);
				} catch (DinheiroIndisponivel dinheiroIndisponivel) {
					dinheiroIndisponivel.printStackTrace();
				}
				break;
			}
			default:
				break;
			}
		}
	}
}
