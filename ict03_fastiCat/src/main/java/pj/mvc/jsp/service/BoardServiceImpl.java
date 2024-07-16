package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.BoardDAO;
import pj.mvc.jsp.dao.BoardDAOImpl;
import pj.mvc.jsp.dto.BoardDTO;
import pj.mvc.jsp.dto.HeartDTO;
import pj.mvc.jsp.page.Paging;
import pj.mvc.jsp.util.ImageNameChange;

public class BoardServiceImpl implements BoardService {
	
	// 게시판 목록 조회
	@Override 
	public void boardListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("service - boardListAction");
		// 아이디 있다고 가정
		String sessionID = "sessionID_2";
		
		String pageNum = request.getParameter("pageNum"); // null
		String category = request.getParameter("board_category");
		
		System.out.println("pageNum: " + pageNum);
		System.out.println("category: " + category);
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		// 5-1 단계. 전체 게시글 갯수 카운트
		Paging paging = new Paging(pageNum);
		int total = dao.boardCnt(category);
		
		System.out.println("total: " + total);
		
		paging.setTotalCount(total);
		
		// 5-2 단계. 게시글 목록조회
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		
		List<BoardDTO> list = dao.boardList(category, start, end);
		
		System.out.println("service - boardListAction 성공");
		
		//6단계 jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.getSession().setAttribute("sessionID", sessionID);
		
		System.out.println("sessionID: " + sessionID);
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
		
		// 하트체크 조회 => 해당 게시글번호의 하트를 누른 아이디라면 하트 filled
		BoardDAO dao = BoardDAOImpl.getInstance();
		HeartDTO dto2 = new HeartDTO();
		dto2.setUserID(sessionID);
		dto2.setBoard_num(board_num);
		dto2.setBoard_category(category);
		
		int heartChk = dao.selectHeart(dto2); //=> 1 이면 하트 filled
		
		System.out.println("sessionID: " + sessionID);
		
		//다음게시글(전체 게시글수)
		int total = dao.boardCnt(category);
				
		//사용자 게시글 이력 조회 => '수정' 태그가 뜨도록함
		int selWriter = dao.selectOfwriter(category, sessionID, board_num);
		
		//조회수 증가
		dao.plusViews(board_num, category);
		
		BoardDTO dto = dao.getBoardDetail(board_num, category);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", dto);
		request.setAttribute("selWriter", selWriter);
		request.setAttribute("dto2", dto2); // 임시임..로그인확인
		request.setAttribute("heartChk", heartChk);
		request.setAttribute("total", total);
	}
	
	//좋아요 추가 / 게시판 좋아요 수 증가
	public void heartInsertAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서비스 - heartInsertAction");
		// sessionID 있다고 가정
		String sessionID = (String)request.getSession().getAttribute("sessionID");
		
		System.out.println("sessionID: " + sessionID);
		
		int board_num = Integer.parseInt(request.getParameter("board_num")); 
		String board_category = request.getParameter("board_category");
		int board_heart = Integer.parseInt(request.getParameter("board_heart")); 
		
		System.out.println("board_num: " + board_num);
		System.out.println("category: " + board_category);
		System.out.println("board_heart" + board_heart);
		
		//게시판 하트 증가
		BoardDTO dto = new BoardDTO();
		dto.setBoard_num(board_num);
		dto.setBoard_category(board_category);
		dto.setBoard_heart(board_heart);
		
		// 세션아이디가 살아있는 동안, 해당 게시글의 하트는 한번 클릭할 수 있도록 함
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
		
		System.out.println("sessionID: " + sessionID);
		
		int board_num = Integer.parseInt(request.getParameter("board_num")); 
		String board_category = request.getParameter("board_category");
		int board_heart = Integer.parseInt(request.getParameter("board_heart")); 
		
		System.out.println("board_num: " + board_num);
		System.out.println("category: " + board_category);
		System.out.println("board_heart" + board_heart);
		
		//게시판 하트 감소
		BoardDTO dto = new BoardDTO();
		dto.setBoard_num(board_num);
		dto.setBoard_category(board_category);
		dto.setBoard_heart(board_heart);
		
		// 세션아이디가 살아있는 동안, 해당 게시글의 하트는 한번 클릭할 수 있도록 함
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
	
	// 댓글 작성처리
	@Override
	public void commentAddAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	// 댓글 목록처리
	@Override
	public void commentListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	// 댓글 수정처리
	@Override
	public void commentModAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	// 댓글 삭제처리
	@Override
	public void commentDelAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	@Override
	public void boardHeartSelectAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
