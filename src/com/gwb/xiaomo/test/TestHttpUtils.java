package com.gwb.xiaomo.test;

import com.gwb.xiaomo.utils.HttpConectUtils;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestHttpUtils extends AndroidTestCase {
	
	public void testSendInfo(){
		String result1 = HttpConectUtils.doGet("你好");
		Log.i("result", result1);
		String result2 = HttpConectUtils.doGet("讲个笑话");
		Log.i("result", result2);
		String result3 = HttpConectUtils.doGet("今天天气怎样");
		Log.i("result", result3);
	}
}
