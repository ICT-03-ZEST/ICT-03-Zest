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

import pj.mvc.jsp.dto.AdminBannerDTO;

public class AdminBannerDAOImpl implements AdminBannerDAO{

	// 커넥션 풀 객체를 보관
	DataSource dataSource = null;
	
	// 싱글톤 객체 생성
	static AdminBannerDAOImpl instance = new AdminBannerDAOImpl();
	
	public static AdminBannerDAOImpl getInstance() {
		if(instance == null) {
			instance  = new AdminBannerDAOImpl();
		}
		return instance;
	}
	
	// 디폴트 생성자 
	// 커넥션 풀(DBCP : DataBase Connection Pool 방식) - context.xml에 설정
	private AdminBannerDAOImpl () {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ict03_zest");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// 배너 등록
	@Override
	public int bannerInsert(AdminBannerDTO dto) {
		System.out.println("AdminBannerDAOImpl - bannerInsert()");
		
		int insertCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "INSERT INTO mvc_ad_banner_tbl(bannerNo, bannerArea, bannerImg, bannerStatus, bannerIndate) "
					+ " VALUES((SELECT NVL(MAX(bannerNo)+1, 1) FROM mvc_ad_banner_tbl), ?, ?, ?, sysdate) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBannerArea());
			pstmt.setString(2, dto.getBannerImg());
			pstmt.setString(3, dto.getBannerStatus());
			
			
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

	
	// 배너 갯수
	@Override
	public int bannerCnt() {
		System.out.println("AdminBannerDAOImpl - bannerCnt() ");
		
		int total = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM mvc_ad_banner_tbl";
			
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

	// 배너 목록
	@Override
	public List<AdminBannerDTO> bannerList(int start, int end) {
		System.out.println("AdminBannerDAOImpl - bannerList()");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<AdminBannerDTO> list = new ArrayList<AdminBannerDTO>();
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT * FROM mvc_ad_banner_tbl "
					 + "ORDER BY bannerArea";
			
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, start);
//			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				AdminBannerDTO dto = new AdminBannerDTO();
				
				dto.setBannerNo(rs.getInt("bannerNo"));
				dto.setBannerArea(rs.getString("bannerArea"));
				dto.setBannerImg(rs.getString("bannerImg"));
				dto.setBannerStatus(rs.getString("bannerStatus"));
				dto.setBannerIndate(rs.getDate("bannerIndate"));
				
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

	// 배너 수정 상세페이지
	@Override
	public AdminBannerDTO bannerDetail(int bannerNo) {
		System.out.println("DAO - bannerDetail()");
		
		// DTO 생성
		AdminBannerDTO dto = new AdminBannerDTO();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM mvc_ad_banner_tbl "
					+ "WHERE bannerNo=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bannerNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBannerNo(rs.getInt("bannerNo"));
				dto.setBannerArea(rs.getString("bannerArea"));
				dto.setBannerImg(rs.getString("bannerImg"));
				dto.setBannerStatus(rs.getString("bannerStatus"));
				dto.setBannerIndate(rs.getDate("bannerIndate"));
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

	// 배너 수정
	@Override
	public int bannerUpdate(AdminBannerDTO dto) {
		System.out.println("AdminConcertDAOImpl - concertUpdate()");
		
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE mvc_ad_banner_tbl "
					+ "SET bannerArea=?, bannerImg=?, bannerStatus=? ,bannerIndate=sysdate "
					+ "WHERE bannerNo=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBannerArea());
			pstmt.setString(2, dto.getBannerImg());
			pstmt.setString(3, dto.getBannerStatus());
			pstmt.setInt(4, dto.getBannerNo());
			
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

	// 배너 삭제
	@Override
	public int bannerDelete(int bannerNo) {
		System.out.println("DAO - bannerDelete");
		int deleteCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "DELETE mvc_ad_banner_tbl "
					+ "WHERE bannerNo =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bannerNo);
			
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

	
	//  메인 - 배너 조회
	@Override
	public List<AdminBannerDTO> getBannerList() {
		System.out.println("AdminBannerDAOImpl - bannerList()");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<AdminBannerDTO> bannerList = new ArrayList<AdminBannerDTO>();
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "SELECT * FROM mvc_ad_banner_tbl "
					 + "WHERE bannerStatus='사용함'"
					 + "ORDER BY bannerArea";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				AdminBannerDTO dto = new AdminBannerDTO();
				
				dto.setBannerNo(rs.getInt("bannerNo"));
				dto.setBannerArea(rs.getString("bannerArea"));
				dto.setBannerImg(rs.getString("bannerImg"));
				dto.setBannerStatus(rs.getString("bannerStatus"));
				dto.setBannerIndate(rs.getDate("bannerIndate"));
				
				bannerList.add(dto);
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
		
		return bannerList;
	}
	
	
	

}
