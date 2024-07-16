package pj.mvc.jsp.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminConcertService {
		// 공연등록
		public void concertAddAction(HttpServletRequest request, HttpServletResponse res)
				throws ServletException, IOException;
		
		
		// 공연목록
		public void concertListAction(HttpServletRequest request, HttpServletResponse res)
				throws ServletException, IOException;
		
		
		// 공연 상세페이지
		public void concertDetailAction(HttpServletRequest request, HttpServletResponse res)
				throws ServletException, IOException;
		
		
		// 공연수정
		public void concertUpdateAction(HttpServletRequest request, HttpServletResponse res)
				throws ServletException, IOException;
		
		// 공연삭제
		public void concertDeleteAction(HttpServletRequest request, HttpServletResponse res)
				throws ServletException, IOException;
}
