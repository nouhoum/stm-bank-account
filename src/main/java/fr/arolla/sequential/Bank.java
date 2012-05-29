package fr.arolla.sequential;

/**
 * @NotThreadSafe
 * 
 * @author nouhoum
 */
public class Bank {
	public void transfer(Account from, Account to, int amount) {
		from.withdraw(amount);
		to.deposit(amount);
	}
}
