<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 후 저장을 누르면 나오는 페이지</title>
</head>
<body>
	<c:if test="${updateCnt==1}">
		<script type="text/javascript">
			setTimeout(function() {
				alert("공지사항 수정 성공");
				window.location = "${path}/notice_boardList.nb";
			}, 1000);
		</script>
	</c:if>

	<c:if test="${updateCnt!=1}">
		<script type="text/javascript">
			setTimeout(function() {
				alert("공지사항 수정 실패");
				window.location = "${path}/notice_detail.nb?n_Board_Num=" + ${dto.n_Board_Num};
			}, 1000);
		</script>
	</c:if>
</body>
</html>