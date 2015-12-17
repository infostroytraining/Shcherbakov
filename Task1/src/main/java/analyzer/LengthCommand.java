package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class LengthCommand implements Command{

	@Override
	public void execute(String filename,boolean parallel) {
	long startTime=System.currentTimeMillis();
		try {
			Stream<String> stream=Files.lines(Paths.get(filename));
			if(parallel){
			stream=	stream.parallel();
			}
			stream
			.map(line -> line.split("[\\s\\p{Punct}]+")) 
			.flatMap(Arrays::stream) 
			.distinct()
			.sorted(Comparator.comparing(String::length).reversed())
			.limit(3)
			.forEach((string)-> {System.out.println(string+" --> "+string.length());});
		} catch (IOException e) {
			System.err.println("wrong file path");
		} 
	long elapsedTime=System.currentTimeMillis()-startTime;
	System.out.println("Elapsed time: "+elapsedTime+" millis");
	}

}