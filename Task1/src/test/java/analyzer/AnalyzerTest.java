package analyzer;

import org.junit.Test;

public class AnalyzerTest {

	Analyzer analyzer=new Analyzer();
	@Test
	public void testMain() {
		Analyzer.main(new String[]  { "-i", "input.txt", "-t", "frequency" });
	}
	@Test
	public void testHelp() {
		Analyzer.main(new String[]  {"--help" });
	}
	@Test
	public void testWrongParams() {
		Analyzer.main(new String[]  {"--sadp" });
	}
}
