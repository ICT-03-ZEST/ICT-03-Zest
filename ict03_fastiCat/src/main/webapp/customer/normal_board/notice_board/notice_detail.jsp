<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
body {
    font-family: Arial, sans-serif;
    line-height: 1.6;
    margin: 0;
    padding: 0;
}

table {
    width: 80%; /* 반응형 디자인을 위한 너비 조정 */
    margin: 50px auto;
    border-collapse: collapse;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0,0	,0,0.1); /* 카드 스타일 그림자 효과 추가 */
}

th, td {
    border: 1px solid #ddd;
    padding: 12px;
    text-align: center;
}

th {
	background-color: orange;
    font-weight: bold;
}

.button {
    text-align: center;
    margin-top: 20px; /* 버튼 위 여백 조정 */
}

.button input[type="button"] {
 	border: 2px solid orange;
    background-color: white;
    width: 100px;
    height: 50px;
    font-size: 14px;
    color: orange;
    cursor: pointer;
    border-radius: 5px;
</style>
<title>상세페이지</title>
</head>
<body>
     <!-- header 시작-->
   <%@ include file="/common/header.jsp"%>
   <!-- header 끝-->

    <!-- 컨텐츠 시작 -->
        <h2 align="center">공지사항</h2>
        <form name="detailForm" method="post">
        <table>
         <tr>
            <th style="width: 200px">공지사항 번호</th>
            <td style="width: 200px"> ${dto.n_Board_Num} </td>
                <th style="width: 200px">조회수</th>
                <td style="width: 200px"> ${dto.n_ReadCnt} </td>
         </tr>
         
         <tr>
            <th style="width: 200px">공지사항 제목</th>
            <td colspan="3" style="text-align:center">
                ${dto.n_Title}
            </td>
         </tr>
         
         <tr>
            <th style="width: 200px">글내용</th>
            <td colspan="3" style="text-align:center">
                ${dto.n_Content}
            </td>
         </tr>
      

         <tr>
            <th style="width: 200px">작성자</th>
            <td style="width: 200px"> ${dto.n_Writer} </td>
                <th style="width: 200px">작성일</th>
                <td style="width: 200px"> ${dto.n_WriteDate} </td>
         </tr>
      </table>
   
    <!-- 컨텐츠 끝 -->
        <div class="button" align="center" >
            <input type="hidden" name="n_Board_Num" value="${dto.n_Board_Num}"> 
             <input class="inputbutton" type="button" value="수정" id="btnEdit">
             <input class="inputbutton" type="button" value="목록" id="btnList">
             <input class="inputbutton" type="button" value="삭제" id="btnDelete">
        </div>
    </form>   
       <!-- footer 시작-->
    <%@ include file="/common/footer.jsp"%>
   <!-- footer 끝-->

   <script>
      $(function() {
         $('#btnEdit').click(function() {
            alert('수정')
            document.detailForm.action = "${path}/notice_update.nb";
            document.detailForm.submit();
         });
   
         $('#btnList').click(function() {
            location.href = "${path}/notice_boardList.nb";
         });
   
         $('#btnDelete').click(function() {
            alert('삭제하겠습니다. 정말로 삭제하시겠습니까?')
            document.detailForm.action = "${path}/notice_deleteAction.nb";
            document.detailForm.submit();
         });
      });
   </script>
</body>
</html> 