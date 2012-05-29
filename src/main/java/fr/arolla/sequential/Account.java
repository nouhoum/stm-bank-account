package fr.arolla.sequential;

/**
 * Sequential version of the account
 * 
 * @NotThreadSafe
 *  
 * @author nouhoum
 */
public class Account {
	private int balance;

	public Account(int initialBalance) {
		this.balance = initialBalance;
	}

	public int balance() {
		return balance;
	}

	public void withdraw(int amount) {
		balance -= amount;
	}

	public void deposit(int amount) {
		balance += amount;
	}
}
