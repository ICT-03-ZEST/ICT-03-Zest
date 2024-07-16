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

import pj.mvc.jsp.dto.BoardDTO;
import pj.mvc.jsp.dto.MyPageDTO;

public class MyPageDAOImpl implements MyPageDAO{

	// 싱글톤 객체 생성 
		private static MyPageDAO instance = new MyPageDAOImpl();
		
		public static MyPageDAO getInstance() {
			
			if(instance == null) {
				instance = new MyPageDAOImpl();
			}
			
			return instance;
		}
		
		DataSource dataSource = null;
		// 디폴트 생성자
		// 커넥션풀(DBCP : DataBase Connection Pool 방식 ) - context.xml에 설정
		private MyPageDAOImpl() {
			try {
				Context context = new InitialContext();
				 dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ict03_zest");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
		
		// ID 중복확인 처리
		@Override
		public int useridCheck(String strId) {
			
			System.out.println("DAO - useridCheck");
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int selectCnt = 0;

			try {
				conn = dataSource.getConnection();
				String sql = "SELECT * FROM mvc_user_tbl "
							+ "WHERE userId=?";
				
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
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return selectCnt;
		}
		
		// 회원가입 처리
		@Override
		public int insertUser(MyPageDTO dto) {
			System.out.println("DAO - insertUser");
			
			
			
			return 0;
		}


		@Override
		public int idPasswordChk(String strId, String strPassword) {
			
			System.out.println("DAO - useridCheck");
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			System.out.println("strId : " + strId);
			System.out.println("strPassword : " + strPassword);
			
			int selectCnt = 0;

			try {
				conn = dataSource.getConnection();
				String sql = "SELECT * FROM mvc_customer_tbl "
							+ "WHERE userid=? and password=?";
				
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
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return selectCnt;
		}


		@Override
		public int deleteUser(String strId) {
			System.out.println("DAO - deleteCustomer");
			
			int deleteCnt = 0;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = dataSource.getConnection();
				String query = "DELETE FROM mvc_customer_tbl "
							+ "WHERE userid = ?";
				
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, strId);
				
				deleteCnt = pstmt.executeUpdate();
				System.out.println("deleteCnt : " + deleteCnt); //성공 : 1 , 실패 : 0
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return deleteCnt;
		}


		@Override
		public MyPageDTO getUserDetail(String strId) {
			System.out.println("DAO - getCustomerDetail");
			
			// 1. CustomerDTO 생성
			MyPageDTO dto = new MyPageDTO();
			
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
				
				// 3. resultSet에 담고
				rs = pstmt.executeQuery();
				
				// 4. resultSet에 데이터가 존재하면
				if(rs.next()) {
					//resultSet을 읽어서 CustomerDTO에 setter로 담는다.
					dto.setUserid(rs.getString("userid"));
					dto.setPassword(rs.getString("password"));
					dto.setUsername(rs.getString("username"));
					dto.setBirthday(rs.getDate("birthday"));
					dto.setAddress(rs.getString("address"));
					dto.setHp(rs.getString("hp"));
					dto.setEmail(rs.getString("email"));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			// 5. CustomerDTO를 리턴한다.
			return dto;
		}


		@Override
		public int updateUser(MyPageDTO dto) {
			System.out.println("DAO - insertCustomer");
			
			int updateCnt = 0;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = dataSource.getConnection();
				String query = "UPDATE mvc_customer_tbl "
							+ "SET password = ?, username = ? , birthday = ?, address = ?, hp = ?, email = ? "
							+ "WHERE userid = ?";
				
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, dto.getPassword());
				pstmt.setString(2, dto.getUsername());
				pstmt.setDate(3, dto.getBirthday());
				pstmt.setString(4, dto.getAddress());
				pstmt.setString(5, dto.getHp());
				pstmt.setString(6, dto.getEmail());
				pstmt.setString(7, dto.getUserid());
				
				updateCnt = pstmt.executeUpdate();
				System.out.println("updateCnt : " + updateCnt); //성공 : 1 , 실패 : 0
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return updateCnt;
		}
		
		// 게시글 목록
		@Override
		public List<BoardDTO> myBoardList(String strId, int start, int end) {
			System.out.println("BoardDAO - boardList");
			
			List<BoardDTO> list = new ArrayList<>();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
				
				try {
					conn = dataSource.getConnection();
					String sql = "SELECT * "
							+ "    FROM ( "
							+ "SELECT A.*, "
							+ "    rownum as rn "
							+ "    FROM "
							+ "        (SELECT * FROM (SELECT * FROM reviewBoard_tbl UNION ALL SELECT * FROM freeBoard_tbl) "
							+ "			   WHERE board_show = 'y' "
							+ "				AND  board_writer = ?"
							+ "            ORDER BY board_num DESC "
							+ "        ) A "
							+ ") "
							+ "WHERE rn BETWEEN ? AND ? ";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, strId);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
					
					// 3. resultSet에 담고
					rs = pstmt.executeQuery();
					
					// 4. resultSet에 데이터가 존재하면
					while(rs.next()) {
						//DTO 생성
						BoardDTO dto = new BoardDTO();
						
						dto.setBoard_num(rs.getInt("board_num"));
						dto.setBoard_category(rs.getString("board_category"));
						dto.setBoard_title(rs.getString("board_title"));
						dto.setBoard_content(rs.getString("board_content"));
						dto.setBoard_thumnail(rs.getString("board_thumnail"));
						dto.setBoard_image(rs.getString("board_image"));;
						dto.setBoard_writer(rs.getString("board_writer"));;
						dto.setBoard_regDate(rs.getDate("board_regDate"));
						dto.setBoard_views(rs.getInt("board_views"));
						dto.setBoard_heart(rs.getInt("board_heart"));
							
					    list.add(dto);
					}
				} catch(SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				// 5. CustomerDTO를 리턴한다.
				return list;
			
		}
		
		// 게시글 갯수 구하기
		@Override
		public int myBoardCnt(String strId) {
			System.out.println("BoardDAO - boardCnt");
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int total = 0;
			
			try {
				conn = dataSource.getConnection();
				String sql = "SELECT COUNT(*) AS cnt "
						+ "FROM (SELECT * FROM reviewBoard_tbl UNION ALL SELECT * FROM freeBoard_tbl) "
						+ "WHERE board_writer = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, strId);
				
				// 3. resultSet에 담고
				rs = pstmt.executeQuery();
				
				// 4. resultSet에 데이터가 존재하면
				
				
				if(rs.next()) {
					total = rs.getInt("cnt");
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			// 5. CustomerDTO를 리턴한다.
			return total;
		
		}
	
}
