package com.gwb.xiaomo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentMore extends Fragment implements OnClickListener {

	private LinearLayout layout_theme, layout_setting, layout_about;
	private FragmentActivity context;
	private TextView tv_title;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_more, container, false);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		TitleLayout.changeTitle("¸ü¶à");
		context = getActivity();
		initView();
	}

	public void initView() {

		layout_theme = (LinearLayout) getView().findViewById(R.id.ll_theme);
		layout_setting = (LinearLayout) getView().findViewById(R.id.ll_setting);
		layout_about = (LinearLayout) getView().findViewById(R.id.ll_about);

		layout_theme.setOnClickListener(this);
		layout_setting.setOnClickListener(this);
		layout_about.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_theme:
			Intent intentTheme = new Intent(context, MoreTheme.class);
			startActivity(intentTheme);
			break;

		case R.id.ll_setting:
			Intent intentSetting = new Intent(context, MoreSetting.class);
			startActivity(intentSetting);
			break;
		case R.id.ll_about:
			Intent intentAbout = new Intent(context, MoreAbout.class);
			startActivity(intentAbout);
			break;
		}
	}
}
