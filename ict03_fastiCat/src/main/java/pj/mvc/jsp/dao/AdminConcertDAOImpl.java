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

import pj.mvc.jsp.dto.AdminConcertDTO;

public class AdminConcertDAOImpl implements AdminConcertDAO{
	
	// 커넥션 풀 객체를 보관
	DataSource dataSource = null;
	
	// 싱글톤 객체 생성
	static AdminConcertDAOImpl instance = new AdminConcertDAOImpl();
	
	public static AdminConcertDAOImpl getInstance() {
		if(instance == null) {
			instance  = new AdminConcertDAOImpl();
		}
		return instance;
	}
	
	// 디폴트 생성자 
	// 커넥션 풀(DBCP : DataBase Connection Pool 방식) - context.xml에 설정
	private AdminConcertDAOImpl () {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ict03_zest");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	// 공연 등록
	@Override
	public int concertInsert(AdminConcertDTO dto) {
		System.out.println("AdminConcertDAOImpl - concertInsert()");
		
		int insertCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO mvc_ad_concert_tbl(conNo, conCategory, conName, conGrade, conTime, conPlace, conImg, conBuy, conPrice, conContent, conStatus, conIndate) "
					+ " VALUES((SELECT NVL(MAX(conNo)+1, 1) FROM mvc_ad_concert_tbl), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getConCategory());
			pstmt.setString(2, dto.getConName());
			pstmt.setString(3, dto.getConGrade());
			pstmt.setString(4, dto.getConTime());
			pstmt.setString(5, dto.getConPlace());
			pstmt.setString(6, dto.getConImg());
			pstmt.setString(7, dto.getConBuy());
			pstmt.setInt(8, dto.getConPrice());
			pstmt.setString(9, dto.getConContent());
			pstmt.setString(10, dto.getConStatus());
			
			
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
	
	//공연 갯수
	@Override
	public int concertCnt() {
		System.out.println("AdminConcertDAOImpl - concertCnt() ");
		
		int total = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM mvc_ad_concert_tbl";
			
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
	
	//공연 목록
	@Override
	public List<AdminConcertDTO> concertList(int start, int end) {
		System.out.println("AdminConcertDAOImpl - concertList() ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<AdminConcertDTO> list = new ArrayList<AdminConcertDTO>();
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT * FROM mvc_ad_concert_tbl "
					 + "WHERE show = 'y' "
					 + "ORDER BY conNo";
			
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				AdminConcertDTO dto = new AdminConcertDTO();
				
				dto.setConNo(rs.getInt("conNo"));
				dto.setConCategory(rs.getString("conCategory"));
				dto.setConName(rs.getString("conName"));
				dto.setConGrade(rs.getString("conGrade"));
				dto.setConTime(rs.getString("conTime"));
				dto.setConPlace(rs.getString("conPlace"));
				dto.setConImg(rs.getString("conImg"));
				dto.setConBuy(rs.getString("conBuy"));
				dto.setConPrice(rs.getInt("conPrice"));
				dto.setConContent(rs.getString("conContent"));
				dto.setConStatus(rs.getString("conStatus"));
				dto.setConIndate(rs.getDate("conIndate"));
				
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
	}
	
	//공연 수정 상세페이지
	@Override
	public AdminConcertDTO concertDetail(int conNo) {
		System.out.println("DAO - productDetail()");
		
		// DTO 생성
		AdminConcertDTO dto = new AdminConcertDTO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM mvc_ad_concert_tbl "
					+ "WHERE conNo=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, conNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setConNo(rs.getInt("conNo"));
				dto.setConCategory(rs.getString("conCategory"));
				dto.setConName(rs.getString("conName"));
				dto.setConGrade(rs.getString("conGrade"));
				dto.setConTime(rs.getString("conTime"));
				dto.setConPlace(rs.getString("conPlace"));
				dto.setConImg(rs.getString("conImg"));
				dto.setConBuy(rs.getString("conBuy"));
				dto.setConPrice(rs.getInt("conPrice"));
				dto.setConContent(rs.getString("conContent"));
				dto.setConStatus(rs.getString("conStatus"));
				dto.setConIndate(rs.getDate("conIndate"));
				
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
	
	//공연 수정
	@Override
	public int concertUpdate(AdminConcertDTO dto) {
		System.out.println("AdminConcertDAOImpl - concertUpdate()");
		
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_ad_concert_tbl "
					+ "SET conCategory=?, conName=?, conGrade=?, conTime=?, conPlace=?, conImg=?, conBuy=?, conPrice=?, conContent=?, conStatus=?, conIndate=sysdate "
					+ "WHERE conNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getConCategory());
			pstmt.setString(2, dto.getConName());
			pstmt.setString(3, dto.getConGrade());
			pstmt.setString(4, dto.getConTime());
			pstmt.setString(5, dto.getConPlace());
			pstmt.setString(6, dto.getConImg());
			pstmt.setString(7, dto.getConBuy());
			pstmt.setInt(8, dto.getConPrice());
			pstmt.setString(9, dto.getConContent());
			pstmt.setString(10, dto.getConStatus());
			pstmt.setInt(11, dto.getConNo());
			
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
	
	//공연 삭제
	@Override
	public int concertDelete(int conNo) {
		System.out.println("DAO - concertDelete ");
		
		int deleteCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_ad_concert_tbl "
					+ "SET show='n' "
					+ "WHERE conNo =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, conNo);
			
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
