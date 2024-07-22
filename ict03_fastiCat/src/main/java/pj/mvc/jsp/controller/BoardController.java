package pj.mvc.jsp.controller;

import java.io.IOException;   

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.BoardService;
import pj.mvc.jsp.service.BoardServiceImpl;
import pj.mvc.jsp.util.ImageUploadHandler;

//회원이 공연/페스티벌 후기 및 자유 게시판 및 댓글을 작성,수정,삭제,조회
@WebServlet("*.bc")
@MultipartConfig(location="D:\\git\\ict03_festiCat\\ict03_fastiCat\\src\\main\\webapp\\resources\\upload", 
			fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String IMG_UPLOAD_DIR = "D:\\git\\ict03_festiCat\\ict03_fastiCat\\src\\main\\webapp\\resources\\upload";
       
    public BoardController() {
        super();
    }
    //1단계. 웹 브라우저가 전송한 HTTP 요청을 받음 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		action(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	//집에서는 C 학원에서는 D 드라이브로 변경
	public void action(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//2단계. 클라이언트 요청 분석
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = "";
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = uri.substring(contextPath.length());
		
		ImageUploadHandler uploader = null; // 썸네일, 컨텐츠이미지(여러개)
		BoardService serv = new BoardServiceImpl();
		///**********게시글 추가,수정,삭제 하트 클릭은 로그인 후 가능***********
		
		// 1.게시판 목록조회
		if(url.equals("/board.bc")) {
			String category = request.getParameter("board_category");
			request.getSession().removeAttribute("myBoard"); // 게시판 목록으로 돌아오기위해 마이페이지 세션을 종료
			
			 serv.boardListAction(request, response);
		 
			if(category.equals("공연후기")) {
				viewPage = "customer/normal_board/review_board/review_board.jsp";
			}
			else {
				viewPage = "customer/normal_board/free_board/free_board.jsp";
			}
		}
		
		// 1-1. 게시판 상세페이지 
		else if(url.equals("/content.bc")) {
			
			System.out.println("컨트롤러 - /content.bc");
			
			String category = request.getParameter("board_category");
			System.out.println("category: " + category);
			serv.boardDetailAction(request, response);
			
			if(category.equals("공연후기")) {
				viewPage = "customer/normal_board/review_board/review_content.jsp";
			}
			else {
				viewPage = "customer/normal_board/free_board/free_content.jsp";
			}
			
		}
		
		//좋아요 추가 / 삭제
		else if(url.equals("/heartClick.bc")) { 
			int heart = Integer.parseInt(request.getParameter("heart"));
			
			if(heart == 1) {
				
				serv.heartInsertAction(request, response);
			}
			else {
				serv.heartDeleteAction(request, response);
			}
		}
		
		//2-1. 게시글 추가 페이지
		else if(url.equals("/myWriting.bc")) {
			System.out.println("컨트롤러 - myWriting.bc");
			
			String category = request.getParameter("board_category");
			request.setAttribute("category", category);
			
			viewPage = "customer/mypage/board_fn/myWriting.jsp";
		}
		
		// 2-2. 게시글 추가 처리
		else if(url.equals("/boardInsertAction.bc")) {
			System.out.println("컨트롤러 - boardInsertAction.bc");
			
			//추가 : 서비스 호출전에 추가
			String contentType = request.getContentType();
			if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				uploader = new ImageUploadHandler();
				uploader.setUploadPath(IMG_UPLOAD_DIR);//img경로(upload 폴더)
				uploader.imageUpload(request, response);
			}
			
			serv.boardInsertAction(request, response);
			
			viewPage = "customer/mypage/board_fn/boardInsertAction.jsp";
		}
		
		// 3-1.게시글 수정 페이지 - 1. 게시판에서 본인게시글이면 수정가능 2. 마이페이지에서 수정가능
		else if(url.equals("/boardUpdate.bc")) {
			System.out.println("컨트롤러 - myBoardUpdate.bc");
			
			serv.boardDetailAction(request, response);
			
			viewPage = "customer/mypage/board_fn/boardUpdate.jsp";
		}
		
		// 3-2.게시글 수정 처리
		else if(url.equals("/boardUpdateAction.bc")) {
			System.out.println("컨트롤러 - myBoardUpdate.bc");
			
			//추가 : 서비스 호출전에 추가
			String contentType = request.getContentType();
			if(contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				uploader = new ImageUploadHandler();
				uploader.setUploadPath(IMG_UPLOAD_DIR);//img경로(upload 폴더)
				uploader.imageUpload(request, response);
			}
			
			serv.boardUpdateAction(request, response);
	
			viewPage = "customer/mypage/board_fn/boardUpdateAction.jsp";
		}
		
		// 4.게시글 삭제 -  1. 게시판에서 본인게시글이면 삭제가능 2. 마이페이지에서 삭제가능
		else if(url.equals("/boardDeleteAction.bc")) {
			System.out.println("컨트롤러 - boardDeleteAction.bc");
			
			serv.boardDeleteAction(request, response);
			
			viewPage = "customer/mypage/board_fn/boardDeleteAction.jsp";
		}
		
		// 5. 댓글 목록조회
		else if(url.equals("/comment_list.bc")) {
			System.out.println("컨트롤러 - comment_list.bc");
			
			serv.commentListAction(request, response);
			
			viewPage = "customer/mypage/comment_fn/comment.jsp";
		}
		
		// 6. 댓글 추가
		else if(url.equals("/comment_insert.bc")) {
			System.out.println("컨트롤러 - comment_insert.bc");
			
			serv.commentAddAction(request, response);
		}
		
		// 7. 댓글 수정페이지
		else if(url.equals("/comment_update.bc")) {
			System.out.println("컨트롤러 - comment_update.bc");
			
			serv.commentMod(request, response);
			
			viewPage = "customer/mypage/comment_fn/comment_update.jsp";
		}
		
		// 7. 댓글 수정처리
		else if(url.equals("/comment_updateAction.bc")) {
			System.out.println("컨트롤러 - comment_updateAction.bc");
			
			serv.commentModAction(request, response);
			
			viewPage = "customer/mypage/comment_fn/comment_updateAction.jsp";
		}
		
		// 8. 댓글 삭제처리
		else if(url.equals("/comment_deleteAction.bc")) {
			System.out.println("컨트롤러 - comment_delete.bc");
			
			serv.commentDelAction(request, response);
			
			viewPage = "customer/mypage/comment_fn/comment_deleteAction.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
	}

}
