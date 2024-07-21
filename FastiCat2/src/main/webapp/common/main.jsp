<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pj.mvc.jsp.dto.ShowDTO" %>
<%@ include file="/common/setting.jsp" %>    
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${path}/resources/css/common/header.css">
    <link rel="stylesheet" href="${path}/resources/css/common/main.css">
    <link rel="stylesheet" href="${path}/resources/css/common/footer.css">
    <link rel="stylesheet" href="${path}/resources/css/customer/search.css">
    <link rel="stylesheet" href="${path}/resources/css/calender/style.css" />
	
    <script src="https://kit.fontawesome.com/e99c5d1543.js" crossorigin="anonymous"></script>
    <script src="/FastiCat/resources/js/customer/main.js" defer></script>
    
    <title>Main</title>

</head>
<body>
   <div class="wrap">
      <%@ include file="header.jsp" %>
      <br>
      <center>
        <div class="slide_section">
            <input type="radio" name="slide" id="slide01" checked>
            <input type="radio" name="slide" id="slide02">
            <input type="radio" name="slide" id="slide03">
            <div class="slidewrap">
                <ul class="slidelist">
                    <li class="slideitem">
                        <a>
                            <div class="textbox"></div>
                            <img src="${path}/resources/images/slide1.PNG">
                        </a>
                    </li>
                    <li class="slideitem">
                        <a>
                            <div class="textbox"></div>
                            <img src="${path}/resources/images/slide2.PNG">
                        </a>
                    </li>
                    <li class="slideitem">
                        <a>
                            <div class="textbox"></div>
                            <img src="${path}/resources/images/slide3.PNG">
                        </a>
                    </li>
                </ul>
                <div class="slide-control">
                    <div>
                        <label for="slide03" class="left"></label>
                        <label for="slide02" class="right"></label>
                    </div>
                    <div>
                        <label for="slide01" class="left"></label>
                        <label for="slide03" class="right"></label>
                    </div>
                    <div>
                        <label for="slide02" class="left"></label>
                        <label for="slide01" class="right"></label>
                    </div>
                </div>
                <ul class="slide-pagelist">
                    <li><label for="slide01"></label></li>
                    <li><label for="slide02"></label></li>
                    <li><label for="slide03"></label></li>
                </ul>
            </div>
        </div>	
      </center>
     <!-- 컨텐츠 끝 -->
    <div class="calendar-container">
        <div class="calendar-header">
            <button id="prevBtn">◀</button>
            <span id="currentMonth"></span>
            <button id="nextBtn">▶</button>
        </div>
        <div id="calendarDates"></div>
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