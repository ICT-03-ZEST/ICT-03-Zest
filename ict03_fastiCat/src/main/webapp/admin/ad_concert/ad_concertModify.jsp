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

    <title>국내공연 수정</title>

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
                        <h1 class="h3 mb-0 text-gray-800">국내공연 수정</h1>
                    </div>
                    
				 <!-- DataTales Example -->   
			 	 <div class="card shadow mb-4"> 
		             <div class="card-header py-3 d-flex justify-content-between align-items-center">
   							<h6 class="m-0 font-weight-bold text-primary">국내공연 수정</h6>
					 </div>
					 
					 <div class="card-body">
	                  <div class="table_div">
	                     <form name="ad_concertModify" action="ad_concertModifyAction.con" method="post" enctype="multipart/form-data">
	                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                      	 <!-- hidden : 직접 input 태그에서 입력받지 못한 값들을 전달할 때 사용 -->
	                           <input type="hidden" name="hiddenPageNum" value="${pageNum}">
	                           <input type="hidden" name="hiddenConNo" value="${dto.conNo}">
	                           <input type="hidden" name="hiddenConImg" value="${dto.conImg}">  <!-- 기존 상품이미지 -->
	                      	 <tr>
								<th> * 공연번호 </th>
								<td> 
									${dto.conNo}
								</td>
							 </tr>
	                      	 <tr>
	                              <th> * 공연 카테고리 </th>
	                              <td>                                 
	                                 <select class="input" name="conCategory" id="conCategory" required>
	                                    <option <c:if test="${dto.conCategory == '케이팝'}">selected</c:if> value="케이팝">케이팝</option>
	                                    <option <c:if test="${dto.conCategory == '트로트'}">selected</c:if> value="트로트">트로트</option>
	                                    <option <c:if test="${dto.conCategory == '인디음악'}">selected</c:if> value="인디음악">인디음악</option>
	                                 </select>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 공연명 </th>
	                              <td>
	                                 <input type="text" class="input" name="conName" id="conName" value="${dto.conName}" size="40" placeholder="공연명 작성" required autofocus>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 관람등급 </th>
	                              <td>
	                                 <input type="text" class="input" name="conGrade" id="conGrade" value="${dto.conGrade}" size="40" placeholder="관람등급 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 공연날짜/시간 </th>
	                              <td>
	                            	 <textarea rows="3" cols="43" name="conTime" id="conTime" placeholder="공연날짜/시간 작성" required>${dto.conTime}</textarea>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 공연장소 </th>
	                              <td>
	                                 <input type="text" class="input" name="conPlace" id="conPlace" value="${dto.conPlace}" size="40" placeholder="공연장소 작성" required>
	                              </td>
	                           </tr>
	                           
	                           <tr>
	                              <th> * 공연 이미지 </th>
	                              <td>
	                              	 <img src="${dto.conImg}" width="80px">
	                                 <input type="file" class="input" name="conImg" id="conImg" accept="image/*">
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 공연 예매처 </th>
	                              <td>
	                                 <input type="text" class="input" name="conBuy" id="conBuy" value="${dto.conBuy}" size="40" placeholder="공연 예매처 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 티켓가격 </th>
	                              <td>
	                                 <input type="number" class="input" name="conPrice" id="conPrice" value="${dto.conPrice}" size="40" placeholder="티켓가격 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 공연상태 </th>
	                              <td>                                 
	                                 <select class="input" name="conStatus" id="conStatus" required>
	                                    <option <c:if test="${dto.conStatus == '판매중'}">selected</c:if> value="판매중">판매중</option>
	                                    <option <c:if test="${dto.conStatus == '품절'}">selected</c:if> value="품절">품절</option>
	                                 </select>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> 공연 설명 </th>
	                              <td>
	                                 <textarea rows="5" cols="77" name="conContent" id="conContent" placeholder="공연 설명 작성">${dto.conContent}</textarea>
	                              </td>
	                           </tr>
	                           
	                           <tr>
	                              <td colspan="2">
	                                 <br>
	                                 <div align="right">
	                                 	<!-- 상품번호 hidden  추가  -->
                                 		<%-- <input type="hidden" name="hiddenConNo" value="${dto.conNo}"> --%>
	                                    <input class="btn btn-primary inputButton" type="submit" value="공연수정">
	                                    <input class="btn btn-danger inputButton" type="reset" value="초기화">
	                                    <input class="btn btn-secondary inputButton" type="button" value="공연목록" onclick="window.location='ad_concertEdit.con'">
	                                 </div>
	                              </td>
	                           </tr>
	                        </table>
                   		 </form>
                  	  </div>
                   </div> 
                </div>
                 
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- footer 시작 -->
			<%@ include file="../ad_common/ad_footer.jsp" %>
			<!-- footer 끝 -->


</body>

</html>