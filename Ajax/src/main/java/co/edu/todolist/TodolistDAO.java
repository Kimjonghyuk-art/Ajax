package co.edu.todolist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodolistDAO extends DAO {

	//리스트 출력
	public List<TodolistVO> viewTodolist() {
		connect();
		String sql = "SELECT * FROM todolist";
		List<TodolistVO> list = new ArrayList<TodolistVO>();
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				TodolistVO vo = new TodolistVO();
				vo.setTitle(rs.getString("title"));
				vo.setCheckcol(rs.getString("checkcol"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;		
	};
	
	//입력 기능
	public void insertTodolist(TodolistVO todovo) {
		connect();	
		int result = 0;
		String sql = "INSERT INTO todolist VALUES(?,?)";		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, todovo.getTitle());
			psmt.setString(2, todovo.getCheckcol());
			psmt.executeUpdate();
			if(result != 0) {
				System.out.println("입력완료!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}	
	};	
	
	//삭제 기능
	public void deleteTodolist(String title) {
		connect();
		int result = 0;
		String sql = "DELETE FROM todolist WHERE title =?";
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.executeUpdate();
			if(result != 0) {
				System.out.println(result+"건 삭제완료");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	
}
