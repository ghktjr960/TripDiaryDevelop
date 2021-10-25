package com.tripdiary.vo;

import java.sql.Timestamp;
import java.util.Date;

public class MainBoardListVo {
	private int boardNum;
	private int memberNum;
	private String place;
	private String content;
	private Timestamp regdate;
	private Date tripdate;
	private int tdLikeCnt;
	private String orgFileName;
	private String storeFileName;
	private String nickname;
	
	
	public MainBoardListVo() {

	}
	
	public MainBoardListVo(int boardNum, int memberNum, String place, String content, Timestamp regdate, 
			Date tripdate, int tdLikeCnt, String orgFileName, String storeFileName, String nickname) {
		
		this.boardNum = boardNum;
		this.memberNum = memberNum;
		this.place = place;
		this.content = content;
		this.regdate = regdate;
		this.tripdate = tripdate;
		this.tdLikeCnt = tdLikeCnt;
		this.orgFileName = orgFileName;
		this.storeFileName = storeFileName;
		this.nickname = nickname;
	}

	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public Date getTripdate() {
		return tripdate;
	}
	public void setTripdate(Date tripdate) {
		this.tripdate = tripdate;
	}
	public int getTdLikeCnt() {
		return tdLikeCnt;
	}
	public void setTdLikeCnt(int tdLikeCnt) {
		this.tdLikeCnt = tdLikeCnt;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getStoreFileName() {
		return storeFileName;
	}
	public void setStoreFileName(String storeFileName) {
		this.storeFileName = storeFileName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "MainBoardListVo [boardNum=" + boardNum + ", memberNum=" + memberNum + ", place=" + place + ", content="
				+ content + ", regdate=" + regdate + ", tripdate=" + tripdate + ", tdLikeCnt=" + tdLikeCnt
				+ ", orgFileName=" + orgFileName + ", storeFileName=" + storeFileName + ", nickname=" + nickname + "]";
	}
	
	
	
}
