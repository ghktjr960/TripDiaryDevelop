package com.tripdiary.dao;

import java.util.List;

import com.tripdiary.vo.MainBoardListVo;

public interface MainDao {
	
	public List<MainBoardListVo> mainBoardList(String sort) throws Exception;
}
