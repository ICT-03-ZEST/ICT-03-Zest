package pj.mvc.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.service.MyPageService;
import pj.mvc.jsp.service.MyPageServiceImpl;
//회원이 공연/페스티벌 후기 및 자유 게시판 및 댓글을 작성,수정,삭제,조회
@WebServlet("*.myp")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyPageController() {
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
	
	public void action(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//2단계. 클라이언트 요청 분석
		request.setCharacterEncoding("UTF-8");
		
		String viewPage = "";
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = uri.substring(contextPath.length());
		
		MyPageService service = new MyPageServiceImpl();
		
		//test(테스트용 세션 아이디 미리 설정)
		request.getSession().setAttribute("sessionID", "test");
		
		// 1.게시판 목록조회 - 공연후기, 페스티벌후기, 자유 메뉴 선택시 해당 목록 전체조회(최신글 부터)
		// test(링크 수정필요)
		if(url.equals("/mypage.myp")) {
			
			viewPage = "customer/mypage/myPage.jsp";
		}
		
		// 나의 게시물 목록
		else if (url.equals("/myBoardList.myp")) {
			System.out.println("<url --> /myBoardList.myp>");
	          
			// 서비스 -> DAO(SELECT)
			service.boardListAction(request, response);
  		
       	 	viewPage= "customer/mypage/myBoardList.jsp";
		} 
		
		// 나의 예매 내역
		else if (url.equals("/myTicketDetail.myp")) {
			System.out.println("<url --> /myTicketDetail.myp>");
			
			viewPage = "customer/mypage/myTicketDetail.jsp";
		} else if (url.equals("/join.myp")) {
			System.out.println("<<< url ==> /join.myp >>>");
			
			viewPage = "views/join.jsp";
		} 
		
		// [회원수정 ]	
		// 회원수정 인증
		else if(url.equals("/modifyPwdChk.myp")) {
			System.out.println("<<< url ==> /modifyDetailAction.myp >>>");
			
			service.modifyPwdChk(request, response);
			
			viewPage = "customer/mypage/myModifyPopup.jsp";
		}
		
		// 회원수정 처리 페이지
		else if(url.equals("/modifyUserAction.myp")) {
			System.out.println("<<< url ==> /modifyUserAction.myp >>>");
			
			service.modifyUserAction(request, response);
			
			viewPage = "customer/mypage/myModifyAction.jsp";
		}
		
		// [회원탈퇴]
		// 회원탈퇴 인증
		else if(url.equals("/deletePwdChk.myp")) {
			System.out.println("<<< url ==> /deletePwdChk.myp >>>");
			
			service.deletePwdChk(request, response);
			
			viewPage = "customer/mypage/myDeletePopup.jsp";
		}
		
		// 회원탈퇴 처리 페이지
		else if(url.equals("/deleteUserAction.myp")) {
			System.out.println("<<< url ==> /deleteUserAction.myp >>>");
			
			service.deleteUserAction(request, response);
			
			viewPage = "customer/mypage/myDeleteAction.jsp";
		}
		
		// [게시물 삭제]
		// 게시물 삭제 비밀번호 인증
		else if(url.equals("/bdDelPwdChk.myp")) {
			System.out.println("<<< url ==> /bdDelPwdChk.myp >>>");
			
			service.bdDelPwdChk(request, response);
			
			viewPage = "customer/mypage/bdDelete.jsp";
		}
		
		// 게시물 삭제 처리
		else if(url.equals("/BoardDeleteAction.myp")) {
			System.out.println("<<< url ==> /BoardDeleteAction.myp >>>");
			
			service.BoardDeleteAction(request, response);
			
			viewPage = "customer/mypage/myBoardList.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
