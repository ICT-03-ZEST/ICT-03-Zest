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

	<script type="text/javascript">
	alert("${strId}");
	function bdDelPwdChk() {
		   
		   let param = {
			  "password": $('#pwd_chk').val()
		   };
		   
		   $('#pwd_chk').val('');
		   
		   $.ajax({
	           url :'${path}/bdDelPwdChk.myp' ,         //3.
	           type : 'POST',
	           data : param,                  //요청데이터 형식(html,xml,json,text)
	           success : function(data){      //6. 콜백함수 - 전송성공시의 결과가 result에 전달된다.
	        	  let result = document.getElementById("bd_del_popup");
	         	  result.innerHTML = data;
	           	  
	        	  bdDelChkClosePopup();
	        	  delChkShowPopup();
	           },
	           error : function(){
	              alert('bdDelPwdChk() 오류');
	           }
	        });
		   
	   }
	
	function deleteConfirm() {
		
			 let checkedCheckboxIds = [];
	
	         // 체크된 체크박스들을 순회하며 ID 값을 리스트에 추가
	         $('input[type="checkbox"]:checked').each(function() {
	             
	          // ID에서 특정 문자 제거
                 let id = $(this).attr('id').replace('_chkBox', '');
                 checkedCheckboxIds.push(id);
	         });
	         
	      	// 중복 제거
             let uniqueIds = [...new Set(checkedCheckboxIds)];
			
             let param = {
      			  "num_list": uniqueIds
      		 };
             
			alert(param.num_list);
           //여기부터 작업
		   $.ajax({
	           url :'${path}/BoardDeleteAction.myp' ,         //3.
	           type : 'POST',
	           data : param,                  //요청데이터 형식(html,xml,json,text)
	           success : function(data){            //6. 콜백함수 - 전송성공시의 결과가 result에 전달된다.
	           	  alert("삭제가 완료되었습니다.")
	           	  
	              delChkClosePopup();
	           },
	           error : function(){
	              alert('deleteConfirm() 오류');
	           }
	        });
	   }
	</script>
</head>
<body>	
	<!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->
	
	
    <div class="container_box">
		<div class="container">
			<div class="writing">
		        <input type="button" name="boardWrite" class="write" value="글쓰기" onclick="location.href='${path}/myWriting.bc'">
		        <input type="button" name="delete" class="delete" value="삭제" onclick="bdDelChkShowPopup()">
		    </div>
			<table class="board_list">  <!-- 가능하면 자유/ 후기 나누기-->
				<thead>
		        <tr>
		        	<td class="td_chk"></td>
		            <th class="serialNum">번호</th>
		            <th class="title">제목</th>
		            <th class="category">카테고리</th>
		            <th class="writer">글쓴이</th>
		            <th class="regDate">작성일</th>
		            <th class="views">조회</th>
		            <th class="like"><i class="fa-regular fa-heart"></i></th>
		        </tr> 
				</thead>

		        <!-- 게시글이 있으면 -->
		        <tbody>
           		<c:forEach var="dto" items="${list}"> 
            		<tr>
						<td class="td_chk"><input type="checkbox" class="chkBox" id="${dto.board_num}_chkBox"></td>
			            <td class="serialNum"> ${dto.board_num} </td>
			            <td class="title">
			            	
							<a href="${path}/content.bc?board_num=${dto.board_num}&board_category=${dto.board_category}&pageNum=${paging.pageNum}&views=1">
								${dto.board_title}
							</a>
							
						</td>
			            <td class="category">${dto.board_category}</td>
			            <td class="writer">${dto.board_title}</td>
			            <td class="regDate">${dto.board_regDate}</td>
			            <td class="views">${dto.board_views}</td>
			            <td class="like"><i>${dto.board_heart}</i></td>
		        	</tr> 
           		</c:forEach>
				</tbody>
				
				<tfoot>
				<tr>
           			<td colspan="9" align="center">
           				<!-- 페이징 처리 -->
           				<!-- 이전 버튼 활성화 -->
           				<c:if test="${paging.startPage > 10}">
           					<a href="${path}/myBoardList.myp?pageNum=${paging.prev}">[이전]</a>
           				</c:if>
           				<!-- 페이지 번호 처리 -->
           				<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
           					<a href="${path}/myBoardList.myp?pageNum=${num}">${num}</a>
           				</c:forEach>
           				
           				<!-- 다음 버튼 활성화 -->
           				<c:if test="${paging.endPage < paging.pageCount}">
           					<a href="${path}/myBoardList.myp?pageNum=${paging.next}">[다음]</a>
           				</c:if>
           			</td>
           		</tr>
           		</tfoot>
			</table>
		</div>
	</div>
	
	<!-- 게시글 삭제 확인 팝업 -->
    <div id="bd_del_popup" class="bd_del_popup">
        
    </div>
    
	<!-- 게시글 삭제 본인 확인 -->
    <div id="bd_del_chk_popup" class="bd_del_chk_popup">
        <div class="popup-header">비밀번호 확인</div>
        
        <div class="chk_popup-body"> 
            비밀번호를 입력해주세요
            <table>
            	<tr>
            		<td>비밀번호</td>
            		<td><input id="pwd_chk" class="pwd_chk" type="text" placeholder="비밀번호확인"></td>
            	</tr>
            </table>
        </div>
        <div>
            <button class="pop_button" onclick="bdDelPwdChk()">확인</button>
            <button class="pop_button" onclick="bdDelChkClosePopup()">취소</button>
        </div>
    </div>
	
  	<!-- footer 시작-->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer 끝-->
	
  <script type="text/javascript">
	//게시글 삭제 확인 팝업
	function bdDelChkShowPopup() {
	    document.getElementById('bd_del_chk_popup').style.display = 'block';
	    $('.dis_btn').prop('disabled', true);
	    $(".page_out").css("opacity","30%");
	}
	
	function bdDelChkClosePopup() {
	    document.getElementById('bd_del_chk_popup').style.display = 'none';
	    $('.dis_btn').prop('disabled', false);
	    $(".page_out").css("opacity","");
	}
	
	//게시글 삭제 확인 팝업
	function delChkShowPopup() {
	    document.getElementById('bd_del_popup').style.display = 'block';
	    $('.dis_btn').prop('disabled', true);
	    $(".page_out").css("opacity","30%");
	}
	
	function delChkClosePopup() {
	    document.getElementById('bd_del_popup').style.display = 'none';
	    $('.dis_btn').prop('disabled', false);
	    $(".page_out").css("opacity","");
	}
  </script>
		
</body>
</html>