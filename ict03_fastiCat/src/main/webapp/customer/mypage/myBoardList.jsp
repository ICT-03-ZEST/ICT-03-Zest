<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>나의 게시판</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/customer/myBoardList.css">
    <script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
</head>
<body>	
	<!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->
	
    <div class="container_box">
		<div class="container">
			<table class="board_list">  <!-- 가능하면 자유/ 후기 나누기-->
                <!-- <tr>
                    <td class="td_writing" colspan="9">
                        <div class="writing">
                            글쓰기
                            <a href="myWriting.jsp"><input type="button" class="write" value="글쓰기"></a>	
                       		삭제
                            <a href="#"><input type="button" name="delete" class="delete" value="삭제"></a>	
                        </div>
                    </td>
                </tr> -->

		        <tr>
		        	<td class="td_chk"></td>
		            <th class="serialNum">번호</th>
		            <th class="title" colspan="2">제목</th>
		            <th class="category" colspan="2">카테고리</th>
		            <th class="writer">글쓴이</th>
		            <th class="regDate">작성일</th>
		            <th class="views">조회</th>
		            <th class="like"><i class="fa-regular fa-heart"></i></th>
		        </tr> 
		

		        <!-- 게시글이 있으면 -->
           		<c:forEach var="dto" items="${list}"> 
            		<tr>
						<td class="td_chk"><input type="checkbox" class="del_btn"></td>
			            <td class="serialNum"> ${dto.board_num} </td>
			            <td class="title">
							<div id="thumnail1"><a href="free_content.html"><img src="${dto.board_thumnail}" id="thumnail1" alt="썸네일"></a></div>
						</td>
	                    <td  onclick="moveMyContent()"><div><label for="thumnail1">${dto.board_title}</label></div></td>
			            <td class="category" colspan="2">${dto.board_category}</td>
			            <td class="writer">${dto.board_title}</td>
			            <td class="regDate">${dto.board_regDate}</td>
			            <td class="views">${dto.board_views}</td>
			            <td class="like">${dto.board_heart}</i></td>
		        	</tr> 
           		</c:forEach>

				<tr>
           			<td colspan="9" align="center">
           				<!-- 페이징 처리 -->
           				<!-- 이전 버튼 활성화 -->
           				<c:if test="${paging.startPage > 10}">
           					<a href="${path}/myBoardList.do?pageNum=${paging.prev}">[이전]</a>
           				</c:if>
           				<!-- 페이지 번호 처리 -->
           				<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
           					<a href="${path}/myBoardList.do?pageNum=${num}">${num}</a>
           				</c:forEach>
           				
           				<!-- 다음 버튼 활성화 -->
           				<c:if test="${paging.endPage < paging.pageCount}">
           					<a href="${path}/myBoardList.do?pageNum=${paging.next}">[다음]</a>
           				</c:if>
           			</td>
           		</tr>
			</table>
		</div>
	</div>
	
  	<!-- footer 시작-->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer 끝-->
	
  <script type="text/javascript">
	function moveMyContent() {
		location.href = "myContent.jsp";
	}

  </script>
		
</body>
</html>