package analyzer;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class Analyzer {

	public static void main(String[] args) {
		AnalyzerBean analyzerBean=new AnalyzerBean();
		try{
		JCommander jCommander=new JCommander(analyzerBean, args);
		if(analyzerBean.isHelp()){
		jCommander.usage();
		}
		Command command=analyzerBean.getTask();
		if(command!=null){
		command.execute(analyzerBean.getInputFile(),analyzerBean.isParallel());}}
		catch(ParameterException ex){
			System.err.println("Wrong parameters,type --help for more info");
		}
	}

}
