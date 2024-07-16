package pj.mvc.jsp.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pj.mvc.jsp.dao.CustomerDAO;
import pj.mvc.jsp.dao.CustomerDAOImpl;
import pj.mvc.jsp.dto.ReservationDTO;

public class CustomerServiceImpl implements CustomerService{

   // ID 중복확인 처리
   @Override
   public void idConfirmAction(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      System.out.println("서비스 - idConfirmAction()");
      
      // 3단계. 화면에서 입력받은 값을 가져오기
      String strId = request.getParameter("userid");
      
      // 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
      // CustomerDAOImpl dao = new CustomerDAOImpl();
      CustomerDAO dao = CustomerDAOImpl.getInstance();
      
      // 5단계. 중복확인 처리
      int selectCnt = dao.useridCheck(strId);
      
      // 6단계. jsp로 처리결과 전달
      request.setAttribute("strId", strId);  // key, value
      request.setAttribute("selectCnt", selectCnt);
   }

   // 회원가입 처리
   @Override
   public void signInAction(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      System.out.println("서비스 - signInAction()");
      
      // 3단계. 화면에서 입력받은 값을 가져와서 DTO의 setter로 값 전달
      // DTO 생성
      ReservationDTO dto = new ReservationDTO();
      
      // String strId = request.getParameter("userid");
      // dto.setUserid(strId);
      
      dto.setUserid(request.getParameter("userid"));
      dto.setPassword(request.getParameter("password"));
      dto.setUsername(request.getParameter("username"));
      dto.setBirthday(Date.valueOf(request.getParameter("birthday")));
      dto.setAddress(request.getParameter("address"));
      
      // hp은 필수가 아니므로 null값이 들어올수 있으므로 값이 존재할 떄만 받아온다.(010-1111-2222)
      String hp = "";
      String hp1 = request.getParameter("hp1");
      String hp2 = request.getParameter("hp2");
      String hp3 = request.getParameter("hp3");
      if(!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
         hp = hp1 + "-" + hp2 + "-" + hp3 ;
      }
      dto.setHp(hp);
      
      // 이메일
      String email1 = request.getParameter("email1");
      String email2 = request.getParameter("email2");
      String email = email1 + "@" + email2;
      dto.setEmail(email);
      
      // 등록일
      dto.setRegDate(new Timestamp(System.currentTimeMillis()));
      
      // 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
      CustomerDAO dao = CustomerDAOImpl.getInstance();
      
      // 5단계. 회원가입 처리
      int insertCnt = dao.insertCustomer(dto);
      
      // 6단계. jsp로 처리결과 전달
      request.setAttribute("insertCnt", insertCnt);
      
   }

   // 로그인 처리 / 회원정보 인증(수정, 탈퇴)
   @Override
   public void loginAction(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      System.out.println("서비스 - loginAction");
      
      // 3단계. 화면에서 입력받은 값을 가져오기
      String strId = request.getParameter("userid");
      String strPassword = request.getParameter("password");
            
      // 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
      CustomerDAO dao = CustomerDAOImpl.getInstance();
      
      // 5단계. 로그인 처리
      int selectCnt= dao.idPasswordChk(strId, strPassword);
      
      // 로그인 성공시 세션ID를 설정
      if(selectCnt == 1) {
         // HttpSession session = request.getSession();
         // session.setAttribute("sessionID", strId);
         
         request.getSession().setAttribute("sessionID", strId);
      }
      // 6단계. jsp로 처리결과 전달
      // request.setAttribute("selectCnt", selectCnt);
   }

   // 회원정보 인증처리 및 탈퇴처리
   @Override
   public void deleteCustomerAction(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      System.out.println("서비스 - deleteCustomerAction()");
      
      // 3단계. 화면에서 입력받은 값을 가져오기
      String strId = (String)request.getSession().getAttribute("sessionID");
      String strPassword = request.getParameter("password");
      
      // 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
      CustomerDAO dao = CustomerDAOImpl.getInstance();
      
      // 5-1단계. 회원정보 인증처리 
      int selectCnt = dao.idPasswordChk(strId, strPassword);
      
      if(selectCnt == 1) {
         // 5-2단계. 회원탈퇴 처리 
         int deleteCnt = dao.deleteCustomer(strId);
         request.getSession().invalidate();  // 세션 삭제
         
         // 6단계. jsp로 처리결과 전달
         request.setAttribute("deleteCnt", deleteCnt);
      }
   }

   // 회원정보 인증처리 및 상세페이지
   @Override
   public void modifyDetailAction(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      System.out.println("서비스 - modifyDetailAction()");
      
      // 3단계. 화면에서 입력받은 값을 가져오기
      String strId = (String)request.getSession().getAttribute("sessionID");
      String strPassword = request.getParameter("password");
      
      // 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
      CustomerDAO dao = CustomerDAOImpl.getInstance();
      
      // 5-1단계. 회원정보 인증처리 
      int selectCnt = dao.idPasswordChk(strId, strPassword);
      
      ReservationDTO dto = null;
      if(selectCnt == 1) {
         // 5-2단계. 상세페이지
         dto = dao.getCustomerDetail(strId);
      }
      
      // 6단계. jsp로 처리결과 전달
      request.setAttribute("dto", dto);
      request.setAttribute("selectCnt", selectCnt);
   }

   // 회원정보 수정 처리
   @Override
   public void modifyCustomerAction(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      System.out.println("서비스 - modifyCustomerAction()");
      
      // 3단계. 화면에서 입력받은 값을 가져와서 DTO의 setter로 값 전달
      // DTO 생성
      ReservationDTO dto = new ReservationDTO();
      
      // 아이디 - 세션ID
      dto.setUserid(request.getParameter("userid")); // input 박스에서 입력받지 않고 세션ID
      dto.setPassword(request.getParameter("password"));
      dto.setUsername(request.getParameter("username"));
      dto.setBirthday(Date.valueOf(request.getParameter("birthday")));
      dto.setAddress(request.getParameter("address"));
      
      // hp은 필수가 아니므로 null값이 들어올수 있으므로 값이 존재할 떄만 받아온다.(010-1111-2222)
      String hp = "";
      String hp1 = request.getParameter("hp1");
      String hp2 = request.getParameter("hp2");
      String hp3 = request.getParameter("hp3");
      if(!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
         hp = hp1 + "-" + hp2 + "-" + hp3 ;
      }
      dto.setHp(hp);
      
      // 이메일
      String email1 = request.getParameter("email1");
      String email2 = request.getParameter("email2");
      String email = email1 + "@" + email2;
      dto.setEmail(email);
      
      // 등록일
      dto.setRegDate(new Timestamp(System.currentTimeMillis()));
      
      // 4단계. 싱글톤 방식으로 DAO 객체 생성, 다형성 적용
      CustomerDAO dao = CustomerDAOImpl.getInstance();
      
      // 5단계. 회원정보 수정처리
      int updateCnt = dao.updateCustomer(dto);
      
      // 6단계. jsp로 처리결과 전달
      request.setAttribute("updateCnt", updateCnt);
      
   }

}
