package co.edu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class FullcalendarServelet
 */
@WebServlet("/FullcalendarServelet")
public class FullcalendarServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FullcalendarServelet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		FullcalendarDAO dao = new FullcalendarDAO(); //DAO 객체생성
		List<FullcalendarVO> list = dao.calendarList();//List에 셀렉트 결과 담음
		
		Gson gson = new GsonBuilder().create(); // Gson 생성
		response.getWriter().print(gson.toJson(list)); // 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String schedule = request.getParameter("schedule"); // add, del ...
		String title = request.getParameter("title");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		FullcalendarVO vo = new FullcalendarVO();
		vo.setTitle(title);
		vo.setStart(startDate);
		vo.setEnd(endDate);
		
		if(schedule.equals("add")) { //등록
			
			FullcalendarDAO dao = new FullcalendarDAO();
			dao.insertCalendar(vo);
			//{"retCode" : "Success"}
			response.getWriter().print("{\"retCode\" : \"Success\"}");
			
		} else if(schedule.equals("del")) { //삭제
			FullcalendarDAO dao = new FullcalendarDAO();
			dao.deleteCalendar(vo);
			response.getWriter().print("{\"retCode\" : \"Success\"}");
			
		} else {
			
			response.getWriter().print("{\"retCode\" : \"No Request\"}");
		}
		
		
		
		
	}

}
