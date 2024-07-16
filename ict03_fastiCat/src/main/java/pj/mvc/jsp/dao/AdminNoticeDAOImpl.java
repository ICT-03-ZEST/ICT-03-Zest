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

import pj.mvc.jsp.dto.AdminNoticeDTO;

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
	public int noticeInsert(AdminNoticeDTO dto) {
		System.out.println("AdminNoticeDAOImpl - noticeInsert()");
		int insertCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO mvc_ad_notice_tbl(noticeNo, noticeTitle, noticeContent, noticeImg, noticeWriter, noticeReadCnt, noticeRegDate) "
					+ " VALUES((SELECT NVL(MAX(noticeNo)+1, 1) FROM mvc_ad_notice_tbl), ?, ?, ?, ?, 0, sysdate)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNoticeTitle());
			pstmt.setString(2, dto.getNoticeContent());
			pstmt.setString(3, dto.getNoticeImg());
			pstmt.setString(4, dto.getNoticeWriter());
			
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
			String sql = "SELECT COUNT(*) as cnt FROM mvc_ad_notice_tbl";
			
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
	public List<AdminNoticeDTO> noticeList(int start, int end) {
		System.out.println("AdminNoticeDAOImpl - noticeList() ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<AdminNoticeDTO> list = new ArrayList<AdminNoticeDTO>();
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT * FROM mvc_ad_notice_tbl "
					 + "WHERE show = 'y' "
					 + "ORDER BY noticeNo";
			
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				AdminNoticeDTO dto = new AdminNoticeDTO();
				
				dto.setNoticeNo(rs.getInt("noticeNo"));
				dto.setNoticeTitle(rs.getString("noticeTitle"));
				dto.setNoticeContent(rs.getString("noticeContent"));
				dto.setNoticeImg(rs.getString("noticeImg"));
				dto.setNoticeWriter(rs.getString("noticeWriter"));
				dto.setNoticeReadCnt(rs.getInt("noticeReadCnt"));
				dto.setNoticeRegDate(rs.getDate("noticeRegDate"));
				
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
	public AdminNoticeDTO getBoardDetail(int noticeNo) {
		System.out.println("DAO - getBoardDetail()");
		
		// DTO 생성
		AdminNoticeDTO dto = new AdminNoticeDTO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM mvc_ad_notice_tbl "
					+ "WHERE noticeNo=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNoticeNo(rs.getInt("noticeNo"));
				dto.setNoticeTitle(rs.getString("noticeTitle"));
				dto.setNoticeWriter(rs.getString("noticeWriter"));
				dto.setNoticeContent(rs.getString("noticeContent"));
				dto.setNoticeImg(rs.getString("noticeImg"));
				dto.setNoticeReadCnt(rs.getInt("noticeReadCnt"));
				dto.setNoticeRegDate(rs.getDate("noticeRegDate"));
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
	public int updateNotice(AdminNoticeDTO dto) {
		System.out.println("DAO - updateNotice");
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_ad_notice_tbl "
					+ "SET noticeTitle=?, noticeContent=?, noticeImg=?, noticeWriter=?, noticeRegDate=sysdate "
					+ "WHERE noticeNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNoticeTitle());
			pstmt.setString(2, dto.getNoticeContent());
			pstmt.setString(3, dto.getNoticeImg());
			pstmt.setString(4, dto.getNoticeWriter());
			pstmt.setInt(5, dto.getNoticeNo());
			
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
	public int deleteNotice(int noticeNo) {
		System.out.println("DAO - deleteNotice ");
		
		int deleteCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_ad_notice_tbl "
					+ "SET show='n' "
					+ "WHERE noticeNo =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, noticeNo);
			
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
