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
	public List<SearchDTO> boardList(int start, int end) {
		System.out.println("DAO - useridCheck()");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SearchDTO> list = new ArrayList<SearchDTO>();

		try {
			conn = dataSource.getConnection();

			String sql = "SELECT * " + " 	 FROM( " + "				SELECT A.*, " + " 		       rownum AS rn "
					+ " 			FROM " + "   			 (SELECT * FROM mvc_board_tbl "
					+ "  		      WHERE show = 'y'" + "				  ORDER BY num DESC " + "   		      ) A "
					+ "         ) " + "WHERE rn BETWEEN ? AND ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// dto 생성.
				SearchDTO dto = new SearchDTO();
				// dto 에 1건의 rs 게시글 정보를 받는다.
				dto.setNum(rs.getInt("num"));
				dto.setTitle(rs.getString("Title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setPassword(rs.getString("password"));
				dto.setReadCnt(rs.getInt("readCnt"));
				dto.setRegDate(rs.getDate("regDate"));
				dto.setComment_count(rs.getInt("comment_count"));

				// list 에 dto 를 추가.
				list.add(dto);

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

		// list를 리턴한다.
		return list;
	}

	// 게시글 갯수 구하기
	@Override
	public int boardCnt() {
		System.out.println("BoardDAOImpl - boardCnt");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		try {
			conn = dataSource.getConnection();
			String sql = " SELECT COUNT(*) AS cnt " + " FROM mvc_board_tbl";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt("cnt");
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
}
