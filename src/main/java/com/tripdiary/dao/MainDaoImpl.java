package com.tripdiary.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.tripdiary.vo.MainBoardListVo;

@Repository
public class MainDaoImpl implements MainDao{
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<MainBoardListVo> mainBoardList(String sort) throws Exception {
		return sqlSession.selectList("mainMapper.mainBoardList", sort);
	}
}
