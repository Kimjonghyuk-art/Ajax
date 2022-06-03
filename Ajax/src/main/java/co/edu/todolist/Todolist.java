package co.edu.todolist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Todolist")
public class Todolist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
	
		TodolistDAO dao = new TodolistDAO();
		
		if(req.getMethod().equals("GET")) {
			System.out.println("==GET==");
			
			List<TodolistVO> list = dao.viewTodolist();
			for(int i = 0; i < list.size(); i++) {
				//System.out.println(list.get(i).getTitle());
				if(i != list.size()-1) {
					out.print(list.get(i).getTitle()+",");
				} else if(i == list.size()-1) {
					out.print(list.get(i).getTitle());
				}
			}
		} else if(req.getMethod().equals("POST")) {
			System.out.println("==post==");
			String title = req.getParameter("title");
			String cmd = req.getParameter("cmd");
			TodolistVO todovo = new TodolistVO();
			System.out.println("title > " + title);
			todovo.setTitle(title);
			todovo.setCheckcol("0");
			dao.insertTodolist(todovo);
			
			if(cmd.equals("delete")) {
				String title2 = req.getParameter("title");
				System.out.println("delete 눌렀구나!");
				dao.deleteTodolist(title2);
				
			}
			
		}
		
		
		
	}

}
