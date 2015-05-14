package com.gwb.xiaomo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class MoreSetting extends Activity implements OnClickListener {

	private TextView textView_clean, textView_functions, textView_version;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.more_setting);
		TitleLayout.changeTitle("设置");

		initview();
		textView_clean = (TextView) findViewById(R.id.tv_clean);
	}

	public void initview() {
		textView_clean = (TextView) findViewById(R.id.tv_clean);
		textView_functions = (TextView) findViewById(R.id.tv_functions);
		textView_version = (TextView) findViewById(R.id.tv_version);

		textView_clean.setOnClickListener(this);
		textView_functions.setOnClickListener(this);
		textView_version.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_clean:
			Builder builderClean = new AlertDialog.Builder(MoreSetting.this);
			builderClean.setMessage("清除缓存成功");
			builderClean.setTitle("提示");
			builderClean.setNegativeButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			builderClean.create().show();
			break;

		case R.id.tv_functions:
			Toast.makeText(this, "此功能正在开发中，敬请期待", Toast.LENGTH_SHORT).show();
			break;
		case R.id.tv_version:
			Intent intent = new Intent(MoreSetting.this, DialogView.class);
			startActivity(intent);
			break;


		}

	}

}
