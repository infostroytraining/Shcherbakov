package analyzer;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses({ AnalyzerTest.class, DuplicatesCommandTest.class, FrequencyCommandTest.class,
	LengthCommandTest.class ,CommandConverterTest.class})
public class AllTests {
}
