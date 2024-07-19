package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.AdminFestivalServiceImpl;
import pj.mvc.jsp.util.ImageUploadHandler;

@WebServlet("*.fes")
@MultipartConfig(location="D:\\git\\ict03_festiCat\\ict03_fastiCat\\src\\main\\webapp\\resources\\upload",
fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class AdminFestivalController extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String IMG_UPLOAD_DIR = "D:\\git\\ict03_festiCat\\ict03_fastiCat\\src\\main\\webapp\\resources\\upload";

    public AdminFestivalController() {
        super();
    }

    // 1단계. 웹브라우저가 전송한 HTTP요청을 받음
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
		action(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void action(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		// 2. 클라이언트 요청 분석
		
		// 한글 안깨지게 처리
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = "";
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = uri.substring(contextPath.length());	// uri.substring(시작위치, 끝);
		
		ImageUploadHandler uploader = null; // 작성
		AdminFestivalServiceImpl service = new AdminFestivalServiceImpl();
		
		// 페스티벌 등록 화면
		if(url.equals("/ad_festivalAdd.fes")) {
			System.out.println("<<< url ==> /ad_festivalAdd.fes >>>");
			
			viewPage = "admin/ad_festival/ad_festivalAdd.jsp";
		
		}
		
		// 페스티벌 등록 처리
		else if(url.equals("/ad_festivalAddAction.fes")) {
			System.out.println("<<< url ==> /ad_festivalAddAction.fes >>>");
			
			// 추가 : 서비스 호출 전에 추가!
			String contentType = request.getContentType();
			if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				uploader = new ImageUploadHandler();
				uploader.setUploadPath(IMG_UPLOAD_DIR); // img 경로(upload 폴더)
				uploader.imageUpload(request, response);
			}
			
			// 서비스 호출
			service.festivalAddAction(request, response);
			
			viewPage = "admin/ad_festival/ad_festivalAddAction.jsp";
		}
		
		
		// 페스티벌 목록 조회
		else if(url.equals("/ad_festivalEdit.fes")) {
			System.out.println("<<< url ==> /ad_festivalEdit.fes >>>");
			
			viewPage = "admin/ad_festival/ad_festivalEdit.jsp";
			service.festivalListAction(request, response);
		
		}
		
		// 페스티벌 수정 상세페이지 조회
		else if(url.equals("/ad_festivalModify.fes")) {
			System.out.println("<<< url ==> /ad_festivalModify.fes >>>");
			
			viewPage = "admin/ad_festival/ad_festivalModify.jsp";
			service.festivalDetailAction(request, response);
		
		}
		
		
		// 페스티벌 수정
		else if(url.equals("/ad_festivalModifyAction.fes")) {
			System.out.println("<<< url ==> /ad_festivalModifyAction.fes >>>");
			
			// 추가 : 서비스 호출 전에 추가!
			String contentType = request.getContentType();
			if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				uploader = new ImageUploadHandler();
				uploader.setUploadPath(IMG_UPLOAD_DIR); // img 경로(upload 폴더)
				uploader.imageUpload(request, response);
			}
			
			// 서비스 호출
			service.festivalUpdateAction(request, response);
			viewPage = "admin/ad_festival/ad_festivalModifyAction.jsp";
		}
		
		
		// 페스티벌 삭제
		else if(url.equals("/ad_festivalDeleteAction.fes")) {
			System.out.println("<<< url ==> /ad_festivalDeleteAction.fes >>>");
			
			viewPage = "admin/ad_festival/ad_festivalDeleteAction.jsp";
			service.festivalDeleteAction(request, response);
		
		}
		
		
		
		
		// RequestDispatcher : 서블릿 또는 JSP 요청을 받은 후, 다른 컴포넌트로 요청을 위임하는 클래스이다.
		RequestDispatcher dispatcher =request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		
	}	
}
