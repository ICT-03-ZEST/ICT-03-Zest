<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/customer/join.css">

<script src="https://kit.fontawesome.com/e99c5d1543.js" crossorigin="anonymous"></script>
<script type="${path}/resources/js/customer/main.js" defer></script>
<title>회원가입</title>


</head>
<body>

   <div class="wrap">
      <!-- header 시작 -->
 	<%@include file="../../common/header.jsp" %>
     <!-- header 끝 -->
      
      <!-- 컨텐츠 시작 -->
	  <script type="text/javascript">
	  		alert("회원가입 성공!!");
	  		window.location='${path}/login.do';
	  </script>
      <!-- 컨텐츠 끝 -->
      
      
      <!-- footer 시작 -->
 	<%@include file="../../common/footer.jsp" %>
      <!-- footer 끝 -->
      
   </div>
   
</body>
</html>