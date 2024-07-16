<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 게시판</title> 
	<script type="text/javascript" src="${path}/resources/js/jquery-3.7.1.min.js"></script>
    <script type="text/javascript">
    alert("목록");
        // 상세페이지 이동
        $(function(){
        	$('#btnInsert').click(function(){
    			location.href="${path}/notice_insert.nb"
    		});
        });
        
        
    </script>
</head>
<body>

	<!-- header 시작-->
	 <%--  <%@ include file="/common/header.jsp"%> --%>
	<!-- header 끝-->
	
    <h3 align="center">공지사항 게시판</h3>

    <div class="t_container">
        <table>
            <thead class="t_head">
                <tr>
                    <th style="width: 5%">번호</th>
                    <th style="width: 15%">제목</th>
                    <th style="width: 5%">작성자</th>
                    <th style="width: 5%">작성일</th>
                    <th style="width: 5%">조회수</th>
                </tr>
            </thead>

            <tbody class="t_body">
            <c:forEach var="dto" items="${list}">
                <tr> 
                    <td>${dto.N_Board_Num}</td>
                    <td>
                    <a href="${path}/notice_content.nb?N_Board_Num=${dto.N_Board_Num}">${dto.N_Title}</a>
                    </td>
                    <td>${dto.N_Writer}</td>
                    <td>${dto.N_WriteDate}</td>
                    <td>${dto.N_ReadCnt}</td>
                </tr>
              </c:forEach>
            </tbody>
        </table>
    </div>
    
    
    <div class="btnNum" align="center">
		<!-- 페이징처리 -->
		<!-- 이전 버튼 활성화 -->
		<c:if test="${paging.startPage > 10}">
			<a href="${path}/notice_boardList.nb?pageNum=${paging.prev}">[이전]</a>
		</c:if>
		<!-- 페이지 번호 처리 -->
		<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
			<a href="${path}/notice_boardList.nb?pageNum=${num}">${num}</a>
		</c:forEach	>
		<!-- 다음 번호 활성화 -->
		<c:if test="${paging.endPage < paging.pageCount}">
			<a href="${path}/notice_boardList.nb?pageNum=${paging.next}">[다음]</a>
		</c:if>
    </div>

    <!-- footer 시작-->
	<%-- <%@ include file="/common/footer.jsp"%> --%>
	<!-- footer 끝-->
</body>

</html>
