package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.BoardDAO;
import pj.mvc.jsp.dao.BoardDAOImpl;
import pj.mvc.jsp.dto.BoardDTO;
import pj.mvc.jsp.dto.CommentDTO;
import pj.mvc.jsp.dto.HeartDTO;
import pj.mvc.jsp.page.BoardPaging;
import pj.mvc.jsp.util.ImageNameChange;

public class BoardServiceImpl implements BoardService {
	
	// 게시판 목록 조회
	@Override 
	public void boardListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("service - boardListAction");
		// 아이디 있다고 가정
		//String sessionID = "sessionID_2";
		String sessionID = (String) request.getSession().getAttribute("sessionID");
		System.out.println("sessionID: " + sessionID);
		String pageNum = request.getParameter("pageNum"); // null
		String category = request.getParameter("board_category");
		
		System.out.println("pageNum: " + pageNum);
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		// 5-1 단계. 전체 게시글 갯수 카운트
		BoardPaging boardPaging = new BoardPaging(pageNum);
		int total = dao.boardCnt(category);
		
		boardPaging.setTotalCount(total);
		
		// 5-2 단계. 게시글 목록조회
		int start = boardPaging.getStartRow();
		int end = boardPaging.getEndRow();
		
		List<BoardDTO> list = dao.boardList(category, start, end);
		
		System.out.println("service - boardListAction 성공");
		
