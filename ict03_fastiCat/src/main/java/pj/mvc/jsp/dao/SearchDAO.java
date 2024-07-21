package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.SearchDTO;

public interface SearchDAO {

	// 게시글 목록
	public List<SearchDTO> boardList(String query,int start,int end);
	
	// 공연갯수
	public int getTotalCount(String query);
	
	// 공연목록
	public List<SearchDTO> concertList(String query, int start, int end);

	// 게시글 개수 구하기
	public int boardCnt(String query);
	
	// 게시글 상세 목록
	public List<SearchDTO> boardDetailList(String searchItem,String searchInput,int start, int end);
	

}
