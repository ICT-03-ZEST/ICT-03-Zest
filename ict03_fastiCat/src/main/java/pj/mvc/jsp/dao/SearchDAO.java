package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.SearchDTO;

public interface SearchDAO {

	// 게시글 목록
	public List<SearchDTO> boardList(int start, int end);
	
	public int boardCnt();
	
	// 조회수 증가
	public void plusReadCnt(int num);
	
	// 게시글 상세페이지
	public SearchDTO getBoardDetail(int num);
	
}
