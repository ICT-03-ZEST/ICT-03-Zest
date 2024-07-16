package pj.mvc.jsp.dto;

import java.sql.Date;

public class AdminConcertDTO {
	private int conNo;                  //공연번호    
	private String conCategory;         //공연카테고리  
	private String conName;             //공연명     
	private String conGrade;            //관람등급    
	private String conTime;             //공연날짜/시간 
	private String conPlace;            //공연장소    
	private String conImg;              //공연이미지   
	private String conBuy;              //공연예매처   
	private int conPrice;               //공연가격    
	private String conContent;           //상품설명    
	private String conStatus;           //상품상태코드  
	private Date conIndate;             //공연등록일 
	
	public AdminConcertDTO() {}
	
	public AdminConcertDTO(int conNo, String conCategory, String conName, String conGrade, String conTime, String conPlace,
			String conImg, String conBuy, int conPrice, String conConten, String conStatus, Date conIndate) {
		super();
		this.conNo = conNo;
		this.conCategory = conCategory;
		this.conName = conName;
		this.conGrade = conGrade;
		this.conTime = conTime;
		this.conPlace = conPlace;
		this.conImg = conImg;
		this.conBuy = conBuy;
		this.conPrice = conPrice;
		this.conContent = conConten;
		this.conStatus = conStatus;
		this.conIndate = conIndate;
	}

	public int getConNo() {
		return conNo;
	}

	public void setConNo(int conNo) {
		this.conNo = conNo;
	}

	public String getConCategory() {
		return conCategory;
	}

	public void setConCategory(String conCategory) {
		this.conCategory = conCategory;
	}

	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public String getConGrade() {
		return conGrade;
	}

	public void setConGrade(String conGrade) {
		this.conGrade = conGrade;
	}

	public String getConTime() {
		return conTime;
	}

	public void setConTime(String conTime) {
		this.conTime = conTime;
	}

	public String getConPlace() {
		return conPlace;
	}

	public void setConPlace(String conPlace) {
		this.conPlace = conPlace;
	}

	public String getConImg() {
		return conImg;
	}

	public void setConImg(String conImg) {
		this.conImg = conImg;
	}

	public String getConBuy() {
		return conBuy;
	}

	public void setConBuy(String conBuy) {
		this.conBuy = conBuy;
	}

	public int getConPrice() {
		return conPrice;
	}

	public void setConPrice(int conPrice) {
		this.conPrice = conPrice;
	}

	public String getConContent() {
		return conContent;
	}

	public void setConContent(String conContent) {
		this.conContent = conContent;
	}

	public String getConStatus() {
		return conStatus;
	}

	public void setConStatus(String conStatus) {
		this.conStatus = conStatus;
	}

	public Date getConIndate() {
		return conIndate;
	}

	public void setConIndate(Date conIndate) {
		this.conIndate = conIndate;
	}

	@Override
	public String toString() {
		return "AdminConcertDTO [conNo=" + conNo + ", conCategory=" + conCategory + ", conName=" + conName + ", conGrade="
				+ conGrade + ", conTime=" + conTime + ", conPlace=" + conPlace + ", conImg=" + conImg + ", conBuy="
				+ conBuy + ", conPrice=" + conPrice + ", conContent=" + conContent + ", conStatus=" + conStatus
				+ ", conIndate=" + conIndate + "]";
	}
	
	
	
	
}


