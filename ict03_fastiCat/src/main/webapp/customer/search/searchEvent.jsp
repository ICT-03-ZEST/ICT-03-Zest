<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/customer/search.css">
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="${path}/resources/js/jquery-3.7.1.min.js"></script>
<script src="../../resources/js/customer/main.js" defer></script>
<title>검색 페이지</title>

</head>
<body>

	<!-- header 시작 -->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝 -->
	
	
	<!-- 컨텐츠 시작 -->
	<div id="container">
		<div id="contents">

			<div id="section2">
				<div class="s2_inner">
					<div class="join">
						<table>
							<thead>
								<div id="container">
									<div id="contents">
										<!-- 상단 중앙1 시작 -->
										<div id="section1">
											<h1 align="center">공연검색</h1>
											<span
												style="font-size: 1.2em; color: gray; text-align: center; display: block; margin: 0 auto;">
												찾으시는 공연을 검색하세요 </span>

										</div>

							<!-- 드롭다운 + 검색 창 시작 -->
							<div align="right" id="searchfooter">
								<form action="${path}/searchconcertList.sc" method="get">
									<input id="searchInput" class="searchInputButton" type="text"
										name="query" placeholder="검색어를 입력하세요" aria-label="Search">
									<input id="searchInputButton" class="searchInputButton" type="submit"
										value="search">
								</form>
							</div>
							<!-- 드롭다운 + 검색 창 끝 -->
						</table>
					</div>
				</div>
			</div>
		</div>
		<!-- 컨텐츠 끝 -->

		<!-- footer 시작 -->
		<%@include file="/common/footer.jsp"%>
		<!-- footer 끝 -->

	</div>

</body>
</html>
