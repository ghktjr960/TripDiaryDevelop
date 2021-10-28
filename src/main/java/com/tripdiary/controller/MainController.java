package com.tripdiary.controller;

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
import com.tripdiary.vo.PageVo;
import com.tripdiary.vo.PickVo;
import com.tripdiary.vo.ProfileImgVo;
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
	public String mainView(Model model, PageVo pageVo,
			HttpSession session) throws Exception {
		
		logger.info("main");
		
		try {
			// pageVo : 정렬(sort), 여행지검색(place), 태그(tag)를 가지고 있는 객체
			// 	- 추후에 페이징 처리에 쓰이는 객체
			if(pageVo.getSort() == null && session.getAttribute("sort") == null) {
				// 페이지가 처음 실행됐을 때 정렬값이 아예 존재하지 않는 경우 초기 정렬값 설정
				pageVo.setSort("regdate"); 
				session.setAttribute("sort", pageVo.getSort());
			} else if(pageVo.getSort() == null && session.getAttribute("sort") != null) {
				// 요청되는 정렬값은 없지만 세션에 저장된 정렬값이 있을경우 : 정렬상태를 유지시키기 위한 작업
				pageVo.setSort((String) session.getAttribute("sort")); 
			} else if(pageVo.getSort() != null) {
				// 새로운 정렬값이 들어왔을 때
				session.setAttribute("sort",  pageVo.getSort());
			}
			
			// pageVo값이 어떤게 들어왔는지 확인하기 위해 사용
			System.out.println("sort : " +  pageVo.getSort());
			System.out.println("place : " +  pageVo.getPlace());
			System.out.println("tag : " +  pageVo.getTag());

			// 여행지검색(place), 태그(tag)를 계속사용하여 결과를 유지시키기 위해 세션에 저장
			session.setAttribute("place",  pageVo.getPlace());
			session.setAttribute("tag",  pageVo.getTag());
			
			
			// tag검색을 통해 TagVo에 해당하는 정보를 가져온다.
			// tag검색을 통해 가져온 TagVo에 있는 boardNum으로 검색결과를 나타낸다.
			if(pageVo.getTag() != null && !pageVo.getTag().equals("")) {
				List<TagVo> tagSearch = mainService.tagSearch(pageVo);
				if(tagSearch.isEmpty()) {
					model.addAttribute("tagSearch", null);
					System.out.println("tagSearch 내용 없음");
				} else {
					model.addAttribute("tagSearch", tagSearch);
					System.out.println(tagSearch.toString());
				}
			}
			
			// 메인페이지에서 보여줄 게시글들을 가져옴 : sort값과 place값을 사용
			// 검색시 sort와 place는 하나에 쿼리에서 사용이 가능하지만 tag는 따로 테이블에 요청해 값을 가져와 if문을 사용해 비교해준다.
			List<MainBoardListVo> mainBoardList = mainService.mainBoardList(pageVo);
			
			// 만약 검색 등으로 게시글 결과가 존재하지 않을 경우를 파악하기 위해 사용
			if(mainBoardList.isEmpty()) {
				model.addAttribute("mainBoardList", null);
				System.out.println("mainBoardList 내용 없음");
			} else {
				model.addAttribute("mainBoardList", mainBoardList);
				System.out.println(mainBoardList.toString());
			}

			// 메인페이지에서 게시글을 보여줄 때 사용하기 위한 태그결과이다.
			// 각 게시글 마다 회원 닉네임, 게시글 정보, 태그등을 보여주는데 사용하는 코드
			List<TagVo> mainTagList = mainService.mainTagList();
			System.out.println(mainTagList.toString());
			model.addAttribute("mainTagList", mainTagList);
			
			// 세션 테스트용 코드 삭제해야됨 : 시작 
			// 세션에 저장되어 있는 로그인 세션을 검사하여 있다면 해당 세션에 저장된 회원번호를 통해 프로필 사진에 대한 정보를 가져온다.
			MemberVo memberVo = (MemberVo) session.getAttribute("memberLoginTest");
			if(memberVo != null) {
				System.out.println(memberVo.toString());
				List<PickVo> mainPickList = mainService.mainPickList(memberVo.getMemberNum());
				System.out.println(mainPickList.toString());
				model.addAttribute("mainPickList", mainPickList);

				// 프로필 사진 정보 가져올 DB : 세션에 저장된 회원번호로 사용
				ProfileImgVo profileImgVo = mainService.profileImg(memberVo.getMemberNum());
				System.out.println("profileImgVo : " + profileImgVo);
				session.setAttribute("profileImg", profileImgVo);
				
				
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
		
		// 로그인 시 검색결과, 정렬결과를 초기화하기 위한 작업
		session.removeAttribute("sort");
		session.removeAttribute("place");
		session.removeAttribute("tag");
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

	// 찜하기 버튼을 눌렀을 때 작동되는 코드
	@RequestMapping(value = "/pickClick", method = RequestMethod.GET)
	public String pickClick(PickVo pickVo, HttpServletRequest request, 
			HttpServletResponse response, HttpSession session) throws Exception {
		
		// pickVo에 해당하는 데이터확인
		System.out.println("pickVo : " + pickVo.toString());
		
		// 로그인 상태에서만 동작 : 세션검사
		MemberVo memberVo = (MemberVo) session.getAttribute("memberLoginTest");		
		if(memberVo != null && memberVo.getMemberNum() == pickVo.getMemberNum()) {
			// 회원이 로그인된 상태이고 로그인한 회원과 찜하기를 누른 회원이 같은지 검사 후 같다면 동작
			System.out.println(memberVo.toString());
			
			// 찜하기를 눌렀을 때 해당 게시글의 찜하기가 되어 있는지 확인하는 과정
			// 찜하기를 눌렀을 때 가져온 정보를 사용해 pick테이블에 정보를 확인하여 찜하기를 했는지 안했는지 검사
			// 만약 pick테이블에서 가져오는 정보가 없다면 찜하기를 누르지 않은 상태이고
			// 만약 pick테이블에서 가져오는 정보가 있다면 찜하기를 누른 상태이다.
			PickVo pickCheck = mainService.pickCheck(pickVo);
			if(pickCheck == null) {
				// 테이블에 데이터가 없을 경우 : pick테이블에 찜하기를 눌렀다는 의미로 데이터를 저장
				PickCmd pickcmd = new PickCmd(pickVo.getMemberNum(), pickVo.getBoardNum());
				mainService.pickInsert(pickcmd);
				return "redirect:/";
			} else {
				// 테이블에 데이터가 있을 경우 : pick테이블에 찜하기를 취소했다는 의미로 데이터를 삭제
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
			// 로그인 상태가 아닐 경우 아무런 동작을 하지 않는다.
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
