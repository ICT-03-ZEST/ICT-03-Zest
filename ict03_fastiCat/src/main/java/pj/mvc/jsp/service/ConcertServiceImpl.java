package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.ConcertDAO;
import pj.mvc.jsp.dao.ConcertDAOImpl;
import pj.mvc.jsp.dto.ConcertDTO;
import pj.mvc.jsp.page.Paging;

public class ConcertServiceImpl implements ConcertService{
	
	//공연등록
	@Override
	public void concertAddAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - concertAddAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		// DTO 생성
		ConcertDTO dto = new ConcertDTO();
		
		dto.setConCategory(request.getParameter("conCategory"));
		dto.setConName(request.getParameter("conName"));
		dto.setConGrade(request.getParameter("conGrade"));
		dto.setConTime(request.getParameter("conTime"));
		dto.setConPlace(request.getParameter("conPlace"));
		//pdImg => ImageUploadHandler 클래스에서 setAttribute()로 넘겼으므로 getAttribute로 처리
		String p_img1 = "/FastiCat_admin/resources/upload/" + request.getAttribute("fileName");
		dto.setConImg(p_img1);
		dto.setConPrice(Integer.parseInt(request.getParameter("conPrice")));
		dto.setConBuy(request.getParameter("conBuy"));
		dto.setConContent(request.getParameter("conContent"));
		dto.setConStatus(request.getParameter("conStatus"));
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		ConcertDAO dao = ConcertDAOImpl.getInstance();
		
		// 5단계. 상품정보 insert
		int insertCnt = dao.concertInsert(dto);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("insertCnt", insertCnt);
		
	}
	
	//공연 목록
	@Override
	public void concertListAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - concertListAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum : " + pageNum);
		
		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		ConcertDAO dao = ConcertDAOImpl.getInstance();
		
		//5-1단계. 공연갯수
		int total = dao.concertCnt();
		System.out.println("total : " + total);
		
		//5-2단계. 공연목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		List<ConcertDTO> list = dao.concertList(start, end);
		//System.out.println("list : " + list);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		
	}
	
	
	//공연 수정(상세페이지)
	@Override
	public void concertDetailAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - productDetailAction()");
		
		int conNo = Integer.parseInt(request.getParameter("conNo"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		ConcertDAO dao = ConcertDAOImpl.getInstance();
		
		// 5단계
		ConcertDTO dto = dao.concertDetail(conNo);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		
	}
	
	//공연 수정
	@Override
	public void concertUpdateAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - concertUpdateAction()");
		
		
		// 3단계. 화면에서 입력받은 값, hidden 값을 가져오기
		int hiddenConNo = Integer.parseInt(request.getParameter("hiddenConNo"));
		String hiddenPageNum = request.getParameter("hiddenPageNum");
		String hiddenConImg = request.getParameter("hiddenConImg");
		String uploadConImg = (String)request.getAttribute("fileName"); //ImageUploadHandler 클래스에서 setAttribute로 upload 파일명
		
		System.out.println("hiddenConImg : " + hiddenConImg);
		System.out.println("uploadConImg : " + uploadConImg);
		System.out.println("hiddenConNo : " + hiddenConNo);
		
		// DTO 생성 
		ConcertDTO dto = new ConcertDTO();
		
		dto.setConNo(hiddenConNo);
		dto.setConCategory(request.getParameter("conCategory"));
		dto.setConName(request.getParameter("conName"));
		dto.setConGrade(request.getParameter("conGrade"));
		dto.setConTime(request.getParameter("conTime"));
		dto.setConPlace(request.getParameter("conPlace"));
		
		String strConImg = "";
		// 이미지를 수정 안했을때
		if(uploadConImg == null) {
			strConImg = hiddenConImg;
		}
		// 이미지를 수정 했을때
		else {
			//pdImg => ImageUploadHandler 클래스에서 setAttribute()로 넘겼으므로 getAttribute로 처리
			strConImg = "/FastiCat_kgy/resources/upload/" + uploadConImg;
		}
		
		dto.setConImg(strConImg);
		dto.setConPrice(Integer.parseInt(request.getParameter("conPrice")));
		dto.setConBuy(request.getParameter("conBuy"));
		dto.setConContent(request.getParameter("conContent"));
		dto.setConStatus(request.getParameter("conStatus"));
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		ConcertDAO dao = ConcertDAOImpl.getInstance();
		
		// 5단계. 게시글 수정처리 후 컨트롤러에서 list로 이동
		int updateCnt = dao.concertUpdate(dto);
		
		//6단계.jsp로 처리결과 전달
		request.setAttribute("updateCnt", updateCnt);
		request.setAttribute("hiddenPageNum", hiddenPageNum);
		request.setAttribute("hiddenConNo", hiddenConNo);
		
	}
	
	// 공연 삭제
	@Override
	public void concertDeleteAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - concertDeleteAction()");
		
		// 3단계. get방식 화면에서 입력받은 값을 가져오기
		int conNo = Integer.parseInt(request.getParameter("conNo"));
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		ConcertDAO dao = ConcertDAOImpl.getInstance();
		
		// 5단계. 게시글 삭제처리 후 컨트롤러에서 list로 이동
		int deleteCnt = dao.concertDelete(conNo);
		request.setAttribute("deleteCnt", deleteCnt);
		
	}

}
