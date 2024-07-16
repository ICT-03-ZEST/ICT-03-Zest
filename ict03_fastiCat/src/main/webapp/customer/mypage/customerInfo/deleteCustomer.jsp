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
<title>회원탈퇴 - 인증화면</title>

</head>
<body>

   <div class="wrap">
      <!-- header 시작 -->
      <%@ include file="/common/header.jsp" %>
      <!-- header 끝 -->
      
      <!-- 컨텐츠 시작 -->
      <div id="container">
         <div id="contents">
            <!-- 상단 중앙1 시작 -->
            <div id="section1">
               <h1 align="center"> 회원탈퇴 - 인증화면 </h1>
            </div>
            
            <!-- 상단 중앙2 시작 -->
            <div id="section2">
               <div id="s2_inner">
                  <div class="join">
                     <form name="passwordform" action="deleteCustomerAction.do" method="post">
                        <table>
                           <tr>
                              <th colspan="2" align="center">
                                 <h3>
                                    <span style="color:#FF82AA"><b>${sessionID }</b></span>님 비밀번호를 입력하세요!!
                                 </h3>    
                              </th>
                           </tr>
                           
                           <tr>
                              <th> * 비밀번호 </th>
                              <td>
                                 <input type="password" class="input" name="password" size="20" placeholder="공백없이 20자이내로 작성" required autofocus>
                              </td>
                           </tr>
                           
                           <tr>
                              <td colspan="2" style="border-bottom:none">
                                 <br>
                                 <div align="right">
                                    <input class="inputButton" type="submit" value="회원탈퇴">
                                    <input class="inputButton" type="button" value="탈퇴취소" onclick="window.location='main.do'">
                                    
                                 </div>
                              </td>
                           </tr>
                        </table>
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