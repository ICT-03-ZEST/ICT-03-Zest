package pj.mvc.jsp.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FestivalService {
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
}
