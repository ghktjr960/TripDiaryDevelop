package com.tripdiary.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tripdiary.service.MainService;
import com.tripdiary.vo.MainBoardListVo;
import com.tripdiary.vo.MemberVo;
import com.tripdiary.vo.PickVo;
import com.tripdiary.vo.TagVo;

@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Inject
	private MainService mainService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String main() {
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String mainView(Model model, @RequestParam(value = "sort", required = false) String sort, HttpSession session) throws Exception {
		
		logger.info("main");
		
		try {
			if(sort == null && session.getAttribute("sort") == null) {
				sort = "regdate";
				session.setAttribute("sort", sort);
			} else if(sort == null && session.getAttribute("sort") != null) {
				sort = (String) session.getAttribute("sort");
			} else if(sort != null) {
				session.setAttribute("sort", sort);
			}
			System.out.println("sort : " + sort);
			
			List<MainBoardListVo> mainBoardList = mainService.mainBoardList(sort);
			System.out.println(mainBoardList.toString());
			model.addAttribute("mainBoardList", mainBoardList);
			
			List<TagVo> mainTagList = mainService.mainTagList();
			System.out.println(mainTagList.toString());
			model.addAttribute("mainTagList", mainTagList);
			
			// 세션 테스트용 코드 삭제해야됨 : 시작
			MemberVo memberVo = (MemberVo) session.getAttribute("memberLoginTest");
			if(memberVo != null) {
				System.out.println(memberVo.toString());
				List<PickVo> mainPickList = mainService.mainPickList(memberVo.getMemberNum());
				System.out.println(mainPickList.toString());
				model.addAttribute("mainPickList", mainPickList);
			}
			// 세션 테스트용 코드 삭제해야됨 : 끝
			
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "main";
	}
	
	// 세션 확인용 로그인 테스트 코드 : 삭제해야됨 : 시작
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String signInGet() {
		return "signIn";
	}
	
	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public String signInPost(@RequestParam(value = "userId", required = false) String id,
							 @RequestParam(value = "userPass", required = false) String password,
							 HttpSession session) throws Exception {
		
		session.removeAttribute("sort");
		MemberVo memberLoginTest = mainService.memberLoginTest(id);
		
		if(memberLoginTest.getPassword().equals(password)) {
			session.setAttribute("memberLoginTest", memberLoginTest);
			return "redirect:/main";
		} else {
			return "signIn";
		}
	}
	@RequestMapping(value = "/signOut", method = RequestMethod.GET)
	public String signOutGet(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// 세션 확인용 로그인 테스트 코드 : 삭제해야됨 : 끝
	
	@RequestMapping(value = "/pickClick", method = RequestMethod.GET)
	public String pickClick(PickVo pickVo, HttpServletRequest request, 
			HttpServletResponse response, HttpSession session) throws Exception {
		
		// 로그인 상태에서만 동작 : 세션검사 : sort값 받아서 정렬유지 시켜야됨 => 정렬값을 세션에 저장시키는 방법 생각하기
		MemberVo memberVo = (MemberVo) session.getAttribute("memberLoginTest");
		System.out.println("pickVo : " + pickVo.toString());
		
		
		if(memberVo != null && memberVo.getMemberNum() == pickVo.getMemberNum()) {
			System.out.println(memberVo.toString());
			
			PickVo pickCheck = mainService.pickCheck(pickVo);
			if(pickCheck == null) {
				// 테이블에 데이터가 없을 경우
				
				PickCmd pickcmd = new PickCmd(pickVo.getMemberNum(), pickVo.getBoardNum());
				mainService.pickInsert(pickcmd);
				return "redirect:/";
			} else {
				// 테이블에 데이터가 있을 경우
				mainService.pickDelete(pickCheck);
				return "redirect:/";
			}
			
			/*
			 * 쿼리문
			 * select * from pick where pick_num = ${pickNum} and member_num = ${memberNum}
			 * 1. if : 테이블에 없을 경우 : null이면 insert
			 * 2. if : 테이블에 있을 경우 : not null이면 delete
			 * 
			 */
			
		} else {
			response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 정보를 확인해주세요.'); history.go(-1);</script>");
            out.flush();
			return "redirect:/";
		}
	}
	
	// 게시글 상세보기할 때 사용할 코드 : 게시글 번호 날아오는거 확인완료
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(@RequestParam(value = "boardNum", required = false) String boardNum ) {
		System.out.println("boardNum : " + boardNum);
		
		return "redirect:/";
	}
	
	// 에러페이지
	@RequestMapping(value = "/pageError", method = RequestMethod.GET)
	public String pageError(HttpServletResponse response, Model model) {
		return "pageError";
	}

}
