<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/admin/ad_productAdd.css">

<script src="https://kit.fontawesome.com/b435fbc087.js" crossorigin="anonymous"></script>
<script src="${path}/resources/js/customer/main.js" defer></script>

<script>
	$(function() {	// 상세페이지가 로딩되면
		
		// 댓글목록 자동 호출
		comment_list();
	
		// 1. 댓글 쓰기 버튼 클릭 
		$('#btnCommentSave').click(function() {
			comment_add();
		});
		
		
		// 게시글 수정삭제 버튼 클릭시 컨트롤러의 패스워드 체크화면으로 이동		
		$('#btnEdit').click(function()	{
		document.detailForm.action="${path}/password_chkAction.bc";
		document.detailForm.submit();
		});
		
		// 게시판 목록 버튼 클릭 시 컨트롤러의 목록으로 이동
		$('#btnList').click(function()	{
			 location.href="${path}/board_list.bc";

		});		
	});
	
	// 1. 댓글 쓰기 버튼 클릭
	function comment_add() {	// 2. 함수 실행
		
		// 게시글 번호, 댓글작성, 댓글내용을 파라미터로 넘김
		let param = {
			"board_num" : ${dto.num },	// key : value => 댓글번호 : 게시글번호
			"writer" : $('#writer').val(),
			"content" : $('#content').val()
		}
		
		$.ajax({
			url:  '${path}/comment_insert.bc',	// 3. 컨트롤러로 이동
			type: 'POST', 
			data: param,
			success: function() {	// 6. '콜백함수' : 댓글 쓰기가 완료 되면 서버에서 콜백함수 호출
				$('#writer').val("");
				$('#content').val("");
				comment_list();		// 7. 댓글목록 새로고침 
			},
			error: function() {
				alert("comment_add() 오류");				
			}
		});
	}
	
	// 8. 자동으로 댓글목록 호출
	function comment_list() {
		$.ajax({
			url : '${path}/comment_list.bc',		// 9. 컨트롤러로 이동
			type : 'POST',
			data : 'board_num=${dto.num}', 
			// 서버에서 콜백함수 호출 : result는 comment_list.jsp(컨트롤러에서 넘긴)
			success : function (result) {	// 12. 
				// div id 가 comeentList 자리에 result 출력
				$('#commentList').html(result); // 리스트 출력
			},
			
			error : function () {
				alert("comment_list() 오류");				
			}
		});
		
	}
	
</script>

<title>관리자 - 게시판 상세페이지</title>
</head>
<body>

   <div class="wrap">
      <!-- header 시작 -->
      <%@ include file="/common/header.jsp" %>
      <!-- header 끝 -->
      
      <!-- 컨텐츠 시작 -->
      <div id="container">
         <div id="contents">
            <!-- 상단 중앙1 시작 -->
            <div id="section1">
               <h1 align="center"> 게시판 상세페이지 </h1>
            </div>
            
            <!-- 상단 중앙2 시작 -->
            <div id="section2">
          
               <!-- 우측 화면 시작 -->
               <div id="right">
                  <div class="table_div">
                     <form name="detailForm" method="post">
                        <table>
                           <tr>
                              <th style="width:200px"> 글번호 </th>
                              <th style="width:200px; text-align: center"> ${dto.num } </th>
                              
                              <th style="width:200px"> 조회수 </th>
                              <td style="width:200px; text-align: center"> ${dto.readCnt } </td>
                           </tr>
                           
                           <tr>
                              <th style="width:200px"> 작성자 </th>
                              <td style="width:200px; text-align: center"> ${dto.writer } </td>
                              
                              <th style="width:200px"> 비밀번호 </th>
                              <td style="width:200px; text-align: center"> 
                           		<input style="width:200px" type="password" class="input" name="password"
                           			id="password" size="30" placeholder="비밀번호 입력" required autofocus>
                          		
                          		<c:if test="${param.message == 'error'}">
								<br><span style="color:red">비밀번호 불일치!</span>                          			
                          		</c:if>
                          		</td>
                          		
                           </tr>
                           
                           <tr>
                              <th style="width:200px"> 글제목 </th>
                              <td colspan="3" style="text-align: center"> ${dto.title } </td>
                           </tr>
                             
                           <tr>
                              <th style="width:200px"> 글내용 </th>
                              <td colspan="3" style="text-align: center"> ${dto.content } </td>
                           </tr> 
                            
                           <tr>
                              <th style="width:200px"> 작성일 </th>
                              <td colspan="3" style="text-align: center"> ${dto.regDate } </td>
                           </tr>  
                           
                           <tr>
								<td colspan="4">
									<br>
									<div align="center">
										<!-- 게시글번호 hidden 추가 -->
										<input type="hidden" name="hidden_num" value="${dto.num }" >
										<input class="inputButton" type="button" value="수정/삭제" id="btnEdit">
										<input class="inputButton" type="button" value="목록" id="btnList">
									</div>
								</td>
							</tr>
                        </table>
                        
                        <br><br>
                        
                        <!-- 댓글 목록 코드 -->
                    	<div id="commentList" align="center">
                    		<!-- 댓글목록을 받음 -->
                    	
                    	</div>
                        
                        
                        <br><br>

                        <!-- 댓글 입력 코드 -->
						<table>
                           <tr>
                              <th style="width:200px"> 댓글작성자 </th>
                              <td style="width:200px; text-align: left;">
                              <input style="width:200px; text-align: center" type="text" class="input"
                              		name="writer" id="writer" size="30" placeholder="작성자 입력">
                           		</td>
                              
                              <th style="width:40px" rowspan="2" align="right">
                              	<center><input type="button" class="inputButton" value="작성" id="btnCommentSave"></center>
                              </th>
                           </tr>
                           
                           <tr>
                              <th style="width:300px"> 글내용 </th>
                              <td style="width:200px; text-align: center">
                               <textarea rows="5" cols="93" name="content" id="content" placeholder="글내용 입력"></textarea>
                           </tr>
                             
                        </table>
                     </form>
                  </div>
               </div>
               <!-- 우측 화면 종료 -->
            </div>
         </div>
      </div>
      <!-- 컨텐츠 끝 -->
      
     <!-- footer 시작 -->
      <%@ include file="/common/footer.jsp" %>
    <!-- footer 끝 -->
   </div>
   
   
</body>
</html>