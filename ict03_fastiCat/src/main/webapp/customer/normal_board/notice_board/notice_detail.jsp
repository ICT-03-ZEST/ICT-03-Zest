<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
table {
   width: 1200px;
   margin: 50px auto;
   border-collapse: collapse;
}

th, td {
   border: 1px solid orange;
   padding: 10px;
   text-align: center;
   height: 50px;
}

th {
   font-weight: bold;
}

.button {
   text-align: center; /* 가운데 정렬 */
   margin-top: 10px; /* 위 여백 */
}

.button input[type="button"] {
   margin: 5px; 
   padding: 10px 20px; 
   background-color: orange;
   border: none; 
   cursor: pointer; 
   border-radius: 5px; 
}
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