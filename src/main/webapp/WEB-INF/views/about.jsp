<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
<title>Insert title here</title>
</head>
	<script>
        // 링크 색 바꾸기
        function LinkSetColor(color){
            var alist = document.querySelectorAll('a');
            for(var i = 0; i < alist.length; i++)
                alist[i].style.color = color;
        }

        // 바디 글자색 바꾸기
        function BodySetColor(color){
            document.querySelector('body').style.color = color;
        }

        // 바디 배경색 바꾸기
        function BodySetBackgroundColor(color){
            document.querySelector('body').style.backgroundColor = color;
        }

        // 주간, 야간모드
        function day_night_handler(self) {
            var target = document.querySelector('body');
            if (self.value == 'day') {
                BodySetBackgroundColor('RGB(25,25,25)');
                BodySetColor('white');
                LinkSetColor('powderblue')
                self.value = 'night';
            }
            else {
                BodySetBackgroundColor('white');
                BodySetColor('black');
                LinkSetColor('blue')
                self.value = 'day';
            }
        }
    </script>
<body>
	<jsp:include page="common/header.jsp" flush="false" />
	
	<div class="container">
		<h2>About 페이지</h2>
		
	</div>
	
	<input type="button" value="night" onclick="day_night_handler(this)">
   
	<jsp:include page="common/sidebar.jsp" flush="false" />

</body>
</html>




