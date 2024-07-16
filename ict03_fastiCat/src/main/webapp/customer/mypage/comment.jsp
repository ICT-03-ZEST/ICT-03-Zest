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
    <script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
</head>
<body>
	
    <!-- 댓글 목록.. 댓글 작성 ..  -->
    <div class="comment_section">
        <div class="comment_list">
            <div class="comment"> 
                <div>
                    <span class="comment_img"><i class="fa-regular fa-circle-user"></i></span>
                    <span class="comment_user">고양이</span>
                    <span class="comment_date">2024-06-28</span>
                </div>
                <p class="comment_text">이것은 예시 댓글입니다.</p>
            </div>

            <!-- 추가 댓글은 이곳에 추가됩니다 -->
            <form class="comment_form" name="comment_form" action="myPage.jsp">
                <textarea id="comment" name="comment" placeholder="댓글을 입력하세요" required></textarea>
                <button type="submit">댓글 작성</button>
            </form>
        </div>
    </div>
    
</body>
</html>