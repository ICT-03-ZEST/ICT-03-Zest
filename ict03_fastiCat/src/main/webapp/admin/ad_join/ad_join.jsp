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

    <title>관리자 회원가입</title>

    <!-- Custom fonts for this template-->
    <link href="../startbootstrap-admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../../resources/css/admin/sb-admin-2.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-12">
                        <div class="p-5" >
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">관리자 회원가입</h1>
                            </div>
                            
	                        <form name="joinform" action="ad_joinAction.do" method="post">
								

	                            
                           		 <!-- AA -->
                                <div class="form-group row">
                                	<div class="col-sm-2 col-form-label text-center" align="center">
                                    	*아이디
                                    </div>
                                    <div class="col-sm-10 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="exampleFirstName"
                                            placeholder="공백없이 20자이내로 작성">
                                    </div>
                                </div>
                                <div class="form-group row">
                                 	<div class="col-sm-2 col-form-label text-center" align="center">
                                    	*비밀번호
                                    </div>
                                    <div class="col-sm-5 mb-3 mb-sm-0">
                                        <input type="password" class="form-control form-control-user"
                                            id="exampleInputPassword" placeholder="공백없이 20자이내로 작성">
                                    </div>
                                    <div class="col-sm-5">
                                        <input type="password" class="form-control form-control-user"
                                            id="exampleRepeatPassword" placeholder="비밀번호 확인">
                                    </div>
                                </div>
                                <div class="form-group row">
                                	<div class="col-sm-2 col-form-label text-center" align="center">
                                    	*이름
                                    </div>
                                    <div class="col-sm-10 mb-3 mb-sm-0">
                                        <input type="email" class="form-control form-control-user" id="exampleInputEmail"
                                        placeholder="이름 작성">
                                    </div>
                                </div>
                                <div class="form-group row">
                                	<div class="col-sm-2 col-form-label text-center" align="center">
                                    	*생년월일
                                    </div>
                                    <div class="col-sm-10 mb-3 mb-sm-0">
                                        <input type="email" class="form-control form-control-user" id="exampleInputEmail"
                                        placeholder="생년월일 ">
                                    </div>
                                </div>
                                <div class="form-group row">
                                	<div class="col-sm-2 col-form-label text-center" align="center">
                                    	*주소
                                    </div>
                                    <div class="col-sm-10 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="exampleFirstName"
                                            placeholder="주소 작성">
                                    </div>
                                </div>
                                <div class="form-group row">
                                 	<div class="col-sm-2 col-form-label text-center" align="center">
                                    	*이메일
                                    </div>
                                    <div class="col-sm-3 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="exampleInputEmail" name="email1" size="30"  required>
									</div>
									<div class="col-sm-1 col-form-label text-center" align="center">
                                    	@
                                    </div>	
                                    <div class="col-sm-3 mb-3 mb-sm-0">
                                    	<input type="text" class="form-control form-control-user" id="exampleInputEmail" name="email2" size="30"  required>
                                    </div>
                                    <div class="col-sm-3 mb-3 mb-sm-0">
                                        <select class="form-control form-control-user" name="email3"  onchange="selectEmailChk()">
												<option value="0">직접입력</option>
												<option value="naver.com">네이버</option>
												<option value="google.com">구글</option>
												<option value="daum.net">다음</option>
												<option value="nate.com">네이트</option>
										</select>
                                    </div>
                                </div>
                             
                               
                                <a href="ad_login.jsp" class="btn btn-primary btn-user btn-block">
                                    가입하기
                                </a>
                                 
                            </form>      
                                 
                                 
                            <hr>
                            <div class="py-3 d-flex justify-content-between align-items-center">
                                <a class="small" href="../ad_login/ad_login_find.jsp">아이디/비밀번호 찾기</a>
                                <a class="small" href="../ad_login/ad_login.jsp">이미 계정이 있다면? 로그인하기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="../startbootstrap-admin/vendor/jquery/jquery.min.js"></script>
    <script src="../startbootstrap-admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../startbootstrap-admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../startbootstrap-admin/js/sb-admin-2.min.js"></script>

</body>

</html>