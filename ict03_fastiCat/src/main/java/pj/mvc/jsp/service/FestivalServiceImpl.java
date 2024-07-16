package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.FestivalDAO;
import pj.mvc.jsp.dao.FestivalDAOImpl;
import pj.mvc.jsp.dto.FestivalDTO;
import pj.mvc.jsp.page.Paging;

public class FestivalServiceImpl implements FestivalService{
	
	// 페스티벌 등록
	@Override
	public void festivalAddAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		
System.out.println("서비스 - concertAddAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		// DTO 생성
		FestivalDTO dto = new FestivalDTO();
		
		dto.setFesName(request.getParameter("fesName"));
		dto.setFesGrade(request.getParameter("fesGrade"));
		dto.setFesTime(request.getParameter("fesTime"));
		dto.setFesPlace(request.getParameter("fesPlace"));
		
		//pdImg => ImageUploadHandler 클래스에서 setAttribute()로 넘겼으므로 getAttribute로 처리
		String p_img1 = "/FastiCat_admin/resources/upload/" + request.getAttribute("fileName");
		dto.setFesImg(p_img1);
		dto.setFesBuy(request.getParameter("fesBuy"));
		dto.setFesPrice(Integer.parseInt(request.getParameter("fesPrice")));
		dto.setFesContent(request.getParameter("fesContent"));
		dto.setFesStatus(request.getParameter("fesStatus"));
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		FestivalDAO dao = FestivalDAOImpl.getInstance();
		
		// 5단계. 상품정보 insert
		int insertCnt = dao.festivalInsert(dto);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("insertCnt", insertCnt);
		
	}

	// 페스티벌 목록
	@Override
	public void festivalListAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - festivalListAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum : " + pageNum);
		
		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		FestivalDAO dao = FestivalDAOImpl.getInstance();
		
		//5-1단계. 공연갯수
		int total = dao.festivalCnt();
		System.out.println("total : " + total);
		
		//5-2단계. 공연목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		List<FestivalDTO> list = dao.festivalList(start, end);
		//System.out.println("list : " + list);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		
		
	}

	// 페스티벌 상세페이지
	@Override
	public void festivalDetailAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
	}

	// 페스티벌 수정
	@Override
	public void festivalUpdateAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
	}

	// 페스티벌 삭제
	@Override
	public void festivalDeleteAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
	}

}
