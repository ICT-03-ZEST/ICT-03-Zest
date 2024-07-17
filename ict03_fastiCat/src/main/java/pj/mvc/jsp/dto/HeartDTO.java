package pj.mvc.jsp.dto;

public class HeartDTO {
	
	private int board_num;        //글번호
	private String board_category;//카테고리
	private String userID;     //작성자 fk (userid)
    private int heart;     	  // 좋아요
	
    public HeartDTO() {
		super();
	}

	public HeartDTO(int board_num, String board_category, String userID, int heart) {
		super();
		this.board_num = board_num;
		this.board_category = board_category;
		this.userID = userID;
		this.heart = heart;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getBoard_category() {
		return board_category;
	}

	public void setBoard_category(String board_category) {
		this.board_category = board_category;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	@Override
	public String toString() {
		return "LikeDTO [board_num=" + board_num + ", board_category=" + board_category + ", userID=" + userID
				+ ", heart=" + heart + "]";
	}
    
    

}

