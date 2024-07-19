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

import pj.mvc.jsp.dto.SearchDTO;

public class SearchDAOImpl implements SearchDAO {

	// 커넥션 풀 객체를 보관
	DataSource dataSource = null;

	// 싱글톤 객체 생성
	static SearchDAOImpl instance = new SearchDAOImpl();

	public static SearchDAOImpl getInstance() {
		if (instance == null) {
			instance = new SearchDAOImpl();
		}
		return instance;
	}

	// 디폴트 생성자
	// 커넥션풀(DBCP : DataBase Connnection Pool 방식) - context.xml에 설정
	private SearchDAOImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ict03_zest"); // java:comp/env/Resource명
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 게시글 목록
	@Override
	public List<SearchDTO> boardList(String query, int start, int end) {
	    List<SearchDTO> list = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = dataSource.getConnection();
	        String sql = "SELECT * FROM ("
	                   + "SELECT A.*, rownum AS rn FROM ("
	                   + "SELECT board_num AS num, board_title AS title, board_content AS content, board_writer AS writer, board_regDate AS regDate, board_category AS source "
	                   + "FROM reviewBoard_tbl "
	                   + "WHERE board_show = 'y' AND (board_title LIKE ? OR board_content LIKE ? OR board_writer LIKE ?) "
	                   + "UNION ALL "
	                   + "SELECT board_num AS num, board_title AS title, board_content AS content, board_writer AS writer, board_regDate AS regDate, board_category AS source "
	                   + "FROM freeBoard_tbl "
	                   + "WHERE board_show = 'y' AND (board_title LIKE ? OR board_content LIKE ? OR board_writer LIKE ?) "
	                   + "UNION ALL "
	                   + "SELECT noticeNo AS num, noticeTitle AS title, noticeContent AS content, noticeWriter AS writer, noticeRegDate AS regDate, 'notice' AS source "
	                   + "FROM mvc_ad_notice_tbl "
	                   + "WHERE show = 'y' AND (noticeTitle LIKE ? OR noticeContent LIKE ? OR noticeWriter LIKE ?) "
	                   + "ORDER BY regDate DESC "
	                   + ") A "
	                   + ") WHERE rn BETWEEN ? AND ?";

	        pstmt = conn.prepareStatement(sql);
	        String searchQuery = "%" + query + "%";
	        pstmt.setString(1, searchQuery);
	        pstmt.setString(2, searchQuery);
	        pstmt.setString(3, searchQuery);
	        pstmt.setString(4, searchQuery);
	        pstmt.setString(5, searchQuery);
	        pstmt.setString(6, searchQuery);
	        pstmt.setString(7, searchQuery);
	        pstmt.setString(8, searchQuery);
	        pstmt.setString(9, searchQuery);
	        pstmt.setInt(10, start);
	        pstmt.setInt(11, end);

	        rs = pstmt.executeQuery();

	        System.out.println(sql);
	        while (rs.next()) {
	            SearchDTO dto = new SearchDTO();
	            dto.setNum(rs.getInt("num"));
	            dto.setTitle(rs.getString("title"));
	            dto.setContent(rs.getString("content"));
	            dto.setWriter(rs.getString("writer"));
	            dto.setRegDate(rs.getDate("regDate"));
	            
	            if(rs.getString("source").equals("자유")) {
		            dto.setSource(rs.getString("source"));
	            }
	            else if(rs.getString("source").equals("공연후기")) {
		            dto.setSource(rs.getString("source"));
	            }
	            else {
	            	 dto.setSource("공지사항");
	            }
	            System.out.println(rs.getString("source"));
	            
	            list.add(dto);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return list;
	}


	// 게시글 갯수 구하기
	@Override
	public int boardCnt(String query) {
	    System.out.println("SearchDAOImpl - boardCnt");

	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int total = 0;
	    try {
	        conn = dataSource.getConnection();
	        String sql = "SELECT COUNT(*) AS cnt " +
	                     "FROM (" +
	                     "    SELECT N_Board_Num AS num, N_Title AS title, N_Content AS content, N_Writer AS writer, N_ReadCnt AS readCnt, N_WriteDate AS regDate, show " +
	                     "    FROM mvc_Notice_TBL " +
	                     "    WHERE show = 'y' AND (N_Title LIKE ? OR N_Content LIKE ? OR N_Writer LIKE ?) " +
	                     "    UNION ALL " +
	                     "    SELECT board_num AS num, board_title AS title, board_content AS content, board_writer AS writer, board_views AS readCnt, board_regDate AS regDate, board_show AS show " +
	                     "    FROM reviewBoard_tbl " +
	                     "    WHERE board_show = 'y' AND (board_title LIKE ? OR board_content LIKE ? OR board_writer LIKE ?) " +
	                     "    UNION ALL " +
	                     "    SELECT board_num AS num, board_title AS title, board_content AS content, board_writer AS writer, board_views AS readCnt, board_regDate AS regDate, board_show AS show " +
	                     "    FROM freeBoard_tbl " +
	                     "    WHERE board_show = 'y' AND (board_title LIKE ? OR board_content LIKE ? OR board_writer LIKE ?) " +
	                     ") combinedResults";

	        pstmt = conn.prepareStatement(sql);
	        String searchQuery = "%" + query + "%";
	        for (int i = 1; i <= 9; i++) {
	            pstmt.setString(i, searchQuery);
	        }

	        rs = pstmt.executeQuery();
	        if (rs.next()) {
	            total = rs.getInt("cnt");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return total;
	}

	@Override
	public void plusReadCnt(int num) {
		System.out.println("DAO - plusReadCnt()");

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();
			String sql = " UPDATE mvc_board_tbl " + "   SET readCnt = readCnt + 1 " + " WHERE num = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 게시글 상세페이지
	@Override
	public SearchDTO getBoardDetail(int num) {
		System.out.println("DAO - getBoardDetail()");

		SearchDTO dto = new SearchDTO();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			String sql = " SELECT * FROM mvc_board_tbl " + " WHERE num = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("Title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setPassword(rs.getString("password"));
				dto.setReadCnt(rs.getInt("readCnt"));
				dto.setRegDate(rs.getDate("regDate"));
				dto.setComment_count(rs.getInt("comment_count"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
	
    // 게시글 상세 검색
	@Override
	public List<SearchDTO> boardDetailList(String searchItem, String searchInput, int start, int end) {
	    List<SearchDTO> list = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = dataSource.getConnection();
	        
	        // 기본 쿼리
	        String sql = "SELECT * FROM ("
	                     + "SELECT A.*, rownum AS rn FROM ("
	                     + "SELECT board_num AS num, board_title AS title, board_content AS content, board_writer AS writer, board_regDate AS regDate, '공연후기' AS source "
	                     + "FROM reviewBoard_tbl "
	                     + "WHERE board_show = 'y' AND ";

	        // searchItem에 따른 SQL 조건 추가
	        if ("writer".equals(searchItem)) {
	            sql += "board_writer LIKE ? ";
	        } else if ("title".equals(searchItem)) {
	            sql += "board_title LIKE ? ";
	        } else if ("content".equals(searchItem)) {
	            sql += "board_content LIKE ? ";
	        } else {
	            sql += "1=1 "; // 기본적으로 모든 항목을 반환 (일치하는 항목 없음)
	        }

	        // 유사한 조건을 freeBoard_tbl과 mvc_ad_notice_tbl에도 적용
	        sql += "UNION ALL "
	             + "SELECT board_num AS num, board_title AS title, board_content AS content, board_writer AS writer, board_regDate AS regDate, '자유' AS source "
	             + "FROM freeBoard_tbl "
	             + "WHERE board_show = 'y' AND ";

	        if ("writer".equals(searchItem)) {
	            sql += "board_writer LIKE ? ";
	        } else if ("title".equals(searchItem)) {
	            sql += "board_title LIKE ? ";
	        } else if ("content".equals(searchItem)) {
	            sql += "board_content LIKE ? ";
	        } else {
	            sql += "1=1 "; // 기본적으로 모든 항목을 반환 (일치하는 항목 없음)
	        }

	        sql += "UNION ALL "
	             + "SELECT noticeNo AS num, noticeTitle AS title, noticeContent AS content, noticeWriter AS writer, noticeRegDate AS regDate, '' AS source "
	             + "FROM mvc_ad_notice_tbl "
	             + "WHERE show = 'y' AND ";

	        if ("writer".equals(searchItem)) {
	            sql += "noticeWriter LIKE ? ";
	        } else if ("title".equals(searchItem)) {
	            sql += "noticeTitle LIKE ? ";
	        } else if ("content".equals(searchItem)) {
	            sql += "noticeContent LIKE ? ";
	        } else {
	            sql += "1=1 "; // 기본적으로 모든 항목을 반환 (일치하는 항목 없음)
	        }

	        sql += "ORDER BY regDate DESC "
	             + ") A "
	             + ") WHERE rn BETWEEN ? AND ?";

	        pstmt = conn.prepareStatement(sql);
	        String searchQuery = "%" + searchInput + "%";

	        // 조건에 맞게 파라미터 설정
	        int parameterIndex = 1;
	        if ("writer".equals(searchItem) || "title".equals(searchItem) || "content".equals(searchItem)) {
	            pstmt.setString(parameterIndex++, searchQuery);
	            pstmt.setString(parameterIndex++, searchQuery);
	            pstmt.setString(parameterIndex++, searchQuery);
	        } else {
	            pstmt.setString(parameterIndex++, searchQuery); // 기본 검색 조건
	            pstmt.setString(parameterIndex++, searchQuery);
	            pstmt.setString(parameterIndex++, searchQuery);
	        }

	        // 페이징 처리 파라미터 설정
	        pstmt.setInt(parameterIndex++, start);
	        pstmt.setInt(parameterIndex, end);

	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            SearchDTO dto = new SearchDTO();
	            dto.setNum(rs.getInt("num"));
	            dto.setTitle(rs.getString("title"));
	            dto.setContent(rs.getString("content"));
	            dto.setWriter(rs.getString("writer"));
	            dto.setRegDate(rs.getDate("regDate"));
	            
	            String source = rs.getString("source");
	            if (source == null) {
	                dto.setSource("공지사항");  // 기본값으로 설정
	            } else if (source.equals("자유")) {
	                dto.setSource("자유");
	            } else if (source.equals("공연후기")) {
	                dto.setSource("공연후기");
	            } else {
	                dto.setSource("공지사항");
	            }
	            System.out.println(source);
	            
	            list.add(dto);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return list;
	}
}