package pj.mvc.jsp.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminFestivalService {
		// 페스티벌 등록
		public void festivalAddAction(HttpServletRequest request, HttpServletResponse res)
				throws ServletException, IOException;
		
		
		// 페스티벌 목록
		public void festivalListAction(HttpServletRequest request, HttpServletResponse res)
				throws ServletException, IOException;
		
		
		// 페스티벌 상세페이지
		public void festivalDetailAction(HttpServletRequest request, HttpServletResponse res)
				throws ServletException, IOException;
		
		
		// 페스티벌 수정
		public void festivalUpdateAction(HttpServletRequest request, HttpServletResponse res)
				throws ServletException, IOException;
		
		// 페스티벌 삭제
		public void festivalDeleteAction(HttpServletRequest request, HttpServletResponse res)
				throws ServletException, IOException;
		
		// 메인 - 페스티벌 목록 (페이징 처리 방식 차이)
		public void mainFestivalList(HttpServletRequest request, HttpServletResponse res)
				throws ServletException, IOException;
}
