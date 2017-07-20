package c.s.basics;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureDemo {
	
	/*
	 * Callable is very similar to Runnable, but returns a generic result.
	 * Callable<V> -> ExecutorService -> Future<V>
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newCachedThreadPool();
		Callable<Integer> task = () -> { Thread.sleep(5000); return 77; };
		Future<Integer> result = service.submit(task);
		System.out.println(result.isDone());
		System.out.println("if not done, getting a result will block current thread");
		System.out.println(result.get());
		service.shutdown(); // closes the thread pool
	}

}
