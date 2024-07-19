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
    <title>배너 관리</title>
    <!-- Custom fonts for this template-->
    <link href="${path}/admin/startbootstrap-admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="${path}/resources/css/admin/sb-admin-2.css" rel="stylesheet">
    
<script type="text/javascript">
    function setBannerNo(bannerNo) {
        document.getElementById('deleteButton').setAttribute('onclick', "window.location='${path}/ad_bannerDeleteAction.ban?bannerNo=" + bannerNo + "'");
    }
</script>    
    
    
</head>
<body id="page-top">
	
				<!-- header 시작 -->
				<%@ include file="../ad_common/ad_header.jsp" %>
		     	<!-- header 끝 -->


                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">배너 관리</h1>
                    </div>
					
					<!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3 d-flex justify-content-between align-items-center">
   							<h6 class="m-0 font-weight-bold text-primary">배너 목록</h6>
   							<button class="btn btn-primary" id="registerButton" onclick="window.location='ad_bannerAdd.ban'">배너 등록</button>
						</div>

                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                           <tr>
		                              <th> 배너 번호 </th>
		                              <th> 배너 영역 </th>
		                              <th> 배너 이미지 </th>
		                              <th> 사용여부 </th>
		                              <th> 등록일 </th>
		                              <th> 배너관리 </th>
		                           </tr>
                                 <!-- 배너가 있으면 -->
	                             <c:forEach var="dto" items="${list}" varStatus="status">
		                           <tr>
					                  <td>${status.index + 1}</td>
					                  <td>
						                 ${dto.bannerArea}
                					  </td>
					                  <td>
					                    <img src="${dto.bannerImg}" width="220px" height="100px" alt="배너 이미지">
					                  </td>
					                  <td>${dto.bannerStatus}</td>
					                  <td>${dto.bannerIndate}</td>
					                  <td> <!-- 배너삭제 모달 -->
					                  	<button class="btn btn-outline-danger btn-sm btn-custom" id="btnEdit" onclick="window.location='${path}/ad_bannerModify.ban?bannerNo=${dto.bannerNo}&pageNum=${paging.pageNum}'">수정</button>
					    				<button class="btn btn-outline-danger btn-sm btn-custom" id="btnDelete" href="#" data-toggle="modal" data-target="#DeleteModal"
				                              		onclick="setBannerNo(${dto.bannerNo})">삭제</button>
					                  </td>
					                </tr>
					            </c:forEach>
                                </table>
                            </div>
                   		 </div>
					
					
                    <div class="row">

                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->


    		<!-- footer 시작 -->
			<%@ include file="../ad_common/ad_footer.jsp" %>
			<!-- footer 끝 -->
    
    
    <!-- 배너 삭제 모달 delete Modal-->
    <div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">배너 삭제</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">정말 삭제 하시겠습니까?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                    <button class="btn btn-primary" id="deleteButton">삭제</button>
                </div>
            </div>
        </div>
    </div>


</body>

</html>