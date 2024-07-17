package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.AdminBoardDTO;

public interface AdminBoardDAO {
	
	// 공연후기,자유게시판 목록 조회
	public List<AdminBoardDTO> boardList(String category, int start, int end);
	
	// 게시판 갯수
	public int boardCnt(String category);
	
	// 공연후기,자유게시판 삭제
	public int boardDelete(int board_num, String category);
	
	

}
