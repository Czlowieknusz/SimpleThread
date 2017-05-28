import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main implements Runnable {
	static int number = 100000;
	static Lock lock = new ReentrantLock();
	int counter;

	@Override
	public void run() {
		boolean isOver = false;
		while (!isOver) {
			lock.lock();
			if (number > 0) {
				number--;
				System.out.println(Thread.currentThread().getName() + " - " + number);
				counter++;
			} else
				isOver = true;
			lock.unlock();
		}
		System.out.println(Thread.currentThread().getName() + " zadzia³a³ " + counter + " razy");
	}

	public static void main(String[] args) throws InterruptedException {
		Thread[] thread = new Thread[4];
		for (int i = 0; i < 4; i++) {
			thread[i] = new Thread(new Main(), "Thread " + (i + 1));
			thread[i].start();
		}
		for (int i = 0; i < 4; i++) {
			thread[i].join();
		}
	}

}
