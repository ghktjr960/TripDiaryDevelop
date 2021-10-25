package com.tripdiary.service;

import java.util.List;

import com.tripdiary.vo.MainBoardListVo;

public interface MainService {
	
	public List<MainBoardListVo> mainBoardList(String sort) throws Exception;
}
