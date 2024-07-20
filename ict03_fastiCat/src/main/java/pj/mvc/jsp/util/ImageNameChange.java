package pj.mvc.jsp.util;

import java.io.IOException;   

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageNameChange {

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
		String category = request.getParameter("board_category"); 
		
		// 게시글추가
		if(board_img == null) {
			
			if(category.equals("공연후기")) {
				this.thumnail = "/ict03_fastiCat/resources/upload/free.jfif";
			}
			else {
				this.thumnail = "/ict03_fastiCat/resources/upload/default.jpg";
			}
		}
		else {
			this.thumnail = "/ict03_fastiCat/resources/upload/" + board_img;
			this.image = "/ict03_fastiCat/resources/upload/" + board_img;
		}
	
	}
}

