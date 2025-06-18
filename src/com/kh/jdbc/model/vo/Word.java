package com.kh.jdbc.model.vo;

import java.util.Objects;

public class Word {

	private int wordNo;
	private String word;
	private String definition;
	
	public Word() {
		super();
	}

	public Word(String word, String definition) {
		super();
		this.word = word;
		this.definition = definition;
	}

	public Word(int wordNo, String word, String definition) {
		super();
		this.wordNo = wordNo;
		this.word = word;
		this.definition = definition;
	}

	public int getWordNo() {
		return wordNo;
	}

	public void setWordNo(int wordNo) {
		this.wordNo = wordNo;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	@Override
	public String toString() {
		return "Word [wordNo=" + wordNo + ", word=" + word + ", definition=" + definition + "]";
	}
	
}
