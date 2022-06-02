package co.edu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FullcalendarDAO extends DAO {

	
	//스케줄 리스트 
	
	public List<FullcalendarVO> calendarList() {
		connect();
		List<FullcalendarVO> list = new ArrayList<FullcalendarVO>();
		String sql = "SELECT * FROM fullcalendar";
		
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				FullcalendarVO vo = new FullcalendarVO();
				//emp.setEmployeeId(rs.getInt("employee_id"));
				vo.setTitle(rs.getString("title"));
				vo.setStart(rs.getString("start_date"));
				vo.setEnd(rs.getString("end_date"));
				
				list.add(vo);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}
	
	//스케줄 등록
	public FullcalendarVO insertCalendar(FullcalendarVO vo) {
		connect();
		String sql = "INSERT INTO fullcalendar values(?,?,?)";
		
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
	
				psmt.setString(1, vo.getTitle());
				psmt.setString(2, vo.getStart());
				psmt.setString(3, vo.getEnd());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;

	}
	
	
	
	
	//스케줄 삭제
	public void deleteCalendar(FullcalendarVO vo) {
		connect();
		String sql = "DELETE FROM fullcalendar WHERE title = ?";
		
		try {
			//System.out.println(vo.getTitle());
			psmt = con.prepareStatement(sql);
			psmt.setString(1, vo.getTitle());
			psmt.executeUpdate();
			System.out.println("del sucess");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
}
