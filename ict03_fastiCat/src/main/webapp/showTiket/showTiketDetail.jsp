<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %> 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${path}/resources/css/showTicket/showTicketDetail.css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
    <h1>공연 일정</h1>
    <table>
        <thead>
            <tr>
                <th>날짜</th>
                <th>공연명</th>
                <th>상세 보기</th>
            </tr>
        </thead>
        <tbody>
            <%
                // 예시 데이터
                String[][] shows = {
                    {"2024-07-01", "공연명1"},
                    {"2024-07-02", "공연명2"},
                    {"2024-07-03", "공연명3"}
                };

                for (String[] show : shows) {
            %>
                <tr>
                    <td><%= show[0] %></td>
                    <td><%= show[1] %></td>
                    <td>
                        <button class="btn" onclick="openModal('<%= show[1] %>', '<%= show[0] %>', 'path/to/image.jpg')">
                            상세 보기
                        </button>
                    </td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

<!-- 모달 -->
<div id="ticketModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <h5 id="modalShowName">${list.showName}</h5>
        
        <img id="modalImage" src="" alt="공연 이미지">
        <p id="modalShowDate"></p>
        <p>여기에 상세 정보와 티켓팅 정보를 표시합니다.</p>
        <button class="btn">티켓 구매</button>
    </div>
</div>

<script>

</script>

<script src="${path}/resources/js/ticket/ticketDetail.js"></script>

</body>
</html>