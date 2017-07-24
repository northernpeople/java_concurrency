package c.s.streams;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapDemp {

	public static void main(String[] args) {
		System.out.println("\n~~~ first example");
		
			System.out.println(
					Stream.of(Arrays.asList("AA", "BB"), Arrays.asList("CC", "DD")) 	// <- stream of lists
					.flatMap(l -> l.stream())														// <- maps each list to stream and flattens the result.
					.collect(Collectors.toList())
			);
		
		System.out.println("\n~~~ without flatMap");
		
			Stream.of("a b c", "d e f")			// stream of words
			.forEach(System.out::println);
		
		
		System.out.println("\n~~~ with flatMap");
		
			/* flatMap takes a function that returns a stream from each element of the original stream. Resulting stream of streams is then flattened into one stream. */
		
			Stream.of("a b c", "d e f")						// stream of words
			.flatMap( a -> Stream.of(a.split("\\s")) )
			.forEach(System.out::println);


	}

}
