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

    <title>관리자 - 회원목록</title>

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
    function setDelete(userid) {
    	console.log("userid:", userid); // userid 값을 콘솔에 출력하여 확인
        document.getElementById('deleteButton').setAttribute('onclick', "window.location='${path}/ad_memberDeleteAction.admember?userid=" + userid + "'");
    	
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
                    <h1 class="h3 mb-2 text-gray-800">회원관리</h1>



                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">회원목록</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>아이디</th>
                                            <th>비밀번호</th>
                                            <th>이름</th>
                                            <th>생년월일</th>
                                            <th>주소</th>
                                            <th>전화번호</th>
                                            <th>이메일</th>
                                            <th>가입일</th>
                                            <th>강제탈퇴</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>번호</th>
                                            <th>아이디</th>
                                            <th>비밀번호</th>
                                            <th>이름</th>
                                            <th>생년월일</th>
                                            <th>주소</th>
                                            <th>전화번호</th>
                                            <th>이메일</th>
                                            <th>가입일</th>
                                            <th>강제탈퇴</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    	<!-- 회원목록이 있으면 -->
                                    	<c:forEach var="dto" items="${list}" varStatus="status">
                                        <tr>
                                            <td>${status.index + 1}</td>
                                            <td>${dto.userid}</td>
                                            <td>${dto.password}</td>
                                            <td>${dto.username}</td>
                                            <td>${dto.birthday}</td>
                                            <td>${dto.address}</td>
                                            <td>${dto.hp}</td>
                                            <td>${dto.email}</td>
                                            <td>${dto.regDate}</td>
                                            <td> <!-- 강제탈퇴 모달 -->
					    					<button class="btn btn-danger" id="btnDelete" href="#" data-toggle="modal" data-target="#DeleteModal"
				                              		onclick="setDelete('${dto.userid}')">강제탈퇴</button>
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
    
    <!--  회원 강제탈퇴 모달 delete Modal-->
    <div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">강제탈퇴</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">정말 강제탈퇴 처리하시겠습니까?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                    <button class="btn btn-primary" id="deleteButton">강제탈퇴</button>
                </div>
            </div>
        </div>
    </div>


</body>

</html>