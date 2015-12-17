package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class DuplicatesCommand implements Command{
	@Override
	public void execute(String filename, boolean parallel) {
		long startTime=System.currentTimeMillis();
		Set<String> duplicateWords = new HashSet<>();
		try {
			Stream<String> stream=Files.lines(Paths.get(filename));
			if(parallel){
			stream=	stream.parallel();
			}
			stream
			.map(line -> line.split("[\\s\\p{Punct}]+"))
			.flatMap(Arrays::stream)
			.filter(n -> !duplicateWords.add(n))
			.distinct()
			.limit(3)
			.map(string->string.toUpperCase())
			.sorted(Comparator.comparing(String::length))
			.map(string->reverse(string))
			.forEach(System.out::println);
		} catch (IOException e) {
			System.err.println("wrong file path");
		} 
	long elapsedTime=System.currentTimeMillis()-startTime;
	System.out.println("Elapsed time: "+elapsedTime+" millis");
	}
	private String reverse(String text){
		return new StringBuilder(text).reverse().toString();
	}
}

