<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>페스티벌</title> 
<link rel="stylesheet" type="text/css" href="${path}/resources/css/category_board.css/category.css">
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="${path}/resources/js/jquery-3.7.1.min.js"></script>
<script src="${path}/resources/js/request.js"></script>
<script type="text/javascript">
$(function() {
	
	 $('.link_festivInfo').each(function(){
	      let fesNo = $(this).data('fes_no');
	     	$(this).attr('href','${path}/festivInfo.fv?fesNo='+ fesNo +'&pageNum=${paging.pageNum}');
	     	
	    });
	
});
</script>
</head>
<body>
   	<!-- header 시작-->
	<%@ include file="/common/header.jsp"%>
	<!-- header 끝-->
	
	<!-- 컨텐츠 정렬 -->
	<c:set var="totalSize" value="${fn:length(list)}"/>
	<c:set var="rows" value="${totalSize / 4}" />
	<c:if test="${totalSize % 4 != 0}">
	    <c:set var="rows" value="${rows + 1}" />
	</c:if>

    <!-- 컨텐츠 시작 -->
    <section> 
         <div class="nav_title" align="center"><span>페스티벌</span></div>

        <hr class="hr_line">

		<!-- 행 반복 -->
		<div class="catgry_tab">
			<div id="k_tab" class="photo_container">
				<c:forEach var="rowIndex" begin="0" end="${rows - 1}">
				    <ul class="photo_list">
				        <!-- 열 반복 -->
				        <c:forEach var="colIndex" begin="0" end="3">
				            <c:set var="itemIndex" value="${rowIndex * 4 + colIndex}" />
				             <c:choose>
				                <c:when test="${itemIndex < totalSize}">
				                    <li class="photo_box">
				                        <div class="photo">
				                            <a class="link_festivInfo" data-fes_no="${list[itemIndex].fesNo}"><img src="${list[itemIndex].fesImg}"></a>
				                        </div>
				                        <div>${list[itemIndex].fesName}</div>
				                    </li>
				                </c:when>
				                
				                <c:otherwise>
				                    <li class="photo_box">
				                        <div class="photo">
				                            <img class="no-data">
				                        </div>
				                        <div></div>
				                    </li>
				                </c:otherwise>
				            </c:choose>
				        </c:forEach>
				    </ul>
				</c:forEach>
			</div>
	 	</div>   
		<!-- 콘텐츠 끝 -->
	    <hr class="hr_line">
      
	    <!-- 페이징 처리 -->	
		<div class="btnNum" align="center">
	        <div>
	        	<!-- 이전 버튼 활성화 -->
				<c:if test="${paging.startPage > 3}"> <!-- 시작페이지가 4부터 시작할때 이전버튼이 보임-->
					<a class="prev" href="${path}/festivalList.fv?pageNum=${paging.prev}"> << </a>
				</c:if>
	        </div>
	        
	        <div>
	        	<!-- 페이지 번호 처리 -->
				<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
					<a href="${path}/festivalList.fv?pageNum=${num}">${num}</a>
				</c:forEach>
	        </div>
	        
	        <div>
	        	<!-- 다음 버튼 활성화 -->	
				<c:if test="${paging.endPage < paging.pageCount}"> <!-- 마지막페이지가 전체페이지수()보다 작을때 다음버튼이 보임-->
					<a class="next"  href="${path}/festivalList.fv?pageNum=${paging.next}"> >> </a>
				</c:if>
	        </div>
	    </div>  <!-- 페이징 처리 끝-->
    </section>
   
    <!-- footer 시작-->
	<%@ include file="/common/footer.jsp"%>
	<!-- footer 끝-->
	

</body>
<!-- 
	
	// 페스티벌 목록
	@Override
	public List<AdminFestivalDTO> festivalList(int start, int end) {
		System.out.println("AdminFestivalDAOImpl - festivalList() ");
		System.out.println("start: " + start);
		System.out.println("end: " + end);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<AdminFestivalDTO> list = new ArrayList<AdminFestivalDTO>();
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT * "
						+ " FROM "
						+ "    ( "
						+ "     SELECT b.*, ROWNUM AS rn "
						+ "      FROM ( "
						+ "        SELECT * "
						+ "          FROM mvc_ad_festival_tbl "
						+ "        ORDER BY fesNo DESC "
						+ "       ) b "
						+ "    ) "
						+ " WHERE rn BETWEEN ? AND ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				AdminFestivalDTO dto = new AdminFestivalDTO();
				
				dto.setFesNo(rs.getInt("fesNo"));
				dto.setFesName(rs.getString("fesName"));
				dto.setFesGrade(rs.getString("fesGrade"));
				dto.setFesTime(rs.getString("fesTime"));
				dto.setFesPlace(rs.getString("fesPlace"));
				dto.setFesImg(rs.getString("fesImg"));
				dto.setFesBuy(rs.getString("fesBuy"));
				dto.setFesPrice(rs.getInt("fesPrice"));
				dto.setFesContent(rs.getString("fesContent"));
				dto.setFesStatus(rs.getString("fesStatus"));
				dto.setFesIndate(rs.getDate("fesIndate"));
				
				list.add(dto);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	} -->
</html>
