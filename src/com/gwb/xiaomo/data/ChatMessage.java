package com.gwb.xiaomo.data;

import java.util.Date;

/**
 * 聊天消息数据
 * @author 彬
 *
 */
public class ChatMessage {
	

	private Type type;
	private String msg;
	private Date date;

	public ChatMessage(){
		
	}
	
	public ChatMessage(Type type, String msg, Date date) {
		super();
		this.type = type;
		this.msg = msg;
		this.date = date;
	}
	
	public enum Type
	{
		INCOMING, OUTCOMING
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}


}
