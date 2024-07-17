package pj.mvc.jsp.dto;

import java.sql.Date;

public class CommentDTO {
	
 	private int comment_num;        //댓글 일련번호
    private int board_num;          //게시글 번호
    private String board_category;  //게시판 카테고리 : 공연후기 / 자유
    private String userID;          //작성자
    private String content;         //글내용
    private Date regDate;           //등록일
    
	public CommentDTO() {
		super();
	}
	
	public CommentDTO(int comment_num, int board_num, String board_category, String userID, String content,
			Date regDate) {
		super();
		this.comment_num = comment_num;
		this.board_num = board_num;
		this.board_category = board_category;
		this.userID = userID;
		this.content = content;
		this.regDate = regDate;
	}
	
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
    
    
}
