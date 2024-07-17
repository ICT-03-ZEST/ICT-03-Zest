<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>자유게시판 관리</title>

    <!-- Custom fonts for this template -->
    <link href="../startbootstrap-admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../../resources/css/admin/sb-admin-2.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="../startbootstrap-admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

			<!-- header 시작 -->
			<%@ include file="../ad_common/ad_header.jsp" %>
	     	<!-- header 끝 -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">자유게시판 관리</h1>



                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">자유게시판</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                        	<th>게시글번호</th>
                                            <th>제목</th>
                                            <th>내용</th>
                                            <th>작성자</th>
                                            <th>작성일</th>
                                            <th>게시글 삭제</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>게시글번호</th>
                                            <th>제목</th>
                                            <th>내용</th>
                                            <th>작성자</th>
                                            <th>작성일</th>
                                            <th>게시글 삭제</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>자유게시판 제목1</td>
                                            <td>자유게시판 내용1</td>
                                            <td>홍길동</td>
                                            <td>2024/06/01</td>
                                            <td><!-- 자유게시글 삭제 모달 -->
					    						<button class="btn btn-danger"
					    						href="#" data-toggle="modal" data-target="#DeleteModal">삭제</button>
					    					</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>자유게시판 제목2</td>
                                            <td>자유게시판 제목2</td>
                                            <td>홍길동</td>
                                            <td>2024/06/02</td>
                                            <td>
                                        		<!-- 자유게시글 삭제 모달 -->
					    						<button class="btn btn-danger"
					    						href="#" data-toggle="modal" data-target="#DeleteModal">삭제</button>
                                        	</td>
                                        </tr>
                                        <tr>
                                            <td>4</td>
                                            <td>제목1</td>
                                            <td>내용1</td>
                                            <td>홍길동</td>
                                            <td>2024/06/16</td>
                                            <td>
                                        		<!-- 자유게시글 삭제 모달 -->
					    						<button class="btn btn-danger"
					    						href="#" data-toggle="modal" data-target="#DeleteModal">삭제</button>
                                        	</td>
                                        </tr>
                                        <tr>
                                            <td>5</td>
                                            <td>제목1</td>
                                            <td>내용1</td>
                                            <td>홍길동</td>
                                            <td>2024/06/16</td>
                                            <td>
                                        		<!-- 자유게시글 삭제 모달 -->
					    						<button class="btn btn-danger"
					    						href="#" data-toggle="modal" data-target="#DeleteModal">삭제</button>
                                        	</td>
                                        </tr>
                                        <tr>
                                            <td>6</td>
                                            <td>제목1</td>
                                            <td>내용1</td>
                                            <td>홍길동</td>
                                            <td>2024/06/16</td>
                                            <td>
                                        		<!-- 자유게시글 삭제 모달 -->
					    						<button class="btn btn-danger"
					    						href="#" data-toggle="modal" data-target="#DeleteModal">삭제</button>
                                        	</td>
                                        </tr>
                                        <tr>
                                            <td>7</td>
                                            <td>제목1</td>
                                            <td>내용1</td>
                                            <td>홍길동</td>
                                            <td>2024/06/16</td>
                                            <td>
                                        		<!-- 자유게시글 삭제 모달 -->
					    						<button class="btn btn-danger"
					    						href="#" data-toggle="modal" data-target="#DeleteModal">삭제</button>
                                        	</td>
                                        </tr>
                                        <tr>
                                            <td>8</td>
                                            <td>제목1</td>
                                            <td>내용1</td>
                                            <td>홍길동</td>
                                            <td>2024/06/16</td>
                                            <td>
                                        		<!-- 자유게시글 삭제 모달 -->
					    						<button class="btn btn-danger"
					    						href="#" data-toggle="modal" data-target="#DeleteModal">삭제</button>
                                        	</td>
                                        </tr>
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
    
    <!-- 자유게시글 삭제 모달 delete Modal-->
    <div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">자유게시글 삭제</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">정말 삭제 하시겠습니까?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                    <a class="btn btn-primary" href="ad_freeboardDeleteAction.jsp">삭제</a>
                </div>
            </div>
        </div>
    </div>


</body>

</html>