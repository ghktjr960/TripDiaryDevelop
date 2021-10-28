<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<link href="${pageContext.request.contextPath}/resources/css/style.css" 
	rel="stylesheet"/>
	
<title>Trip Diary</title>

</head>

<body>
	<jsp:include page="common/header.jsp" flush="false" />

	<div class="container">
		<h1>메인 페이지</h1>
		<br>
		<c:choose>
			<!-- 세션 저장된 값에 따라 글자를 다르게 표시 -->
			<c:when test="${sort eq 'regdate'}">
				<button onclick='location.href="/main?sort=regdate&place=${place}&tag=${tag}"'><b>작성일순</b>	</button>
				<button onclick='location.href="/main?sort=tripdate&place=${place}&tag=${tag}"'>여행일순</button>
				<button onclick='location.href="/main?sort=like&place=${place}&tag=${tag}"'>좋아요순</button>
			</c:when>
			<c:when test="${sort eq 'tripdate'}">
				<button onclick='location.href="/main?sort=regdate&place=${place}&tag=${tag}"'>작성일순</button>
				<button onclick='location.href="/main?sort=tripdate&place=${place}&tag=${tag}"'><b>여행일순</b></button>
				<button	onclick='location.href="/main?sort=like&place=${place}&tag=${tag}"'>좋아요순</button>
			</c:when>
			<c:when test="${sort eq 'like'}">
				<button onclick='location.href="/main?sort=regdate&place=${place}&tag=${tag}"'>작성일순</button>
				<button onclick='location.href="/main?sort=tripdate&place=${place}&tag=${tag}"'>여행일순</button>
				<button onclick='location.href="/main?sort=like&place=${place}&tag=${tag}"'><b>좋아요순</b></button>
			</c:when>
		</c:choose>
		
		<!-- 지역별 검색 기준, 태그검색을 위한 코드 -->
		<form name="search" method="get">
			<select id="place" name="place">
				<option selected disabled hidden>지역선택</option>
				<option value="seoul">서울특별시</option>
				<option value="busan">부산광역시</option>
				<option value="daegu">대구광역시</option>
				<option value="incheon">인천광역시</option>
				<option value="gwangju">광주광역시</option>
				<option value="daejeon">대전광역시</option>
				<option value="ulsan">울산광역시</option>
				<option value="sejong">세종특별자치시</option>
				<option value="gyeonggi">경기도</option>
				<option value="gangwon">강원도</option>
				<option value="chungbuk">충천북도</option>
				<option value="chungnam">충천남도</option>
				<option value="jeonbuk">전라북도</option>
				<option value="jeonnam">전라남도</option>
				<option value="gyeongbuk">경상북도</option>
				<option value="gyeongnam">경상남도</option>
				<option value="jeju">제주도특별자치도</option>
				<option value="abroad">해외</option>
			</select> 
			<input type="text" name="tag" id="tag" placeholder="태그검색" value="${tag}"> 
			<input type="submit" value="검색" id="search">
		</form>


		<!-- 전체 게시물 부분  -->
		<!-- 검색등으로 게시글에 대한 결과가 없을 경우 보여주는 화면 -->
		<c:if test="${mainBoardList eq null}">
			<div class="container">
				<div class="container">
					<img alt="" src="resources/img/error.png" style="width: 30%;">
				</div>
				<div class="container">
					<h1>등록된 글이 없습니다</h1>
				</div>
			</div>
		</c:if>

		<!-- 태그검색결과와 상관없이 보여주는  코드 -->
		<c:if test="${tagSearch eq null}">
			<div class="diary-mid row mt-5 mb-5">
				<c:forEach items="${mainBoardList}" var="mainBoardList">
					<!-- 게시물 1개 부분 이 주석 밑부분 부터 반복문 실행-->
					<div class="col-sm-4 diary-board-container">
						<div class="border border-secondary p-3 icon2">
							<div class="board-top">
								<div style="float: left;">
									<!-- 프로필 이미지와 닉네임 -->
									<c:if test="${mainBoardList.profileStoreFileName ne null}">
										<img alt=""
											src="<spring:url value='/profile/${mainBoardList.profileStoreFileName}.${mainBoardList.profileFileType}'/>"
											class="border rounded-circle"
											style="width: 50px; height: 50px; object-fit: cover;">
									</c:if>
									<c:if test="${mainBoardList.profileStoreFileName eq null}">
										<img alt="" src="resources/img/profile_48.png"
											class="border rounded-circle"
											style="width: 50px; height: 50px; object-fit: cover;">
									</c:if>
									<!-- 각 닉네임별 다이어리 페이지 이동 -->
									<a href="#">
										${mainBoardList.nickname}
									</a>
								</div>

								<!-- pick 이미지 -->
								<div style="float: right; display: inline-block;" class="">
									<c:if test="${mainPickList ne null}">
										<c:forEach items="${mainPickList}" var="mainPickList">
											<c:if
												test="${mainPickList.boardNum eq mainBoardList.boardNum }">
												<c:set var="count" value="${count+1}"></c:set>
												<c:set var="pickNum" value="${mainPickList.pickNum}"></c:set>
												<c:set var="boardNum" value="${mainPickList.boardNum}"></c:set>
												<c:set var="memberNum" value="${mainPickList.memberNum}"></c:set>
											</c:if>
										</c:forEach>
										<c:if test="${count > 0 }">
											<a href="/pickClick?pickNum=${pickNum}&memberNum=${memberNum}&boardNum=${boardNum}"
												onclick="alert('찜하기가 취소되었습니다.')"> 
												<img alt="" src="resources/img/pick_basic_dark.png" class=""
												style="width: 40px; height: 40px; object-fit: cover;">
											</a>
										</c:if>
										<c:if test="${count eq null}">
											<a href="/pickClick?memberNum=${memberLoginTest.memberNum}&boardNum=${mainBoardList.boardNum}"
												onclick="alert('찜하기가 추가되었습니다.')"> 
												<img alt="" src="resources/img/pick_basic_white.png" class=""
												style="width: 40px; height: 40px; object-fit: cover;">
											</a>
										</c:if>
										<c:remove var="count" />
										<c:remove var="pickNum" />
										<c:remove var="boardNum" />
										<c:remove var="memberNum" />

									</c:if>

									<!-- 세션이 없는경우 로그인으로 유도 -->
									<c:if test="${memberLoginTest eq null}">
										<a href="/signIn" onclick="alert('로그인 후 사용가능합니다.')"> 
											<img alt="" src="resources/img/pick_basic_white.png" class=""
											style="width: 40px; height: 40px; object-fit: cover;">
										</a>
									</c:if>
								</div>
							</div>

							<!-- 썸네일 이미지 -->
							<div class="board-mid">
								<a href="/board?boardNum=${mainBoardList.boardNum}"> 
									<img class="image-thumbnail border border-secondary mt-3"
									src="<spring:url value='/main/${mainBoardList.mainStoreFileName}.${mainBoardList.mainFileType}'/>"
									style="width: 100%;">
								</a>
							</div>

							<!-- 하단 정보부분 -->
							<div class="board-bottom mt-5 mb-3">
								<div>
									여행날짜 : <fmt:formatDate value="${mainBoardList.tripdate}" pattern="yyyy-MM-dd" />
								</div>
								<div>
									좋아요 ${mainBoardList.tdLikeCnt}개
								</div>
								<div>
									<c:forEach items="${mainTagList}" var="mainTagList">
										<c:if test="${mainTagList.boardNum eq mainBoardList.boardNum }">
											#${mainTagList.tag}
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>

		<!-- 태그검색을 했을 때 해당 태그에 맞는 결과만 뽑아 보여주는 코드 -->
		<c:if test="${tagSearch ne null}">
			<div class="diary-mid row mt-5 mb-5">
				<c:forEach items="${mainBoardList}" var="mainBoardList">
					<c:forEach items="${tagSearch}" var="tagSearch">
						<c:if test="${tagSearch.boardNum eq mainBoardList.boardNum }">

							<!-- 게시물 1개 부분 이 주석 밑부분 부터 반복문 실행-->
							<div class="col-sm-4 diary-board-container">
								<div class="border border-secondary p-3 icon2">
									<div class="board-top">
										<div style="float: left;">
											<!-- 프로필 이미지와 닉네임 -->
											<c:if test="${mainBoardList.profileStoreFileName ne null}">
												<img alt=""
													src="<spring:url value='/profile/${mainBoardList.profileStoreFileName}.${mainBoardList.profileFileType}'/>"
													class="border rounded-circle"
													style="width: 50px; height: 50px; object-fit: cover;">
											</c:if>
											<c:if test="${mainBoardList.profileStoreFileName eq null}">
												<img alt="" src="resources/img/profile_48.png"
													class="border rounded-circle"
													style="width: 50px; height: 50px; object-fit: cover;">
											</c:if>
											
											<!-- 닉네임별 다이어리 페이지로 이동 -->
											<a href="#">
												${mainBoardList.nickname}
											</a>
										</div>

										<!-- pick 이미지 -->
										<div style="float: right; display: inline-block;" class="">
											<c:if test="${mainPickList ne null}">
												<c:forEach items="${mainPickList}" var="mainPickList">
													<c:if test="${mainPickList.boardNum eq mainBoardList.boardNum }">
														<c:set var="count" value="${count+1}"></c:set>
														<c:set var="pickNum" value="${mainPickList.pickNum}"></c:set>
														<c:set var="boardNum" value="${mainPickList.boardNum}"></c:set>
														<c:set var="memberNum" value="${mainPickList.memberNum}"></c:set>
													</c:if>
												</c:forEach>

												<c:if test="${count > 0 }">
													<a href="/pickClick?pickNum=${pickNum}&memberNum=${memberNum}&boardNum=${boardNum}"
														onclick="alert('찜하기가 취소되었습니다.')"> <img alt=""
														src="resources/img/pick_basic_dark.png" class=""
														style="width: 40px; height: 40px; object-fit: cover;">
													</a>
												</c:if>
												<c:if test="${count eq null}">
													<a href="/pickClick?memberNum=${memberLoginTest.memberNum}&boardNum=${mainBoardList.boardNum}"
														onclick="alert('찜하기가 추가되었습니다.')"> <img alt=""
														src="resources/img/pick_basic_white.png" class=""
														style="width: 40px; height: 40px; object-fit: cover;">
													</a>
												</c:if>
												<c:remove var="count" />
												<c:remove var="pickNum" />
												<c:remove var="boardNum" />
												<c:remove var="memberNum" />
											</c:if>

											<!-- 세션이 없는경우 로그인으로 유도 -->
											<c:if test="${memberLoginTest eq null}">
												<a href="/signIn" onclick="alert('로그인 후 사용가능합니다.')"> 
													<img alt="" src="resources/img/pick_basic_white.png" class=""
													style="width: 40px; height: 40px; object-fit: cover;">
												</a>
											</c:if>
										</div>
									</div>

									<!-- 썸네일 이미지 -->
									<div class="board-mid">
										<a href="/board?boardNum=${mainBoardList.boardNum}"> 
											<img class="image-thumbnail border border-secondary mt-3"
											src="<spring:url value='/main/${mainBoardList.mainStoreFileName}.${mainBoardList.mainFileType}'/>"
											style="width: 100%;">
										</a>
									</div>

									<!-- 하단 정보부분 -->
									<div class="board-bottom mt-5 mb-3">
										<div>
											여행날짜 : <fmt:formatDate value="${mainBoardList.tripdate}" pattern="yyyy-MM-dd" />
										</div>
										<div>
											좋아요 ${mainBoardList.tdLikeCnt}개
										</div>
										<div>
											<c:forEach items="${mainTagList}" var="mainTagList">
												<c:if test="${mainTagList.boardNum eq mainBoardList.boardNum }">
													#${mainTagList.tag}
												</c:if>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</c:forEach>
			</div>
		</c:if>
	</div>

	<jsp:include page="common/sidebar.jsp" flush="false" />
</body>
</html>