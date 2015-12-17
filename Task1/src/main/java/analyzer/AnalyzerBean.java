package analyzer;

import com.beust.jcommander.Parameter;

public class AnalyzerBean {
	@Parameter(names ={"-i","--input"}, description = "File name",required = true)
	String inputFile;
	@Parameter(names={"-t","--task"}, description = "Permitted values: frequency, length, duplicates",converter=CommandConverter.class,required=true)
	Command task;
	@Parameter(names = "--help", help = true)
    private boolean help;
	@Parameter(names={"-p","--parallel"},required=false)
	private boolean parallel=false;
	public String getInputFile() {
		return inputFile;
	}
	public boolean isParallel() {
		return parallel;
	}
	public Command getTask() {
		return task;
	}
	public boolean isHelp() {
		return help;
	}
}
