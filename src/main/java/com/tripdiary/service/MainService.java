package com.tripdiary.service;

import java.util.List;

import com.tripdiary.controller.PickCmd;
import com.tripdiary.vo.MainBoardListVo;
import com.tripdiary.vo.MemberVo;
import com.tripdiary.vo.PageVo;
import com.tripdiary.vo.PickVo;
import com.tripdiary.vo.ProfileImgVo;
import com.tripdiary.vo.TagVo;

public interface MainService {
	
	public List<MainBoardListVo> mainBoardList(PageVo pageVo) throws Exception;
	
	public List<TagVo> mainTagList() throws Exception;
	
	public List<PickVo> mainPickList(int memberNum) throws Exception;
	
	// 세션 확인용 테스트 코드 삭제해야됨
	public MemberVo memberLoginTest(String id) throws Exception;
	
	public PickVo pickCheck(PickVo pickVo) throws Exception;
	
	public void pickInsert(PickCmd pickCmd) throws Exception;

	public void pickDelete(PickVo pickVo) throws Exception;
	
	public ProfileImgVo profileImg(int memberNum) throws Exception;
	
	public List<TagVo> tagSearch(PageVo pageVo) throws Exception;
}
