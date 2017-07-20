package c.s.basics;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println(
				new CompletableFutureDemo().getPriceAsync("aa").get()
				);
		
		new CompletableFutureDemo().printPrice();
	}
	/**
	 * Uses CompletableFuture chaining to a series of async tasks.
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public void printPrice() throws InterruptedException, ExecutionException {		
		CompletableFuture<Double> price = 
				getPriceAsync("a")									
				.thenApply(this::addTax)			 		//  thenApply takes a function and returns a CompletableFuture: addTax will be applied to result of getPriceAsync when it is ready.
				.thenApply(this::addDiscount)
				;
		System.out.println(price.get());
	}
	
	/*
	 * new Thread created inside of this method will perform the work, and complete the future when done. 
	 * CompletableFuture is returned right away, but is incomplete!
	 */
	public CompletableFuture<Double> getPriceAsync(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		
			new Thread(() -> {
				try {
					double price = calculatePrice(product);
					futurePrice.complete(price);
				} catch (Exception ex) { futurePrice.completeExceptionally(ex); }
			}).start();
			
		return futurePrice;
	}
	
	/*
	 * supplyAsync method does everything getPriceAsync does.
	 */
	public CompletableFuture<Double> addTaxAsync(Double base) {
		return CompletableFuture.supplyAsync(() -> addTax(base));
	}
	
	
	private double addDiscount(Double base)	{ return base * 0.95; }
	
	private double addTax(Double base)			{ return base += base * 0.17; }
	
	private double calculatePrice(String product) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		return Math.random() * 1000.0;
	}
	
	
	
}
