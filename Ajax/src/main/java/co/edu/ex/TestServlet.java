package co.edu.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
  
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 호출");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/xml;charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
//		 [{"name":"길동홍","age":10},{"name":"주호쨩","age":30}]
		
		//text/json 일 경우 
		//out.print("[{\"name\":\"길동홍\",\"age\":10},{\"name\":\"주호쨩\",\"age\":30}]");
		
		// text/xml 일 경우 
		// <data><name>우잉옹</name><age>10</age></data>
		out.print("<data><name>우잉옹</name><age>10</age><name>주호쨩</name><age>30</age></data>");
		
	}
	
	
	
	
	
	
}
