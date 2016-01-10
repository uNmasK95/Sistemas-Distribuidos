/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aula7;

/**
 *
 * @author ruifreitas
 */
public interface BancoIface {
    
    final static int CONSULTAR = 0;
    final static int CONSULTAR_TOTAL = 1;
    final static int LEVANTAR = 2;
    final static int DEPOSITAR = 3;
    final static int TRANSFERENCIA = 4;
    
    final static int OK = 5;
    final static int KO = 6;
    
    public double consultar(int nr_conta);
    public double consultartotal(int[] nr_contas);
    public void levantar(int nr_conta, double valor) throws DinheiroIndisponivel;
    public void depositar(int nr_conta, double valor);
    public void transferir(int conta_origem, int conta_destino, double valor) throws DinheiroIndisponivel;
}
