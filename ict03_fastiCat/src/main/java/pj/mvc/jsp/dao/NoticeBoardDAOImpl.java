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

import pj.mvc.jsp.dto.NoticeDTO;

public class NoticeBoardDAOImpl implements NoticeBoardDAO{
	
	//커넥션 풀 객체 보관
	DataSource dataSource = null;
	
	//싱글톤 객체 생성
	static NoticeBoardDAOImpl instance = new NoticeBoardDAOImpl();
		//메서드를 통해 접근
		public static NoticeBoardDAOImpl getInstance() {
			if(instance == null) {
				instance = new NoticeBoardDAOImpl();
			}
			return instance;
		}
	//디폴트생성자
	// 커넥션풀(DBCP : DataBase Connection Pool 방식) - context.xml에 설정
	private NoticeBoardDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/ict03_zest");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}

	
	//공지사항 목록
	@Override
	public List<NoticeDTO> noticeBoardList(int start, int end) {
		System.out.println("DAO-noticeBoardList");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		List<NoticeDTO> list = new ArrayList<NoticeDTO>();
		
		try {
			conn = dataSource.getConnection();
			
		String sql = "SELECT * "
	         		+ "  FROM("
	        		+ " 	SELECT A.*,"
	                + "	     		N_Board_Num AS rn " 
	                + "			FROM "
	                + "	          (SELECT * "
	                + "              FROM mvc_Notice_TBL"
	                + "             WHERE show = 'y'"
	                + "             ORDER BY N_Board_Num DESC"
	                + "			   ) A "    
	                + "	  ) "
	                + " WHERE rn BETWEEN ? AND ?";
		
		 pstmt = conn.prepareStatement(sql);
		 pstmt.setInt(1, start);
		 pstmt.setInt(2, end);
		 
		 rs = pstmt.executeQuery();
		 
		 while(rs.next()) {
			//dto 생성
			NoticeDTO dto = new NoticeDTO();
			//dto에 1건의 정보를 담는다.
			dto.setN_Board_Num(rs.getInt("N_Board_Num"));
			dto.setN_Title(rs.getString("N_Title"));
			dto.setN_Content(rs.getString("N_Content"));
			dto.setN_Writer(rs.getString("n_Writer"));
			dto.setN_WriteDate(rs.getDate("n_WriteDate"));
			dto.setN_ReadCnt(rs.getInt("n_ReadCnt"));
			
			//list에 dto를 추가
			list.add(dto);
		 }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
	            //사용할 자원 해제
	            if(rs != null) rs.close();
	            if(pstmt != null) pstmt.close();
	            if(conn != null) conn.close();
	         } catch(SQLException e){
	            e.printStackTrace();
	         }
		}
		return list;
	}

	//공지사항 갯수 구하기
	@Override
	public int NoticeBoardCnt() {
		System.out.println("DAO-NoticeBoardCnt");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		int total=0;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT COUNT(*) AS cnt "
					   + "FROM mvc_Notice_TBL";
		
		 pstmt = conn.prepareStatement(sql);
		 
		 rs = pstmt.executeQuery();
		 
		 if(rs.next()) {
			 total = rs.getInt("cnt");
		 }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
	            //사용할 자원 해제
	            if(rs != null) rs.close();
	            if(pstmt != null) pstmt.close();
	            if(conn != null) conn.close();
	         } catch(SQLException e){
	            e.printStackTrace();
	         }
		}
		return total;
	}

	//공지사항 조회수 증가
	@Override
	public void plusNoticeReadCnt(int num) {
		System.out.println("DAO-plusNoticeReadCnt");
		
		Connection conn= null;
		PreparedStatement pstmt =null;

		try {
			conn = dataSource.getConnection();
			
			 String sql = "UPDATE mvc_Notice_TBL "
						+ "   SET N_readCnt = N_readCnt+1 "
						+ " WHERE N_Board_Num= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeQuery();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				//사용할 자원 해제
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
		

	//공지사항 상세페이지
	@Override
	public NoticeDTO getNoticeBoardDetail(int num) {
		System.out.println("DAO-getNoticeBoardDetail");
		
		//DTO생성
		NoticeDTO dto = new NoticeDTO();
		
		Connection conn= null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			
		String sql = "SELECT * FROM mvc_Notice_TBL "
	                + " WHERE N_Board_Num=?";
		
		 pstmt = conn.prepareStatement(sql);
		 pstmt.setInt(1, num);
	
		 //rs에 값 담기
		 rs = pstmt.executeQuery();
		 
		 if(rs.next()) {
			
			dto.setN_Board_Num(rs.getInt("N_Board_Num"));
			dto.setN_Title(rs.getString("N_Title"));
			dto.setN_Content(rs.getString("N_Content"));
			dto.setN_Writer(rs.getString("n_Writer"));
			dto.setN_WriteDate(rs.getDate("n_WriteDate"));
			dto.setN_ReadCnt(rs.getInt("n_ReadCnt"));
		 }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try{
	            //사용할 자원 해제
	            if(rs != null) rs.close();
	            if(pstmt != null) pstmt.close();
	            if(conn != null) conn.close();
	         } catch(SQLException e){
	            e.printStackTrace();
	         }
		}
		return dto;
	}

	//공지사항 수정처리
	@Override
	public int updateNoticeBoard(NoticeDTO dto) {
		System.out.println("DAO - updateNoticeBoard");
		
		Connection conn= null;
		PreparedStatement pstmt =null;
		
		int updateCnt = 0;
		try {
			conn = dataSource.getConnection();
			
			String sql  = "UPDATE mvc_Notice_TBL "
						+ "   SET N_title =? "
						+ "      ,N_content =?"
						+ "   WHERE N_Board_Num= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getN_Title());
			pstmt.setString(2, dto.getN_Content());
			pstmt.setInt(3, dto.getN_Board_Num());
			
			updateCnt = pstmt.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				//사용할 자원 해제
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return updateCnt;
	}
	
	//공지사항 삭제처리
	@Override
	public int deleteNoticeBoard(int num) {
		System.out.println("DAO - deleteNoticeBoard");
		
		Connection conn= null;
		PreparedStatement pstmt =null;
		
		int deleteCnt=0;
		
		try {
			conn = dataSource.getConnection();
			
			String sql="UPDATE mvc_Notice_TBL "
					+ "    SET show = 'n'"
					+ "  WHERE num= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeQuery();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try{
				//사용할 자원 해제
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return deleteCnt;
	}
	
	//공지사항 작성처리
	@Override
	public int insertNoticeBoard(NoticeDTO dto) {
		System.out.println("DAO - insertNoticeBoard");
		 Connection conn= null;
	      PreparedStatement pstmt =null;
	      
	      int insertCnt= 0;
	      
	      try {
	    	  conn = dataSource.getConnection();
	    	  
	    	  String sql="INSERT INTO mvc_Notice_TBL(N_Board_Num, N_Title, N_Content, N_Writer, N_WriteDate) "
	    			    +"VALUES((SELECT NVL(MAX(N_Board_Num)+1,1) FROM mvc_Notice_TBL), ?, ?, ?, sysdate)";
	    	  pstmt = conn.prepareStatement(sql);
	    	  
	    	  pstmt.setString(1, dto.getN_Title());
	    	  pstmt.setString(2, dto.getN_Content());
	    	  pstmt.setString(3, dto.getN_Writer());
	    	  
	    	  insertCnt = pstmt.executeUpdate();
	    	  
	    	  System.out.println("insertCnt:" + insertCnt);
	      }catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try{
					//사용할 자원 해제
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch(SQLException e){
					e.printStackTrace();
				}
			}
		
		return insertCnt;
	}

}
