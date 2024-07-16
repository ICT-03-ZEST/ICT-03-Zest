<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>공지사항 작성페이지</title>
  <style>

    table {
        width: 1200px;
        margin: 50px auto;
        border-collapse: collapse;
        border: 1px solid orange;
        border-radius: 8px;
    }
    th, td {
        border: 1px solid orange;
        padding: 10px;
        text-align: center;
    }
    th {
        font-weight: bold;
    }
  

    .inputButton{
	border: 2px solid orange;
	background-color: white;
	width: 100px;
	height: 50px;
	font-size: 12px;
	}
    
  </style>
</head>
<body>
    <!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->

    <!-- 컨텐츠 시작 -->
	<div align="center" id="table">
		<table>
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
               <th style="width: 200px">작성자</th>
               <td colspan="3" style="text-align:center">관리자 </td>
            </tr>
		</table>
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