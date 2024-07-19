package pj.mvc.jsp.util;

import java.io.IOException;   

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateImageName {

	String thumnail;
	String image;
	
	
	public String getThumnail() {
		return thumnail;
	}

	public String getImage() {
		return image;
	}

	public void imageName(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("ImageNameChange - imageName");
		
		String board_img  = (String)request.getAttribute("fileName");
		String hiddenThumnail = request.getParameter("hiddenThumnail"); // 수정시 파일선택을 안하면${dto.board_image}
		String hiddenImage = request.getParameter("hiddenImage");
		String category = request.getParameter("hiddenCategory"); 
		
		// 게시글 수정
		if(board_img == null) {
			
			this.image = hiddenImage;
			
			if(category.equals("공연후기")) {
				this.thumnail = hiddenThumnail;
			}
			else {
				this.thumnail = hiddenThumnail;
			}
		}
		else {
			this.thumnail = "/ict03_fastiCat/resources/upload/" + board_img;
			this.image = "/ict03_fastiCat/resources/upload/" + board_img;
		}
			
	
	}
}

