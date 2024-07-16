<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>writing</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/css/normal_board/writing.css">
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<script src="${path}/resources/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	
   $(function() {
	   $('#board_thumnail').change(function() {
           let fileName = $(this).val().split('\\').pop();
           $('#thumnail_name').text(fileName);
       });

       $('#board_image').change(function() {
       	let fileName = $(this).val().split('\\').pop();
           $('#image_name').text(fileName);
       });
   });
</script>
</head>
<body>
    <!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->

    <!-- 로그인 시 게시글 작성가능 -->
    <!-- 컨텐츠 시작 -->
    <h3>게시글 작성하기</h3>
    <!-- 드롭다운 => 카테고리 공연 ,페스티벌-->
    <div class="outline">
        <form id="myWritingForm" action="boardInsertAction.bc" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="hiddenThumnail" value="/js_pj_fasticat/resources/upload/default.jpg">
        	<input type="hidden" name="hiddenImage" value="">
        
                <table class="container">
                    <tr><!-- 제목  -->
                        <td colspan="2"><input class="title" type="text" name="board_title" id="board_title" size="30px" placeholder="제목을 입력하세요" required autofocus></td>   
                    </tr>

					<tr>
						<td colspan="2">
							<label class="btn_choice" for="board_thumnail">thumnail</label>
		          			<input type="file" class="input_file" name="board_thumnail" id="board_thumnail" accept="image/*">
		          			<span class="file_name" id="thumnail_name">파일선택 없음</span>
	                    </td>
					</tr>
					
                    <tr>
                        <td> <!-- 게시판 유형  -->
                            <div class="div_medium">
                                <select name="board_category" id="board_category" required>
                                    <option <c:if test="${category == '공연후기'}">selected</c:if> value="공연후기">공연후기</option>
	                                <option <c:if test="${category == '자유'}">selected</c:if> value="자유">자유주제</option>
                                </select>
                            </div>
                       </td>      
	                    <td align="left">  	<!-- 이미지  -->
                            <label class="btn_choice" for="board_image">image</label>
			        		<input type="file" class="input_file" name="board_image" id="board_image" accept="image/*">
			        		<span class="file_name" id="image_name">파일선택 없음</span>
                        </td> 
                    </tr>

                    <tr>
                        <td colspan="2"><hr class="hr_between"></td>
                    </tr>

                    <tr> <!-- 내용  -->
                        <td colspan="2" class="td_content">
                        	<textarea name="board_content" id="board_content" cols="50" rows="15" placeholder="내용을 입력하세요" required></textarea>
                        </td>
                    </tr>
                </table>
                    <div class="input_btn">
                        <input class="upload" type="submit" name="writing" value="작성완료">
                        <input class="reset" type="reset" name="reset" value="초기화">
                        <input class="cancel" type="button" name="cancel" value="작성취소" onclick="history.back()">
                    </div>         
        </form>
    </div>
     <!-- 컨텐츠 끝 -->

	<!-- footer 시작-->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer 끝--></body>
</html>