		//6단계 jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", boardPaging);
		request.setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("sessionID", sessionID);
		
	}
	
	// 게시글 상세페이지
	@Override
	public void boardDetailAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("service - boardDetailAction");
		//String sessionID = "sessionID_1";
		String sessionID = (String) request.getSession().getAttribute("sessionID");
		
		String pageNum = request.getParameter("pageNum");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String category = request.getParameter("board_category");
		String views = request.getParameter("views");
		
		//int views = Integer.parseInt(request.getParameter("views"));
		
		//System.out.println("views: " + views);
		// 하트체크 조회 => 해당 게시글번호의 하트를 누른 아이디라면 하트 filled
		BoardDAO dao = BoardDAOImpl.getInstance();
		HeartDTO dto2 = new HeartDTO();
		dto2.setUserID(sessionID);
		dto2.setBoard_num(board_num);
		dto2.setBoard_category(category);
		
		int heartChk = dao.selectHeart(dto2); //=> 1 이면 하트 filled
		
		//다음게시글(전체 게시글수)
		int total = dao.boardCnt(category);
				
		//사용자 게시글 이력 조회 => '수정' 태그가 뜨도록함
		BoardDTO bd_dto = new BoardDTO();
		bd_dto.setBoard_writer(sessionID);
		bd_dto.setBoard_num(board_num);
		bd_dto.setBoard_category(category);
		
		int selWriter = dao.selectOfwriter(bd_dto); // 사용자게시글 수정,삭제
		
		System.out.println("selWriter: " + selWriter);
		 // 게시판에서 상세페이지 클릭시에만 조회수증가
		if(views != null) {
			dao.plusViews(board_num, category);
		}
		
		BoardDTO dto = dao.getBoardDetail(board_num, category);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		request.setAttribute("selWriter", selWriter);
		request.setAttribute("heartChk", heartChk);
		request.setAttribute("total", total); 
		request.setAttribute("sessionID", sessionID); // 댓글작성시  param
	}
	
	//좋아요 추가 / 게시판 좋아요 수 증가
	public void heartInsertAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서비스 - heartInsertAction");
		// sessionID 있다고 가정
		String sessionID = (String)request.getSession().getAttribute("sessionID");
		
		int board_num = Integer.parseInt(request.getParameter("board_num")); 
		String board_category = request.getParameter("board_category");
		int board_heart = Integer.parseInt(request.getParameter("board_heart")); 
		
		//게시판 하트 증가
		BoardDTO dto = new BoardDTO();
		dto.setBoard_num(board_num);
		dto.setBoard_category(board_category);
		dto.setBoard_heart(board_heart);
		
		// 하트 추가
		HeartDTO dto2 = new HeartDTO();
		dto2.setUserID(sessionID);
		dto2.setBoard_num(board_num);
		dto2.setBoard_category(board_category);
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		dao.modHeartCount(dto);	
		dao.insertHeart(dto2);
		
		request.setAttribute("dto", dto);
	}
	
	//좋아요 삭제  / 게시판 좋아요 감소
	public void heartDeleteAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서비스 - heartDeleteAction");
		// sessionID 있다고 가정
		String sessionID = (String)request.getSession().getAttribute("sessionID");
		
		int board_num = Integer.parseInt(request.getParameter("board_num")); 
		String board_category = request.getParameter("board_category");
		int board_heart = Integer.parseInt(request.getParameter("board_heart"));
		
		//게시판 하트 감소
		BoardDTO dto = new BoardDTO();
		dto.setBoard_num(board_num);
		dto.setBoard_category(board_category);
		dto.setBoard_heart(board_heart);
		
		// 하트 삭제
		HeartDTO dto2 = new HeartDTO();
		dto2.setUserID(sessionID);
		dto2.setBoard_num(board_num);
		dto2.setBoard_category(board_category);
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		dao.modHeartCount(dto);	
		dao.delHeart(dto2);
	}
	
	// 게시글 작성처리
	@Override
	public void boardInsertAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("서비스 - boardInsertAction");
		String sessionID = (String)request.getSession().getAttribute("sessionID");
		//이미지 변환
		ImageNameChange inc= new ImageNameChange();
		inc.imageName(request, response);
		
		//param
		String title = request.getParameter("board_title");
		String category = request.getParameter("board_category");
		String content = request.getParameter("board_content");
		String thumnail = inc.getThumnail(); 
		String image = inc.getImage(); 
		
		System.out.println("thumnail:" + thumnail);
		System.out.println("image:" + image);
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_title(title);
		dto.setBoard_content(content);
		dto.setBoard_thumnail(thumnail);
		dto.setBoard_image(image);
		dto.setBoard_writer(sessionID);
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		int insertCnt = dao.insertBoard(dto, category);
		
		request.setAttribute("insertCnt", insertCnt);
		request.setAttribute("category", category);
	}
	
	// 게시글 수정처리
	@Override
	public void boardUpdateAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("서비스 - boardUpdateAction");
		String sessionID = (String)request.getSession().getAttribute("sessionID");
		
		//param
		int num = Integer.parseInt(request.getParameter("hiddenNum"));
		String category = request.getParameter("hiddenCategory");
		
		//이미지 변환
		ImageNameChange inc= new ImageNameChange();
		inc.imageName(request, response);
		
		//수정사항
		String title = request.getParameter("board_title");
		String content = request.getParameter("board_content");
		String thumnail = inc.getThumnail(); 
		String image = inc.getImage(); 
		
		System.out.println("thumnail:" + thumnail);
		System.out.println("image:" + image);
		
		BoardDTO dto = new BoardDTO();
		dto.setBoard_title(title);
		dto.setBoard_content(content);
		dto.setBoard_thumnail(thumnail);
		dto.setBoard_image(image);
		dto.setBoard_writer(sessionID);
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		dao.updateBoard(dto, category, num);
		
		request.setAttribute("category", category);
	}
	// 게시글 삭제처리
	@Override
	public void boardDeleteAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서비스 - boardDeleteAction");
		
		String category = request.getParameter("board_category");
		int num = Integer.parseInt(request.getParameter("board_num"));
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		dao.deleteBoard(num, category);
		
		request.setAttribute("category", category);
	}
	
	// 댓글 목록조회
	@Override
	public void commentListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서비스 - commentListAction");
		
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("board_num"));
		String category = request.getParameter("board_category");
		String sessionID = (String)request.getSession().getAttribute("sessionID");
		System.out.println("sessionID : " + sessionID);

		BoardDAO dao = BoardDAOImpl.getInstance();
		BoardDTO dto = dao.getBoardDetail(num, category); //댓글 목록 자동조회시 해당 카테고리 조회됨
		List<CommentDTO> list = dao.cmtList(num, category); // 댓글목록
		
		request.setAttribute("sessionID", sessionID); // 댓글 수정 삭제 버튼
		request.setAttribute("dto", dto);
		request.setAttribute("list", list);
		request.setAttribute("pageNum", pageNum);
	}
	
	// 댓글 작성처리
	@Override
	public void commentAddAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서비스 - commentAddAction");
		
		int num = Integer.parseInt(request.getParameter("board_num"));
		String category = request.getParameter("board_category");
		String userID = request.getParameter("userID");
		String content = request.getParameter("content");
		
		CommentDTO dto = new CommentDTO();
		dto.setBoard_num(num);
		dto.setBoard_category(category);
		dto.setUserID(userID);
		dto.setContent(content);
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		int insetCnt =  dao.cmtInsert(dto);
		System.out.println("insetCnt: " + insetCnt);
	}
	
	//댓글 한건조회
	public void commentMod(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pageNum = request.getParameter("pageNum");
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		String board_category = request.getParameter("board_category");
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		BoardDTO bd_dto = dao.boardSelect(comment_num, board_category);
		CommentDTO dto = dao.cmtSelect(comment_num, board_category);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("bd_dto", bd_dto);
		request.setAttribute("dto", dto);
	}
	
	// 댓글 수정처리
	@Override
	public void commentModAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서비스 - commentModAction");
		
		String pageNum = request.getParameter("h_pageNum");
		System.out.println("pageNum: " + pageNum);
		int comment_num = Integer.parseInt(request.getParameter("h_comment_num"));
		String category = request.getParameter("h_category");
		String content = request.getParameter("content");
		
		CommentDTO dto = new CommentDTO();
		dto.setContent(content);
		dto.setBoard_category(category);
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		BoardDTO bd_dto = dao.boardSelect(comment_num, category);
		int updateCnt = dao.cmtUpdate(comment_num, dto);
		System.out.println("updateCnt:" + updateCnt);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("bd_dto", bd_dto);
		
	}
	// 댓글 삭제처리
	@Override
	public void commentDelAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
		String board_category = request.getParameter("board_category");
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO dao = BoardDAOImpl.getInstance();
		BoardDTO dto = dao.boardSelect(comment_num, board_category);
		int deleteCnt = dao.cmtDelete(comment_num, board_category);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		request.setAttribute("deleteCnt", deleteCnt);
		System.out.println("deleteCnt: " + deleteCnt);
	}

}
