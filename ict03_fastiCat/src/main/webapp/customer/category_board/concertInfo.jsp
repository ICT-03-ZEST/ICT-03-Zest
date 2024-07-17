<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Concert Information</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/css/category_board.css/category_Info.css">
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<script src="${path}/resources/js/request.js"></script>
<script type="text/javascript">
$(function() {
	
	//목록으로 돌아가기(새로고침)
	$('#btn_back').click(function() {
		location.href="${path}/concertList.cc?pageNum=${pageNum}";
	});	

});      

</script>
</head>
<body>
  	<!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->
	
    <h3>${dto.conCategory} 공연정보</h3>
    <div class="info" id="detail1">
    <!--상세페이지1 시작-->
    <h4>${dto.conName}</h4>
    <hr>
        <div class="info_box" >  
                <!--포스터&예매버튼-->
            <ul>
                <li><div class="photo"><img src="${dto.conImg}"></div></li>
                <li><div class="buy_ticket"><a href="${path}/showTicket_Detail.do">예매하기</a></div></li>
            </ul>   
                    
            <!--상세 정보-->  
            <table class="descript">
                <tr>
                    <th>관람연령 :</th>
                    <td>${dto.conGrade}</td>
                </tr>

                <tr>
                    <th>장소  :</th>
                    <td>${dto.conPlace}</td>
                </tr>

                <tr>
                    <th>공연시간 :</th>
                    <td>${dto.conTime}</td>
                </tr>

                <tr>
                    <th>가격 :</th>
                    <td>${dto.conPrice}</td>
                </tr>

                <tr>
                    <th>예매사이트 :</th>
                    <td>${dto.conBuy}</td>
                </tr>
            </table>
        </div>
        <hr>
        <!-- 목록으로 돌아가기 -->
	    <div class="div_back">
	    	 <div class="btn_back_box">
	        	<button class="btn_back" id="btn_back">목록</button>   
	    	</div>
	    </div>
	</div>
    
    <!-- footer 시작-->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer 끝-->
    
</body>
</html>