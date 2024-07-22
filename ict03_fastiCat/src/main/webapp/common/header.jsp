<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function() {
		const navbarToggleBtn = document.querySelector(".navbar_toggleBtn");
		const navbarMenu = document.querySelector(".navbar_menu");
		const navbarIcons = document.querySelector("#navbar_icons");

		if (navbarToggleBtn && navbarMenu) {
			navbarToggleBtn.addEventListener("click", function() {
				// 토글 메뉴 열기/닫기
				navbarMenu.classList.toggle("active");
				navbarIcons.classList.toggle("active");
			});
		}
	});
</script>
<title>Insert title here</title>
<link rel="stylesheet" href="${path }/resources/css/common/header.css">
</head>
<body>
	<!-- header 시작 -->
	<nav class="navbar">
		<a href="${path }/main.do"><img
			src="${path }/resources/images/festicat.PNG" width="155px"
			height="50px" id="festicat"></a>
		<ul class="navbar_menu">
			<li><a href="${path}/concertList.cc">국내공연</a></li>
			<li><a href="${path}/festivalList.fv">국내 페스티벌</a></li>
			<li><a href="${path}/searchEvent.sc">공연 검색</a></li>
			<li><a href="#">게시판</a>
				<ul class="submenu">
					<li><a href="${path}/board.bc?board_category=공연후기">공연후기</a></li>
					<li><a href="${path}/board.bc?board_category=자유">자유게시판</a></li>
					<li><a href="${path}/notice_boardList.nb">공지사항 게시판</a></li>
				</ul>
		</ul>

		<ul id="navbar_icons">
			<!-- 로그인 안한 경우  -->
			<c:if test="${sessionScope.sessionID == null}">
				<form action="${path}/search.sc" method="get">
					<input id="searchInput" class="searchInputButton" type="search"
						name="query" placeholder="검색어를 입력하세요" aria-label="Search">
					<button id="searchInputButton" class="searchInputButton" type="submit">
						<i class="fa-solid fa-magnifying-glass" style="color: #eb9500;"></i>
					</button>
				</form>
				<li><a href="${path}/login.do">LOGIN</a></li>
				<li><a href="${path}/join.do">JOIN</a></li>
			</c:if>

			<!-- 로그인 한 경우  -->
			<c:if test="${sessionScope.sessionID != null}">
				<form action="${path}/search.sc" method="get">
					<input id="searchInput" class=searchInputButton type="search"
						name="query" placeholder="검색어를 입력하세요" aria-label="Search">
					<button id="searchInputButton" class="searchInputButton" type="submit">
						<i class="fa-solid fa-magnifying-glass" style="color: #eb9500;"></i>
					</button>
				</form>
				<li><span style="color: orange">${sessionScope.sessionID}님</span></li>
				<li><a href="${path}/logout.do">LOGOUT</a></li>
				<li><a href="${path}/mypage.myp"><i
						class="fa-solid fa-user">myPage</i></a></li>
			</c:if>
			<li><a href="main.html"><i class="fa-brands fa-twitter"></i></a></li>
			<li><a href="main.html"><i class="fa-brands fa-facebook"></i></a></li>
		</ul>
		<!-- 반응형 웹 - (1). 햄버거 아이콘 fontawesome - free - bars -->
		<a href="#" class="navbar_toggleBtn"> <i class="fa-solid fa-bars"></i>
		</a>
	</nav>
	<!-- header 끝 -->
</body>
</html>
