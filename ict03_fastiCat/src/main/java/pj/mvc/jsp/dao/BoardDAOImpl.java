package pj.mvc.jsp.dao;

import java.sql.Connection;  


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import pj.mvc.jsp.dto.BoardDTO;
import pj.mvc.jsp.dto.CommentDTO;
import pj.mvc.jsp.dto.HeartDTO;

public class BoardDAOImpl implements BoardDAO {
	
	//커넥션 풀 객체를 보관
	DataSource dataSource = null;
	
	// 싱글톤 객체 생성 => 같은 주소값을 줄 수 있음
	private static BoardDAOImpl instance = new BoardDAOImpl();
	
	public static BoardDAOImpl getInstance() {
		if(instance == null) {
			return instance;
		}
		return instance;
	}
	
	// 디폴트 생성자
	//DataBase Connection Pool(DBCP): 커넥션풀 방식 - context.xml에 설정
	private BoardDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/ict03_zest");//java:comp/env/Resource name명 
		
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	// 게시판 목록 조회 - 내림차순 조회
	@Override
	public List<BoardDTO> boardList(String category, int start, int end) {

		 Connection conn = null; 
		 PreparedStatement pstmt = null; 
		 ResultSet rs = null;
		 
		//1. list 생성
			List<BoardDTO> list = new ArrayList<>();
			
		try { 
			conn = dataSource.getConnection();
			
			if(category.equals("공연후기")) {
				String sql = "SELECT * "
						+ "     FROM ( "
						+ "        SELECT A.* "
						+ "             , ROWNUM AS rn "
						//+ 			   ", NVL(board_thumnail, '/ict03_fastiCat/resources/upload/free.jfif') AS thumnail"	
						+ "          FROM "
						+ "            (SELECT * FROM reviewBoard_tbl "
						+ "              WHERE board_show = 'y' "
						+ "              ORDER BY board_num DESC "
						+ "             ) A "
						+ "        ) "
						+ " WHERE rn BETWEEN ? AND ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			
				rs = pstmt.executeQuery();
			}
			else {//자유게시판
				String sql = "SELECT * "
						+ "  FROM ( "
						+ "        SELECT A.* "
						+ "             , ROWNUM AS rn "
						//+ 			   ", NVL(board_thumnail, '/ict03_fastiCat/resources/upload/default.jpg') AS thumnail"	
						+ "          FROM "
						+ "            (SELECT * FROM freeBoard_tbl "
						+ "              WHERE board_show = 'y' " 
						+ "              ORDER BY board_num DESC "
						+ "             ) A "
						+ "        ) "
						+ " WHERE rn BETWEEN ? AND ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			
				rs = pstmt.executeQuery();
			}
			
			 while(rs.next()) { 
				 BoardDTO dto = new BoardDTO();
			 
				 dto.setBoard_num(rs.getInt("board_num"));
				 dto.setBoard_category(rs.getString("board_category"));
				 dto.setBoard_title(rs.getString("board_title"));
				 dto.setBoard_content(rs.getString("board_content"));
				 dto.setBoard_thumnail(rs.getString("thumnail"));
				 dto.setBoard_writer(rs.getString("board_writer"));
				 dto.setBoard_regDate(rs.getDate("board_regDate"));
				 dto.setBoard_views(rs.getInt("board_views"));
				 dto.setBoard_heart(rs.getInt("board_heart"));
			  
				 //4. list에 dto 추가 
				 list.add(dto); 
			 }
			 
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		//5. list 리턴
		return list;
	}
	
	// 게시글 개수 구하기 - 페이지계산시 필요
	@Override
	public int boardCnt(String category) {
		System.out.println("BoardDAOImpl - boardCnt");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		
		try {
			conn = dataSource.getConnection();
			if(category.equals("공연후기")) {
				
				String sql = "SELECT COUNT(*) AS cnt "
						+ 	   "FROM  reviewBoard_tbl "
						+ 	  "WHERE board_show = 'y'";
				
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					total = rs.getInt("cnt");
				}
			}
			else { //자유게시판
				String sql = "SELECT COUNT(*) AS cnt "
						+ 	   "FROM  freeBoard_tbl "
						+ 	  "WHERE board_show = 'y'";
				
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					total = rs.getInt("cnt");
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
	}
	//회원 게시글 이력조회
	public int selectOfwriter(BoardDTO dto) {
		System.out.println("dao - selectOfwriter");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int selWriter = 0;
		
		try {
			conn = dataSource.getConnection();
			if(dto.getBoard_category().equals("공연후기")) {
				
				String sql = "SELECT COUNT(*) AS cnt "
						+    "  FROM  reviewBoard_tbl "
						+    " WHERE board_writer = ? "
						+    "   AND board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1 , dto.getBoard_writer());
				pstmt.setInt(2, dto.getBoard_num());
				
				rs = pstmt.executeQuery();
				rs.next();
				selWriter = rs.getInt("cnt");
				
			}
			else { //자유게시판
				String sql = "SELECT COUNT(*) AS cnt "
						+    "  FROM  freeBoard_tbl "
						+    " WHERE board_writer = ? "
						+    "   AND board_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1 , dto.getBoard_writer());
				pstmt.setInt(2, dto.getBoard_num());
				
				rs = pstmt.executeQuery();
				rs.next();
				selWriter = rs.getInt("cnt");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return selWriter;
	}
	//조회수 증가
	@Override
	public void plusViews(int num, String category) {
		System.out.println("BoardDAOImpl - plusViews" + category);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			if(category.equals("공연후기")) {
				
				String sql = "UPDATE reviewBoard_tbl "
						   +   " SET board_views = board_views + 1 "
						   + " WHERE board_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				return;
			}
			else {
				String sql = "UPDATE freeBoard_tbl "
						   +   " SET board_views = board_views + 1 "
						   + " WHERE board_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				return;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 게시글 상세페이지
	@Override
	public BoardDTO getBoardDetail(int num, String category) {
		
		BoardDTO dto = new BoardDTO();
		System.out.println("BoardDAOImpl - getBoardDetail");
		
		 Connection conn = null; 
		 PreparedStatement pstmt = null; 
		 ResultSet rs = null;
		 
		try { 
			conn = dataSource.getConnection();
			
			if(category.equals("공연후기")) {
				String sql = "SELECT * "
						+ "  FROM reviewBoard_tbl "
						+ " WHERE board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
			
				rs = pstmt.executeQuery();
			}
			else {
				String sql = "SELECT * "
						+ "  FROM freeBoard_tbl f "
						+ " WHERE board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
			
				rs = pstmt.executeQuery();
			}
			
			if(rs.next()) {
				
				//3. dto에 한건의 rs 게시글 정보 담기
				dto.setBoard_num(rs.getInt("board_num"));
				dto.setBoard_category(rs.getString("board_category"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_content(rs.getString("board_content"));
				dto.setBoard_thumnail(rs.getString("board_thumnail"));
				dto.setBoard_image(rs.getString("board_image"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_regDate(rs.getDate("board_regDate"));
				dto.setBoard_views(rs.getInt("board_views"));
				dto.setBoard_heart(rs.getInt("board_heart"));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
	// 게시판 테이블 좋아요count 수정
	public int modHeartCount(BoardDTO dto) {
		System.out.println("BoardDAOImpl - updateHeart");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			if(dto.getBoard_category().equals("공연후기")) {
				
				String sql = "UPDATE reviewBoard_tbl "
						   +   " SET board_heart = ? "
						   + " WHERE board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getBoard_heart());
				pstmt.setInt(2, dto.getBoard_num());
				updateCnt = pstmt.executeUpdate();
				
			}
			else {//자유게시판
				String sql = "UPDATE freeBoard_tbl "
						   +   " SET board_heart = ? "
						   + " WHERE board_num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getBoard_heart());
				pstmt.setInt(2, dto.getBoard_num());
				updateCnt = pstmt.executeUpdate();
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return updateCnt;
	}
	
	// 좋아요 클릭 => 하트 insert
	public int insertHeart(HeartDTO dto) {
		System.out.println("BoardDAOImpl - insertHeart");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			if(dto.getBoard_category().equals("공연후기")) {
				
				String sql = "INSERT INTO heart_reviewBoard_tbl(heart_num, board_num, userID) "
						   + "VALUES ((SELECT NVL(MAX(heart_num)+1, 1) FROM heart_reviewBoard_tbl), ?, ?)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getBoard_num());
				pstmt.setString(2, dto.getUserID());
				
				insertCnt = pstmt.executeUpdate();
			}
			else {
				String sql = "INSERT INTO heart_freeBoard_tbl(heart_num, board_num, userID) "
						   + "VALUES ((SELECT NVL(MAX(heart_num)+1, 1) FROM heart_freeBoard_tbl), ?, ?)";
							
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getBoard_num());
				pstmt.setString(2, dto.getUserID());
				
				insertCnt = pstmt.executeUpdate();
			}
			System.out.println("insertHeart-insertCnt:" + insertCnt);
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return insertCnt;
	}
		
	// 좋아요 취소 => 하트 지우기 
	public int delHeart(HeartDTO dto){
		System.out.println("dao - delHeart");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deleteCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			if(dto.getBoard_category().equals("공연후기")) {
				
				String sql = "DELETE FROM heart_reviewBoard_tbl "
							+ "WHERE userID = ? "
							+ "  AND board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getUserID());
				pstmt.setInt(2, dto.getBoard_num());
				
				deleteCnt = pstmt.executeUpdate();
			}
			else {//자유게시판
							
				String sql = "DELETE FROM heart_freeBoard_tbl "
						    + "WHERE userID = ? "
						    + "  AND board_num = ?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getUserID());
				pstmt.setInt(2, dto.getBoard_num());
				
				deleteCnt = pstmt.executeUpdate();
			}
			System.out.println("delHeart-deleteCnt:" + deleteCnt);
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return deleteCnt;
	}
	
	// 좋아요 조회
	public int selectHeart(HeartDTO dto) {
		System.out.println("BoardDAOImpl - selectHeart");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int selectCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			if(dto.getBoard_category().equals("공연후기")) {
				
				String sql = "SELECT COUNT(*) AS cnt FROM heart_reviewBoard_tbl "
						+ 	 " WHERE userID = ? "
						+ 	   " AND board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getUserID());
				pstmt.setInt(2, dto.getBoard_num());
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					selectCnt = rs.getInt("cnt");
				}
			}
			else {
				String sql = "SELECT COUNT(*) AS cnt FROM heart_freeBoard_tbl "
						+ 	 " WHERE userID = ? "
						+ 	   " AND board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getUserID());
				pstmt.setInt(2, dto.getBoard_num());
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					selectCnt = rs.getInt("cnt");
				}
			}
			System.out.println("selectHeart-selectCnt:" + selectCnt);
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return selectCnt;
	}
	
	// 게시글 작성처리
	public int insertBoard(BoardDTO dto, String category) {
		System.out.println("dao - insertBoard");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			if(category.equals("공연후기")) {
				
				String sql = "INSERT INTO reviewBoard_tbl(board_num, board_title, board_content, board_thumnail, board_image, board_writer) "
						+ 	 "VALUES ((SELECT NVL(MAX(board_num)+1, 1) FROM reviewBoard_tbl), ?, ?, COALESCE(?, '/js_pj_fasticat/resources/upload/default.jpg'), ?, ?)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getBoard_title());
				pstmt.setString(2, dto.getBoard_content());
				pstmt.setString(3, dto.getBoard_thumnail());
				pstmt.setString(4, dto.getBoard_image());
				pstmt.setString(5, dto.getBoard_writer());
				
				insertCnt = pstmt.executeUpdate();
			}
			else {
				String sql = "INSERT INTO freeBoard_tbl(board_num, board_title, board_content, board_thumnail, board_image, board_writer) "
						+ 	 "VALUES ((SELECT NVL(MAX(board_num)+1, 1) FROM freeBoard_tbl), ?, ?, ?, ?, ?)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getBoard_title());
				pstmt.setString(2, dto.getBoard_content());
				pstmt.setString(3, dto.getBoard_thumnail());
				pstmt.setString(4, dto.getBoard_image());
				pstmt.setString(5, dto.getBoard_writer());
				
				insertCnt = pstmt.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return insertCnt;
	}
	
	// 게시글 수정처리
	public int updateBoard(BoardDTO dto, String category, int num) {
		System.out.println("dao - updateBoard");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			if(category.equals("공연후기")) {
				
				String sql = "UPDATE reviewBoard_tbl "
						+    "   SET board_title = ?, board_content= ?, board_thumnail= ?, board_image = ? "
						+    " WHERE board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getBoard_title());
				pstmt.setString(2, dto.getBoard_content());
				pstmt.setString(3, dto.getBoard_thumnail());
				pstmt.setString(4, dto.getBoard_image());
				pstmt.setInt(5, num);
				
				updateCnt = pstmt.executeUpdate();
			}
			else {
				String sql = "UPDATE freeBoard_tbl "
						+    "   SET board_title = ?, board_content= ?, board_thumnail= ?, board_image = ? "
						+    " WHERE board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getBoard_title());
				pstmt.setString(2, dto.getBoard_content());
				pstmt.setString(3, dto.getBoard_thumnail());
				pstmt.setString(4, dto.getBoard_image());
				pstmt.setInt(5, num);
				
				updateCnt = pstmt.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return updateCnt;
	}
	
	// 게시글 삭제처리 - +checkbox 삭제
	public int deleteBoard(int num, String category) {
		System.out.println("dao - deleteBoard");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			if(category.equals("공연후기")) {
				
				String sql = "UPDATE reviewBoard_tbl "
						+    "   SET board_show = 'n' "
						+    " WHERE board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				updateCnt = pstmt.executeUpdate();
			}
			else {
				String sql = "UPDATE freeBoard_tbl "
						+    "   SET board_show = 'n' "
						+    " WHERE board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				updateCnt = pstmt.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return updateCnt;
	}

	// 게시글 한건 조회 - join 댓글번호
	public BoardDTO boardSelect(int num, String category) {
		System.out.println("BoardDAOImpl - boardSelect");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardDTO dto = new BoardDTO();
		try {
			conn = dataSource.getConnection();
			if(category.equals("공연후기")) {
				
				String sql = "SELECT b.* "
						+ "  FROM reviewBoard_tbl b "
						+ "  JOIN reviewComment_tbl c "
						+ "    ON b.board_num = c.board_num "
						+ " WHERE c.comment_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
			}
			else {
				String sql = "SELECT b.* "
						+ "  FROM freeBoard_tbl b "
						+ "  JOIN freeComment_tbl c "
						+ "    ON b.board_num = c.board_num "
						+ " WHERE c.comment_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
			}
			
			if(rs.next()) {
				dto.setBoard_num(rs.getInt("board_num"));
				dto.setBoard_category(rs.getString("board_category"));
				dto.setBoard_writer(rs.getString("board_writer"));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
		
	}
		
	// 댓글 목록조회
	@Override
	public List<CommentDTO> cmtList(int num, String category) {
		
		System.out.println("dao - cmtList");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CommentDTO> list = new ArrayList<>();
		
		try {
			conn = dataSource.getConnection();
			if(category.equals("공연후기")) {
				
				String sql = "SELECT * FROM reviewComment_tbl "
						+ 	 " WHERE board_num = ? "
						+ 	 " ORDER BY comment_num DESC";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
			}
			else {
				String sql = "SELECT * FROM freeComment_tbl "
						+ 	 " WHERE board_num = ? "
						+ 	 " ORDER BY comment_num DESC";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
			}
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				
				//3. dto에 한건의 rs 게시글 정보 담기
				dto.setComment_num(rs.getInt("comment_num"));
		        dto.setBoard_num(rs.getInt("board_num"));
		        dto.setBoard_category(rs.getString("board_category"));
				dto.setUserID(rs.getString("userID"));
				dto.setContent(rs.getString("content"));
				dto.setRegDate(rs.getDate("regDate"));
				
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
		
	
	// 댓글 작성처리
	@Override
	public int cmtInsert(CommentDTO dto) {
		System.out.println("BoardDAOImpl - cmtInsert");
		System.out.println("dto.getBoard_category() :" + dto.getBoard_category());
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			if(dto.getBoard_category().equals("공연후기")) {
				
				String sql = "INSERT INTO reviewComment_tbl (comment_num, board_num, userID, content) "
						+ 	 "VALUES ((SELECT NVL(MAX(comment_num)+1, 1) FROM reviewComment_tbl), ?, ?, ?)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getBoard_num());
				pstmt.setString(2, dto.getUserID());
				pstmt.setString(3, dto.getContent());
				
				insertCnt = pstmt.executeUpdate();
			}
			else {
				String sql = "INSERT INTO freeComment_tbl (comment_num, board_num, userID, content) "
						+ 	 "VALUES ((SELECT NVL(MAX(comment_num)+1, 1) FROM freeComment_tbl), ?, ?, ?)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, dto.getBoard_num());
				pstmt.setString(2, dto.getUserID());
				pstmt.setString(3, dto.getContent());
				
				insertCnt = pstmt.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return insertCnt;
	}
	// 댓글 한건 조회 - 댓글번호
	public CommentDTO cmtSelect(int num, String category) {
		System.out.println("BoardDAOImpl - cmtSelect");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		CommentDTO dto = new CommentDTO();
		try {
			conn = dataSource.getConnection();
			if(category.equals("공연후기")) {
				
				String sql = "SELECT * "
						   + "  FROM reviewComment_tbl "
						   + " WHERE comment_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
			}
			else {
				String sql = "SELECT * "
						   + "  FROM freeComment_tbl "
						   + " WHERE comment_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
			}
			
			if(rs.next()) {
				dto.setComment_num(rs.getInt("comment_num"));
				dto.setBoard_num(rs.getInt("board_num"));
				dto.setBoard_category(rs.getString("board_category"));
				dto.setUserID(rs.getString("userID"));
				dto.setContent(rs.getString("content"));
				dto.setRegDate(rs.getDate("regDate"));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
		
	}
	// 댓글 수정처리
	@Override
	public int cmtUpdate(int num, CommentDTO dto) {
		
		System.out.println("dao - cmtUpdate");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updateCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			if(dto.getBoard_category().equals("공연후기")) {
				String sql = "UPDATE reviewComment_tbl "
							+   "SET content = ? "
							+ "WHERE comment_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getContent());
				pstmt.setInt(2, num);
				
				updateCnt = pstmt.executeUpdate();
			}
			else {//자유 게시판 댓글
				String sql = "UPDATE freeComment_tbl "
						+   "SET content = ? "
						+ "WHERE comment_num = ?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dto.getContent());
				pstmt.setInt(2, num);
				
				updateCnt = pstmt.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return updateCnt;
	}
	
	// 댓글 삭제처리
	@Override
	public int cmtDelete(int num, String category) {
		System.out.println("dao - delHeart");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deleteCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			if(category.equals("공연후기")) {
				String sql = "DELETE FROM reviewComment_tbl "
							+ "WHERE comment_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				deleteCnt = pstmt.executeUpdate();
			}
			else {//자유 게시판 댓글
				String sql = "DELETE FROM freeComment_tbl "
							+ "WHERE comment_num = ?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				deleteCnt = pstmt.executeUpdate();
			}
			System.out.println("delHeart-deleteCnt:" + deleteCnt);
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return deleteCnt;
	}
	

}
