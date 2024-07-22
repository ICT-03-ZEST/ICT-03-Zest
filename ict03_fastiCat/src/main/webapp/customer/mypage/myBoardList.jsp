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
	
    window.onload = function() {
    	
    	//시작 테이블 토글
    	let category = "<%= request.getAttribute("category") %>"
    	
    	if(category == "공연후기" || category == null || category == ""){
    		toggleTable('review_table'); 
    	} else if(category == "자유"){
    		toggleTable('free_table'); 
    	}
        
    };
    
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
			
		let num_list = [];
		let category = "";
		
		if ($('#review_category').is(':checked')) {
			 // 체크된 체크박스들을 순회하며 ID 값을 리스트에 추가
	         $('#review_table input[type="checkbox"]:checked').each(function() {
	          // ID에서 특정 문자 제거
                let id = $(this).attr('id').replace('_review_chkBox', '');
                num_list.push(id);
	         });
			 
			 category = "review"
	         
        } else if ($('#free_category').is(':checked')) {
        	 // 체크된 체크박스들을 순회하며 ID 값을 리스트에 추가
	         $('#free_table input[type="checkbox"]:checked').each(function() {
	             
	          // ID에서 특정 문자 제거
                let id = $(this).attr('id').replace('_free_chkBox', '');
                num_list.push(id);
	         });
        	 
	         category = "free"
        }
		
		// 삭제할 항목이 선택되지 않은 경우
	    if (num_list.length === 0) {
	        alert("선택된 항목이 없습니다.");
	        delChkClosePopup();
	        return;
	    }
		
             
          //여기부터 작업
             $.ajax({
           	    url: '${path}/BoardDeleteAction.myp',
	           	 type: 'POST',
	             traditional: true,
	             data: { 'num_list': num_list, 'category': category },
	             success: function(response) {
	                 alert("삭제가 완료되었습니다.");
	                 delChkClosePopup();
	                 location.reload();
	             },
	             error: function(xhr, status, error) {
	                 console.error(xhr.responseText); // 에러 응답 내용을 콘솔에 출력
	                 alert('삭제 중 오류가 발생했습니다.'); // 사용자에게 일반적인 오류 메시지 표시
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
		        <input type="button" name="boardWrite" class="write" value="글쓰기" onclick="goToMyWriting()">
		        <input type="button" name="delete" class="delete" value="삭제" onclick="bdDelChkShowPopup()">
		    </div>
		    
           	<div class="board_category">
	            <label>
	                <input type="radio" id="review_category" name="board_category" value="공연후기" onclick="toggleTable('review_table')"<c:if test="${category == '공연후기'}">checked</c:if>>
	                공연후기
	            </label>
	            <label>
	                <input type="radio" id="free_category" name="board_category" value="자유" onclick="toggleTable('free_table')" <c:if test="${category == '자유'}">checked</c:if>>
	                자유
	            </label>
        	</div>
        	
        	<!-- 자유 -->
			<table id="free_table" class="table-container">  <!-- 가능하면 자유/ 후기 나누기-->
				<thead>
		        <tr>
		        	<td style="width: 20px;" class="td_chk"></td>
		            <th style="width: 40px;"class="serialNum">번호</th>
		            <th style="width: 103px;"class="title">제목</th>
		            <th style="width: 60px;"class="image">이미지</th>
		            <th style="width: 80px;"class="category">카테고리</th>
		            <th style="width: 103px"class="writer">글쓴이</th>
		            <th style="width: 105px	;"class="regDate">작성일</th>
		            <th style="width: 40px;"class="views">조회</th>
		            <th style="width: 20px;"class="like"><i class="fa-regular fa-heart"></i></th>
		        </tr> 
				</thead>

		        <!-- 게시글이 있으면 -->
		        <tbody>
           		<c:forEach var="dto" items="${fbList}"> 
            		<tr>
						<td class="td_chk"><input type="checkbox" class="free_chkBox" id="${dto.board_num}_free_chkBox"></td>
			            <td class="serialNum"> ${dto.board_num} </td>
			            <td class="title">
			            	
							<a href="${path}/content.bc?board_num=${dto.board_num}&board_category=${dto.board_category}&pageNum=${paging.pageNum}&views=1">
								${dto.board_title}
							</a>
							
						</td>
						<td class="thumnail"><img src="${dto.board_thumnail}" alt="${dto.board_thumnail}" class="thumnailImg"></td>
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
           				<c:if test="${fbPaging.startPage > 10}">
           					<a href="${path}/myBoardList.myp?fbPageNum=${fbPaging.prev}&category='자유'">[이전]</a>
           				</c:if>
           				<!-- 페이지 번호 처리 -->
           				<c:forEach var="num" begin="${fbPaging.startPage}" end="${fbPaging.endPage}">
           					<a href="${path}/myBoardList.myp?fbPageNum=${num}&category=자유">${num}</a>
           				</c:forEach>
           				
           				<!-- 다음 버튼 활성화 -->
           				<c:if test="${fbPaging.endPage < fbPaging.pageCount}">
           					<a href="${path}/myBoardList.myp?fbPageNum=${fbPaging.next}&category=자유">[다음]</a>
           				</c:if>
           			</td>
           		</tr>
           		</tfoot>
			</table>
			
			<!-- 공연 -->
			<table id="review_table" class="table-container">  <!-- 가능하면 자유/ 후기 나누기-->
				<thead>
		        <tr>
		        	<td style="width: 20px;" class="td_chk"></td>
		            <th style="width: 40px;"class="serialNum">번호</th>
		            <th style="width: 103px;"class="title">제목</th>
		            <th style="width: 60px;"class="image">이미지</th>
		            <th style="width: 80px;"class="category">카테고리</th>
		            <th style="width: 103px"class="writer">글쓴이</th>
		            <th style="width: 105px	;"class="regDate">작성일</th>
		            <th style="width: 40px;"class="views">조회</th>
		            <th style="width: 20px;"class="like"><i class="fa-regular fa-heart"></i></th>
		        </tr> 
				</thead>

		        <!-- 게시글이 있으면 -->
		        <tbody>
           		<c:forEach var="dto" items="${rbList}"> 
            		<tr>
						<td class="td_chk"><input type="checkbox" class="free_chkBox" id="${dto.board_num}_review_chkBox"></td>
			            <td class="serialNum"> ${dto.board_num} </td>
			            <td class="title">
			            	
							<a href="${path}/content.bc?board_num=${dto.board_num}&board_category=${dto.board_category}&pageNum=${paging.pageNum}&views=1">
								${dto.board_title}
							</a>
							
						</td>
						<td class="thumnail"><img src="${dto.board_thumnail}" alt="${dto.board_thumnail}" class="thumnailImg"></td>
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
           				<c:if test="${rbPaging.startPage > 10}">
           					<a href="${path}/myBoardList.myp?rbPageNum=${rbPaging.prev}&category=공연후기">[이전]</a>
           				</c:if>
           				<!-- 페이지 번호 처리 -->
           				<c:forEach var="num" begin="${rbPaging.startPage}" end="${rbPaging.endPage}">
           					<c:if test="${num != 0}">
           						<a href="${path}/myBoardList.myp?rbPageNum=${num}&category=공연후기'">${num}</a>
           					</c:if>
           				</c:forEach>
           				
           				<!-- 다음 버튼 활성화 -->
           				<c:if test="${rbPaging.endPage < rbPaging.pageCount}">
           					<a href="${path}/myBoardList.myp?rbPageNum=${rbPaging.next}&category=공연후기">[다음]</a>
           				</c:if>
           			</td>
           		</tr>
           		</tfoot>
			</table>
         	<!-- 목록으로 돌아가기  - 소연-->
		    <div class="backMyPage" align="center">
				<button class="btn_backmypage" onclick="window.location='${path}/mypage.myp'">마이페이지</button>
    		</div>
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
	
	function goToMyWriting() {
		location.href = "${path}/myWriting.bc"; 
	}
	
	//테이블 토글
	 function toggleTable(tableId) {
         
         document.querySelectorAll('.table-container').forEach(table => {
             table.style.display = 'none';
         });

         document.getElementById(tableId).style.display = 'block';
     }
  </script>
		
</body>
</html>