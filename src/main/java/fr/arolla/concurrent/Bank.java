package fr.arolla.concurrent;

public class Bank {
	public void transfer(Account from, Account to, int amount) {
		int toHashCode = to.hashCode();
		int fromHashCode = from.hashCode();

		synchronized (fromHashCode > toHashCode ? to : from) {
			synchronized (fromHashCode > toHashCode ? from : to) {
				from.withdraw(amount);
				to.deposit(amount);
			}
		}
	}
}
