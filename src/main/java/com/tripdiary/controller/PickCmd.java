package com.tripdiary.controller;

public class PickCmd {
	private int memberNum;
	private int boardNum;
	
	public PickCmd() {

	}

	public PickCmd(int memberNum, int boardNum) {
		this.memberNum = memberNum;
		this.boardNum = boardNum;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	@Override
	public String toString() {
		return "PickCmd [memberNum=" + memberNum + ", boardNum=" + boardNum + "]";
	}
}
