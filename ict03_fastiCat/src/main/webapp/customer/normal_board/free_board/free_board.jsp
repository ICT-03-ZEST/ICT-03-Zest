<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>자유게시판</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/css/normal_board/review_free_board.css">
<script src="${path}/resources/js/jquery-3.7.1.min.js"></script>
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function() {
	//alert("${sessionID}");
	
		//로그인 안했을때 글쓰기 선택 
		$('#writing').click(function() {
			
			if(${sessionID.equals("sessionID_1")}) {
				let writing = confirm("로그인 하시겠습니까?");
				
				if(writing) { // 로그인.do 로 이동하도록 경로 바꾸기
					location.href='${path}/common/main.jsp';
				}
				else {
					location.href= '#';
				}
			}
			else {
				alert("${dto.board_category}");
				location.href='${path}/myWriting.bc?board_category=자유';
			}
			
		});	

});
</script>
</head>
<body>
   <!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->
   
    <h3>자유게시판</h3>

     <!-- 검색창 -->
     <div class="formBox">
        <form class="form_srch" action="free_searchAction.html" method="post">
            <input type="search" name="search_text" id="search" size="10px" placeholder="search"><label for="search"><i class="fa-solid fa-magnifying-glass"></i></label>
        </form>
    </div>
    
       <!-- 컨텐츠 시작 -->
    <div class="board_list">
        <ul class="outline">
            <li class="serialNum">번호</li>
            <li class="title">제목</li>
            <li class="writer">작성자</li>
            <li class="regDate">작성일</li>
            <li class="views">조회</li>
            <li class="like"><i class="fa-regular fa-heart"> 11</i></li>
        </ul> 
		
		<c:forEach var="dto" items="${list}">
	        <ul class="post_cover">
	            <div><img src="${dto.board_thumnail}"></div> <!-- 사진 사이즈 조절 -->
	            <li class="serialNum">${dto.board_num}</li>
	            <li class="title">
	            	<a id="detail" href="${path}/content.bc?board_num=${dto.board_num}&board_category=${dto.board_category}&pageNum=${paging.pageNum}">
	            		${dto.board_title}
	            	</a>
	                <ul>
	                    <li class="writer">${dto.board_writer}</li>
	                    <li class="regDate">${dto.board_regDate}</li>
	                    <li class="views">조회 ${dto.board_views}</li>
	                    <li class="like"><i class="fa-regular fa-heart">&nbsp;${dto.board_heart}</i></li>
	                </ul>
	            </li>
	        </ul> 
	     </c:forEach>
	</div>
    <!-- 컨텐츠 끝 -->
    
   <!--  글쓰기 -->
    <input type="button" class="write" id="writing" value="글쓰기">


    <!-- 페이징 처리 -->	
	<div class="btnNum" align="center">
        <div>
        	<!-- 이전 버튼 활성화 -->
			<c:if test="${paging.startPage > 3}"> <!-- 시작페이지가 4부터 시작할때 이전버튼이 보임-->
				<a href="${path}/board.bc?pageNum=${paging.prev}&board_category=자유"> << </a>
			</c:if>
        </div>
        
        <div>
        	<!-- 페이지 번호 처리 -->
			<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
				<a href="${path}/board.bc?pageNum=${num}&board_category=자유">${num}</a>
			</c:forEach>
        </div>
        
        <div>
        	<!-- 다음 버튼 활성화 -->	
			<c:if test="${paging.endPage < paging.pageCount}"> <!-- 마지막페이지가 전체페이지수()보다 작을때 다음버튼이 보임-->
				<a href="${path}/board.bc?pageNum=${paging.next}&board_category=자유"> >> </a>
			</c:if>
        </div>
    </div>

     <!-- footer 시작-->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer 끝-->
</body>
</html>