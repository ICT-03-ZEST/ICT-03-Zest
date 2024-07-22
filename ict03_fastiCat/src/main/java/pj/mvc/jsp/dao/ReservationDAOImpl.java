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

import pj.mvc.jsp.dto.ShowDTO;

public class ReservationDAOImpl implements ReservationDAO{
	// 커넥션 풀 객체를 보관
	DataSource dataSource = null;
	
	// 싱글톤 객체 생성
	static ReservationDAO instance = new ReservationDAOImpl();
	
	public static ReservationDAO getInstance() {
		if(instance == null) {
			instance = new ReservationDAOImpl();
		}
		return instance;
	}
	
	// 디폴트 생성자
	// 커넥션풀(DBCP : DataBase Connnection Pool 방식) - context.xml에 설정 
	private ReservationDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ict03_zest");  // java:comp/env/Resource명
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<ShowDTO> ResList(String curMonth) {
		System.out.println("ReservationDAOImpl - ResList ");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 1. list 생성
		List<ShowDTO> list = new ArrayList<ShowDTO>();
		try {
			System.out.println("try { 진입 확인 curMonth :"+curMonth);
			conn = dataSource.getConnection();
			String sql = "SELECT showNum, showName, curCapacity, maxCapacity, showDay, showCHK "
					+ "					FROM show_tbl "
					+ "					WHERE LPAD(TO_CHAR(showDay, 'MM'), 2, '0') = LPAD('"+curMonth+"', 2, '0') "
					+ "					AND EXTRACT(YEAR FROM showDay) = EXTRACT(YEAR FROM SYSDATE) "
					+ "					AND SHOWCHK = 'Y' "
					+ "					ORDER BY showNum";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("rs.next() : "+rs.next());
			while(rs.next()) {
				// 2. dto 생성 
				System.out.println("while(rs.next()) { 진입");
				ShowDTO dto = new ShowDTO();
	            dto.setShowNum(rs.getInt("showNum"));
	            dto.setShowName(rs.getString("showName"));
	            dto.setCurCapacity(rs.getInt("curCapacity"));
	            dto.setMaxCapacity(rs.getInt("maxCapacity"));
	            dto.setShowDay(rs.getDate("showDay"));
	            dto.setShowCHK(rs.getString("showCHK"));
	            
	            System.out.println("dto : "+dto);
	            
	            list.add(dto);
	            System.out.println("list : "+list);
		              
			}
			
		} catch(SQLException e) {
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
		
		// 5. list를 리턴한다.
		return list;
	}
	
	@Override
	public ShowDTO ResDetailPageView(int shownum){
	System.out.println("ReservationDAOImpl - ResDetailPageView");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 1. dto 생성
		ShowDTO dto = new ShowDTO();
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT showNum, showName,showPlace, showPrice,showTime,showAge,showBene,curCapacity, maxCapacity, showDay,showImage, showCHK  "
					+ "FROM show_tbl "
					+ "WHERE SHOWCHK LIKE 'Y' "
					+ "AND showNum = ?  "
					+ "ORDER BY showNum";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, shownum);
			
			rs = pstmt.executeQuery();
			
			// 2. setter에 결과를 담는다.
			if(rs.next()) {
				// 3. dto에 1건의 rs 게시글 정보를 담는다.
				dto.setShowNum(rs.getInt("shownum"));
				dto.setShowName(rs.getString("showName"));
				dto.setShowPlace(rs.getString("showPlace"));
				dto.setShowPrice(rs.getInt("showPrice"));
				dto.setShowTime(rs.getInt("showTime"));
				dto.setShowAge(rs.getInt("showAge"));
				dto.setShowBene(rs.getString("showBene"));
				dto.setCurCapacity(rs.getInt("curCapacity"));
				dto.setMaxCapacity(rs.getInt("maxCapacity"));
				dto.setShowDay(rs.getDate("showDay"));
				dto.setShowImage(rs.getString("showImage"));
				dto.setShowCHK(rs.getString("showCHK"));
			}
			
		} catch(SQLException e) {
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
	
	@Override
	public List<ShowDTO> ResDetailPageViewList(String showdto){
		System.out.println("ReservationDAOImpl - ResDetailPageViewList");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 1. dto 생성
		List<ShowDTO> list = new ArrayList<ShowDTO>();
		
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * "
					+ "FROM show_tbl  "
					+ "WHERE showName LIKE ? "
					+ "AND SHOWCHK LIKE 'Y' "
					+ "ORDER BY SHOWNUM";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, showdto);
			
			rs = pstmt.executeQuery();
			
			// 2. setter에 결과를 담는다.
			while(rs.next()) {
				
				ShowDTO dto = new ShowDTO();
				// 3. dto에 1건의 rs 게시글 정보를 담는다.
				dto.setShowNum(rs.getInt("shownum"));
				dto.setShowName(rs.getString("showName"));
				dto.setShowPlace(rs.getString("showPlace"));
				dto.setShowPrice(rs.getInt("showPrice"));
				dto.setShowTime(rs.getInt("showTime"));
				dto.setShowAge(rs.getInt("showAge"));
				dto.setShowBene(rs.getString("showBene"));
				dto.setCurCapacity(rs.getInt("curCapacity"));
				dto.setMaxCapacity(rs.getInt("maxCapacity"));
				dto.setShowDay(rs.getDate("showDay"));
				dto.setShowImage(rs.getString("showImage"));
				dto.setShowCHK(rs.getString("showCHK"));
				
				list.add(dto);
			}
			
		} catch(SQLException e) {
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
		
		return list;
	}
	
	// 선택한 날짜에 있는 공연정보
	@Override
	public void showUpdate(int showNum, int quantity) {
		System.out.println("ReservationDAOImpl - showUpdate");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			String sql = "UPDATE show_tbl "
						+ "SET curCapacity = curCapacity+? "
						+ "WHERE shownum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, showNum);
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
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

}
