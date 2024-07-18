package pj.mvc.jsp.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.BoardDAO;
import pj.mvc.jsp.dao.BoardDAOImpl;
import pj.mvc.jsp.dao.MyPageDAO;
import pj.mvc.jsp.dao.MyPageDAOImpl;
import pj.mvc.jsp.dto.BoardDTO;
import pj.mvc.jsp.dto.MyPageDTO;
import pj.mvc.jsp.page.Paging;

public class MyPageServiceImpl implements MyPageService {
	
		// 회원정보 인증처리 및 상세페이지
		@Override
		public void deletePwdChk(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
				System.out.println("서비스 - deletePwdChk()");
			
			String strId = (String) request.getSession().getAttribute("sessionID");
			String strPwd = request.getParameter("password");
			
			// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
			// MyPageDAOImpl dao = new MyPageDAOImpl();
			MyPageDAO dao = MyPageDAOImpl.getInstance();
			
			// 5단계. 중복확인 처리
			int selectCnt = dao.idPasswordChk(strId, strPwd);
			
			// 6단계. jsp로 처리결과 전달
			request.setAttribute("selectCnt", selectCnt);
		};
				
		// 회원정보 인증처리 및 탈퇴처리
		@Override
		public void deleteUserAction(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
			
			System.out.println("서비스 - deleteUserAction()");
			
			// 3단계. 화면에서 입력받은 값을 가져오기
			String strId = (String) request.getSession().getAttribute("sessionID");
			
			MyPageDAO dao = MyPageDAOImpl.getInstance();
			
			// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
			// MyPageDAOImpl dao = new MyPageDAOImpl();
			
			int deleteCnt = dao.deleteUser(strId);
			
			request.getSession().invalidate(); // 세션 삭제
			
			request.setAttribute("selectCnt", deleteCnt);
			
		};
		
		// 회원정보 인증처리 및 상세페이지
		@Override
		public void modifyPwdChk(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
				System.out.println("서비스 - modifyPwdChk()");
			
			String strId = (String) request.getSession().getAttribute("sessionID");
			String strPwd = request.getParameter("password");
			
			// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
			// MyPageDAOImpl dao = new MyPageDAOImpl();
			MyPageDAO dao = MyPageDAOImpl.getInstance();
			
			// 5단계. 중복확인 처리
			int selectCnt = dao.idPasswordChk(strId, strPwd);
			
			// 6단계. jsp로 처리결과 전달
			request.setAttribute("selectCnt", selectCnt);
			
			if(selectCnt == 1) {
				MyPageDTO dto = dao.getUserDetail(strId);
				request.setAttribute("dto", dto);
			}
		};
		
		// 회원정보 수정처리
		@Override
		public void modifyUserAction(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
			
			System.out.println("서비스 - modifyUserAction()");
			
			String strId = (String) request.getSession().getAttribute("sessionID");
			
			// 3단계. 화면에서 입력받은 값을 가져와서 DTO의 setter로 값 전달
			// DTO 생성
			MyPageDTO dto = new MyPageDTO();
			 
			dto.setUserid(strId); //아이디
			dto.setPassword(request.getParameter("password")); //비밀번호
			dto.setUsername(request.getParameter("username")); //이름
			dto.setBirthday(Date.valueOf(request.getParameter("birthday"))); //생년월일
			dto.setAddress(request.getParameter("address")); //주소
			
			// hp은 필수가 아니므로 null값이 들어올 수 있으므로 값이 존재할 때만 받아온다.
			String hp = "";
			String hp1 = request.getParameter("hp1");
			String hp2 = request.getParameter("hp2");
			String hp3 = request.getParameter("hp3");
			if(!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
				hp  = hp1 + "-" + hp2 + "-" + hp3;
			}
			dto.setHp(hp);
			
			// 이메일
			String email1 = request.getParameter("email1");
			String email2 = request.getParameter("email2");
			String email = email1 + "@" + email2;
			dto.setEmail(email); //이메일1
				
			// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
			// MyPageDAOImpl dao = new MyPageDAOImpl();
			MyPageDAO dao = MyPageDAOImpl.getInstance();
			
			int updateCnt = dao.updateUser(dto);
			
			request.setAttribute("updateCnt", updateCnt);
			
		};
		
		// 게시글 목록
		@Override
		public void boardListAction(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			System.out.println("서비스 - boardListAction");
			
			// 3단계. 화면에서 입력받은 값을 가져오기
			String pageNum = request.getParameter("pageNum");
			
			String strId = (String) request.getSession().getAttribute("sessionID");
			// 4단계. 싱글톤 방식으로 DAO 객체 생성,
			MyPageDAO dao = MyPageDAOImpl.getInstance();
			
			// 5-1단계. 전체 게시글 갯수 카운트
			Paging paging = new Paging(pageNum);
			int total = dao.myBoardCnt(strId);
			System.out.println("total => " + total);
			
			paging.setTotalCount(total);
			
			// 5-2단계. 게시글 목록 조회
			int start = paging.getStartRow();
			int end = paging.getEndRow();
			
			List<BoardDTO> list = dao.myBoardList(strId,start,end);
			System.out.println("list: " + list);
			
			
			// 6단계. jsp로 처리결과 전달
			request.setAttribute("list", list);
			request.setAttribute("paging", paging);
			request.setAttribute("strId", strId);
		}
		
		// 회원정보 인증처리 및 상세페이지
		@Override
		public void bdDelPwdChk(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
				System.out.println("서비스 - bdDelPwdChk()");
			
			String strId = (String) request.getSession().getAttribute("sessionID");
			String strPwd = request.getParameter("password");
			
			// 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
			// MyPageDAOImpl dao = new MyPageDAOImpl();
			MyPageDAO dao = MyPageDAOImpl.getInstance();
			
			// 5단계. 중복확인 처리
			int selectCnt = dao.idPasswordChk(strId, strPwd);
			
			// 6단계. jsp로 처리결과 전달
			request.setAttribute("selectCnt", selectCnt);
			
		};

}
