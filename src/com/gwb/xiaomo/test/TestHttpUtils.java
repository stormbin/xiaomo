package com.gwb.xiaomo.test;

import com.gwb.xiaomo.utils.HttpConectUtils;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestHttpUtils extends AndroidTestCase {
	
	public void testSendInfo(){
		String result1 = HttpConectUtils.doGet("���");
		Log.i("result", result1);
		String result2 = HttpConectUtils.doGet("����Ц��");
		Log.i("result", result2);
		String result3 = HttpConectUtils.doGet("������������");
		Log.i("result", result3);
	}
}
