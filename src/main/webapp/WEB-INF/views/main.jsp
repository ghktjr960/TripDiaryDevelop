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
	</div>


	<!-- 
	<div class="container">
		<img alt="" src="resources/img/1.jpg" style="width: 80%;">
		<img alt="" src="resources/img/2.jpg" style="width: 80%;">
		<img alt="" src="resources/img/2.jpg" style="width: 80%;">
		<img alt="" src="resources/img/2.jpg" style="width: 80%;">
		<img alt="" src="resources/img/2.jpg" style="width: 80%;">
	</div>

	Modal
	<a id="fixedbtn2" class="btn btn-primary mx-1 mt-2 mb-5" data-toggle="modal" href="#reportModal">모달 예시</a>
	<a id="fixedbtn3" class="btn btn-primary mx-1 mt-2 mb-5" data-toggle="modal" href="#reportModal2">모달 예시2</a>
	
	<div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">게시글 상세 보기</h5>
					<button type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true"></span>
					</button>
				</div>
				<div class="modal-body">
					<form action ="./reportAction.jsp" method="post">
						<div class="form-group">
							<label>게시글 사진</label>
							<textarea name="reportContent" class="form-control" maxlength="2048" style="height: 380px;"></textarea>
						</div>
						<div class="form-group">
							<label>게시글 내용</label>
							<input type="text" name="reportTitle" class="form-control" maxlength="30">
						</div>
						<div class="form-group">
							<label>#태그</label>
							<input type="text" name="reportTitle" class="form-control" maxlength="30">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">수정</button>
							<button type="button" class="btn btn-secondary" data-dismiss="modal">삭제</button>
							<button type="submit" class="btn btn-danger">신고</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
		<div class="modal fade" id="reportModal2" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modal">게시글 상세 보기</h5>
					<button type="button" class="btn-close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true"></span>
					</button>
				</div>
				<div class="modal-body">
					<form action ="./reportAction.jsp" method="post">
						<div class="form-group">
							<label>게시글 사진</label>
							<textarea name="reportContent" class="form-control" maxlength="2048" style="height: 380px;"></textarea>
						</div>
						<div class="form-group">
							<label>게시글 내용</label>
							<input type="text" name="reportTitle" class="form-control" maxlength="30">
						</div>
						<div class="form-group">
							<label>#태그</label>
							<input type="text" name="reportTitle" class="form-control" maxlength="30">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">수정</button>
							<button type="button" class="btn btn-secondary" data-dismiss="modal">삭제</button>
							<button type="submit" class="btn btn-danger">신고</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	 -->
	<c:forEach items="${mainBoardList}" var="mainBoardList">
		${mainBoardList}<br>
		
	</c:forEach>

	<jsp:include page="common/sidebar.jsp" flush="false" />


</body>
</html>