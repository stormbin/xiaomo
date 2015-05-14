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
	 * @param msg 发送的消息
	 * @return chatMessage 消息返回的结果
	 */
	public static ChatMessage sendMessage(String msg) {
		ChatMessage chatMessage = new ChatMessage();
		String jsonResult = doGet(msg);
		Gson gson = new Gson();
		if (jsonResult != null) {
			ResultData result = gson.fromJson(jsonResult, ResultData.class);
			chatMessage.setMsg(result.getText());
		} else {
			chatMessage.setMsg("服务器繁忙，请检查网络");
		}
		//设置系统时间
		chatMessage.setDate(new Date());
		chatMessage.setType(Type.INCOMING);
		return chatMessage;

	}

	//建立网络连接
	public static String doGet(String msg) {
		String result = null;
		try {
			String path = setParams(msg);
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置连接超时
			conn.setConnectTimeout(5 * 1000);
			// 设置数据读取超时
			conn.setReadTimeout(5 * 1000);
			// 设置请求方式
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

	// 设置请求参数URL
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
