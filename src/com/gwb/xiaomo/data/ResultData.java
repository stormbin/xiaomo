package com.gwb.xiaomo.data;
/**
 * ������Ϣ����
 * @author ��
 *
 */
public class ResultData {
	private int code;
	private String text;

	public ResultData(int code, String text) {
		super();
		this.code = code;
		this.text = text;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
