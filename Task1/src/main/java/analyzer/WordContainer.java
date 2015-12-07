package analyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordContainer {

	private List<Word> list = new ArrayList<Word>();

	public List<Word> getList() {
		return list;
	}

	public void addWord(Word word) {
		if (list.contains(word)) {
			list.get(list.indexOf(word)).incrFreq();
		} else {
			list.add(word);
		}

	}

	public void sort() {
		Collections.sort(list);
	}
}
