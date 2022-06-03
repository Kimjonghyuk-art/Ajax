package co.edu.miniprj;

import lombok.Data;

@Data
public class MessageVO {

	private int message_id;
	private String content;
	private String writer;
	private String write_date;
	
	
}
