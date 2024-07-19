package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.AdminBannerDTO;

public interface AdminBannerDAO {
	
	// 배너등록
	public int bannerInsert(AdminBannerDTO dto);
	
	// 배너갯수
	public int bannerCnt();
	
	// 배너목록
	public List<AdminBannerDTO> bannerList(int start, int end);
	
	// 배너 상세페이지
	public AdminBannerDTO bannerDetail(int bannerNo);
	
	// 배너수정
	public int bannerUpdate(AdminBannerDTO dto);
	
	// 배너삭제
	public int bannerDelete(int bannerNo);
	
	//  메인 - 배너 조회
	public List<AdminBannerDTO> getBannerList();
}
