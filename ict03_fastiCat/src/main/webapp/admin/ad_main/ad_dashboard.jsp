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
    <title>관리자 - 현황조회</title>
    <!-- Custom fonts for this template-->
    <link href="../startbootstrap-admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="../../resources/css/admin/sb-admin-2.css" rel="stylesheet">
</head>
<body id="page-top">

      			<!-- header 시작 -->
				<%@ include file="../ad_common/ad_header.jsp" %>
		     	<!-- header 끝 -->


                <!-- Begin Page Content 현황조회 대시보드 -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">현황조회</h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">

						<!-- 회원가입 수 card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-blue shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-blue text-uppercase mb-1">
                                                전체 회원 수 </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">215</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-users fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 전체 게시판 조회수 card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                전체 게시판 조회수 </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">12,345</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-eye fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 등록된 공연 및 페스티벌 수 card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-warning shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                등록된 공연 및 페스티벌 수</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">102</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-music fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 일주일간 등록된 게시글 수 card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                                일주일간 등록된 게시글 수</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">57</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    

                    <!-- Content Row -->

                    <div class="row">

                        <!-- Area Chart 웹사이트 방문자 수 (월별) -->
						<div class="col-xl-8 col-lg-7">
						    <div class="card shadow mb-4">
						        <!-- Card Header - Dropdown -->
						        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						            <h6 class="m-0 font-weight-bold text-primary">웹사이트 방문자 수 (월별)</h6>
						        </div>
						        <!-- Card Body -->
						        <div class="card-body">
						            <div class="chart-area">
						                <canvas id="myAreaChart"></canvas>
						            </div>
						        </div>
						    </div>
						</div>

                        
       						<div class="card shadow mb-4">
			            		<div class="card-header py-3">
			                		<h6 class="m-0 font-weight-bold text-primary">인기 게시글 목록</h6>
			            		</div>
			            	<div class="card-body">
			               		<div class="table-responsive">
			                  	  <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			                        
			                            <tr>
			                                <th>제목</th>
			                                <th>작성자</th>
			                                <th>조회수</th>
			                                <th>작성일</th>
			                            </tr>
			                            <tr>
			                                <td>제목1</td>
			                                <td>이름</td>
			                                <td>100</td>
			                                <td>2024/05/10</td>
			                            </tr>
			                            <tr>
			                                <td>제목1</td>
			                                <td>이름</td>
			                                <td>100</td>
			                                <td>2024/05/10</td>
			                            </tr>
			                            <tr>
			                                <td>제목1</td>
			                                <td>이름</td>
			                                <td>100</td>
			                                <td>2024/05/10</td>
			                            </tr>
			                            <tr>
			                                <td>제목1</td>
			                                <td>이름</td>
			                                <td>100</td>
			                                <td>2024/05/10</td>
			                            </tr>
			                            <tr>
			                                <td>제목1</td>
			                                <td>이름</td>
			                                <td>100</td>
			                                <td>2024/05/10</td>
			                            </tr>
			                    	</table>
			                	</div>
			           		</div>
				       	 	</div>
                    </div>

                </div>
                <!-- /.container-fluid -->
            <!-- End of Main Content 현황조회 대시보드 끝-->

    		<!-- footer 시작 -->
			<%@ include file="../ad_common/ad_footer.jsp" %>
			<!-- footer 끝 -->
  

</body>

</html>