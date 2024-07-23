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
<link rel="stylesheet" href="${path}/resources/css/customer/login.css">

<script src="https://kit.fontawesome.com/e99c5d1543.js" crossorigin="anonymous"></script>
<script type="${path}/resources/js/customer/main.js" defer></script>

<title>로그인 페이지</title>

</head>
<body>
   <div class="wrap">
      <!-- header 시작 -->
	<%@ include file="/common/header.jsp" %>
      <!-- header 끝 -->
      
      <!-- 컨텐츠 시작 -->
     <div id="container">
      	<div id="contents">
      		<!-- 상단 중앙 1 시작 -->
      		<div id="section1">
      			<h1 align="center"> 로그인 처리</h1>
      		</div>
      		
      		<!-- 상단 중앙 2 시작 -->
      		<div id="section2">
      			<div class="s2_inner">
      				<div class="join">
      					<form name="loginform" action="loginAction.do" method="post">
					
						<!--  // 세션이 없는 경우 : 로그인 실패 --> 
     					<c:if test="${sessionScope.sessionID == null}">
      					<script type="text/javascript">
      						alert("아이디와 비밀번호가 일치하지 않습니다.");
      					</script>
      					<table>
								<tr>
									<th>* 아이디 </th>
									<td>
										<input type="text" class="input" name="userid" size="20" placeholder="공백없이 20자 이내로 작성" required autofocus>
									</td>
								</tr>
								<tr>
									<th>* 비밀번호 </th>
									<td>
										<input type="password" class="input" name="password" size="20" placeholder="공백없이 20자 이내로 작성" required>
									</td>
								</tr>	
								<tr>
									<td colspan="2" style="border-bottom: none">
										<br>
										<div align="right">
											<input class="inputButton" type="submit" value="로그인">
											<input class="inputButton" type="reset" value="초기화">
											<input class="inputButton" type="button" value="회원가입" onclick="window.location='join.do'">
											<a href="../../admin/ad_product/ad_productAdd.jsp" style="color:black">관리자</a>
										</div>
									</td>
								</tr>
      						</table>
      					</c:if>	
      					
						<!-- // 세션이 있는 경우 -->
     					<c:if test="${sessionScope.sessionID != null}">
						<%-- <table>
								<tr>
									<td colspan="2" align="center">
										<h3>
											<span style="color: orange"><b>${sessionScope.sessionID}</b></span>님 환영합니다.
										</h3>
									</td>
								</tr>
								<tr>
									<td colspan="2" style="border-bottom: none">
										<br>
										<div align="right">
											<input class="inputButton" type="button" value="회원수정" onclick="window.location='modifyCustomer.do'">
											<input class="inputButton" type="button" value="회원탈퇴" onclick="window.location='deleteCustomer.do'">
											<input class="inputButton" type="button" value="로그아웃" onclick="window.location='logout.do'">
											
											<a href="${path}/admin/ad_main/ad_dashboard.jsp" style="color:white">관리자</a>
										</div>
									</td>
								</tr>
      						</table>   --%>
      						
      						<script type="text/javascript">
      							window.location="/ict03_fastiCat/main.do"
      						</script>
      						
      						</c:if>    					
      					</form>
      				</div>
      			</div>
      		</div>
 		</div>
      </div>
      <!-- 컨텐츠 끝 -->
      
      <!-- footer 시작 -->
	<%@ include file="/common/footer.jsp" %>
      <!-- footer 끝 -->
      
   </div>
      
</body>
</html>