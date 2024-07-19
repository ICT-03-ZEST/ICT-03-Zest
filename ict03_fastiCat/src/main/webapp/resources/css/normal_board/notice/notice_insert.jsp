<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${path}/resources/css/normal_board/notice_insertAction.css">
<title>공지사항 작성페이지</title>
 <script>
	 $(function(){
	     $('#btnSave').click(function() {
	         const title = $('#title').val();
	         const content = $('#content').val();
	         
	         if(title == ""){
	             alert("게시글 제목을 입력하세요!");
	             $('#title').focus();
	             return false;
	         }
	         
	         if(content == ""){
	             alert("게시글 내용을 입력하세요!");
	             $('#content').focus();
	             return false;
	         }
	         document.insertForm.action = "${path}/notice_insertAction.nb";
	         document.insertForm.submit();
	     });
	 });
  </script>
  
</head>
<body>
    <!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->

    <!-- 컨텐츠 시작 -->
	<div align="center" id="table">
	 <form name="insertForm" method="post">
			<table>
				<tr>
					<th style="width: 200px">공지사항 제목</th>
					<td colspan="3" style="text-align:center">
					  <input type="text" class="input" name="title" id="title" size="60" placeholder="글제목 입력" required>
					</td>
					
					<th style="width: 200px">작성자</th>
	               	<td colspan="3" style="text-align:center">
	               	 <input type="text" class="input" name="writer" id="writer" size="20" placeholder="작성자이름" required>
	                </td>
				</tr>
				
				<tr>
					<th style="width: 200px">글내용</th>
					<td colspan="5" style="text-align:center">
					  <textarea rows="5" cols="93" name="content" id="content" placeholder="본문을 입력하세요"></textarea> 
					</td>
				</tr>
			</table>
		 <input class="inputButton" type="button" value="저장" id="btnSave">
         <input class="inputButton" type="reset" value="초기화" >
	   </form>
	</div>

    <!-- footer 시작-->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer 끝-->
</body>
</html>