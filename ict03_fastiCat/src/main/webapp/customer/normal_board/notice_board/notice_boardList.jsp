<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
   
<script>
   // 글쓰기 버튼 클릭시 작성페이지로 이동
   $(function(){
      $('#btnInsert').click(function(){
      location.href="${path}/notice_insert.nb"
   });
   });
</script>

<style>
table{
   text-align: center;
   padding: 20px;
}

table th {
   border-bottom: 1px solid orange;
   color: black;   
   height: 50px;
}

table td{
   height: 50px;
   border-bottom: 1px solid orange;
}
.t_container a{
   color: black;
   text-decoration: none;
   
}

.btnNum {
   text-align: center;
   margin-top: 10px;
}

.inputButton {
   padding: 12px 24px;
   background-color: orange;
   border: none;
   color: white;
   cursor: pointer;
   border-radius: 5px;
}
</style>
<title>공지사항 작성</title>
</head>
<body>

   <!-- header 시작-->
  <%@ include file="/common/header.jsp"%> 
   <!-- header 끝-->
   
    <h3 align="center">공지사항 게시판</h3>

    <div class="t_container">

      <table>
         <tr>
            <th style="width: 5%">번호</th>
            <th style="width: 15%">제목</th>
            <th style="width: 5%">작성자</th>
            <th style="width: 5%">작성일</th>
            <th style="width: 1%">조회수</th>
         </tr>

         <c:forEach var="dto" items="${list}">
            <tr>
               <td>${dto.n_Board_Num}</td>
               <td><a
                  href="${path}/notice_detail.nb?n_Board_Num=${dto.n_Board_Num}">${dto.n_Title}</a>
               </td>
               <td>${dto.n_Writer}</td>
               <td>${dto.n_WriteDate}</td>
               <td>${dto.n_ReadCnt}</td>
            </tr>
         </c:forEach>
      </table>


      <div class="btnNum">
         <!-- 페이징처리 -->
         <!-- 이전 버튼 활성화 -->
         <c:if test="${paging.startPage > 10}">
            <a href="${path}/notice_boardList.nb?pageNum=${paging.prev}">[이전]</a>
         </c:if>
         <!-- 페이지 번호 처리 -->
         <c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
            <a href="${path}/notice_boardList.nb?pageNum=${num}">${num}</a>
         </c:forEach   >
         <!-- 다음 번호 활성화 -->
         <c:if test="${paging.endPage < paging.pageCount}">
            <a href="${path}/notice_boardList.nb?pageNum=${paging.next}">[다음]</a>
         </c:if>
    </div>
    <div align="right">
       <input type="button" class="inputButton" value="글쓰기" id="btnInsert">
    </div>
    <!-- footer 시작-->
   <%@ include file="/common/footer.jsp"%> 
   <!-- footer 끝-->
</body>

</html>
