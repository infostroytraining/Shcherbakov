package analyzer;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.beust.jcommander.ParameterException;

public class CommandConverterTest {
private CommandConverter commandConverter;
@Before
public void start(){
	commandConverter=new CommandConverter();
}
@After
public void end(){
	commandConverter=null;
}
@Test
public void testFrequency(){
	assertTrue(commandConverter.convert("frequency") instanceof FrequencyCommand);
}
@Test
public void testLength(){
	assertTrue(commandConverter.convert("length") instanceof LengthCommand);
}
@Test
public void testDuplicates(){
	assertTrue(commandConverter.convert("duplicates") instanceof DuplicatesCommand);
}
@Test(expected=ParameterException.class)
public void testWrongInput(){
	commandConverter.convert("sad");
}

}
