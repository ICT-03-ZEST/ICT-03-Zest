<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>댓글</title> <!-- 댓글페이지 복사 -->
<link rel="stylesheet" type="text/css" href="${path}/resources/css/customer/comment.css"> 
<script src="${path}/resources/js/request.js"></script>
<script type="text/javascript">

$(function() {
	//alert("${pageNum}");
	$('#cancel').click(function() { //댓글 수정 취소
		location.href="${path}/content.bc?board_category=${bd_dto.board_category}&board_num=${bd_dto.board_num}&pageNum=${pageNum}";
	});
});

</script>
</head>
<body>
    <!-- 댓글 목록.. 댓글 작성 ..  -->
    	<form name="cmt_modForm" action="comment_updateAction.bc" method="post">
    		<input type="hidden" name="h_pageNum" value="${pageNum}">
	    	<input type="hidden" name="h_comment_num" value="${dto.comment_num}">
	    	<input type="hidden" name="h_category" value="${dto.board_category}">
	    	
	        <div class="comment"> 
                <div> <span class="comment_num">${dto.comment_num}</span> 
                    <span class="comment_img"><i class="fa-regular fa-circle-user"></i></span>
                    <span class="comment_user" id="userID">${dto.userID}</span>
                    <span class="comment_date">${dto.regDate}</span>
                    <span class="left_btn"><input type="submit" name="btn_modCmt" id="btn_modCmt" class="btn_cmt" value="수정확인"></span>
                    <span><input type="reset" class="btn_cmt" id="cancel" value="취소"></span>
                </div>
                <div><textarea class="comment_text" name="content">${dto.content}</textarea></div>
	        </div>
	      </form>
</body>
</html>