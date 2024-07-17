package pj.mvc.jsp.dao;

import java.util.List;

import pj.mvc.jsp.dto.AdminBannerDTO;

public interface AdminBannerDAO {
	
	// 공연등록
	public int bannerInsert(AdminBannerDTO dto);
	
	// 상품갯수
	public int bannerCnt();
	
	// 공연목록
	public List<AdminBannerDTO> bannerList(int start, int end);
	
	// 공연 상세페이지
	public AdminBannerDTO bannerDetail(int bannerNo);
	
	// 공연수정
	public int bannerUpdate(AdminBannerDTO dto);
	
	// 공연삭제
	public int bannerDelete(int bannerNo);
}
