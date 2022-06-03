package co.edu.miniprj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO extends DAO {

	//formServlet
	
	public MessageVO getMessage(String user, String pw) {
		MessageVO msg = new MessageVO();
		msg.setMessage_id(100);
		msg.setContent(pw);
		msg.setWriter(user);
		msg.setWrite_date("2022-06-02");
		
		return msg;
	}
	
	
	//리스트 출력
	public List<MessageVO> messageList() {
		connect();
		//String sql = "SELECT * FROM messages WHERE write_date >= sysdate - (1/24/60)*5 order by 1";
		String sql = "SELECT * FROM messages WHERE write_date >= sysdate - (1/24/60)*50 order by 1";
		List<MessageVO> list = new ArrayList<MessageVO>();
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				MessageVO vo = new MessageVO();
				
				vo.setMessage_id(rs.getInt("message_id"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setWrite_date(rs.getString("write_date"));
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
	public void insertMessage(MessageVO mesVO) {
		connect();	
		String sql = "INSERT INTO messages VALUES(mess_seq.nextval,?||mess_seq.currval,?,sysdate)";		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, mesVO.getContent());
			psmt.setString(2, mesVO.getWriter());
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}	
	};	
}
