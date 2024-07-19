package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.BoardDTO;
import pj.mvc.jsp.dto.MyPageDTO;

public interface MyPageDAO {
	// ID 중복확인 처리
		public int useridCheck(String strUserId);
		
		// 회원가입 처리
		public int insertUser(MyPageDTO dto);
		
		// 로그인 처리 / 회원정보 인증 (수정, 탈퇴)
		public int idPasswordChk(String strId, String strPassword);
		
		// 회원정보 인증처리 및 탈퇴처리
		public int deleteUser(String strId);
		
		// 회원정보 인증처리 및 상세페이지
		public MyPageDTO getUserDetail(String strId);
		
		// 회원정보 수정처리
		public int updateUser(MyPageDTO dto);
		
		// 게시물 목록
		public List<BoardDTO> myBoardList(String strId, String table,int start, int end);
		
		// 게시물 갯수
		public int myBoardCnt(String strId, String table);
		
		// 게시물 삭제
		public int boardDelete(String strId, String[] numList, String category);
}
