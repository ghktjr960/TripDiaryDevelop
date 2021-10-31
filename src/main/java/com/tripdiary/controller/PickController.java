package com.tripdiary.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripdiary.service.MainService;
import com.tripdiary.vo.MemberVo;
import com.tripdiary.vo.PickVo;

@Controller
public class PickController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Inject
	private MainService mainService;	
	
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
			
			/*
				 찜하기를 눌렀을 때 해당 게시글의 찜하기가 되어 있는지 확인하는 과정
				 찜하기를 눌렀을 때 가져온 정보를 사용해 pick테이블에 정보를 확인하여 찜하기를 했는지 안했는지 검사
				 만약 pick테이블에서 가져오는 정보가 없다면 찜하기를 누르지 않은 상태이고
				 만약 pick테이블에서 가져오는 정보가 있다면 찜하기를 누른 상태이다.
			 */
			PickVo pickCheck = mainService.pickCheck(pickVo);
			String updateType = null;
			// mybatis에서 updateQuery를 받아 insert면 +1 delete면 -1해줘야됨

			PickCmd pickCmd = null;
			System.out.println("pickCheck : " + pickCheck);
			
			if(pickCheck == null) {
				// 테이블에 데이터가 없을 경우 : pick테이블에 찜하기를 눌렀다는 의미로 데이터를 저장, member_act_cnt + 1
				updateType = "insert";
				pickCmd = new PickCmd(pickVo.getMemberNum(), pickVo.getBoardNum(), updateType);
				mainService.pickInsert(pickCmd);
				mainService.memberActCntPick(pickCmd);
				return "redirect:/";
			} else {
				// 테이블에 데이터가 있을 경우 : pick테이블에 찜하기를 취소했다는 의미로 데이터를 삭제, member_act_cnt - 1
				updateType = "delete";
				pickCmd = new PickCmd(pickCheck.getMemberNum(), pickCheck.getBoardNum(), updateType);
				mainService.pickDelete(pickCheck);
				mainService.memberActCntPick(pickCmd);
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
	
	@RequestMapping(value = "/pick", method = RequestMethod.GET)
	public String pick() {
		return "pick";
	}
	
}
