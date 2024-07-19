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

    <title>배너 등록</title>

    <!-- Custom fonts for this template-->
    <link href="${path}/admin/startbootstrap-admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${path}/resources/css/admin/sb-admin-2.css" rel="stylesheet">

<script> /* 이미지 미리보기 */
        function previewImage(event) {
            var reader = new FileReader();
            reader.onload = function() {
                var output = document.getElementById('imgPreview');
                output.src = reader.result;
            }
            reader.readAsDataURL(event.target.files[0]);
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
                        <h1 class="h3 mb-0 text-gray-800">배너 등록</h1>
                    </div>
					
					<!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3 d-flex justify-content-between align-items-center">
   							<h6 class="m-0 font-weight-bold text-primary">배너 등록</h6>
						</div>

                        <div class="card-body">
                            <div class="table-responsive">
                            	<form name="ad_bannerAdd" action="ad_bannerAddAction.ban" method="post" enctype="multipart/form-data">
	                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                                    <tr>
				                            <th> * 배너분류 </th>
				                            <td>                                 
			                                <select class="input" name="bannerArea" id="bannerArea" required>
			                                    <option value="메인배너1">메인배너1</option>
			                                    <option value="메인배너2">메인배너2</option>
			                                    <option value="메인배너3">메인배너3</option>
			                                    <option value="메인배너4">메인배너4</option>
			                                    <option value="메인배너5">메인배너5</option>
			                                </select>
			                                </td>
		                           		</tr>
		                           		<tr>
			                           		<td><strong> * 사용여부</strong></td>
						                    <td>
						                        <label><input type="radio" name="bannerStatus" value="사용함" checked>사용함</label>
						                        <label><input type="radio" name="bannerStatus" value="사용안함">사용안함</label>
						                    </td>
		                           		<tr>
				                             <th> * 이미지 등록 (1160*586 권장) </th>
				                             <td>
				                             	 <input type="file" class="input" name="bannerImg" id="bannerImg" accept="image/*" onchange="previewImage(event)">
				                             </td>
			                            </tr>
			                            <tr>
						                    <th> 이미지 미리보기 </th>
						                    <td>
						                        <img src="#" width="200px" height="100px" id="imgPreview" class="img-preview" alt="이미지 미리보기">
						                    </td>
						                </tr>
			                           
			                            <tr>
			                              <td colspan="2">
			                                 <br>
			                                 <div style="display: flex; justify-content: space-between; align-items: center;">
			                                 	<span>* 이미 등록된 메인배너는 새로 등록할 수 없습니다.(수정 또는 삭제만 가능)</span>
			                                   <div style="display: flex; gap: 10px;">
			                                    <input class="btn btn-primary inputButton" type="submit" value="배너등록">
			                                    <input class="btn btn-secondary inputButton" type="button" value="배너목록" onclick="window.location='ad_bannerEdit.ban'">
			                                 	</div>
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