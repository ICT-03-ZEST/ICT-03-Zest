<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="pj.mvc.jsp.dto.ShowDTO"%>
<%@ include file="/common/setting.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>달력 및 데이터 처리 예제</title>
    <link rel="stylesheet" href="${path}/resources/css/calender/detail.css"/>
    <link rel="stylesheet" href="${path}/resources/css/showTicket/showTicketDetail.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${path}/resources/js/calender/calendarDetail.js"></script>
  <link rel="stylesheet" href="${path}/resources/css/common/header.css">
</head>
<body>
<%
    Date minDayValue = null;
    Date maxDayValue = null;
    List<ShowDTO> showList = (List<ShowDTO>) request.getAttribute("showList");

    for (ShowDTO show : showList) {
        Date showDay = show.getShowDay();
        if (minDayValue == null || showDay.compareTo(minDayValue) < 0) {
            minDayValue = showDay;
        }
        if (maxDayValue == null || showDay.compareTo(maxDayValue) > 0) {
            maxDayValue = showDay;
        }
    }

    // JSP 변수에 설정
    pageContext.setAttribute("minDay", minDayValue);
    pageContext.setAttribute("maxDay", maxDayValue);
%>

<div>     <%@ include file="/common/header.jsp" %></div>
<div class="content-container">
    <div class="content-left">
        <table>
            <tr>
                <th colspan="8"><h3>${dto.showName}</h3></th>
            </tr>
            <tr>
                <td rowspan="6" colspan="5">
                    <img class="table-img" src="${path}/resources/images/show/${dto.showImage}.gif" alt="Show Image">
                </td>
                <td>장소</td>
                <td colspan="2">${dto.showPlace}</td>
            </tr>
            <tr>
                <td>공연기간</td>
                <td colspan="2">${minDay} ~ ${maxDay}</td>
            </tr>
            <tr>
                <td>공연시간</td>
                <td colspan="2">${dto.showTime}분</td>
            </tr>
            <tr>
                <td>관람연령</td>
                <td colspan="2">${dto.showAge}세 이상</td>
            </tr>
            <tr>
                <td>가격</td>
                <td colspan="2">${dto.showPrice}</td>
            </tr>
            <tr>
                <td>혜택</td>
                <td colspan="2">${dto.showBene}</td>
            </tr>
        </table>
    </div>
    <div class="content-right">
        <div class="calendar-container">
            <div class="calendar-header">
                <button id="prevBtn">이전</button>
                <h2 id="currentMonth"></h2>
                <button id="nextBtn">다음</button>
            </div>
            <div id="calendarDates"></div>
        </div>
    </div>
</div>
<img class="table-imgPopup" src="${path}/resources/images/show/모달내용01.jpg" alt="Show Image">

<!-- 모달 구조 -->
<div id="showModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <div class="content-left">
            <table>
                <tr>
                    <th colspan="8">
                        <h3 id="modalShowName" data-showNum=""></h3>
                    </th>
                </tr>
                <tr>
                    <td rowspan="6" colspan="3">
                        <img id="modalShowImage" class="table-img" alt="Show Image">
                    </td>
                    <td class="table-header">장소</td>
                    <td colspan="4" id="modalShowPlace"></td>
                </tr>
                <tr>
                    <td class="table-header">공연일</td>
                    <td colspan="4" id="modalShowDay"></td>
                </tr>
                <tr>
                    <td class="table-header">공연시간</td>
                    <td colspan="4" id="modalShowTime">분</td>
                </tr>
                <tr>
                    <td class="table-header">관람연령</td>
                    <td colspan="4" id="modalShowAge"></td>
                </tr>
                <tr>
                    <td class="table-header">가격</td>
                    <td colspan="4" id="modalShowPrice"></td>
                </tr>
                <tr>
                    <td class="table-header">혜택</td>
                    <td colspan="4" id="modalShowBene"></td>
                </tr>
                <tr>
                    <td colspan="3" id="reserveButton">
                        예약 하기
                    </td>
                    <td colspan="3" class="table-header">인원현황<br>[현인원/최대인원]</td>
                    <td colspan="2" id="modalShowCapacity"></td>
                </tr>
                <tr>
                    <td class="table-header">가격</td>
                    <td colspan="3" id="modalShowTicketPrice">
                        <label for="ticketQuantity">매수:</label>
                        <input type="number" id="ticketQuantity" name="ticketQuantity" min="1" value="1">
                    </td>
                    <td class="table-header">합계</td>
                    <td colspan="3" id="modalShowTicketListTotal"></td>
                </tr>
            </table>
            
        </div>
    </div>
</div>

<script>
var shows = [
    <c:forEach var="item" items="${showList}" varStatus="status">
        {
            "showNum": "<c:out value='${item.showNum}'/>",
            "showName": "<c:out value='${item.showName}'/>",
            "showPlace": "<c:out value='${item.showPlace}'/>",
            "showPrice": "<c:out value='${item.showPrice}'/>",
            "showTime": "<c:out value='${item.showTime}'/>",
            "showAge": "<c:out value='${item.showAge}'/>",
            "showBene": "<c:out value='${item.showBene}'/>",
            "curCapacity": "<c:out value='${item.curCapacity}'/>",
            "maxCapacity": "<c:out value='${item.maxCapacity}'/>",
            "showImage": "<c:out value='${item.showImage}'/>",
            "showDay": "<c:out value='${item.showDay}'/>",
            "showCHK": "<c:out value='${item.showCHK}'/>"
        }<c:if test="${!status.last}">,</c:if>
    </c:forEach>
];
var path = '<c:out value="${path}" />';
</script>
</body>
</html>
