<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <script>
   	//수정버튼을 눌렀을 경우 
   	$('#btnEdit').click(function(){
   		document.detailForm.action="${path}/notice_board_update.nb";
   		document.detailForm.submit();
   	});
   	
   	//목록버튼을 눌렀을 경우
   	$('#btnList').click(function(){
   		location.href="${path}/notice_boardList.nb"
   	});
   </script>
    <title>공지사항</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/normal_board/notice_content.css"> 
</head>
<body>
    <!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->

    <!-- 컨텐츠 시작 -->
    <section>
        <h3>공지사항</h3>
        <div class="container">
            <div class="inner">
                <div class="title">
                	<span>festicat 공지사항 입니다.</span>
                </div> 
                <div class="content">
                    <p>
                        본문
                    </p>
                </div>
            </div>
            <div class ="author" >
             <span> 작성자 : 홍길동</span>
             
            </div>
        </div>
    </section>

    <!-- 컨텐츠 끝 -->
        <div class="button" align="center">
             <input class="inputButton" type="button" value="수정/삭제" id="btnEdit">
             <input class="inputButton" type="button" value="목록" id="btnList">
        </div>
    
       <!-- footer 시작-->
	 <%@ include file="/common/footer.jsp"%>
	<!-- footer 끝-->
</body>
</html>