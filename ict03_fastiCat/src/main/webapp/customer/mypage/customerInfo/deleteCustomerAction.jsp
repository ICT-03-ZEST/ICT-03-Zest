<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/customer/login.css">

<script src="https://kit.fontawesome.com/b435fbc087.js" crossorigin="anonymous"></script>
<script src="${path}/resources/js/customer/main.js" defer></script>
<title>회원탈퇴처리</title>

</head>
<body>

   <div class="wrap">
      <!-- header 시작 -->
      <%@ include file="/common/header.jsp" %>
      <!-- header 끝 -->
      
      <!-- 컨텐츠 시작 -->
      <!-- 서비스에서 넘어온 값을 받는다. -->
      
      <c:if test="${deleteCnt == 1 }">
            <script type="text/javascript">
            alert("회원탈퇴 성공!!");
            window.location='main.do';
         </script>
      </c:if>

     <c:if test="${deleteCnt != 1 }">
 	<script type="text/javascript">
            alert("회원탈퇴 실패!!");
            window.location='deleteCustomer.do';
         </script>
      </c:if>
        
      <!-- 컨텐츠 끝 -->
      
      <!-- footer 시작 -->
      <%@ include file="/common/footer.jsp" %>
      <!-- footer 끝 -->
   </div>
   
   
</body>
</html>    