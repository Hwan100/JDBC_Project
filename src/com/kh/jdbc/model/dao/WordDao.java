package com.kh.jdbc.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.jdbc.model.vo.Word;

public class WordDao {

	private Properties prop = new Properties();

	public WordDao() {
		super();
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//------------------------------------------------------------------------------------------------------


	public boolean check(String word, Connection conn) {//금지어가 있으면 1, 없으면 0
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		boolean a = false;
		String sql = prop.getProperty("check");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, word);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				a = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return a;
	}
	
	public boolean distinctCheck(String word, Connection conn) {//중복단어가 있으면 1, 없으면 0
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		boolean a = false;
		String sql = prop.getProperty("distinctCheck");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, word);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				a = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return a;
	}
	
	public int insertUseWord(Word w, Connection conn) {//api 추가후 String -> Word 객체로 변경
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertUseWord");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, w.getWord());
			pstmt.setString(2, w.getDefinition());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteTable(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteTable");
		
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
//-------------------------------------------------------------------------------------------------------
	public int insertWord(String word, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null, checkpstmt = null;
		String check = prop.getProperty("check");
		String sql = prop.getProperty("insertWord");

		try {
			checkpstmt = conn.prepareStatement(check);
			checkpstmt.setString(1, word);
			ResultSet rs = checkpstmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				// 중복체크
				return 0;
			} else {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, word);

				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteWord(String word, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteWord");

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, word);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateWord(String word_old, String word_new, Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null, checkpstmt = null;
		String check = prop.getProperty("check");
		String sql = prop.getProperty("updateWord");

		try {
			checkpstmt = conn.prepareStatement(check);
			checkpstmt.setString(1, word_new);
			ResultSet rs = checkpstmt.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				// 중복체크
				return 0;
			} else {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, word_new);
				pstmt.setString(2, word_old);

				result = pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList selectList(Connection conn) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList list = new ArrayList();

		String sql = prop.getProperty("selectList");

		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				list.add(rset.getString("WORD_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
}
