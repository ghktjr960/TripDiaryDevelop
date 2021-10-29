package com.tripdiary.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
					/* 
					 * tag검색을 테이블을 따로 사용하기 때문에 검색어가 없을 경우, 검색결과가 없을 때에도 tagSearch은 model에 담기는 값이 없어 null로만 비교하기에는 무리이다.
					 * tagNoSearchResult를 추가하여 jsp에서 tagSearch가 null이고 tagNoSearchResult이 'tagNoSearchResult'일때를 감지하여 화면을 보여준다.
					 * Model tagSearch하나만 사용하면 검색결과가 있을 경우 없을 경우 null인지 아닌지로 구분이 힘듬
					 * jsp에서 empty로 검사하는것과 null로 검사하는 것과 동일한 결과를 가져온다.
					 *  ${tagSearch eq null} == ${empty tagSearch}
					 */
					model.addAttribute("tagSearch", null);
					model.addAttribute("tagNoSearchResult", "tagNoSearchResult");
					System.out.println("tagSearch 내용 없음");
				} else {
					model.addAttribute("tagSearch", tagSearch);
					System.out.println(tagSearch.toString());
				}
			}
			
	 		/* 
	 		 * 페이징 처리
	 		 * 
	 		 * int total = mainService.listCount();
			 *	if(pageVo.getPage() == null) {
			 *		pageVo.setPage(1);
			 *	}
			 * 	pageVo = new PageVo(pageVo.getSort(), pageVo.getPlace(), pageVo.getTag(), total, pageVo.getPage());
		 	 *	System.out.println("pageVo toSting" + pageVo.toString());
			 *	model.addAttribute("paging", pageVo);
			 */

			// 메인페이지에서 보여줄 게시글들을 가져옴 : sort값과 place값을 사용
			// 검색시 sort와 place는 하나에 쿼리에서 사용이 가능하지만 tag는 따로 테이블에 요청해 값을 가져와 if문을 사용해 비교해준다.
			List<MainBoardListVo> mainBoardList = mainService.mainBoardList(pageVo);
			
			// 게시글 결과가 존재하지 않을 경우를 파악하기 위해 사용
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
	
}
