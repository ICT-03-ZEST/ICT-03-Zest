package pj.mvc.jsp.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

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
	
		// 썸네일
		Map<String, String> map = (Map)request.getAttribute("map");
			
			Iterator<String> itr = map.keySet().iterator();
			while(itr.hasNext()) {
				String key = itr.next();
				
				if(key.equals("board_thumnail")) {
					if(map.get(key) == null) {
						this.thumnail = request.getParameter("hiddenThumnail");
					}
					else {
						this.thumnail = "/js_pj_fasticat/resources/upload/" + map.get(key);
					}
				}
				else {
					if(map.get(key) == null) {
						this.image = request.getParameter("hiddenThumnail");
					}
					else {
						this.image = "/js_pj_fasticat/resources/upload/" + map.get(key);
					}
					
				}
			}
		}
		
}

