package co.edu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "hr";
	String pw = "hr";
	Connection con;
	ResultSet rs;
	PreparedStatement psmt;
	
	public void connect() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,id,pw);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public void close() {
		if(con != null) {try {con.close();} catch (SQLException e) {e.printStackTrace();}}
		
		if(rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
		
		if(psmt != null) {try {psmt.close();} catch (SQLException e) {e.printStackTrace();}}
		
	}
	
	
}
