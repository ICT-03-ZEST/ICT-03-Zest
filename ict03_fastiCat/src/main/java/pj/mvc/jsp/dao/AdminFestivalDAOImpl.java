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

import pj.mvc.jsp.dto.AdminFestivalDTO;

public class AdminFestivalDAOImpl implements AdminFestivalDAO {
	
	// 커넥션 풀 객체를 보관
	DataSource dataSource = null;
	
	// 싱글톤 객체 생성
	static AdminFestivalDAOImpl instance = new AdminFestivalDAOImpl();
	
	public static AdminFestivalDAOImpl getInstance() {
		if(instance == null) {
			instance  = new AdminFestivalDAOImpl();
		}
		return instance;
	}
	
	// 디폴트 생성자 
	// 커넥션 풀(DBCP : DataBase Connection Pool 방식) - context.xml에 설정
	private AdminFestivalDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ict03_zest");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	// 페스티벌 등록
	@Override
	public int festivalInsert(AdminFestivalDTO dto) {
		System.out.println("AdminFestivalDAOImpl - festivalInsert()");
		
		int insertCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO mvc_ad_festival_tbl(fesNo, fesName, fesGrade, fesTime, fesPlace, fesImg, fesBuy, fesPrice, fesContent, fesStatus, fesIndate) "
					+ "VALUES((SELECT NVL(MAX(fesNo)+1, 1) FROM mvc_ad_festival_tbl), ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFesName());
			pstmt.setString(2, dto.getFesGrade());
			pstmt.setString(3, dto.getFesTime());
			pstmt.setString(4, dto.getFesPlace());
			pstmt.setString(5, dto.getFesImg());
			pstmt.setString(6, dto.getFesBuy());
			pstmt.setInt(7, dto.getFesPrice());
			pstmt.setString(8, dto.getFesContent());
			pstmt.setString(9, dto.getFesStatus());
			
			
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
	
	// 페스티벌 갯수
	@Override
	public int festivalCnt() {
		System.out.println("AdminFestivalDAOImpl - festivalCnt() ");
		
		int total = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM mvc_ad_festival_tbl";
			
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
	
	
	// 페스티벌 목록
	@Override
	public List<AdminFestivalDTO> festivalList(int start, int end) {
		System.out.println("AdminFestivalDAOImpl - festivalList() ");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<AdminFestivalDTO> list = new ArrayList<AdminFestivalDTO>();
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT * FROM mvc_ad_festival_tbl "
					 + "WHERE show = 'y' "
					 + "ORDER BY fesNo";
			
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
			
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
	}

	// 페스티벌 상세페이지
	@Override
	public AdminFestivalDTO festivalDetail(int fesNo) {
		System.out.println("DAO - festivalDetail() ");
		// DTO 생성
		AdminFestivalDTO dto = new AdminFestivalDTO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM mvc_ad_festival_tbl "
					+ "WHERE fesNo=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fesNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
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

	// 페스티벌 수정
	@Override
	public int festivalUpdate(AdminFestivalDTO dto) {
		System.out.println("DAO - festivalUpdate");
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_ad_festival_tbl "
					+ "SET fesName=?, fesGrade=?, fesTime=?, fesPlace=?, fesImg=?, fesBuy=?, fesPrice=?, fesContent=?, fesStatus=?, fesIndate=sysdate "
					+ "WHERE fesNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getFesName());
			pstmt.setString(2, dto.getFesGrade());
			pstmt.setString(3, dto.getFesTime());
			pstmt.setString(4, dto.getFesPlace());
			pstmt.setString(5, dto.getFesImg());
			pstmt.setString(6, dto.getFesBuy());
			pstmt.setInt(7, dto.getFesPrice());
			pstmt.setString(8, dto.getFesContent());
			pstmt.setString(9, dto.getFesStatus());
			pstmt.setInt(10, dto.getFesNo());
			
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

	// 페스티벌 삭제
	@Override
	public int festivalDelete(int fesNo) {
		System.out.println("DAO - festivalDelete ");
		
		int deleteCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_ad_festival_tbl "
					+ "SET show='n' "
					+ "WHERE fesNo =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fesNo);
			
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
