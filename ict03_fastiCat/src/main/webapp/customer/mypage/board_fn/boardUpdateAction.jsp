<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<script>
			alert("게시글이 수정되었습니다.");
			location.href='${path}/board.bc?board_category=${category}';  
		</script>
</body>
</html>