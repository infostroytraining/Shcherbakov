package analyzer;

import org.junit.Test;

public class AnalyzerTest {

	@Test
	public void testDuplicates() {
		String text = "aaa a aa a a aa a a b bb b b c c c c cc c c ccc cc";
		Analyzer.duplicates(text);

	}

	@Test
	public void testLength() {
		String text = "aaa a aa a a aa a a b bb b b c c c c cc c c ccc cc";
		Analyzer.length(text);

	}

	@Test
	public void testFrequency() {
		String text = "aaa a aa a a aa a a b bb b b c c c c cc c c ccc cc";
		Analyzer.frequency(text);

	}

	@Test
	public void testReadFile() {
		Analyzer.readFile("input.txt", "cp1251");
		Analyzer.readFile("input.txt", "qqq");
		Analyzer.readFile("qq", "cp1251");
	}

	@Test
	public void testMainA() {
		Analyzer.main(new String[] { "-a" });
	}

	@Test
	public void testMainHelp() {
		Analyzer.main(new String[] { "--help" });
	}

	@Test
	public void testMainWrongInput() {
		Analyzer.main(new String[] { "q", "q", "q", "q" });
	}

	@Test
	public void testMainWrongInputWithOneArg() {
		Analyzer.main(new String[] { "q" });
	}

	@Test
	public void testMainCorrectInput() {
		Analyzer.main(new String[] { "-i", "input.txt", "-t", "frequency" });
		Analyzer.main(new String[] { "--input", "input.txt", "-t", "duplicates" });
		Analyzer.main(new String[] { "-i", "input.txt", "--task", "length" });
	}

	@Test
	public void testMainWrongThirdArg() {
		Analyzer.main(new String[] { "-i", "input.txt", "gg", "frequency" });
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testMainWrongOperation() {
		Analyzer.main(new String[] { "-i", "input.txt", "-t", "qq" });
	}

	@Test
	public void testInit() {
		new Analyzer();
	}
}
