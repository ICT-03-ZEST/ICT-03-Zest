<!-- 2024/07/17/11:04 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="pj.mvc.jsp.dto.AdminBannerDTO" %>    
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/main.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/customer/search.css">
<link rel="stylesheet" href="${path}/resources/css/calender/style.css" >

<script src="https://kit.fontawesome.com/e99c5d1543.js" crossorigin="anonymous"></script>
<!-- (3-3-2). 자바스크립트 소스 추가 -->
<!-- defer : 모든 html 파일을 로딩할때까지 html이 브라우저에 표시가 안되는 것을 방지 -->

<script src="${path}/resources/js/customer/main.js" defer></script>
<title>main</title>

</head>
<body>
	 
   <div class="wrap">
      <!-- header 시작 -->
	<%@include file="header.jsp" %>
      <!-- header 끝 -->
      <br>
<%
    int activeBannerCount = 0;
    List<AdminBannerDTO> banners = (List<AdminBannerDTO>)request.getAttribute("bannerList");
    for (AdminBannerDTO dto : banners) {
        if ("사용함".equals(dto.getBannerStatus())) {
            activeBannerCount++;
        }
    }
%>    
       
      
      <!-- 컨텐츠 시작 -->
      <div align="center">
<div class="slide_section">
	<input type="radio" name="slide" id="slide01" checked>
	<% for (int i = 2; i <= activeBannerCount; i++) { %>
        <input type="radio" name="slide" id="slide0<%= i %>">
    <% } %>
	
	
	<div class="slidewrap">
		<ul class="slidelist">
			<!-- 배너 슬라이드 영역 -->
			<c:forEach var="dto" items="${bannerList}">
				<c:if test="${dto.bannerStatus == '사용함'}">
					<li class="slideitem">
						<a>
							<div class="textbox">
							</div>
							<img src="${dto.bannerImg}" width="1160" height="586">
						</a>
					</li>
				</c:if>
			</c:forEach>

			<!-- 좌,우 슬라이드 버튼 -->
			<div class="slide-control">
				<% for (int i = 1; i <= activeBannerCount; i++) { %>
					<div>
						<label for="slide0<%= (i == 1 ? activeBannerCount : i - 1) %>" class="left"></label>
						<label for="slide0<%= (i == activeBannerCount ? 1 : i + 1) %>" class="right"></label>
					</div>
				<% } %>
			</div>
		</ul>
		<!-- 페이징 -->
		<ul class="slide-pagelist">
			<% for (int i = 1; i <= activeBannerCount; i++) { %>
				<li><label for="slide0<%= i %>"></label></li>
			<% } %>
		</ul>
	</div>
</div>	                                                                
      </div>
      <!-- 컨텐츠 끝 -->
      <div class="calendar-container">
        <div class="calendar-header">
            <button id="prevBtn">◀</button>
            <span id="currentMonth"></span>
            <button id="nextBtn">▶</button>
        </div>
        <div id="calendarDates"></div>
    </div>
      
      <!-- footer 시작 -->
		<%@include file="footer.jsp" %>
      <!-- footer 끝 -->
      
   </div>
   <script>
    var shows = [
        <c:forEach var="item" items="${list}" varStatus="status">
            {
                "showNum": "${item.showNum}",
                "showName": "${item.showName}",
                "curCapacity": "${item.curCapacity}",
                "maxCapacity": "${item.maxCapacity}",
                "showDay": "${item.showDay}",
                "showCHK": "${item.showCHK}"
            }<c:if test="${!status.last}">,</c:if>
        </c:forEach>
    ];
    </script>
    <script src="${path}/resources/js/calender/calendar.js"></script>
</body>
</html>
