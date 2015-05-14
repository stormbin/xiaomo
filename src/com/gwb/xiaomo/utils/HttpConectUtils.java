package com.gwb.xiaomo.utils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Date;

import android.util.Log;

import com.google.gson.Gson;
import com.gwb.xiaomo.data.ChatMessage;
import com.gwb.xiaomo.data.ChatMessage.Type;
import com.gwb.xiaomo.data.ResultData;


public class HttpConectUtils {
	private static final String URL = "http://www.tuling123.com/openapi/api";
	private static final String API_KEY = "93a97a73bc2d687443e48ddfc0464961";

	/**
	 * @param msg ���͵���Ϣ
	 * @return chatMessage ��Ϣ���صĽ��
	 */
	public static ChatMessage sendMessage(String msg) {
		ChatMessage chatMessage = new ChatMessage();
		String jsonResult = doGet(msg);
		Gson gson = new Gson();
		if (jsonResult != null) {
			ResultData result = gson.fromJson(jsonResult, ResultData.class);
			chatMessage.setMsg(result.getText());
		} else {
			chatMessage.setMsg("��������æ����������");
		}
		//����ϵͳʱ��
		chatMessage.setDate(new Date());
		chatMessage.setType(Type.INCOMING);
		return chatMessage;

	}

	//������������
	public static String doGet(String msg) {
		String result = null;
		try {
			String path = setParams(msg);
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// �������ӳ�ʱ
			conn.setConnectTimeout(5 * 1000);
			// �������ݶ�ȡ��ʱ
			conn.setReadTimeout(5 * 1000);
			// ��������ʽ
			conn.setRequestMethod("GET");
			int code = conn.getResponseCode();
			if (code == 200) {
				InputStream is = conn.getInputStream();
				result = StreamTools.readInputStream(is);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// �����������URL
	public static String setParams(String msg) {
		String path = null;
		try {
			path = URL + "?key=" + API_KEY + "&info="
					+ URLEncoder.encode(msg, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
