package c.s.basics;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RunnableExecutorDemo {

	/*
	 * Executors are various convenience classes, often implementing a kind of thread pool.
	 * We can submit Runnable tasks to be executed by the executor.
	 */
	public static void main(String[] args) {
		Runnable task1 = () -> System.out.println("task1");
		Runnable task2 = () -> System.out.println("task2");
		
		Executor executor = Executors.newCachedThreadPool();
		executor.execute(task1);
		executor.execute(task2);
		// will run for a while, waiting for new tasks.
	}
}
