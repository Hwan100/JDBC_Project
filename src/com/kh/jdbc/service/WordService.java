package com.kh.jdbc.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.jdbc.model.dao.WordDao;
import com.kh.jdbc.model.vo.Word;
import com.kh.jdbc.api.Dictionary;
public class WordService {
	
	public boolean check(String word) {
		Connection conn = getConnection();
		return new WordDao().check(word, conn);
	}
	
	public boolean distinctCheck(String word) {
		Connection conn = getConnection();
		return new WordDao().distinctCheck(word, conn);
	}
	
	public int insertUseWord(String word){
		Connection conn = getConnection();
		if(check(word)) {
			close(conn);
			return -1;
		}
		
		if(distinctCheck(word)) {
			close(conn);
			return -2;
		}
		
		// API를 호출하고 단어와 설명을 반환받기
		Word w = Dictionary.getWordDefinition(word);
		if(w.getDefinition() == null) {
			close(conn);
			return 0;
		}
		
		int result = new WordDao().insertUseWord(w, conn);
		System.out.print("설명 : " + w.getDefinition() + "\n");
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
	public int deleteTable() {
		Connection conn = getConnection();
		int result = new WordDao().deleteTable(conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

//--------------------------------------------------------------------------------
	public int insertWord(String word) {

		Connection conn = getConnection();
		int result = new WordDao().insertWord(word, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}
	
	public int deleteWord(String word) {

		Connection conn = getConnection();
		int result = new WordDao().deleteWord(word, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}
	
	public int deleteWord(String word_old, String word_new) {

		Connection conn = getConnection();
		int result = new WordDao().updateWord(word_old, word_new, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	public ArrayList selectList(){
		Connection conn = getConnection();
		ArrayList list = new WordDao().selectList(conn);
		
		close(conn);
		
		return list;
	}


}
