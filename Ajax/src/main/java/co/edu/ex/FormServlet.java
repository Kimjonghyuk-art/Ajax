package co.edu.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.miniprj.MessageDAO;
import co.edu.miniprj.MessageVO;


@WebServlet("/FormServ")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();//출력스트림
		
		String user = req.getParameter("user");
		String pw = req.getParameter("pw");
		String cmd = req.getParameter("command");
		
		System.out.println(user+","+pw);
		MessageDAO dao = new MessageDAO();
		
		if(cmd.equals("search")) {
			MessageVO vo = dao.getMessage(user, pw);		
			out.print("<h3>메세지 정보</h3>");
			out.print("<p>메세지 내용(pw)"+ vo.getContent()+"</p>");
			out.print("<p>작성자(user)"+ vo.getWriter()+"</p>");
		} else if(cmd.equals("insert")) {
			out.print("<p>입력한 user 정보" + user + "</p>");
			out.print("<p>입력한 user 정보" + pw + "</p>");
		}
		
	}






}
