package pj.mvc.jsp.dto;

import java.sql.Date;

public class FestivalDTO {
	
	private int fesNo;               // 페스티벌 번호  
	private String fesName;          // 페스티벌명    
	private String fesGrade;         // 관람등급     
	private String fesTime;          // 페스티벌 날짜/시간
	private String fesPlace;         // 페스티벌 장소  
	private String fesImg;           // 페스티벌 이미지 
	private String fesBuy;           // 예매처      
	private int fesPrice;            // 페스티벌 가격  
	private String fesContent;       // 페스티벌 설명  
	private String fesStatus;        // 페스티벌 상태코드
	private Date fesIndate;          // 페스티벌 등록일 
	
	public FestivalDTO() {}
	
	public FestivalDTO(int fesNo, String fesName, String fesGrade, String fesTime, String fesPlace, String fesImg,
			String fesBuy, int fesPrice, String fesContent, String fesStatus, Date fesIndate) {
		super();
		this.fesNo = fesNo;
		this.fesName = fesName;
		this.fesGrade = fesGrade;
		this.fesTime = fesTime;
		this.fesPlace = fesPlace;
		this.fesImg = fesImg;
		this.fesBuy = fesBuy;
		this.fesPrice = fesPrice;
		this.fesContent = fesContent;
		this.fesStatus = fesStatus;
		this.fesIndate = fesIndate;
	}

	public int getFesNo() {
		return fesNo;
	}

	public void setFesNo(int fesNo) {
		this.fesNo = fesNo;
	}

	public String getFesName() {
		return fesName;
	}

	public void setFesName(String fesName) {
		this.fesName = fesName;
	}

	public String getFesGrade() {
		return fesGrade;
	}

	public void setFesGrade(String fesGrade) {
		this.fesGrade = fesGrade;
	}

	public String getFesTime() {
		return fesTime;
	}

	public void setFesTime(String fesTime) {
		this.fesTime = fesTime;
	}

	public String getFesPlace() {
		return fesPlace;
	}

	public void setFesPlace(String fesPlace) {
		this.fesPlace = fesPlace;
	}

	public String getFesImg() {
		return fesImg;
	}

	public void setFesImg(String fesImg) {
		this.fesImg = fesImg;
	}

	public String getFesBuy() {
		return fesBuy;
	}

	public void setFesBuy(String fesBuy) {
		this.fesBuy = fesBuy;
	}

	public int getFesPrice() {
		return fesPrice;
	}

	public void setFesPrice(int fesPrice) {
		this.fesPrice = fesPrice;
	}

	public String getFesContent() {
		return fesContent;
	}

	public void setFesContent(String fesContent) {
		this.fesContent = fesContent;
	}

	public String getFesStatus() {
		return fesStatus;
	}

	public void setFesStatus(String fesStatus) {
		this.fesStatus = fesStatus;
	}

	public Date getFesIndate() {
		return fesIndate;
	}

	public void setFesIndate(Date fesIndate) {
		this.fesIndate = fesIndate;
	}

	@Override
	public String toString() {
		return "FestivalDTO [fesNo=" + fesNo + ", fesName=" + fesName + ", fesGrade=" + fesGrade + ", fesTime="
				+ fesTime + ", fesPlace=" + fesPlace + ", fesImg=" + fesImg + ", fesBuy=" + fesBuy + ", fesPrice="
				+ fesPrice + ", fesContent=" + fesContent + ", fesStatus=" + fesStatus + ", fesIndate=" + fesIndate
				+ "]";
	}
	
}


