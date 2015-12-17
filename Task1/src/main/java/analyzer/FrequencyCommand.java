package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
public class FrequencyCommand implements Command {

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
			.collect(groupingBy(identity(), counting()))
			.entrySet()
			.stream()
			.sorted(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed())
			.limit(2)
			.sorted(Comparator.comparing(Map.Entry<String, Long>::getKey).reversed())
			.forEach(entry -> {
				System.out.println(entry.getKey() + " ==> " + entry.getValue());
			});
		} catch (IOException e) {
			System.err.println("wrong file path");
		} 
	long elapsedTime=System.currentTimeMillis()-startTime;
	System.out.println("Elapsed time: "+elapsedTime+" millis");
	}


}
