package com.tripdiary.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tripdiary.controller.PickCmd;
import com.tripdiary.vo.MainBoardListVo;
import com.tripdiary.vo.MemberVo;
import com.tripdiary.vo.PageVo;
import com.tripdiary.vo.PickVo;
import com.tripdiary.vo.ProfileImgVo;
import com.tripdiary.vo.TagVo;

@Repository
public class MainDaoImpl implements MainDao{
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<MainBoardListVo> mainBoardList(PageVo pageVo) throws Exception {
		return sqlSession.selectList("mainMapper.mainBoardList", pageVo);
	}
	
	@Override
	public List<TagVo> mainTagList() throws Exception {
		return sqlSession.selectList("mainMapper.mainTagList");
	}
	
	@Override
	public List<PickVo> mainPickList(int memberNum) throws Exception {
		return sqlSession.selectList("mainMapper.mainPickList", memberNum);
	}
	
	// 세션 확인용 테스트 코드 삭제해야됨
	@Override
	public MemberVo memberLoginTest(String id) throws Exception {
		return sqlSession.selectOne("mainMapper.memberloginTest", id);
	}
	
	@Override
	public PickVo pickCheck(PickVo pickVo) throws Exception {
		return sqlSession.selectOne("mainMapper.pickCheck", pickVo);
	}

	@Override
	public void pickInsert(PickCmd pickCmd) throws Exception {
		sqlSession.insert("mainMapper.pickInsert", pickCmd);
	}
	
	@Override
	public void pickDelete(PickVo pickVo) throws Exception {
		sqlSession.insert("mainMapper.pickDelete", pickVo);
	}
	
	@Override
	public ProfileImgVo profileImg(int memberNum) throws Exception {
		return sqlSession.selectOne("mainMapper.profileImg", memberNum);
	}
	
	@Override
	public List<TagVo> tagSearch(PageVo pageVo) throws Exception {
		return sqlSession.selectList("mainMapper.tagSearch", pageVo);
	}
	
	@Override
	public int listCount() throws Exception {
		return sqlSession.selectOne("mainMapper.listCount");
	}
}
