package co.edu.miniprj;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/msg")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/json;charset=UTF-8");
		
		Gson gson = new GsonBuilder().create();
		MessageDAO dao = new MessageDAO();
		
		if(req.getMethod().equals("GET")) {
			
			List<MessageVO> list = dao.messageList();
			resp.getWriter().print(gson.toJson(list));
			
			
		} else if(req.getMethod().equals("POST")) {
			String content = req.getParameter("content");
			String writer = req.getParameter("writer");
			MessageVO msgvo = new MessageVO();
			
			msgvo.setContent(content);
			msgvo.setWrite_date(writer);
			
			dao.insertMessage(msgvo);
			
		}
		
	
	}
	
	
}
