package com.kh.jdbc.controller;

import com.kh.jdbc.model.vo.Word;
import com.kh.jdbc.service.WordService;
import com.kh.jdbc.view.WordMenu;

public class WordController {

	private WordService ws = new WordService();
	public char a =' ';
	public boolean start(String word) {

		char first = word.charAt(0);
		char last = word.charAt(word.length() - 1);
		
		if (first == a || a == ' ') { 		//최근단어의 끝자리가 word의 첫단어와 일치함 || 시작 단어면 패스
			int num = ws.insertUseWord(word);
			switch(num) {
			case -1 -> System.out.println("금지어 입니다.");
			case -2 -> {
				System.out.println("이미 사용한 단어 입니다.");
				return false;
			}
			case 0 -> System.out.println("존재하지 않는 단어 입니다."); 
			default -> {
				System.out.println();
				a = last;
			}
			}
				return true;		
		}
		else {
			System.out.println("끝단어가 맞지 않습니다.");
			return true;
		}
	}
	
	public void deleteTable() {	
		int result = ws.deleteTable();
		if (result > 0) {
			new WordMenu().displaySuccess("기록테이블 초기화");
		} else {
			new WordMenu().displayFail("기록이 없음");
		}
		
	}
	
	public void insertWord(String word) {

		int result = ws.insertWord(word);

		if (result > 0) {
			new WordMenu().displaySuccess("단어 추가 성공");
		} else {
			new WordMenu().displayFail("이미 있는 단어");
		}
	}

	public void deleteWord(String word) {

		int result = ws.deleteWord(word);

		if (result > 0) {
			new WordMenu().displaySuccess("단어 삭제 성공");
		} else {
			new WordMenu().displayFail("단어 삭제 실패");
		}
	}

	public void updateWord(String word_old, String word_new) {

		int result = ws.deleteWord(word_old, word_new);

		if (result > 0) {
			new WordMenu().displaySuccess("단어 수정 성공");
		} else
			new WordMenu().displayFail("단어 수정 실패");
	}

	public void selectList() {
		if (ws.selectList().isEmpty()) {
			new WordMenu().displayNoData("조회 결과가 없습니다.");
		} else {
			new WordMenu().displayMemberList(ws.selectList());
		}
	}


}
