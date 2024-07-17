<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <!-- header 시작 -->
	<%@ include file="/common/header.jsp" %>
      <!-- header 끝 -->
      
      <!-- 컨텐츠 시작 -->
      <!-- 서비스에서 넘어온 값을 받는다. -->
	<c:if test="${updateCnt == 1 }">
	<script type="text/javascript">
	  alert("회원수정 성공!!");
	  window.location='main.do';
	  </script>
	</c:if>
	
	<c:if test="${updateCnt != 1 }">
	<script type="text/javascript">
		  alert("회원수정 실패!!");
		  window.location='modifyDetailAction.do';		  
	</script>
	</c:if>
      <!-- 컨텐츠 끝 -->
      
      
      <!-- footer 시작 -->
	<%@ include file="/common/footer.jsp" %>
      <!-- footer 끝 -->
</body>
</html>