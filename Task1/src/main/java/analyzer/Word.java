package analyzer;

public class Word implements Comparable<Word> {

	private String word;

	private int frequency;

	public Word(String word) {
		this.word = word;
		frequency = 1;
	}

	public String getWord() {
		return word;
	}

	@Override
	public String toString() {
		return word + " : " + frequency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Word)) {
			return false;
		}
		if (obj.hashCode() != this.hashCode()) {
			return false;
		}
		final Word w = (Word) obj;

		if (!w.getWord().equals(word)) {
			return false;
		}

		return true;
	}

	public void incrFreq() {
		frequency++;
	}

	public int getFrequency() {
		return frequency;
	}

	@Override
	public int compareTo(Word o) {
		int a = o.frequency - frequency;
		if (a == 0) {
			a = word.compareTo(o.word);
		}
		return a;
	}

}
