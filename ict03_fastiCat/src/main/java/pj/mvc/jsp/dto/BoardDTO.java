package pj.mvc.jsp.dto;

import java.sql.Date;   

//(공연/페스티벌)공연후기 게시판과 자유게시판 dto
public class BoardDTO {
	private int board_num;       	//글번호
    private String board_category;  //게시판 카테고리 
    private String board_title;   	//글제목    
    private String board_content;   //글내용
    private String board_thumnail;  //썸네일 
    private String board_image;     //이미지
    private String board_writer;    //작성자
    private Date board_regDate;     //작성일
    private int board_views;       	//조회수
    private int board_heart;		//하트수
 
	public BoardDTO() {
		super();
	}

	public BoardDTO(int board_num, String board_category, String board_title, String board_content, String board_thumnail, String board_image,
					String board_writer, Date board_regDate, int board_views, int board_heart) {
		super();
		this.board_num = board_num;
		this.board_category = board_category;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_thumnail = board_thumnail;
		this.board_image = board_image;
		this.board_writer = board_writer;
		this.board_regDate = board_regDate;
		this.board_views = board_views;
		this.board_heart = board_heart;
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
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
	public String getBoard_thumnail() {
		return board_thumnail;
	}

	public void setBoard_thumnail(String board_thumnail) {
		this.board_thumnail = board_thumnail;
	}
	
	public String getBoard_image() {
		return board_image;
	}
	public void setBoard_image(String board_image) {
		this.board_image = board_image;
	}
	
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public Date getBoard_regDate() {
		return board_regDate;
	}
	public void setBoard_regDate(Date board_regDate) {
		this.board_regDate = board_regDate;
	}
	public int getBoard_views() {
		return board_views;
	}
	public void setBoard_views(int board_views) {
		this.board_views = board_views;
	}
	public int getBoard_heart() {
		return board_heart;
	}
	public void setBoard_heart(int board_heart) {
		this.board_heart = board_heart;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [board_num=" + board_num + ", board_category=" + board_category + ", board_title="
				+ board_title + ", board_content=" + board_content + ", board_image=" + board_image + ", board_writer="
				+ board_writer + ", board_regDate=" + board_regDate + ", board_views=" + board_views + ", board_heart="
				+ board_heart + "]";
	}  
    
}
