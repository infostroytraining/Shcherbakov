package analyzer;
import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
public class CommandConverter implements IStringConverter<Command> {
@Override
	public Command convert(String value){
		Command command=null;
		if(value.equals("frequency")){
			command=new FrequencyCommand();
		}else if(value.equals("duplicates")){
			command=new DuplicatesCommand();
		}else if(value.equals("length")){
			command=new LengthCommand();
		}else throw new ParameterException("wrong task name");
		return command;
	}
}
