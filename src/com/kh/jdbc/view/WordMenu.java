package com.kh.jdbc.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.jdbc.controller.WordController;

public class WordMenu {

	private Scanner sc = new Scanner(System.in);
	private WordController wc = new WordController();

	public void mainMenu() {
		while (true) {
			System.out.println("====== Menu ======");
			System.out.println("1. 시작");
			System.out.println("2. 금지어 추가");
			System.out.println("3. 금지어 삭제");
			System.out.println("4. 금지어 수정");
			System.out.println("5. 금지어 전체출력");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 : ");
			int num = sc.nextInt();
			sc.nextLine();
			System.out.println();
			
			switch (num) {
			case 1 -> {
				gameStart();
				wc.deleteTable();
			}
			case 2 -> insertWord();
			case 3 -> deleteWord();
			case 4 -> updateWord();
			case 5 -> wc.selectList();
			case 9 -> {
				System.out.println("종료합니다.");
				return;
			}
			default -> System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			}

			System.out.println();
		}
	}

	public void gameStart() {
		/*
		 * 처음에는 입력만 출력 api를 통해 표준국어대사전하고 매칭 후, 
		 * 단어랑 뜻 두개만 word객체에 저장해서 반환출력
		 * 이미 word객체에 저장되있는 단어 또는 입력시간 초과시 게임 종료
		 * 단어 : 00 
		 * 설명 : 000 
		 * 입력 :
		 */
		ArrayList arr = new ArrayList();
		
		while (true) {
			System.out.print("단어(gg 입력시 패배) : ");
			String word = sc.nextLine();
			arr.add(word);
			if(word.equals("gg") || !wc.start(word)) {
				System.out.println("패배하셨습니다.\n");
				wc.a = ' '; //초기화
				for (int i = 0; i < arr.size(); i++) {
					if (i == 0) {
						System.out.print("기록 : " + arr.get(0));
					} else {
						System.out.print(" -> " + arr.get(i));
					}
				}
				System.out.println();
				return;
			}
			//
		}
	}
//---------------------------------------------------------------------------------------
	public void insertWord() { // 금지어 추가

		System.out.print("추가할 단어 : ");
		String word = sc.nextLine();

		wc.insertWord(word);
	}

	public void deleteWord() {

		wc.selectList();
		System.out.print("삭제할 단어 : ");
		String word = sc.nextLine();

		wc.deleteWord(word);
	}

	public void updateWord() {
		
		wc.selectList();
		System.out.print("수정전 단어 : ");
		String word_old = sc.nextLine();
		System.out.print("수정후 단어 : ");
		String word_new = sc.nextLine();

		wc.updateWord(word_old, word_new);
	}

	public void displaySuccess(String message) {
		System.out.println("서비스 요청 성공 : " + message);
	}

	public void displayFail(String message) {
		System.out.println("서비스 요청 실패 : " + message);
	}

	public void displayNoData(String message) {
		System.out.println(message);
	}

	public void displayMemberList(ArrayList<String> list) {
//		for (String w : list) {
//			System.out.print(w + ", ");
//		}
		System.out.println(list);
	}
}
