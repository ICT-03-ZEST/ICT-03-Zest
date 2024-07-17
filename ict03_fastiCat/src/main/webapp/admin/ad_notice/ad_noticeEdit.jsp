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

    <title>공지사항 관리</title>

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
    function setNoticeNo(noticeNo) {
        document.getElementById('deleteButton').setAttribute('onclick', "window.location='${path}/ad_noticeDeleteAction.not?noticeNo=" + noticeNo + "'");
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
                    <h1 class="h3 mb-2 text-gray-800">공지사항 관리</h1>


                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3 d-flex justify-content-between align-items-center">
   							<h6 class="m-0 font-weight-bold text-primary">공지사항 목록</h6>
   							<button class="btn btn-primary" id="registerButton" onclick="window.location='ad_noticeAdd.not'">공지사항 등록</button>
						</div>

                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                        	<th style="width:5%">번호</th>
                                            <th style="width:15%">제목</th>
                                            <th style="width:20%">내용</th>
                                            <th style="width:10%">작성자</th>
                                            <th style="width:10%">조회수</th>
                                            <th style="width:10%">등록일</th>
                                            <th style="width:10%">공지사항 관리</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                        	<th>번호</th>
                                            <th>제목</th>
                                            <th>내용</th>
                                            <th>작성자</th>
                                            <th>조회수</th>
                                            <th>등록일</th>
                                            <th>공지사항 관리</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    	<!-- 공지사항이 있으면 -->
			                           <c:forEach var="dto" items="${list}">
                                        <tr>
                                            <td>${dto.noticeNo}</td>
                                            <td>${dto.noticeTitle}</td>
                                            <td>${dto.noticeContent}</td>
                                            <td>${dto.noticeWriter}</td>
                                            <td>${dto.noticeReadCnt}</td>
                                            <td>${dto.noticeRegDate}</td>
                                            <td>
                                            <button class="btn btn-secondary" id="btnEdit" onclick="window.location='${path}/ad_noticeModify.not?noticeNo=${dto.noticeNo}&pageNum=${paging.pageNum}'">수정</button>
				                              		<button class="btn btn-danger" id="btnDelete" href="#" data-toggle="modal" data-target="#DeleteModal"
				                              		onclick="setNoticeNo(${dto.noticeNo})">삭제</button>
                       						</td>
                                        </tr>
                                     </c:forEach>
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
    
    <!-- 공지사항 삭제 모달 delete Modal-->
    <div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">공지사항 삭제</h5>
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