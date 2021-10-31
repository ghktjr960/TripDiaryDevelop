package com.tripdiary.dao;

import com.tripdiary.controller.PickCmd;
import com.tripdiary.vo.PickVo;

public interface PickDao {

	// 게시글 번호와 회원 번호로 pick테이블에 정보가 있는지 확인
	public PickVo pickCheck(PickVo pickVo) throws Exception;
	
	// 회원이 찜하기를 눌렀을 때 찜하기가 안눌려있을 경우 찜하기 추가
	public void pickInsert(PickCmd pickCmd) throws Exception;

	// 회원이 찜하기를 눌렀을 때 찜하기가 눌려있을 경우 찜하기 삭제
	public void pickDelete(PickVo pickVo) throws Exception;
	
	// 찜하기 눌렀을 때 member_act_cnt테이블에 각 회원마다 찜하기 누른 횟수 저장
	public void memberActCntPick(PickCmd pickCmd) throws Exception;
	
}
