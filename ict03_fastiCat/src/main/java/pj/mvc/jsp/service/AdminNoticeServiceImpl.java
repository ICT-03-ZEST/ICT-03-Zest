package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.AdminNoticeDAO;
import pj.mvc.jsp.dao.AdminNoticeDAOImpl;
import pj.mvc.jsp.dto.NoticeDTO;
import pj.mvc.jsp.page.Paging;

public class AdminNoticeServiceImpl implements AdminNoticeService{
	// 공지사항 등록
	@Override
	public void noticeAddAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeAddAction");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		// DTO 생성
		NoticeDTO dto = new NoticeDTO();
		
		dto.setN_Title(request.getParameter("N_Title"));
		dto.setN_Content(request.getParameter("N_Content"));
		
		dto.setN_Writer(request.getParameter("N_Writer"));
	
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminNoticeDAO dao = AdminNoticeDAOImpl.getInstance();
		
		// 5단계. 상품정보 insert
		int insertCnt = dao.noticeInsert(dto);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("insertCnt", insertCnt);
	
	
	}
	
	// 공지사항 목록
	@Override
	public void noticeListAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeListAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum : " + pageNum);
		
		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminNoticeDAO dao = AdminNoticeDAOImpl.getInstance();
		
		//5-1단계. 공지사항갯수
		int total = dao.noticeCnt();
		System.out.println("total : " + total);
		
		//5-2단계. 공지사항목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		List<NoticeDTO> list = dao.noticeList(start, end);
		//System.out.println("list : " + list);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		
		
	}

	// 공지사항 (수정)상세 페이지
	@Override
	public void noticeDetailAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeDetailAction()");
		
		int n_Board_Num = Integer.parseInt(request.getParameter("N_Board_Num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));

		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminNoticeDAO dao = AdminNoticeDAOImpl.getInstance();
		
		// 5단계
		NoticeDTO dto = dao.getBoardDetail(n_Board_Num);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
	}

	// 공지사항 수정 처리
	@Override
	public void noticeUpdateAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeUpdateAction()");
		
		String hiddenPageNum = request.getParameter("hiddenPageNum");
		String boardNumParam = request.getParameter("hidden_num");
		System.out.println("boardNumParam : " + boardNumParam);
		int hidden_num = Integer.parseInt(boardNumParam);
		System.out.println("hidden_num : " + hidden_num);
		System.out.println("hiddenPageNum : " + hiddenPageNum);
		
		// DTO 생성 
		NoticeDTO dto = new NoticeDTO();
		dto.setN_Board_Num(hidden_num);
		dto.setN_Title(request.getParameter("N_Title"));
		dto.setN_Writer(request.getParameter("N_Writer"));
		dto.setN_Content(request.getParameter("N_Content"));
		
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminNoticeDAO dao = AdminNoticeDAOImpl.getInstance();
		
		// 5단계. 게시글 수정처리 후 컨트롤러에서 list로 이동
		int updateCnt = dao.updateNotice(dto);
		
		//6단계.jsp로 처리결과 전달
		request.setAttribute("updateCnt", updateCnt);
		request.setAttribute("hiddenPageNum", hiddenPageNum);
		request.setAttribute("hidden_num", hidden_num);
	}

	// 공지사항 삭제
	@Override
	public void noticeDeleteAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeDeleteAction()");
		
		// 3단계. get방식 화면에서 입력받은 값을 가져오기
		int n_Board_Num = Integer.parseInt(request.getParameter("N_Board_Num"));
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminNoticeDAO dao = AdminNoticeDAOImpl.getInstance();
		
		// 5단계. 게시글 삭제처리 후 컨트롤러에서 list로 이동
		int deleteCnt = dao.deleteNotice(n_Board_Num);
		request.setAttribute("deleteCnt", deleteCnt);
		
	}

}
