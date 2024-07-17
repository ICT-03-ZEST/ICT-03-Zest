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

import pj.mvc.jsp.dto.AdminBoardDTO;

public class AdminBoardDAOImpl implements AdminBoardDAO{

	//커넥션 풀 객체를 보관
	DataSource dataSource = null;
	
	// 싱글톤 객체 생성.
	private static AdminBoardDAOImpl instance = new AdminBoardDAOImpl();
	
	public static AdminBoardDAOImpl getInstance() {
		if(instance == null) {
			return instance;
		}
		return instance;
	}
	
	// 디폴트 생성자 
	// 커넥션 풀(DBCP : DataBase Connection Pool 방식) - context.xml에 설정
	private AdminBoardDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/ict03_zest");//java:comp/env/Resource name명 
		
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// 공연후기,자유게시판 목록 조회
	@Override
	public List<AdminBoardDTO> boardList(String category, int start, int end) {
		System.out.println("DAO - boardList");
		
		System.out.println("Category: " + category + ", Start: " + start + ", End: " + end);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<AdminBoardDTO> list = new ArrayList<AdminBoardDTO>();
		try { 
			conn = dataSource.getConnection();
			
			if(category.equals("공연후기")) {
				String sql = "SELECT * FROM reviewboard_tbl "
						+   "WHERE board_show = 'y' "
						+ 	"ORDER BY board_num";
				
				pstmt = conn.prepareStatement(sql);
//				pstmt.setInt(1, start);
//				pstmt.setInt(2, end);
			
				rs = pstmt.executeQuery();
			}
			else {//자유게시판
				String sql = "SELECT * FROM freeBoard_tbl "
						+   "WHERE board_show = 'y' "
						+ 	"ORDER BY board_num";
				
				pstmt = conn.prepareStatement(sql);
//				pstmt.setInt(1, start);
//				pstmt.setInt(2, end);
			
				rs = pstmt.executeQuery();
			}
			
			 while(rs.next()) { 
				 AdminBoardDTO dto = new AdminBoardDTO();
			 
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
		System.out.println("list : " + list);
		return list;
	}

	// 공연후기,자유게시판 게시글 개수 구하기
	@Override
	public int boardCnt(String category) {
		System.out.println(" DAO - boardCnt");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		
		try {
			conn = dataSource.getConnection();
			if(category.equals("공연후기")) {
				
				String sql = "SELECT COUNT(*) AS cnt "
						+ 	  "FROM reviewBoard_tbl "
						+ 	  "WHERE board_show = 'y'";
				
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					total = rs.getInt("cnt");
				}
			}
			else { //자유게시판
				String sql = "SELECT COUNT(*) AS cnt "
						+ 	   "FROM freeBoard_tbl "
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

	// 공연후기, 자유게시판 게시글 삭제
	@Override
	public int boardDelete(int board_num, String category) {
		System.out.println("DAO - boardDelete");
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deleteCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			if(category.equals("공연후기")) {
				
				String sql = "UPDATE reviewBoard_tbl "
						+    "   SET board_show = 'n' "
						+    " WHERE board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board_num);
				
				deleteCnt = pstmt.executeUpdate();
				System.out.println("deleteCnt : " + deleteCnt);
			}
			else {
				String sql = "UPDATE freeBoard_tbl "
						+    "   SET board_show = 'n' "
						+    " WHERE board_num = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board_num);
				
				deleteCnt = pstmt.executeUpdate();
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
		return deleteCnt;
	}


}
