package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.AdminFestivalDAO;
import pj.mvc.jsp.dao.AdminFestivalDAOImpl;
import pj.mvc.jsp.dto.AdminFestivalDTO;
import pj.mvc.jsp.page.Paging;

public class AdminFestivalServiceImpl implements AdminFestivalService{
	
	// 페스티벌 등록
	@Override
	public void festivalAddAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("서비스 - concertAddAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		// DTO 생성
		AdminFestivalDTO dto = new AdminFestivalDTO();
		
		dto.setFesName(request.getParameter("fesName"));
		dto.setFesGrade(request.getParameter("fesGrade"));
		dto.setFesTime(request.getParameter("fesTime"));
		dto.setFesPlace(request.getParameter("fesPlace"));
		
		//pdImg => ImageUploadHandler 클래스에서 setAttribute()로 넘겼으므로 getAttribute로 처리
		String p_img1 = "/js_pj_fasticat/resources/upload/" + request.getAttribute("fileName");
		dto.setFesImg(p_img1);
		System.out.println("<< p_img1 : " + p_img1);

		dto.setFesBuy(request.getParameter("fesBuy"));
		dto.setFesPrice(Integer.parseInt(request.getParameter("fesPrice")));
		dto.setFesContent(request.getParameter("fesContent"));
		dto.setFesStatus(request.getParameter("fesStatus"));
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminFestivalDAO dao = AdminFestivalDAOImpl.getInstance();
		
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
		AdminFestivalDAO dao = AdminFestivalDAOImpl.getInstance();
		
		//5-1단계. 공연갯수
		int total = dao.festivalCnt();
		System.out.println("total : " + total);
		
		//5-2단계. 공연목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		List<AdminFestivalDTO> list = dao.festivalList(start, end);
		//System.out.println("list : " + list);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		
		
	}

	// 페스티벌 상세페이지
	@Override
	public void festivalDetailAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - festivalDetailAction()");
		
		int fesNo = Integer.parseInt(request.getParameter("fesNo"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminFestivalDAO dao = AdminFestivalDAOImpl.getInstance();
		
		// 5단계
		AdminFestivalDTO dto = dao.festivalDetail(fesNo);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
	}

	// 페스티벌 수정
	@Override
	public void festivalUpdateAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - festivalUpdateAction() ");
		
		// 3단계. 화면에서 입력받은 값, hidden 값을 가져오기
		int hiddenFesNo = Integer.parseInt(request.getParameter("hiddenFesNo"));
		String hiddenPageNum = request.getParameter("hiddenPageNum");
		String hiddenFesImg = request.getParameter("hiddenFesImg");
		String uploadFesImg = (String)request.getAttribute("fileName"); //ImageUploadHandler 클래스에서 setAttribute로 upload 파일명
		
		System.out.println("hiddenFesImg : " + hiddenFesImg);
		System.out.println("uploadFesImg : " + uploadFesImg);
		System.out.println("hiddenFesNo : " + hiddenFesNo);
		
		AdminFestivalDTO dto = new AdminFestivalDTO();
		
		dto.setFesNo(hiddenFesNo);
		dto.setFesName(request.getParameter("fesName"));
		dto.setFesGrade(request.getParameter("fesGrade"));
		dto.setFesTime(request.getParameter("fesTime"));
		dto.setFesPlace(request.getParameter("fesPlace"));
		
		String strFesImg = "";
		// 이미지를 수정 안했을때
		if(uploadFesImg == null) {
			strFesImg = hiddenFesImg;
		}
		// 이미지를 수정 했을때
		else {
			//pdImg => ImageUploadHandler 클래스에서 setAttribute()로 넘겼으므로 getAttribute로 처리
			strFesImg = "/js_pj_fasticat/resources/upload/" + uploadFesImg;
		}
		
		dto.setFesImg(strFesImg);
		dto.setFesBuy(request.getParameter("fesBuy"));
		dto.setFesPrice(Integer.parseInt(request.getParameter("fesPrice")));
		dto.setFesContent(request.getParameter("fesContent"));
		dto.setFesStatus(request.getParameter("fesStatus"));
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminFestivalDAO dao = AdminFestivalDAOImpl.getInstance();
		
		// 5단계. 게시글 수정처리 후 컨트롤러에서 list로 이동
		int updateCnt = dao.festivalUpdate(dto);
		
		//6단계.jsp로 처리결과 전달
		request.setAttribute("updateCnt", updateCnt);
		request.setAttribute("hiddenPageNum", hiddenPageNum);
		request.setAttribute("hiddenFesNo", hiddenFesNo);
		
	}

	// 페스티벌 삭제
	@Override
	public void festivalDeleteAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		
		System.out.println("서비스 - festivalDeleteAction()");
		
		// 3단계. get방식 화면에서 입력받은 값을 가져오기
		int conNo = Integer.parseInt(request.getParameter("fesNo"));
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminFestivalDAO dao = AdminFestivalDAOImpl.getInstance();
		
		// 5단계. 게시글 삭제처리 후 컨트롤러에서 list로 이동
		int deleteCnt = dao.festivalDelete(conNo);
		request.setAttribute("deleteCnt", deleteCnt);
		
		
	}

}
