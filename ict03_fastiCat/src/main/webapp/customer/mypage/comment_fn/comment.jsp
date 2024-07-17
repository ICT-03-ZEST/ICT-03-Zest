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
	
	$('.btn_delCmt').each(function() {
		$(this).click(function() {
			//alert("${pageNum}");
			   let board_num = $(this).data('board_num');
	           let comment_num = $(this).data('comment_num');
	           let board_category = "${dto.board_category}";
	           let pageNum = "${pageNum}";
	           
	           if (confirm("댓글을 삭제하시겠습니까?")) {
	               location.href = '${path}/comment_deleteAction.bc?board_category=' + board_category + '&board_num=' + board_num + '&comment_num=' + comment_num + "&pageNum=" + pageNum;
	           }
		}); 
	});
	
	$('.btn_modCmt').each(function() {
		$(this).click(function() {
			alert("${pageNum}");
		  let param = {
				"pageNum":"${pageNum}",
				"comment_num":$(this).data('comment_num'),
				"board_category":"${dto.board_category}"
			}
         
			$.ajax({
				url:'${path}/comment_update.bc',
				type:'GET',
				data: param,
				dataType: 'html',
				success: function(result) { 
					$('#comment_list').html(result);
				},
				error: function() {
					alert('새로고침 오류');
				}
			});
		});  
	});

	
});

</script>
</head>
<body>
    <!-- 댓글 목록.. 댓글 작성 ..  -->
    <div class="comment_list" id="comment_list" >
        <c:forEach var="dto" items="${list}">
	        <div class="comment"> 
                <div><span class="comment_num">${dto.comment_num}</span> 
                    <span class="comment_img"><i class="fa-regular fa-circle-user"></i></span>
                    <span class="comment_user" id="userID">${dto.userID}</span>
                    <span class="comment_date">${dto.regDate}</span>
                    <!-- 세션아이디가 댓글작성자 아이디와 같을때 -->
                    <c:if test="${dto.userID == sessionID}" ><!-- 댓글 수정/삭제 -->
	                    <span class="left_btn"><button  name="btn_modCmt" id="btn_modCmt" class="btn_cmt btn_modCmt" data-comment_num="${dto.comment_num}">수정</button></span>
	                    <span><button name="btn_delCmt" id="btn_delCmt" class="btn_cmt btn_delCmt" data-comment_num="${dto.comment_num}" data-board_num="${dto.board_num}">삭제</button></span>
                	</c:if>
                </div>
                <div><p class="comment_text">${dto.content}</p></div>
	        </div>
	   </c:forEach>
    </div>
    
</body>
</html>