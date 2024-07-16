<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>공지사항 수정페이지</title>
   <link rel="stylesheet" type="text/css" href="${path}/resources/css/normal_board/notice_Edit.css"> 
  <script>
  	$(function(){	//공지사항 수정페이지가 로딩
  		
  		//공지사항 목록버튼 클릭시 컨트롤러의 목록으로 이동되도록함
  		$('#btnList').click(function(){
  			location.href="${path}/notice_board.nb"
  		});
  	
  		//공지사항 수정페이지로 이동
  		$('#btnEdit').click(function(){
  			const title= $('#title').val();
  			const content= $('#content').val();
  			
  			if(title == ""){
  				alert("제목을 입력해주세요.");
  				$('#title').focus();
  				return false;
  			}
  			
  			if(content ==""){
  				alert("내용을 입력해주세요.");
  				$('#content').focus();
  				return false;
  			}
  			document.editForm.action="${path}/notice_updateAction.nb";
  			document.editForm.submit();
  		});
  		
  		$('#btnDelete').click(function(){
  		 	if(confitm("삭제하시겠습니까?")){
 		 	document.editForm.action="${path}/notice_deleteAction.nb";
			document.editForm.submit();
  		 	}
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
		<form name="editForm" method="post">
		<table>
			<tr>
				<th style="width: 200px">공지사항 번호</th>
				<td style="width: 200px">100</td>
                <th style="width: 200px">조회수</th>
                <td style="width: 200px">100</td>
			</tr>
			
			<tr>
				<th style="width: 200px">공지사항 제목</th>
				<td colspan="3" style="text-align:center">
				  <input type="text" class="input" name="title" id="title" size="60" placeholder="글제목 입력" required>
				</td>
			</tr>
			
			<tr>
				<th style="width: 200px">글내용</th>
				<td colspan="3" style="text-align:center">
				  <textarea rows="5" cols="93" name="content" id="content"></textarea> 
				</td>
			</tr>
			
			<tr>
               <th style="width: 200px">작성일</th>
               <td colspan="3" style="text-align:center">100 </td>
            </tr>
		</table>
		</form>
	</div>
	
	<div align="center">
		<input type="hidden" name="hidden_num" value="${dto.num}">
        <input class="inputButton" type="button" value="저장" id="btnEdit">
        <input class="inputButton" type="button" value="삭제" id="btnDelete">
        <input class="inputButton" type="button" value="목록" id="btnList">
	</div>
    <!-- footer 시작-->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer 끝-->
</body>
</html>