<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
<title>Trip Diary</title>

</head>
<body>
	<jsp:include page="common/header.jsp" flush="false" />

	<div class="container">
		<h1>메인 페이지</h1>
	

	<button onclick='location.href="/main?sort=regdate"'>작성일순</button>
	<button onclick='location.href="/main?sort=tripdate"'>여행일순</button>
	<button onclick='location.href="/main?sort=like"'>좋아요순</button>
	<br>

	<!-- 전체 게시물 부분  -->
	<div class="diary-mid row mt-5 mb-5">
	
	
	<c:forEach items="${mainBoardList}" var="mainBoardList">
			<!-- 게시물 1개 부분 이 주석 밑부분 부터 반복문 실행-->
			<div class="col-sm-4 diary-board-container">
				<div class="border border-secondary p-3 icon2">
					<div class="board-top">
						<div style="float: left;">
							<!-- 프로필 이미지와 닉네임 -->
							<img alt="" src="resources/img/sample.png" class="border rounded-circle" style="width: 50px; height: 50px; object-fit: cover;"> 
							${mainBoardList.profileStoreFileName} / ${mainBoardList.nickname}
						</div>
						
						<!-- pick 이미지 -->
						<div style="float: right; display: inline-block;" class="">
								<c:if test="${mainPickList ne null}">
									
									<c:forEach items="${mainPickList}" var="mainPickList">
										<c:if test="${mainPickList.boardNum eq mainBoardList.boardNum }">
											<c:set var="count" value="${count+1}" ></c:set>
											<c:set var="pickNum" value="${mainPickList.pickNum}" ></c:set>
											<c:set var="boardNum" value="${mainPickList.boardNum}" ></c:set>
											<c:set var="memberNum" value="${mainPickList.memberNum}" ></c:set>
										</c:if>
									</c:forEach>
									
									<c:if test="${count > 0 }">
										<a href="/pickClick?pickNum=${pickNum}&memberNum=${memberNum}&boardNum=${boardNum}" onclick="alert('찜하기가 취소되었습니다.')">
											<img alt="" src="resources/img/pick_basic_dark.png" class="" style="width: 40px; height: 40px; object-fit: cover;">
										</a>
									</c:if>
									<c:if test="${count eq null}">
										<a href="/pickClick?memberNum=${memberLoginTest.memberNum}&boardNum=${mainBoardList.boardNum}" >
											<img alt="" src="resources/img/pick_basic_white.png" class="" style="width: 40px; height: 40px; object-fit: cover;">
										</a>
									</c:if>
									<c:remove var="count"/>
									<c:remove var="pickNum"/>
									<c:remove var="boardNum"/>
									<c:remove var="memberNum"/>
									
								</c:if>
								
								<!-- 세션이 없는경우 클릭이 안되게 막음 -->
								<c:if test="${mainPickList eq null}">
									<img alt="" src="resources/img/pick_basic_white.png" class="" style="width: 40px; height: 40px; object-fit: cover;">
								</c:if>
						</div>
					</div>
					
					<!-- 썸네일 이미지 -->
					<div class="board-mid">
						<a href="/board?boardNum=${mainBoardList.boardNum}">
							<img class="image-thumbnail border border-secondary mt-3" src="resources/img/sampleMain.png" style="width: 100%;"> 
						</a>
						${mainBoardList.mainStoreFileName}<br>
						${mainBoardList.boardNum}
					</div>

					<!-- 하단 정보부분 -->
					<div class="board-bottom mt-5 mb-3">
						<div>여행날짜 : <fmt:formatDate value="${mainBoardList.tripdate}" pattern="yyyy-MM-dd" /></div>
						<div>좋아요 ${mainBoardList.tdLikeCnt}개</div>
						<div>
							<c:forEach items="${mainTagList}" var="mainTagList">
								<c:if test="${mainTagList.boardNum eq mainBoardList.boardNum }">
									${mainTagList.tag}
								</c:if>
							</c:forEach>
						</div>
							
					</div>
				</div>
			</div>
		</c:forEach>
		
	</div>
</div>

	<jsp:include page="common/sidebar.jsp" flush="false" />
</body>
</html>