package co.edu.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletEx extends HttpServlet {

	// 최초 1회 실행
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 호출!!");
	}

	// 요청될 때 마다 실행 doGet(),doPost(), service()
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service() 호출!!");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		// get? post? Check
		if (req.getMethod().equals("GET")) {
			System.out.println("====GET====");
		} else if (req.getMethod().equals("POST")) {
			System.out.println("===POST===");
		}

		String name = req.getParameter("formname");// name = "formname"
		String age = req.getParameter("formage");// name = "formage"
		
		PrintWriter out = resp.getWriter();
		
		out.print("<h3>이름요청파라미터 :"+name+" </h3>");
		out.print("<h3>나이요청파라미터 :"+age+" </h3>");
		
		out.close();
		
	}

	// 서버 stop시 호출 마지막 한번
	@Override
	public void destroy() {
		System.out.println("destroy() 호출");
		super.destroy();
	}

}
