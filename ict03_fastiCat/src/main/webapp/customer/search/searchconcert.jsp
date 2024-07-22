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
												style="font-size: 1.5em; color: gray; text-align: center; display: block; margin: 0 auto;">
												'${query }' 에 대한 검색결과 </span>

										</div>
										
										<!-- 상단 중앙2 시작 -->
										<div id="section2">

											<!-- 우측 화면 시작 -->
											<div id="right">
												<div class="table_div">
													<form name="boardList">
														<table id="searchTable" style="width: 1000px;">
															<tr>
																<th class="searchth" style="width: 30%">카테고리</th>
																<th class="searchth" style="width: 45%">공연명</th>
																<th class="searchth" style="width: 25%">공연장소</th>
															</tr>

															<!-- 상품이 있으면 -->
															<c:forEach var="list" items="${list }">
																<tr>
																	<td class="searchtd">${list.getSource() }</td>
																	<td class="searchtd">
																		<a href="${path}/concertInfo.cc?conNo=${list.getNum()}&pageNum=1">
																			${list.getTitle()}
																		</a>
																	</td>
																	<td class="searchtd">${list.getContent() }</td>
																</tr>
															</c:forEach>

															<tr>
																<td class="searchtd" colspan="5" align="center">
																	<!-- 페이징 처리 --> <!-- 이전 버튼 활성화 --> <c:if
																		test="${paging.startPage > 3 }">
																		<a
																			href="${path }/searchconcertList.sc?pageNum=${paging.prev}&query=${query}">[이전]</a>
																	</c:if> <!-- 페이지 번호 처리 --> <c:forEach var="num"
																		begin="${paging.startPage}" end="${paging.endPage}">
																		<a
																			href="${path }/searchconcertList.sc?pageNum=${num}&query=${query}">${num }</a>
																	</c:forEach> <!-- 다음 버튼 활성화 --> <c:if
																		test="${paging.endPage < paging.pageCount }">
																		<a
																			href="${path }/searchconcertList.sc?pageNum=${paging.next}&query=${query}">[다음]</a>
																	</c:if>
																</td>
															</tr>

														</table>
													</form>
												</div>
											</div>
											<!-- 우측 화면 종료 -->
										</div>
									</div>
								</div>
								</div>
							</thead>

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
