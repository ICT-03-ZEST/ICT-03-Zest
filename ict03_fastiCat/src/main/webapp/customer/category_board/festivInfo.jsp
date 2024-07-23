<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>festival Information</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/css/category_board.css/category_Info.css">
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<script src="${path}/resources/js/request.js"></script>
<script type="text/javascript">
$(function() {
	
	//목록으로 돌아가기(새로고침)
	$('#btn_back').click(function() {
		location.href="${path}/festivalList.fv?pageNum=${pageNum}";
	});	

});
	
</script>
</head>
<body>
   
    <!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->
	
    <h3>페스티벌 정보</h3>
    <div class="info" id="detail1">
    <!--상세페이지1 시작-->
    <h4>${dto.fesName}</h4>
    <hr>
        <div class="info_box" > 

            <!--포스터&예매버튼-->
            <ul>
                <li><div class="photo"><img src="${dto.fesImg}"></div></li>
                <%-- <li><div class="buy_ticket"><a href="${path}/showTicket_Detail.do">예매하기</a></div></li> --%>
            </ul>   
                      
            <!--상세 정보--> 
            <table class="descript">
                <tr>
                    <th>참가연령 : </th>
                    <td>${dto.fesGrade}</td>
                </tr>

                <tr>
                    <th>장소  :</th>
                    <td>${dto.fesPlace}</td>
                </tr>

                <tr>
                    <th>개최일 :</th>
                    <td>${dto.fesTime}</td>
                </tr>

                <tr>
                    <th>페스티벌 설명 :</th>
                    <td>${dto.fesContent}</td>
                </tr>

                <tr>
                    <th>가격 :</th>
                    <td>${dto.fesPrice}<br>
                    </td>
                </tr>

                <tr>
                    <th>예매사이트 :</th>
                    <td>${dto.fesBuy}</td>
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
	
	<!-- 
	// AdminFestivalServiceImpl 페스티벌 상세페이지에 수정
	@Override
	public void festivalDetailAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - festivalDetailAction()");
		
		int fesNo = Integer.parseInt(request.getParameter("fesNo"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminFestivalDAO dao = AdminFestivalDAOImpl.getInstance();
		
		// 5단계
		AdminFestivalDTO dto = dao.festivalDetail(fesNo);
		int total = dao.festivalCnt(); //상세페이지 이동시 
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("total", total);
	} -->

</body>
</html>