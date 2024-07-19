package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.AdminNoticeDAO;
import pj.mvc.jsp.dao.AdminNoticeDAOImpl;
import pj.mvc.jsp.dto.AdminNoticeDTO;
import pj.mvc.jsp.page.Paging;

public class AdminNoticeServiceImpl implements AdminNoticeService{
	// 공지사항 등록
	@Override
	public void noticeAddAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeAddAction");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		// DTO 생성
		AdminNoticeDTO dto = new AdminNoticeDTO();
		
		dto.setNoticeTitle(request.getParameter("noticeTitle"));
		dto.setNoticeContent(request.getParameter("noticeContent"));
		
		//pdImg => ImageUploadHandler 클래스에서 setAttribute()로 넘겼으므로 getAttribute로 처리
		String p_img1 = "/ict03_fastiCat/resources/upload/" + request.getAttribute("fileName");
		dto.setNoticeImg(p_img1);
		dto.setNoticeWriter(request.getParameter("noticeWriter"));
	
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
		
		//5-1단계. 공연갯수
		int total = dao.noticeCnt();
		System.out.println("total : " + total);
		
		//5-2단계. 공연목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		List<AdminNoticeDTO> list = dao.noticeList(start, end);
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
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminNoticeDAO dao = AdminNoticeDAOImpl.getInstance();
		
		// 5단계
		AdminNoticeDTO dto = dao.getBoardDetail(noticeNo);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
	}

	// 공지사항 수정 처리
	@Override
	public void noticeUpdateAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeUpdateAction()");
		
		
		// 3단계. 화면에서 입력받은 값, hidden 값을 가져오기
		int hiddenNoticeNo = Integer.parseInt(request.getParameter("hiddenNoticeNo"));
		String hiddenPageNum = request.getParameter("hiddenPageNum");
		String hiddenNoticeImg = request.getParameter("hiddenNoticeImg");
		String uploadNoticeImg = (String)request.getAttribute("fileName"); //ImageUploadHandler 클래스에서 setAttribute로 upload 파일명
		
		System.out.println("hiddenNoticeImg : " + hiddenNoticeImg);
		System.out.println("uploadNoticeImg : " + uploadNoticeImg);
		System.out.println("hiddenNoticeNo : " + hiddenNoticeNo);
		
		// DTO 생성 
		AdminNoticeDTO dto = new AdminNoticeDTO();
		
		dto.setNoticeNo(hiddenNoticeNo);
		dto.setNoticeTitle(request.getParameter("noticeTitle"));
		dto.setNoticeWriter(request.getParameter("noticeWriter"));
		dto.setNoticeContent(request.getParameter("noticeContent"));
		
		String strNoticeImg = "";
		// 이미지를 수정 안했을때
		if(uploadNoticeImg == null) {
			strNoticeImg = hiddenNoticeImg;
		}
		// 이미지를 수정 했을때
		else {
			//pdImg => ImageUploadHandler 클래스에서 setAttribute()로 넘겼으므로 getAttribute로 처리
			strNoticeImg = "/ict03_fastiCat/resources/upload/" + uploadNoticeImg;
		}
	
		dto.setNoticeImg(strNoticeImg);
	
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminNoticeDAO dao = AdminNoticeDAOImpl.getInstance();
		
		// 5단계. 게시글 수정처리 후 컨트롤러에서 list로 이동
		int updateCnt = dao.updateNotice(dto);
		
		//6단계.jsp로 처리결과 전달
		request.setAttribute("updateCnt", updateCnt);
		request.setAttribute("hiddenPageNum", hiddenPageNum);
		request.setAttribute("hiddenNoticeNo", hiddenNoticeNo);
	}

	// 공지사항 삭제
	@Override
	public void noticeDeleteAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - noticeDeleteAction()");
		
		// 3단계. get방식 화면에서 입력받은 값을 가져오기
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminNoticeDAO dao = AdminNoticeDAOImpl.getInstance();
		
		// 5단계. 게시글 삭제처리 후 컨트롤러에서 list로 이동
		int deleteCnt = dao.deleteNotice(noticeNo);
		request.setAttribute("deleteCnt", deleteCnt);
		
	}

}
