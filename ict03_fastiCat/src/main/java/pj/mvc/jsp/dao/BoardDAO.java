package pj.mvc.jsp.dao;

import java.util.List;



import pj.mvc.jsp.dto.BoardDTO;
import pj.mvc.jsp.dto.CommentDTO;
import pj.mvc.jsp.dto.HeartDTO;

public interface BoardDAO {

	// 게시판 목록 조회 - 내림차순 조회
	public List<BoardDTO> boardList(String category, int start, int end);
	
	// 게시글 개수 구하기 - 페이지계산시 필요
	public int boardCnt(String category);
	
	//회원 게시글 이력조회
	public int selectOfwriter(BoardDTO dto);
	
	//조회수 증가
	public void plusViews(int num, String category);

	// 게시글 상세페이지
	public BoardDTO getBoardDetail(int num, String category);
	
	// 좋아요 수 수정
	public int modHeartCount(BoardDTO dto);
	
	// 좋아요 추가 하트 insert
	public int insertHeart(HeartDTO dto);
	
	// 좋아요 취소 하트 지우기 
	public int delHeart(HeartDTO dto);
	
	// 좋아요 조회
	public int selectHeart(HeartDTO dto);
	
	// 게시글 작성처리
	public int insertBoard(BoardDTO dto, String category);
	
	// 게시글 수정처리
	public int updateBoard(BoardDTO dto, String category, int num);
	
	// 게시글 삭제처리 - +checkbox 삭제
	public int deleteBoard(int num, String category);
	
	// 게시글 한건 페이지이동시 필요 (게시판 테이블 댓글테이블 join)
	public BoardDTO boardSelect(int comment_num, String board_category);
	
	// 댓글 목록조회
	public List<CommentDTO> cmtList(int num, String category);
	
	// 댓글 작성처리
	public int cmtInsert(CommentDTO dto);
	
	// 댓글 한건 조회 - 댓글번호
	public CommentDTO cmtSelect(int num, String category);
		
	// 댓글 수정처리
	public int cmtUpdate(int num, CommentDTO dto);
	
	// 댓글 삭제처리
	public int cmtDelete(int num, String category);

	
	
}
