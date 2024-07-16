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

    <title>국내페스티벌 등록</title>

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
                    
                 <!-- DataTales Example -->   
			 	 <div class="card shadow mb-4"> <!--  테이블 css수정 0618-->
		             <div class="card-header py-3 d-flex justify-content-between align-items-center">
   							<h6 class="m-0 font-weight-bold text-primary">국내페스티벌 등록</h6>
					 </div>
					 
					 <div class="card-body">
		                  <div class="table_div">
		                     <form name="ad_festivalAdd" action="ad_festivalAddAction.fes" method="post" enctype="multipart/form-data">
		                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                      	  
		                            
	                           <tr>
	                              <th> * 페스티벌명 </th>
	                              <td>
	                                 <input type="text" class="input" name="fesName" id="fesName" size="40" placeholder="페스티벌명 작성" required autofocus>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 관람등급 </th>
	                              <td>
	                                 <input type="text" class="input" name="fesGrade" id="fesGrade" size="40" placeholder="관람등급 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 페스티벌 날짜/시간 </th>
	                              <td>
	                              	<textarea rows="3" cols="43" name="fesTime" id="fesTime" placeholder="페스티벌날짜/시간 작성" required></textarea>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 페스티벌 장소 </th>
	                              <td>
	                                 <input type="text" class="input" name="fesPlace" id="fesPlace" size="40" placeholder="페스티벌장소 작성" required>
	                              </td>
	                           </tr>
	                           
	                           <tr>
	                              <th> * 페스티벌 이미지 </th>
	                              <td>
	                                 <input type="file" class="input" name="fesImg" id="fesImg" accept="image/*">
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 페스티벌 예매처 </th>
	                              <td>
	                                 <input type="text" class="input" name="fesBuy" id="fesBuy" size="40" placeholder="페스티벌 예매처 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 티켓 가격 </th>
	                              <td>
	                                 <input type="number" class="input" name="fesPrice" id="fesPrice" size="40" placeholder="티켓가격 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 페스티벌 상태 </th>
	                              <td>                                 
	                                 <select class="input" name="fesStatus" id="fesStatus" required>
	                                    <option value="판매중">판매중</option>
	                                    <option value="품절">품절</option>
	                                 </select>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> 페스티벌 설명 </th>
	                              <td>
	                                 <textarea rows="5" cols="77" name="fesContent" id="fesContent" placeholder="페스티벌 설명 작성"></textarea>
	                              </td>
	                           </tr>
		                           
		                           <tr>
		                              <td colspan="2">
		                                 <br>
		                                 <div align="right">
		                                    <input class="btn btn-primary inputButton" type="submit" value="페스티벌등록">
		                                    <input class="btn btn-danger inputButton" type="reset" value="초기화">
		                                    <input class="btn btn-secondary inputButton" type="button" value="페스티벌목록" onclick="window.location='ad_festivalEdit.fes'">
		                                 </div>
		                              </td>
		                           </tr>
		                        </table>
		                     </form>
		                  </div>
		                </div> 
		            </div>
           		</div> 
                <!-- /.container-fluid -->


            <!-- footer 시작 -->
			<%@ include file="../ad_common/ad_footer.jsp" %>
			<!-- footer 끝 -->


</body>

</html>