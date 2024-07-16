package pj.mvc.jsp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.AdminBannerDAO;
import pj.mvc.jsp.dao.AdminBannerDAOImpl;
import pj.mvc.jsp.dto.AdminBannerDTO;
import pj.mvc.jsp.page.Paging;

public class AdminBannerServiceImpl implements AdminBannerService{
	
	// 배너등록
	@Override
	public void bannerAddAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - bannerAddAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		// DTO 생성
		AdminBannerDTO dto = new AdminBannerDTO();
		
		dto.setBannerArea(request.getParameter("bannerArea"));
		//pdImg => ImageUploadHandler 클래스에서 setAttribute()로 넘겼으므로 getAttribute로 처리
		String p_img1 = "/js_pj_fasticat/resources/upload/" + request.getAttribute("fileName");
		dto.setBannerImg(p_img1);
		dto.setBannerStatus(request.getParameter("bannerStatus"));
	
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminBannerDAO dao = AdminBannerDAOImpl.getInstance();
		
		// 5단계. 상품정보 insert
		int insertCnt = dao.bannerInsert(dto);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("insertCnt", insertCnt);
	
	
	}
	
	// 배너목록
	@Override
	public void bannerListAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - bannerListAction()");
		
		// 3단계. 화면에서 입력받은 값을 가져오기
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum : " + pageNum);
		
		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminBannerDAO dao = AdminBannerDAOImpl.getInstance();
		
		//5-1단계. 공연갯수
		int total = dao.bannerCnt();
		System.out.println("total : " + total);
		
		//5-2단계. 공연목록
		Paging paging = new Paging(pageNum);
		paging.setTotalCount(total);
		
		int start = paging.getStartRow();
		int end = paging.getEndRow();
		
		List<AdminBannerDTO> list = dao.bannerList(start, end);
		//System.out.println("list : " + list);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		
		
		
	}
	
	// 배너 상세페이지
	@Override
	public void bannerDetailAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - bannerDetailAction()");
		
		int bannerNo = Integer.parseInt(request.getParameter("bannerNo"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		///4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminBannerDAO dao = AdminBannerDAOImpl.getInstance();
		
		// 5단계
		AdminBannerDTO dto = dao.bannerDetail(bannerNo);
		
		// 6단계. jsp로 처리결과 전달
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
	}

	// 배너수정
	@Override
	public void bannerUpdateAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - bannerUpdateAction()");
		
		// 3단계. 화면에서 입력받은 값, hidden 값을 가져오기
		int hiddenBannerNo = Integer.parseInt(request.getParameter("hiddenBannerNo"));
		String hiddenPageNum = request.getParameter("hiddenPageNum");
		String hiddenBannerImg = request.getParameter("hiddenBannerImg");
		String uploadBannerImg = (String)request.getAttribute("fileName"); //ImageUploadHandler 클래스에서 setAttribute로 upload 파일명
		
		System.out.println("hiddenBannerImg : " + hiddenBannerImg);
		System.out.println("uploadBannerImg : " + uploadBannerImg);
		System.out.println("hiddenBannerNo : " + hiddenBannerNo);
		
		
		// DTO 생성 
		AdminBannerDTO dto = new AdminBannerDTO();
		
		dto.setBannerNo(hiddenBannerNo);
		dto.setBannerArea(request.getParameter("bannerArea"));	
		dto.setBannerStatus(request.getParameter("bannerStatus"));
		
		String strBannerImg = "";
		// 이미지를 수정 안했을때
		if(uploadBannerImg == null) {
			strBannerImg = hiddenBannerImg;
		}
		// 이미지를 수정 했을때
		else {
			//pdImg => ImageUploadHandler 클래스에서 setAttribute()로 넘겼으므로 getAttribute로 처리
			strBannerImg = "/js_pj_fasticat/resources/upload/" + uploadBannerImg;
		}
		
		dto.setBannerImg(strBannerImg);
		
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminBannerDAO dao = AdminBannerDAOImpl.getInstance();
		
		// 5단계. 게시글 수정처리 후 컨트롤러에서 list로 이동
		int updateCnt = dao.bannerUpdate(dto);
		
		//6단계.jsp로 처리결과 전달
		request.setAttribute("updateCnt", updateCnt);
		request.setAttribute("hiddenPageNum", hiddenPageNum);
		request.setAttribute("hiddenBannerNo", hiddenBannerNo);
	}

	// 배너삭제
	@Override
	public void bannerDeleteAction(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		System.out.println("서비스 - bannerDeleteAction()");
		
		// 3단계. get방식 화면에서 입력받은 값을 가져오기
		int bannerNo = Integer.parseInt(request.getParameter("bannerNo"));
		
		// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
		AdminBannerDAO dao = AdminBannerDAOImpl.getInstance();
		
		// 5단계. 게시글 삭제처리 후 컨트롤러에서 list로 이동
		int deleteCnt = dao.bannerDelete(bannerNo);
		request.setAttribute("deleteCnt", deleteCnt);
		
	}

}
