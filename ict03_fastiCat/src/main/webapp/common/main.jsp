<!-- 2024/06/25/9:40 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>    
<!DOCTYPE html>
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${path }/resources/css/common/header.css">
<link rel="stylesheet" href="${path }/resources/css/common/main.css">
<link rel="stylesheet" href="${path }/resources/css/common/footer.css">
<link rel="stylesheet" href="${path }/resources/css/customer/search.css">

<script src="https://kit.fontawesome.com/e99c5d1543.js" crossorigin="anonymous"></script>
<!-- (3-3-2). 자바스크립트 소스 추가 -->
<!-- defer : 모든 html 파일을 로딩할때까지 html이 브라우저에 표시가 안되는 것을 방지 -->

<script src="/FastiCat/resources/js/customer/main.js" defer></script>
<script>

/* function handleSearch() {
    var searchInput = document.getElementById('searchInput').value.trim();
    
    if (searchInput === '') {
        return false; // 검색어가 비어 있으면 이벤트 취소
    } else {
        // 검색어가 있으면 search.html로 이동
        window.location.href = '../../customer/search/search.html?q=' + encodeURIComponent(searchInput);
        return false; // 이벤트 취소
    }
} */
</script>
<title>main</title>

</head>
<body>
	 
   <div class="wrap">
      <!-- header 시작 -->
	<%@include file="header.jsp" %>
      <!-- header 끝 -->
      <br>
      <!-- 컨텐츠 시작 -->
      <center>
<div class="slide_section">
	<input type="radio" name="slide" id="slide01" checked>
	<input type="radio" name="slide" id="slide02">
	<input type="radio" name="slide" id="slide03">
	<div class="slidewrap">
		
		<ul class="slidelist">
			<!-- 슬라이드 영역 -->
			<li class="slideitem">
				<a>
					<div class="textbox">
					</div>
					<img src="${path }/resources/images/slide1.PNG"">
				</a>
			</li>
			<li class="slideitem">
				<a>
					
					<div class="textbox">
					</div>
					<img src="${path }/resources/images/slide2.PNG">
				</a>
			</li>
			<li class="slideitem">
				<a>
					
					<div class="textbox">
					</div>
					<img src="${path }/resources/images/slide3.PNG"">
				</a>
			</li class="slideitem">

			<!-- 좌,우 슬라이드 버튼 -->
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

		</ul>
		<!-- 페이징 -->
		<ul class="slide-pagelist">
			<li><label for="slide01"></label></li>
			<li><label for="slide02"></label></li>
			<li><label for="slide03"></label></li>
		</ul>
	</div>

	
</div>	                                                                
      </center>
      <!-- 컨텐츠 끝 -->
      
      
      <!-- footer 시작 -->
		<%@include file="footer.jsp" %>
      <!-- footer 끝 -->
      
   </div>
   
</body>
</html>