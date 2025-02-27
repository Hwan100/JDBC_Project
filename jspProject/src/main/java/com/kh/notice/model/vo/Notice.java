package com.kh.notice.model.vo;

import lombok.Data;

@Data
public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private int noticeWriter;
	private int count;
	private String createDate;
	private String status;
	
	private String userId;
}
