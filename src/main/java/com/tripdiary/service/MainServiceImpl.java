package com.tripdiary.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tripdiary.controller.PickCmd;
import com.tripdiary.dao.MainDao;
import com.tripdiary.vo.MainBoardListVo;
import com.tripdiary.vo.MemberVo;
import com.tripdiary.vo.PageVo;
import com.tripdiary.vo.PickVo;
import com.tripdiary.vo.ProfileImgVo;
import com.tripdiary.vo.TagVo;

@Service
public class MainServiceImpl implements MainService{
	
	@Inject
	private MainDao mainDao;
	
	@Override
	public List<MainBoardListVo> mainBoardList(PageVo pageVo) throws Exception {
		return mainDao.mainBoardList(pageVo);
	}
	
	@Override
	public List<TagVo> mainTagList() throws Exception {
		return mainDao.mainTagList();
	}
	
	@Override
	public List<PickVo> mainPickList(int memberNum) throws Exception {
		return mainDao.mainPickList(memberNum);
	}
	
	// 세션 확인용 테스트 코드 삭제해야됨
	@Override
	public MemberVo memberLoginTest(String id) throws Exception {
		return mainDao.memberLoginTest(id);
	}
	
	@Override
	public PickVo pickCheck(PickVo pickVo) throws Exception {
		return mainDao.pickCheck(pickVo);
	}
	
	@Override
	public void pickInsert(PickCmd pickCmd) throws Exception {
		mainDao.pickInsert(pickCmd);
	}
	
	@Override
	public void pickDelete(PickVo pickVo) throws Exception {
		mainDao.pickDelete(pickVo);
	}
	
	@Override
	public ProfileImgVo profileImg(int memberNum) throws Exception {
		return mainDao.profileImg(memberNum);
	}
	
	@Override
	public List<TagVo> tagSearch(PageVo pageVo) throws Exception {
		return mainDao.tagSearch(pageVo);
	}
	
	@Override
	public int listCount() throws Exception {
		return mainDao.listCount();
	}
}
