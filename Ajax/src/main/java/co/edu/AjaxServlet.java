package co.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet({"/Ajax", "/ajax.do" })
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public AjaxServlet() {
        super();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter(); //출력스트림
		
		String emp = request.getParameter("emp");
	
		if(emp.equals("html")) {
		
		out.print("<h3>아작스페이지ㅁ뭊ㄷㄻㅈㄷㄻㅈㄷㄹ</h3>");
		out.print("<h3>아작스페이지입니다1ㅁㅈㄷㄻㅈㄷㄹ1</h3>");
		out.print("<a href='index.html'> 첫 페이지로 </a>");
		
		} else if(emp.equals("json")) {
			   // [{"fname" : ?, "lname":?}, {}, {}]
//		      String json = "[";
		      EmpDAO dao = new EmpDAO();
		      List<Employee> list = dao.empList();
		     
		 
		      Gson gson = new GsonBuilder().create();
		      out.print(gson.toJson(list));
				/*
				 * out.print(gson.toJson(list));
				 * 
				 * for(int i=0; i<list.size(); i++) { json +=
				 * "{\"fname\":"+list.get(i).getFirstName()+ "}"; if(i != list.size() -1) { json
				 * += ","; } } json += "]";
				 */
		      

		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String hdate = request.getParameter("hdate");
		String jobid = request.getParameter("job");
		String empId = request.getParameter("empId");
		String cmd = request.getParameter("cmd");
		
		Employee emp = new Employee();
		emp.setFirstName(fname);
		emp.setLastName(lname);
		emp.setEmail(email);
		emp.setJobId(jobid);
		emp.setHireDate(hdate);
	
		
		
		if(cmd.equals("insert")) {
			EmpDAO dao = new EmpDAO();
			dao.insertEmp(emp);
			Gson gson = new GsonBuilder().create();
			response.getWriter().print(gson.toJson(emp));
			
		} else if(cmd.equals("update")) {
			EmpDAO dao = new EmpDAO();
			emp.setEmployeeId(Integer.parseInt(empId));
			if(dao.updateEmp(emp) == null) {
				System.out.println("error");
			} else {
				System.out.println("success");
			}
		}
		
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(emp));
	}

}
