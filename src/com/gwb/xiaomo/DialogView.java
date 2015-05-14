package com.gwb.xiaomo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class DialogView extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_item);
	}

}
