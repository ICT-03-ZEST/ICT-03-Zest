package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.SearchDTO;

public interface SearchDAO {

	// 게시글 목록
	public List<SearchDTO> boardList(String query,int start,int end);
	
	// 게시글 개수 구하기
	public int boardCnt();
	
	// 조회수 증가
	public void plusReadCnt(int num);
	
	// 게시글 상세페이지
	public SearchDTO getBoardDetail(int num);

	// 게시글 상세 목록
	public List<SearchDTO> boardDetailList(String searchItem,String searchInput,int start, int end);
	

}
