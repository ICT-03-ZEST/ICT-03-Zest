package pj.mvc.jsp.dto;

import java.sql.Date;

public class AdminBannerDTO {
	private int bannerNo;
	private String bannerArea;
	private String bannerImg;
	private String bannerStatus;
	private Date bannerIndate;
	
	public AdminBannerDTO() {}
	
	public AdminBannerDTO(int bannerNo, String bannerArea, String bannerImg, String bannerStatus, Date bannerIndate) {
		super();
		this.bannerNo = bannerNo;
		this.bannerArea = bannerArea;
		this.bannerImg = bannerImg;
		this.bannerStatus = bannerStatus;
		this.bannerIndate = bannerIndate;
	}

	public int getBannerNo() {
		return bannerNo;
	}

	public void setBannerNo(int bannerNo) {
		this.bannerNo = bannerNo;
	}

	public String getBannerArea() {
		return bannerArea;
	}

	public void setBannerArea(String bannerArea) {
		this.bannerArea = bannerArea;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	public String getBannerStatus() {
		return bannerStatus;
	}

	public void setBannerStatus(String bannerStatus) {
		this.bannerStatus = bannerStatus;
	}

	public Date getBannerIndate() {
		return bannerIndate;
	}

	public void setBannerIndate(Date bannerIndate) {
		this.bannerIndate = bannerIndate;
	}

	@Override
	public String toString() {
		return "AdminBannerDTO [bannerNo=" + bannerNo + ", bannerArea=" + bannerArea + ", bannerImg=" + bannerImg
				+ ", bannerStatus=" + bannerStatus + ", bannerIndate=" + bannerIndate + "]";
	}
	
	
}

