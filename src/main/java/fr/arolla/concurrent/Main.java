package fr.arolla.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.System.out;

public class Main {
	private static final int NUMBER_OF_THREADS = 10;
	private static final int INITIAL_BALANCE = 2000;
	private static final int NUMBER_OF_ITERATIONS = 1000;

	public static void main(String[] args) throws InterruptedException {
		final Account[] accounts = new Account[] {new Account(INITIAL_BALANCE), new Account(INITIAL_BALANCE)};
		final Bank bank = new Bank();
		final CountDownLatch startSignal = new CountDownLatch(1);
		final CountDownLatch stopSignal = new CountDownLatch(NUMBER_OF_THREADS);

		final Random random = new Random();
		
		int balanceOne = accounts[0].balance();
		int balanceTwo = accounts[1].balance();
		
		out.println("Initial total balance= " + (balanceOne + balanceTwo));

		class TransferTask implements Runnable {
			public void run() {
				try {
					startSignal.await();
					
					for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
						int rnd = random.nextInt(2);
						Account fromAccount = rnd == 0 ? accounts[0] : accounts[1];
						Account toAccount = rnd == 0 ? accounts[1] : accounts[0];
						bank.transfer(fromAccount, toAccount, 3);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					stopSignal.countDown();
				}
			}
		}

		ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
		
		for (int i = 0; i < NUMBER_OF_THREADS; i++) {
			executor.execute(new TransferTask());
		}

		startSignal.countDown();
		stopSignal.await();
		executor.shutdown();
		
		balanceOne = accounts[0].balance();
		balanceTwo = accounts[1].balance();
		
		System.out.println("Account 1 balance = " + balanceOne);
		System.out.println("Account 2 balance = " + balanceTwo);
		System.out.println("Final total balance = " + (balanceOne + balanceTwo));
	}
}