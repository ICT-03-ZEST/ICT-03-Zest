<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../ad_common/ad_setting.jsp" %> 
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>국내공연 등록</title>

    <!-- Custom fonts for this template-->
    <link href="${path}/admin/startbootstrap-admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${path}/resources/css/admin/sb-admin-2.css" rel="stylesheet">

</head>

<body id="page-top">

			<!-- header 시작 -->
			<%@ include file="../ad_common/ad_header.jsp" %>
	     	<!-- header 끝 -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">국내페스티벌 등록</h1>
                    </div>

    			<!-- 페스티벌 등록 성공 alert창 -->
                 <c:if test="${insertCnt == 1}">
	                <script type="text/javascript">
	                	setTimeout(function(){
	                		alert('페스티벌등록 성공!!');
	                		window.location="${path}/ad_festivalEdit.fes";
	                	}, 1000);
	                </script>
                </c:if>
                
                <c:if test="${insertCnt != 1}">
	                <script type="text/javascript">
	                	setTimeout(function(){
	                		alert('페스티벌등록 실패!!');
	                		window.location="${path}/ad_festivalAdd.fes";
	                	}, 1000);
	                </script>
                </c:if>
                <!-- 페스티벌 등록 성공 alert창 종료 -->
               
               
                 </div> 
                <!-- /.container-fluid -->



            <!-- footer 시작 -->
			<%@ include file="../ad_common/ad_footer.jsp" %>
			<!-- footer 끝 -->


</body>

</html>