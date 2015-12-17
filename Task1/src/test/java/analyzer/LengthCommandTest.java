package analyzer;

import java.io.IOException;

import org.junit.Test;

public class LengthCommandTest {
	private LengthCommand length=new LengthCommand();
	@Test
public void testDuplicates(){
		length.execute("input.txt", false);
}
	@Test
	public void testDuplicatesParallel(){
		length.execute("input.txt", true);
	}
	@Test(expected=IOException.class)
	public void testDuplicatesExs(){
		length.execute("qqq", false);
	}
}
