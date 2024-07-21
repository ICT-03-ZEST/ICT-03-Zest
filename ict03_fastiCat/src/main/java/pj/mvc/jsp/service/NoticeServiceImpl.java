package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.NoticeBoardDAO;
import pj.mvc.jsp.dao.NoticeBoardDAOImpl;
import pj.mvc.jsp.dto.NoticeDTO;
import pj.mvc.jsp.page.Paging;

public class NoticeServiceImpl implements NoticeService{
	
	//공지사항 목록
	@Override
	public void NoticeBoardListAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서비스- NoticeBoardListAction");
		
		String N_pageNum = (request.getParameter("pageNum") != null) ? request.getParameter("pageNum") : "1";
		//화면에서 입력받은 값을 가져옴
		N_pageNum  = request.getParameter("pageNum");	//클릭한 페이지값
		
		//싱글톤 방식으로 DAO객체를 생성
		NoticeBoardDAO dao = NoticeBoardDAOImpl.getInstance();
		
		//전체 공지글 갯수를 카운트
		Paging paging = new Paging(N_pageNum);
		int total = dao.NoticeBoardCnt();
		System.out.println("total" + total);
		
		paging.setTotalCount(total);
		
		//전체 공지글 목록 조회
		int Start = paging.getStartRow();
		int end = paging.getEndRow();
		
		List<NoticeDTO> list= dao.noticeBoardList(Start, end);
		System.out.println("list : " + list);
		//JSP로 처리결과를 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		
		
	}

	//공지사항 상세페이지
	@Override
	public void NoticeBoardDetail_Action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서비스- NoticeBoardDetail_Action");
		
		//화면에서 입력받은 값을 가져온다.
		int num =Integer.parseInt(request.getParameter("n_Board_Num"));
		
		//DAO 객채를 생성
		NoticeBoardDAO dao = NoticeBoardDAOImpl.getInstance();
		
		//조회수 증가
		dao.plusNoticeReadCnt(num);
		//공지사항 상세페이지
		NoticeDTO dto = dao.getNoticeBoardDetail(num);
		//jsp로 값 전달
		request.setAttribute("dto", dto);
		System.out.println("dto.getN_Board_Num() : "+dto.getN_Board_Num());
	}

	//공지사항 수정처리 
	@Override
	public void NoticeBoard_UpdateAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서비스 - NoticeBoard_UpdateAction");
		
		//DTO 객체 생성
		NoticeDTO dto = new NoticeDTO();
		//화면에서 입력받은 값을 dto에 담기
		dto.setN_Board_Num(Integer.parseInt(request.getParameter("hidden_num")));
		dto.setN_Title(request.getParameter("title"));
		dto.setN_Content(request.getParameter("content"));
		
		System.out.println("dto.getN_Board_Num() : "+dto.getN_Board_Num());
		
		//싱글톤 적용
		NoticeBoardDAO dao = NoticeBoardDAOImpl.getInstance();
		//공지사항 수정처리
		int updateCnt = dao.updateNoticeBoard(dto);
		//jsp로 값 전달
		//request.setAttribute("dto", dto);
		request.setAttribute("updateCnt", updateCnt);
		
	}
	
	//공지사항 삭제 처리 
	@Override
	public void NoticeBoard_DeleteAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서비스- NoticeBoard_DeleteAction");
		
		//싱글톤
		NoticeBoardDAO dao = NoticeBoardDAOImpl.getInstance();

		dao.deleteNoticeBoard(Integer.parseInt(request.getParameter("n_Board_Num")));
	}

	//공지사항 입력 처리
	@Override
	public void NoticeBoard_InsertAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("서비스- NoticeBoard_InsertAction");
		
		System.out.println(request.getParameter("n_Title"));
		System.out.println(request.getParameter("n_Content"));
		System.out.println(request.getParameter("n_Writer"));
		//화면에서 입력받은 값 가져와서 DTO에 담기
		NoticeDTO dto = new NoticeDTO();
		dto.setN_Title(request.getParameter("n_Title"));
		dto.setN_Content(request.getParameter("n_Content"));
		dto.setN_Writer(request.getParameter("n_Writer"));
		
		//4. 싱글톤 적용
		NoticeBoardDAO dao = NoticeBoardDAOImpl.getInstance();
		
		dao.insertNoticeBoard(dto);
		
		request.setAttribute("dto", dto);
	}

}
