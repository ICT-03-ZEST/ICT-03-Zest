<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../ad_common/ad_setting.jsp" %> 
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>국내공연 관리</title>
    <!-- Custom fonts for this template -->
    <link href="${path}/admin/startbootstrap-admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${path}/resources/css/admin/sb-admin-2.css" rel="stylesheet">
    <!-- Custom styles for this page -->
    <link href="${path}/admin/startbootstrap-admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">



<script type="text/javascript">
    function setConNo(conNo) {
        document.getElementById('deleteButton').setAttribute('onclick', "window.location='${path}/ad_concertDeleteAction.con?conNo=" + conNo + "'");
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
                    <h1 class="h3 mb-2 text-gray-800">국내공연 관리</h1>



                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3 d-flex justify-content-between align-items-center">
   							<h6 class="m-0 font-weight-bold text-primary">국내공연 목록</h6>
   							<button class="btn btn-primary" id="btnInsert" onclick="window.location='ad_concertAdd.con'">공연등록</button>
						</div>

                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                        	<th style="width:5%">번호</th>
                                            <th style="width:5%">이미지</th>
                                            <th style="width:7%">카테고리</th>
                                            <th style="width:13%">공연명</th>
                                            <th style="width:15%">공연일/시간</th>
                                            <th style="width:15%">공연장소</th>
                                            <th style="width:10%">공연상태</th>
                                            <th style="width:15%">등록일</th>
                                            <th style="width:15%">공연관리</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                        	<th>번호</th>
                                            <th>이미지</th>
                                            <th>카테고리</th>
                                            <th>공연명</th>
                                            <th>공연일/시간</th>
                                            <th>공연장소</th>
                                            <th>공연상태</th>
                                            <th>등록일</th>
                                            <th>공연관리</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    	<!-- 상품이 있으면 -->
			                           <c:forEach var="dto" items="${list}">
				                           <tr>
				                              <td> ${dto.conNo} </td>
				                              <td> <img src="${dto.conImg}" width="80px" height="100px"> </td>
				                              <td> ${dto.conCategory} </td>
				                              <td> ${dto.conName} </td>
				                              <td> ${dto.conTime} </td>
				                              <td> ${dto.conPlace} </td>
				                              <td> ${dto.conStatus} </td>
				                              <td> ${dto.conIndate} </td>
				                              <td>
				                              		<button class="btn btn-secondary" id="btnEdit" onclick="window.location='${path}/ad_concertModify.con?conNo=${dto.conNo}&pageNum=${paging.pageNum}'">수정</button>
				                              		<button class="btn btn-danger" id="btnDelete" href="#" data-toggle="modal" data-target="#DeleteModal"
				                              		onclick="setConNo(${dto.conNo})">삭제</button>
				                           	  </td>
				                           </tr>
			                           </c:forEach>
                                    
                                    <%-- <tr>
		                           		<td colspan="12" align="center">
		                           			<!-- 페이징 처리 -->
		                           			<!-- 이전 버튼 활성화 -->
		                           			<c:if test="${paging.startPage > 10}" >
		                           				<a href="${path}/ad_concertEdit.con?pageNum=${paging.prev}">[이전]</a>
		                           			</c:if>
		                           			
		                           			<!-- 페이지 번호 처리 -->
		                           			<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
		                           				<a href="${path}/ad_concertEdit.con?pageNum=${num}">${num}</a>
		                           			</c:forEach>
		                           			
		                           			<!-- 다음 버튼 활성화 -->
		                           			<c:if test="${paging.startPage < paging.pageCount}" >
		                           				<a href="${path}/ad_concertEdit.con?pageNum=${paging.next}">[다음]</a>
		                           			</c:if>
		                           		</td>
		                           </tr> --%>
                                    
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->


            <!-- footer 시작 -->
			<%@ include file="../ad_common/ad_footer.jsp" %>
			<!-- footer 끝 -->
			
    
    <!-- 공연 삭제 모달 delete Modal-->
    <div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">공연 삭제</h5>
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