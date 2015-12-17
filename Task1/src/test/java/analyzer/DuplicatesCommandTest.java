package analyzer;
import java.io.IOException;

import org.junit.Test;
public class DuplicatesCommandTest {
	private DuplicatesCommand duplicates=new DuplicatesCommand();
	@Test
public void testDuplicates(){
	duplicates.execute("input.txt", false);
}
	@Test
	public void testDuplicatesParallel(){
		duplicates.execute("input.txt", true);
	}
	@Test(expected=IOException.class)
	public void testDuplicatesExs(){
		duplicates.execute("qqq", false);
	}
}
