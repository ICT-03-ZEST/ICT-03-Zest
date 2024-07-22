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
		alert("댓글 수정 완료 ");
		location.href='${path}/content.bc?board_category=${dto.board_category}&board_num=${dto.board_num}&pageNum=${pageNum}';  
	</script>
</body>
</html>