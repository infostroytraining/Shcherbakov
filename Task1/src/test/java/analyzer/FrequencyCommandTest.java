package analyzer;

import java.io.IOException;

import org.junit.Test;

public class FrequencyCommandTest {
	private FrequencyCommand frequency=new FrequencyCommand();
	@Test
public void testDuplicates(){
		frequency.execute("input.txt", false);
}
	@Test
	public void testDuplicatesParallel(){
		frequency.execute("input.txt", true);
	}
	@Test(expected=IOException.class)
	public void testDuplicatesExs(){
		frequency.execute("qqq", false);
	}
}
