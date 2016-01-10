/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guiao2.ex4;

/**
 *
 * @author ruifreitas
 */
public interface Bank {
    int createAccount(float initialBalance);
    float closeAccount(int id) ;//throws InvalidAccount;
    void transfer(int from, int to, float amount) ;//throws InvalidAccount, NotEnoughFunds; 
    float totalBalance(int accounts[]);
}
