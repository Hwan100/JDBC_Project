package com.kh.board.model.vo;

import lombok.Data;

@Data
public class Board {
	private int boardNo;
	private int boardType;
	private String categoryNo;
	private String boardTitle;
	private String boardContent;
	private int boardWriter;
	private int count;
	private String createDate;
	private String status;
	
	private String categoryName;
	private String userId;
}
