package com.gwb.xiaomo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleLayout extends LinearLayout {

	private static TextView tv_title;
	public TitleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.title, this);
		tv_title = (TextView) findViewById(R.id.tv_titel);
	}

	public static void changeTitle(String titleStr){
			tv_title.setText(titleStr);
	}
}
