package com.tripdiary.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tripdiary.dao.MainDao;
import com.tripdiary.vo.MainBoardListVo;

@Service
public class MainServiceImpl implements MainService{
	
	@Inject
	private MainDao mainDao;
	
	@Override
	public List<MainBoardListVo> mainBoardList(String sort) throws Exception {
		return mainDao.mainBoardList(sort);
	}
}
