package com.tripdiary.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tripdiary.service.MainService;
import com.tripdiary.vo.MainBoardListVo;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Inject
	private MainService mainService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model, @RequestParam(value = "sort", required = false) String sort) throws Exception {

		logger.info("main");

		try {

			if (sort == null) {
				sort = "regdate";
			}

			List<MainBoardListVo> mainBoardList = mainService.mainBoardList(sort);
			System.out.println(mainBoardList.toString());

			model.addAttribute("mainBoardList", mainService.mainBoardList(sort));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "main";
	}
}
