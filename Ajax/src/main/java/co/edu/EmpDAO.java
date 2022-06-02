package co.edu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpDAO extends DAO {

	//리스트 
	public List<Employee> empList() {
		connect(); //DAO 커넥트 메소드 호출
		List<Employee> list = new ArrayList<Employee>();
		try {
			String sql = "SELECT * FROM emp_temp order by 1";
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
				
				list.add(emp);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}
	
	//입력
	public Employee insertEmp(Employee emp) {
		String sql = "INSERT INTO emp_temp"
				+ " (employee_id, first_name, last_name, email, hire_date,job_id)"
				+ " VALUES(?,?,?,?,?,?)";
		String seqSql = "SELECT EMPLOYEES_SEQ.nextval from dual";
		
		connect();
		int nextSeq = -1;
		try {
			psmt = con.prepareStatement(seqSql);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				nextSeq = rs.getInt(1);
			}
			
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, nextSeq);
			psmt.setString(2, emp.getFirstName());
			psmt.setString(3, emp.getLastName());
			psmt.setString(4, emp.getEmail());
			psmt.setString(5, emp.getHireDate());
			psmt.setString(6, emp.getJobId());
			emp.setEmployeeId(nextSeq);
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력되었습니다.");
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close();
		}	
		return emp;
		
	}

	
	//수정
	public Employee updateEmp(Employee emp) {
	
		connect();
		String sql = "UPDATE emp_temp SET first_name=?, last_name=?, "
				+ "email=?, hire_date=?"
				+ " WHERE employee_id=?" ;
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, emp.getFirstName());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setInt(5, emp.getEmployeeId());
			
			int r = psmt.executeUpdate();
			System.out.println(r + "건 수정");
			if(r>0) {
				return emp;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return null;
		
	}
	
	//삭제
	
	//한건조회
	
	
	// 부서별 인원(차트) 조회 -> 부서명 = 인원 Map<String, Integer>
	
	public Map<String, Integer> getEmpByDept() {
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		connect();
		String sql = "SELECT d.department_name, count(1) AS cnt "
				+ "from employees e, departments d "
				+ "WHERE e.department_id = d.department_id "
				+ "GROUP BY d.department_name";
	
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) { // key= value
				map.put(rs.getString("department_name"), rs.getInt("cnt"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return map;
	}
	
	
	
	
	
}
