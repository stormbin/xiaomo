package com.gwb.xiaomo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTools {

	public static String readInputStream(InputStream is) {
		String result = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		byte[] buffer = new byte[1024];
		try {
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			result = new String(baos.toByteArray());
			is.close();
			baos.flush();
			baos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
