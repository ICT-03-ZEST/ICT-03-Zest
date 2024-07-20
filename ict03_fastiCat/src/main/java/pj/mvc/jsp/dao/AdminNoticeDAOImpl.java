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

public class AdminNoticeDAOImpl implements AdminNoticeDAO{

	// 커넥션 풀 객체를 보관
	DataSource dataSource = null;
	
	// 싱글톤 객체 생성
	static AdminNoticeDAOImpl instance = new AdminNoticeDAOImpl();
	
	public static AdminNoticeDAOImpl getInstance() {
		if(instance == null) {
			instance  = new AdminNoticeDAOImpl();
		}
		return instance;
	}
	
	// 디폴트 생성자 
	// 커넥션 풀(DBCP : DataBase Connection Pool 방식) - context.xml에 설정
	private AdminNoticeDAOImpl () {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ict03_zest");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	// 공지사항 등록
	@Override
	public int noticeInsert(NoticeDTO dto) {
		System.out.println("AdminNoticeDAOImpl - noticeInsert()");
		int insertCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO mvc_Notice_TBL(N_Board_Num, N_Title, N_Content, N_Writer, N_readCnt, N_WriteDate) "
					+ " VALUES((SELECT NVL(MAX(N_Board_Num)+1, 1) FROM mvc_Notice_TBL), ?, ?, ?, 0, sysdate)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getN_Title());
			pstmt.setString(2, dto.getN_Content());
			pstmt.setString(3, dto.getN_Writer());
			
			insertCnt = pstmt.executeUpdate(); //I, U, D에 사용
			System.out.println("insertCnt : " + insertCnt); //리턴타입은 int => 성공(1) 실패(0)
		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return insertCnt;
	}

	// 공지사항 갯수 구하기
	@Override
	public int noticeCnt() {
		System.out.println("AdminNoticeDAOImpl - noticeCnt()");
		int total = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM mvc_Notice_TBL";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt("cnt");
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
		
		return total;
	}

	// 공지사항 목록
	@Override
	public List<NoticeDTO> noticeList(int start, int end) {
		System.out.println("AdminNoticeDAOImpl - noticeList() ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<NoticeDTO> list = new ArrayList<NoticeDTO>();
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT * FROM mvc_Notice_TBL "
					 + "WHERE show = 'y' "
					 + "ORDER BY N_Board_Num";
			
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				NoticeDTO dto = new NoticeDTO();
				
				dto.setN_Board_Num(rs.getInt("N_Board_Num"));
				dto.setN_Title(rs.getString("N_Title"));
				dto.setN_Content(rs.getString("N_Content"));
				dto.setN_Writer(rs.getString("N_Writer"));
				dto.setN_ReadCnt(rs.getInt("N_readCnt"));
				dto.setN_WriteDate(rs.getDate("N_WriteDate"));
				
				list.add(dto);
				System.out.println("list" + list);
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
	}

	// 공지사항 상세 페이지
	@Override
	public NoticeDTO getBoardDetail(int N_Board_Num) {
		System.out.println("DAO - getBoardDetail()");
		
		// DTO 생성
		NoticeDTO dto = new NoticeDTO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM mvc_Notice_TBL "
					+ "WHERE N_Board_Num=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, N_Board_Num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setN_Board_Num(rs.getInt("N_Board_Num"));
				dto.setN_Title(rs.getString("N_Title"));
				dto.setN_Writer(rs.getString("N_Writer"));
				dto.setN_Content(rs.getString("N_Content"));
				dto.setN_ReadCnt(rs.getInt("N_readCnt"));
				dto.setN_WriteDate(rs.getDate("N_WriteDate"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}

	// 공지사항 수정
	@Override
	public int updateNotice(NoticeDTO dto) {
		System.out.println("DAO - updateNotice");
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_Notice_TBL "
					+ "SET N_Title=?, N_Content=?, N_Writer=?, N_WriteDate=sysdate "
					+ "WHERE N_Board_Num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getN_Title());
			pstmt.setString(2, dto.getN_Content());
			pstmt.setString(3, dto.getN_Writer());
			pstmt.setInt(4, dto.getN_Board_Num());
			
			System.out.println(dto);
			updateCnt = pstmt.executeUpdate(); //I, U, D에 사용
			System.out.println("updateCnt : " + updateCnt); //리턴타입은 int => 성공(1) 실패(0)
		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return updateCnt;
	}

	// 공지사항 삭제 
	@Override
	public int deleteNotice(int N_Board_Num) {
		System.out.println("DAO - deleteNotice ");
		
		int deleteCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_Notice_TBL "
					+ "SET show='n' "
					+ "WHERE N_Board_Num =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, N_Board_Num);
			
			deleteCnt = pstmt.executeUpdate();
			System.out.println("deleteCnt : " + deleteCnt);
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return deleteCnt;
	}

}
