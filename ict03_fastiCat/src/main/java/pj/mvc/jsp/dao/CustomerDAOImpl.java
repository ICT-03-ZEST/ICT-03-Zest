package pj.mvc.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import pj.mvc.jsp.dto.ReservationDTO;

public class CustomerDAOImpl implements CustomerDAO{
   
	// 커넥션 풀 객체를 보관
	DataSource dataSource = null;
   
   // 싱글톤 객체 생성
   static CustomerDAOImpl instance = new CustomerDAOImpl();
   
   public static CustomerDAOImpl getInstance() {
      if(instance == null) {
         instance = new CustomerDAOImpl();
      }
      return instance;
   }
   
   // 디폴트 생성자
   // 커넥션풀(DBCP : DataBase Connnection Pool 방식) - context.xml에 설정 
   private CustomerDAOImpl() {
      try {
         Context context = new InitialContext();
         dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ict03_zest");  // java:comp/env/Resource명
      } catch(NamingException e) {
         e.printStackTrace();
      }
   }

   // ID 중복확인 처리
   @Override
   public int useridCheck(String strId) {
      
      System.out.println("DAO - useridCheck()");
      
      int selectCnt = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         conn = dataSource.getConnection();
         String sql = "SELECT * FROM mvc_customer_tbl "
                  + "WHERE userid=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, strId);
         
         rs = pstmt.executeQuery();
         if(rs.next()) {
            selectCnt = 1;
         }
         
         System.out.println("selectCnt : " + selectCnt);
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
      
      return selectCnt;
   }

   // 회원가입 처리
   @Override
   public int insertCustomer(ReservationDTO dto) {
      System.out.println("DAO - insertCustomer()");
      
      int insertCnt = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = dataSource.getConnection();
         String sql = "INSERT INTO mvc_customer_tbl(userid, password, username, birthday, address, hp, email, regDate) "
               + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getUserid());
         pstmt.setString(2, dto.getPassword());
         pstmt.setString(3, dto.getUsername());
         pstmt.setDate(4, dto.getBirthday());
         pstmt.setString(5, dto.getAddress());
         pstmt.setString(6, dto.getHp());
         pstmt.setString(7, dto.getEmail());
         pstmt.setTimestamp(8, dto.getRegDate());
         
         insertCnt = pstmt.executeUpdate();
         System.out.println("insertCnt : " + insertCnt);  // 성공:1 실패:0
         
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
      
      return insertCnt;
   }

   // 로그인 처리 / 회원정보 인증(수정, 탈퇴)
   @Override
   public int idPasswordChk(String strId, String strPassword) {
      System.out.println("DAO - idPasswordChk()");
      
      int selectCnt = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         conn = dataSource.getConnection();
         String sql = "SELECT * FROM mvc_customer_tbl "
                  + "WHERE userid=? AND password=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, strId);
         pstmt.setString(2, strPassword);
         
         rs = pstmt.executeQuery();
         if(rs.next()) {
            selectCnt = 1;
         }
         
         System.out.println("selectCnt : " + selectCnt);
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
      
      return selectCnt;
   }

   // 회원정보 인증처리 및 탈퇴처리
   @Override
   public int deleteCustomer(String strId) {
      
      System.out.println("DAO - deleteCustomer()");
      int deleteCnt = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn = dataSource.getConnection();
         String sql = "DELETE FROM mvc_customer_tbl WHERE  userid = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, strId);
         
         deleteCnt = pstmt.executeUpdate();
         System.out.println("deleteCnt : " + deleteCnt);  // 성공:1 실패:0
         
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
      
      return deleteCnt;
   }

   // 상세페이지
   @Override
   public ReservationDTO getCustomerDetail(String strId) {
      System.out.println("DAO - getCustomerDetail()");
      
      // 1. CustomerDTO 생성
      ReservationDTO dto = new ReservationDTO();
      
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         // 2. strId(세션ID)와 일치하는 데이터가 존재하는지 확인
         conn = dataSource.getConnection();
         String sql = "SELECT * FROM mvc_customer_tbl "
                  + "WHERE userid=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, strId);
         
         // 3. ResultSet에 담고
         rs = pstmt.executeQuery();
         
         // 4. ResultSet에 데이터가 존재하면
         if(rs.next()) {
            // ResultSet을 읽어서 CustomerDTO에 setter로 담는다.
            dto.setUserid(rs.getString("userid"));
            dto.setPassword(rs.getString("password"));
            dto.setUsername(rs.getString("username"));
            dto.setBirthday(rs.getDate("birthday"));
            dto.setAddress(rs.getString("address"));
            dto.setHp(rs.getString("hp"));
            dto.setEmail(rs.getString("email"));
            dto.setRegDate(rs.getTimestamp("regDate"));
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
      
      // 5. CustomerDTO를 리턴한다.
      return dto;
   }

   // 회원정보 수정 처리
   @Override
   public int updateCustomer(ReservationDTO dto) {
      System.out.println("DAO - updateCustomer()");
      
      int updateCnt = 0;
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      try {
         conn = dataSource.getConnection();
         String sql = "UPDATE mvc_customer_tbl "
               + "SET username=?, birthday=?, address=?, hp=?, email=? "
               + "WHERE  userid = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getUsername());
         pstmt.setDate(2, dto.getBirthday());
         pstmt.setString(3, dto.getAddress());
         pstmt.setString(4, dto.getHp());
         pstmt.setString(5, dto.getEmail());
         pstmt.setString(6, dto.getUserid());
         
         rs = pstmt.executeQuery();
         if(rs.next()) {
        	 updateCnt = 1;
         }
         System.out.println("updateCnt : " + updateCnt);  // 성공:1 실패:0
         
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
      
      return updateCnt;
   }
   
   

}
