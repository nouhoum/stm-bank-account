package fr.arolla.concurrent;

public class Account {
	private int balance;

	public Account(int balance) {
		this.balance = balance;
	}

	public synchronized int balance() {
		return balance;
	}

	public synchronized void withdraw(final int amount) {
		balance -= amount;
	}
	
	public synchronized void deposit(final int amount) {
		balance += amount;
	}
}
