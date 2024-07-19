package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.AdminBannerServiceImpl;
import pj.mvc.jsp.util.ImageUploadHandler;

@WebServlet("*.ban")
@MultipartConfig(location="D:\\git\\ict03_festiCat\\ict03_fastiCat\\src\\main\\webapp\\resources\\upload",
fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class AdminBannerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMG_UPLOAD_DIR = "D:\\git\\ict03_festiCat\\ict03_fastiCat\\src\\main\\webapp\\resources\\upload";
      
    
    public AdminBannerController() {
        super();
    }

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

	
		// 한글 안깨지게 처리
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = "";
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = uri.substring(contextPath.length());	// uri.substring(시작위치, 끝);
		
		ImageUploadHandler uploader = null; // 작성
		AdminBannerServiceImpl service = new AdminBannerServiceImpl();
		
		// 배너등록 화면
		if(url.equals("/ad_bannerAdd.ban")) {
			System.out.println("<<< url ==> /ad_bannerAdd.ban >>>");
			
			viewPage = "admin/ad_banner/ad_bannerAdd.jsp";
		
		}
		
		// 배너등록 처리
		else if(url.equals("/ad_bannerAddAction.ban")) {
			System.out.println("<<< url ==> /ad_bannerAddAction.ban >>>");
			
			// 추가 : 서비스 호출 전에 추가!
			String contentType = request.getContentType();
			if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				uploader = new ImageUploadHandler();
				uploader.setUploadPath(IMG_UPLOAD_DIR); // img 경로(upload 폴더)
				uploader.imageUpload(request, response);
			}
			
			// 서비스 호출
			service.bannerAddAction(request, response);
			
			viewPage = "admin/ad_banner/ad_bannerAddAction.jsp";
		}
		
		// 배너목록 조회
		else if(url.equals("/ad_bannerEdit.ban")) {
			System.out.println("<<< url ==> /ad_bannerEdit.ban >>>");
			
			viewPage = "admin/ad_banner/ad_bannerEdit.jsp";
			service.bannerListAction(request, response);
		
		}
		
		// 배너 수정 상세페이지 조회
		else if(url.equals("/ad_bannerModify.ban")) {
			System.out.println("<<< url ==> /ad_bannerModify.ban >>>");
			
			viewPage = "admin/ad_banner/ad_bannerModify.jsp";
			service.bannerDetailAction(request, response);
		
		}
		
		// 배너 수정
		else if(url.equals("/ad_bannerModifyAction.ban")) {
			System.out.println("<<< url ==> /ad_bannerModifyAction.ban >>>");
			
			// 추가 : 서비스 호출 전에 추가!
			String contentType = request.getContentType();
			if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				uploader = new ImageUploadHandler();
				uploader.setUploadPath(IMG_UPLOAD_DIR); // img 경로(upload 폴더)
				uploader.imageUpload(request, response);
			}
			
			// 서비스 호출
			service.bannerUpdateAction(request, response);
			viewPage = "admin/ad_banner/ad_bannerModifyAction.jsp";
		}
		
		
		// 배너 삭제
		else if(url.equals("/ad_bannerDeleteAction.ban")) {
			System.out.println("<<< url ==> /ad_bannerDeleteAction.ban >>>");
			
			viewPage = "admin/ad_banner/ad_bannerDeleteAction.jsp";
			service.bannerDeleteAction(request, response);
		
		}

		
		
		// RequestDispatcher : 서블릿 또는 JSP 요청을 받은 후, 다른 컴포넌트로 요청을 위임하는 클래스이다.
		RequestDispatcher dispatcher =request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	
	}
}
