package pj.mvc.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Show_ReservationDAOImpl implements Show_ReservationDAO{

	// 커넥션 풀 객체를 보관
	DataSource dataSource = null;
	
	// 싱글톤 객체 생성
	static Show_ReservationDAO Show_R_instance = new Show_ReservationDAOImpl();
	
	public static Show_ReservationDAO getInstance() {
		if(Show_R_instance == null) {
			Show_R_instance = new Show_ReservationDAOImpl();
		}
		return Show_R_instance;
	}
	
	// 디폴트 생성자
	// 커넥션풀(DBCP : DataBase Connnection Pool 방식) - context.xml에 설정 
	private Show_ReservationDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/FastiCat2");  // java:comp/env/Resource명
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	// 공연에 구입자가 쓴 비용 Insert
	@Override
	public void ticketInsert(int showNum, String userID, int totalPrice) {
		System.out.println("Show_ReservationDAOImpl - ticketInsert");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertCnt = 0;
		
		try {
			conn = dataSource.getConnection();
			
			String sql = "INSERT INTO show_Reservation (show_ResId, showNum, userID,  totalPrice) "
					   + "VALUES (show_Reservation_seq.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, showNum);
			pstmt.setString(2, userID);
			pstmt.setInt(3, totalPrice);
			
			insertCnt = pstmt.executeUpdate();
			System.out.println("insertCnt : " + insertCnt);
			
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
