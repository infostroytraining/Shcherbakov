package analyzer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Analyzer {
	private static final String ENCODING = "Cp1251";

	private static void usage() {
		System.out
				.println("Usage: java "
						+ "main.java.analyzer.Analyzer X Y C V,where x=-i or --input,y=file path,c=-t or --task,V=task name(frequency,length,duplicates");
	}

	public static void main(String[] args) {
		if (args.length == 1
				&& (args[0].equals("--help") || args[0].equals("–a"))) {
			usage();
			return;
		}
		if (args.length != 4) {
			usage();
			return;
		}
		String input = args[0];
		if (!input.equals("-i") && !input.equals("--input")) {
			usage();
			return;
		}
		String fileName = args[1];
		String task = args[2];
		if (!task.equals("-t") && !task.equals("--task")) {
			usage();
			return;
		}
		String taskName = args[3];
		String text = readFile(fileName, ENCODING);
		switch (taskName) {
		case "frequency":
			frequency(text);
			break;
		case "length":
			length(text);
			break;
		case "duplicates":
			duplicates(text);
			break;
		default:
			throw new UnsupportedOperationException("No task with this name");
		}
	}

	public static void frequency(String text) {
		long startTime=System.currentTimeMillis();
		WordContainer wordContainer = new WordContainer();
		List<Word> result = new ArrayList<>();

		String[] mass = text.split("\\s");
		for (int i = 0; i < mass.length; i++) {
			wordContainer.addWord(new Word(mass[i]));
		}
		wordContainer.sort();

		List<Word> words = wordContainer.getList();

		result.add(words.get(0));
		result.add(words.get(1));

		Collections.sort(result, new Comparator<Word>() {
			@Override
			public int compare(Word o1, Word o2) {
				return o2.getWord().compareTo(o1.getWord());
			}
		});

		for (Word word : result) {
			System.out.println(word.getWord() + " ==> " + word.getFrequency());
		}
		long elapsedTime=System.currentTimeMillis()-startTime;
		System.out.println("Elapsed time: "+elapsedTime+" millis");
	}

	public static void length(String text) {
		long startTime=System.currentTimeMillis();
		String[] arr = text.split("[\\s\\p{Punct}]");
		Set<String> set = new HashSet<String>();
		Collections.addAll(set, arr);
		List<String> list = new ArrayList<String>(set);
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}

		});
		for (int i = 0; i < 3; i++) {
			String str = list.get(list.size() - 1 - i);
			System.out.println(str + " ==> " + str.length());
		}
		long elapsedTime=System.currentTimeMillis()-startTime;
		System.out.println("Elapsed time: "+elapsedTime+" millis");
	}

	public static void duplicates(String text) {
		long startTime=System.currentTimeMillis();
		String[] arr = text.split("[\\s\\p{Punct}]");
		List<String> list = new ArrayList<String>();
		Collections.addAll(list, arr);
		while (list.contains("")) {
			list.remove("");
		}
		Set<String> set = new HashSet<String>();
		int i = 0;
		for (String str : list) {
			if (!set.add(str)) {
				set.remove(str);
				System.out.println(new StringBuilder(str.toUpperCase())
						.reverse().toString());
				i++;
				if (i == 3) {
					break;
				}
			}
		}
		long elapsedTime=System.currentTimeMillis()-startTime;
		System.out.println("Elapsed time: "+elapsedTime+" millis");
	}

	public static String readFile(String path, String encoding) {
		String s = null;
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			s = new String(encoded, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("wrong encoding");
		} catch (IOException e) {
			System.err.println("wrong file name");
		}
		return s;
	}
}
