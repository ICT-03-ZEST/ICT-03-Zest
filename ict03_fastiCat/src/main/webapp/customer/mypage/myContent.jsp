<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>나의 게시글 상세페이지</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/normal_board/review_free_content.css"> 
    <script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
</head>
<body>
    <!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->

    <section>
        <div class="review_box">
            <!-- 닉네임 --> 
          <div class="head">
                <div class="icon"><i class="fa-regular fa-circle-user"></i></div>
                <ul>
                    <li class="writer"> <span>닉네임</span>
                        <ul>
                            <li class="regDate">2024-06-15</li>
                            <li class="views">조회수 88</li>
                            <li><i class="fa-regular fa-heart"> 11</i></li>
                        </ul>
                    </li>   
                </ul>

                <div class="category">  
                    <span>자유 게시판</span> <!-- 카테고리 자유, 후기(공연, 페스티벌) 자동입력 -->
                </div>
          </div>  

          <div class="top_btn">
                <input type="button"><i class="fa-regular fa-heart"></i></input> 
                
                <div class="btn_center">
                <button class="btn_mod" onclick="">수정</button> 
                <button class="btn_del" onclick="">삭제</button>     
                </div>
          </div>

          <!-- 컨텐츠 -->
          <div class="content_box">
            <ul>
                <li><img src="${path}/resources/images/공연후기.jfif"></li>
                <li>
                    <div class="content_text">
                        <p>
                          쏼라  쏼라
                        </p>     
                    </div>
                </li> 
            </ul>
          </div>
        </div>
    </section>

	<!-- 댓글창 -->
	<%@ include file="comment.jsp" %>
	
    <!-- 목록 이동 -->
    <div class="btnAll">
        <ul>
            <li class="listMove">
                <table class="pre_next">
                    <tr>
                        <th><button class="detailBtn" id="pre"><i class="fa-solid fa-angles-left"></i></button></th>
                        <td><label for="pre">공연제목</label></td>
                    </tr>

                    <tr>
                        <th><button class="detailBtn" id="next"><i class="fa-solid fa-angles-right"></i></button></th>
                        <td><label for="next">공연제목</label></td>
                    </tr>
                </table> 
            </li> <!-- 목록 이동 끝-->

            <!-- 목록으로 돌아가기 -->
            <li class="btn_back_box">
                <button class="btn_back" onclick="page_back()">목록</button>   
            </li>
        </ul>
    </div>
    
    <!-- footer 시작-->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer 끝-->
</body>
</html>