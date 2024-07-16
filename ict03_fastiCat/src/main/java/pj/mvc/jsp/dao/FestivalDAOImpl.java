package pj.mvc.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import pj.mvc.jsp.dto.FestivalDTO;

public class FestivalDAOImpl implements FestivalDAO {
	
	// 커넥션 풀 객체를 보관
	DataSource dataSource = null;
	
	// 싱글톤 객체 생성
	static FestivalDAOImpl instance = new FestivalDAOImpl();
	
	public static FestivalDAOImpl getInstance() {
		if(instance == null) {
			instance  = new FestivalDAOImpl();
		}
		return instance;
	}
	
	// 디폴트 생성자 
	// 커넥션 풀(DBCP : DataBase Connection Pool 방식) - context.xml에 설정
	private FestivalDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ict03_zest");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	// 페스티벌 등록
	@Override
	public int festivalInsert(FestivalDTO dto) {
		System.out.println("FestivalDAOImpl - festivalInsert()");
		
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
	
	// 상품갯수
	@Override
	public int festivalCnt() {
		
		return 0;
	}
	
	
	// 페스티벌 목록
	@Override
	public List<FestivalDTO> festivalList(int start, int end) {
		
		return null;
	}

	// 페스티벌 상세페이지
	@Override
	public FestivalDTO festivalDetail(int fesNo) {
		
		return null;
	}

	// 페스티벌 수정
	@Override
	public int festivalUpdate(FestivalDTO dto) {
		
		return 0;
	}

	// 페스티벌 삭제
	@Override
	public int festivalDelete(int fesNo) {
		
		return 0;
	}

}
