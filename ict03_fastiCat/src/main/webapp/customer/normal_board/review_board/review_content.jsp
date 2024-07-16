<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>리뷰게시판 상세페이지</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/css/normal_board/review_free_content.css"> 
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<script src="${path}/resources/js/request.js"></script>
<script type="text/javascript">
	$(function() {

		//alert('gkdl');
		
		//로그인시 하트체크 여부
		if(${heartChk == 1}) {
			$('#heart').addClass('filled');
		}
		//하트클릭
		heartClick();
		 
		//로그인안하면 하트 안보임
		if(!${dto2.userID.equals('sessionID_2')}) { 
			$('#heart').css('display','none');
		}
		
		//사용자가 작성한 게시글인 경우 수정버튼/ 삭제버튼 보이기
		if(${selWriter == 1}) {
			$('#btn_mod, #btn_del').css('display','block');
		} 
		
		//수정
		$('#btn_mod').click(function() {
			location.href="${path}/myBoardUpdate.bc?board_category=${dto.board_category}&pageNum=${pageNum}&board_num=${dto.board_num}";
		});
		
		//삭제 
		$('#btn_del').click(function() {
			let del = confirm("삭제하시겠습니까?");
			if(del) {
				location.href="${path}/boardDeleteAction.bc?board_category=${dto.board_category}&board_num=${dto.board_num}";
			}
			else {
				location.href="#";
			}
			
		});
		
		//목록으로 돌아가기(새로고침)
		$('#btn_back').click(function() {
			location.href="${path}/board.bc?board_category=${dto.board_category}&pageNum=${pageNum}";
		});		
		
	});
	
	function heartClick() {
		//좋아요 누르면 채워짐
		$('#heart').click(function() {
	        $(this).toggleClass('filled');
		        if ($(this).hasClass('filled')) {
		  
		        	let insHeart = 1;
		        	let plus = ${dto.board_heart+1};
		        	updateLike(insHeart, plus);
		        }
		        else {
		        	let delHeart = 0;
		        	let minus = ${dto.board_heart-1};
		        	updateLike(delHeart, minus);
		        }
	    });
	}
	
	function updateLike(heartChk,modLike) {
		
		// 게시글번호, 카테고리, 하트수 파라미터로 넘김
		let param = {
				"board_num": ${dto.board_num},
				"board_category": "${dto.board_category}",
				"board_heart": modLike,
				"heart": heartChk
		}

		$.ajax({
			url: '${path}/heartClick.bc',
			type: 'POST',
			data: param,
			success: function() {	
				newLoad();
			},
			error: function() {
				alert('하트수정 실패');
			}
		});
	}
	
	function newLoad() { //새로고침
		
		let param = {
				"board_num": ${dto.board_num},
				"board_category": "${dto.board_category}",
				"pageNum": ${pageNum}
		}
	
		$.ajax({
			url:'${path}/content.bc', //컨트롤러로 이동(9)
			type:'POST',
			data: param,
			success: function(result) { //(13)
				$('body').html(result);
			},
			error: function() {
				alert('새로고침 오류');
			}
		});
	}
	
</script>
</head>
<body>
	<!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->
	
    <h4>공연/페스티벌 후기</h4>
    
    <section>
        <div class="review_box">
          <div class="review_tit">${dto.board_title}</div>  
          <div class="head">
                <div class="icon"><i class="fa-regular fa-circle-user"></i></div>
                <ul>
                    <li class="writer"><span>${dto.board_writer}</span>
                        <ul>
                            <li class="review_catgry">${dto.board_category}</li>
                            <li class="regDate">${dto.board_regDate}</li>
                            <li class="views">조회수 ${dto.board_views}</li>
                            <li><i id="board_heart" class="fa-regular fa-heart"></i>${dto.board_heart}</li>
                        </ul>
                    </li>
                </ul>
          </div>  
          
		  <div class="top_btn">	
			<input type="button" name="likes"><i id="heart" class="fa-regular fa-heart"></i> 
		 	
		 	<div class="btn_center">
                <button class="btn_mod" id="btn_mod">수정</button> 
                <button class="btn_del" id="btn_del">삭제</button>     
            </div>
		 </div>	
		 
          <div class="content_box">
         	 
            <ul>
            	<c:if test="${dto.board_image != null}"> 
            		<li><img src="${dto.board_image}"></li>
            	</c:if>
            	
                <li>
                    <div class="content_text">
                        <p>
                        	${dto.board_content}
                        </p>     
                    </div>
                </li> 
            </ul>
          </div>
        </div>
    </section>

    <!-- 이전/ 다음 게시글 이동 -->
    <div class="btnAll">
        <ul>
            <li class="listMove">
                <table class="pre_next">
					<c:if test="${dto.board_num > 1}">
	                    <tr>
	                        <td>
	                        	<a href="${path}/review_content.bc?board_num=${dto.board_num-1}&board_category=${dto.board_category}"><i class="fa-solid fa-angles-left"></i></a></label>
	                        </td>
	                    </tr>
					</c:if>
					
					<c:if test="${dto.board_num < total}">
						<tr>
	                        <td>
	                        	<a href="${path}/review_content.bc?board_num=${dto.board_num+1}&board_category=${dto.board_category}"><i class="fa-solid fa-angles-right"></i></a></label>
	                        </td>
                    	</tr>
					</c:if>
                </table> 
            </li> 

            <!-- 목록으로 돌아가기 -->
            <li class="btn_back_box">
                <button class="btn_back" id="btn_back">목록</button>   
            </li>
        </ul>
    </div>
    
    <!-- footer 시작-->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer 끝-->
	
</body>
</html>