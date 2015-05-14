package com.gwb.xiaomo;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	private Fragment[] mFragments;
	private static RadioGroup bottomRg;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private RadioButton rbOne, rbTwo, rbThree;
	private static InputMethodManager imm;
	private MoreSetting moreSetting;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		mFragments = new Fragment[3];
		fragmentManager = getSupportFragmentManager();
		mFragments[0] = fragmentManager.findFragmentById(R.id.fragement_main);
		mFragments[1] = fragmentManager.findFragmentById(R.id.fragement_search);
		mFragments[2] = fragmentManager
				.findFragmentById(R.id.fragement_setting);
		fragmentTransaction = fragmentManager.beginTransaction()
				.hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
		fragmentTransaction.show(mFragments[0]).commit();
		setFragmentIndicator();

	}


	private void setFragmentIndicator() {

		bottomRg = (RadioGroup) findViewById(R.id.bottomRg);
		rbOne = (RadioButton) findViewById(R.id.rbOne);
		rbTwo = (RadioButton) findViewById(R.id.rbTwo);
		rbThree = (RadioButton) findViewById(R.id.rbThree);

		bottomRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				fragmentTransaction = fragmentManager.beginTransaction()
						.hide(mFragments[0]).hide(mFragments[1])
						.hide(mFragments[2]);
				switch (checkedId) {
				case R.id.rbOne:
					fragmentTransaction.show(mFragments[0]).commit();
					break;

				case R.id.rbTwo:
					Toast.makeText(MainActivity.this, "�˹������ڿ����У������ڴ�",
							Toast.LENGTH_SHORT).show();
					// fragmentTransaction.show(mFragments[1]).commit();
					break;

				case R.id.rbThree:
					fragmentTransaction.show(mFragments[2]).commit();
					break;

				default:
					break;
				}
			}
		});
	}

	// ���صײ���
	public static void hideRadioButton() {
		bottomRg.setVisibility(View.GONE);
	}

	// ��ʾ�ײ���
	public static void showRadioButton() {
		bottomRg.setVisibility(View.VISIBLE);
	}

	// �����������
	public static void HideKeyboard(View v) {
		imm = (InputMethodManager) v.getContext().getSystemService(
				Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);

		}
	}

	// ��ʾ�������
	public static void ShowKeyboard(View v) {
		imm = (InputMethodManager) v.getContext().getSystemService(
				Context.INPUT_METHOD_SERVICE);

		imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {//���µ������BACK��ͬʱû���ظ�
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			dialog();
			return true;
		}
		return true;
	}

	// �����˳���ʾ
	protected void dialog() {
		Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setMessage("Ҫ�˳�СĪ������?");
		builder.setTitle("��ʾ");
		builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Process.killProcess(Process.myPid());
			}
		});
		builder.create().show();
	}

}
