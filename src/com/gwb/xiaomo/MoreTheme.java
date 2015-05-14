package com.gwb.xiaomo;

import com.gwb.xiaomo.data.StateData;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MoreTheme extends Activity implements OnClickListener {

	private Button btn_theme1, btn_theme2, btn_theme3, btn_theme4;
	private final int ThemeId1 = 1, ThemeId2 = 2, ThemeId3 = 3, ThemeId4 = 4;
	private StateData stateData;
	private FragmentChat fragmentChat;
	private SharedPreferences sp;
	private Editor editor;
	private int btnState = 1;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.more_theme);
		fragmentChat = new FragmentChat();
		stateData = new StateData();
		sp = getSharedPreferences("state", Context.MODE_PRIVATE);
		editor = sp.edit();
		TitleLayout.changeTitle("主題");
		initView();
	}

	private void initView() {
		btn_theme1 = (Button) findViewById(R.id.btn_theme1);
		btn_theme2 = (Button) findViewById(R.id.btn_theme2);
		btn_theme3 = (Button) findViewById(R.id.btn_theme3);
		btn_theme4 = (Button) findViewById(R.id.btn_theme4);
		btn_theme1.setOnClickListener(this);
		btn_theme2.setOnClickListener(this);
		btn_theme3.setOnClickListener(this);
		btn_theme4.setOnClickListener(this);
		saveBtnState(sp.getInt("int", 2));
		btnState = sp.getInt("int", 2);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_theme1:
			if (btnState != 1) {
				stateData.setThemeId(ThemeId1);
				fragmentChat.changeChatBackgroup(ThemeId1);
				// 保存所选主题
				editor.putInt("int", ThemeId1);
				editor.commit();
				btn_theme1.setBackgroundResource(R.drawable.theme_btn2);
				changeBtnState(btnState);
				btnState = 1;
				Toast.makeText(this, "主题应用成功", Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.btn_theme2:
			if (btnState != 2) {
				stateData.setThemeId(ThemeId2);
				fragmentChat.changeChatBackgroup(ThemeId2);
				editor.putInt("int", ThemeId2);
				editor.commit();
				btn_theme2.setBackgroundResource(R.drawable.theme_btn2);
				changeBtnState(btnState);
				btnState = 2;
				Toast.makeText(this, "主题应用成功", Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.btn_theme3:
			if (btnState != 3) {
				stateData.setThemeId(ThemeId3);
				fragmentChat.changeChatBackgroup(ThemeId3);
				editor.putInt("int", ThemeId3);
				editor.commit();
				btn_theme3.setBackgroundResource(R.drawable.theme_btn2);
				changeBtnState(btnState);
				btnState = 3;
				Toast.makeText(this, "主题应用成功", Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.btn_theme4:
			if (btnState != 4) {
				stateData.setThemeId(ThemeId4);
				fragmentChat.changeChatBackgroup(ThemeId4);
				editor.putInt("int", ThemeId4);
				editor.commit();
				btn_theme4.setBackgroundResource(R.drawable.theme_btn2);
				changeBtnState(btnState);
				btnState = 4;
				Toast.makeText(this, "主题应用成功", Toast.LENGTH_SHORT).show();
			}
			break;
		}

	}

	public void changeBtnState(int btnState) {
		if (btnState == 1) {
			btn_theme1.setBackgroundResource(R.drawable.theme_btn1);
		} else if (btnState == 2) {
			btn_theme2.setBackgroundResource(R.drawable.theme_btn1);
		} else if (btnState == 3) {
			btn_theme3.setBackgroundResource(R.drawable.theme_btn1);
		} else if (btnState == 4) {
			btn_theme4.setBackgroundResource(R.drawable.theme_btn1);
		}

	}
	
	public void saveBtnState(int btnState){
		if (btnState == 1) {
			btn_theme1.setBackgroundResource(R.drawable.theme_btn2);
		} else if (btnState == 2) {
			btn_theme2.setBackgroundResource(R.drawable.theme_btn2);
		} else if (btnState == 3) {
			btn_theme3.setBackgroundResource(R.drawable.theme_btn2);
		} else if (btnState == 4) {
			btn_theme4.setBackgroundResource(R.drawable.theme_btn2);
		}

	}

}
