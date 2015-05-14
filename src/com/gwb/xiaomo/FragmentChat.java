package com.gwb.xiaomo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gwb.xiaomo.adapter.ChatMessageAdapter;
import com.gwb.xiaomo.data.ChatMessage;
import com.gwb.xiaomo.data.StateData;
import com.gwb.xiaomo.data.ChatMessage.Type;
import com.gwb.xiaomo.utils.HttpConectUtils;

public class FragmentChat extends Fragment {
	private static ListView mListView;
	private ChatMessageAdapter mAdapter;
	private List<ChatMessage> mChatMsg;
	private EditText mInputMsg;
	private Button mButton;
	private Context context;
	private InputMethodManager imm;
	private SharedPreferences sp;
	private StateData stateData;
	private FragmentChat fragmentChat;
	private boolean sendMesState = true;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			ChatMessage fromMessage = (ChatMessage) msg.obj;
			mChatMsg.add(fromMessage);
			// mAdapter.notifyDataSetChanged();
			// ��ListView��λ��ָ��Item��λ��
			mListView.setSelection(mChatMsg.size() - 1);
			//�����û�����
			sendMesState = true;
		};
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_chat, container, false);

	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		context = getActivity();

		imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		// ���ֳ�ʼ��
		initView();
		// ����ԭ��������
		fragmentChat = new FragmentChat();
		stateData = new StateData();
		sp = context.getSharedPreferences("state", Context.MODE_PRIVATE);
		int themeId = sp.getInt("int", 2);
		changeChatBackgroup(themeId);
		// ���ݳ�ʼ��
		initData();

	}

	private void initData() {

		mChatMsg = new ArrayList<ChatMessage>();
		mChatMsg.add(new ChatMessage(Type.INCOMING, "��ã�СĪΪ�����", new Date()));
		mAdapter = new ChatMessageAdapter(context, mChatMsg);
		mListView.setAdapter(mAdapter);
		mListView.setOnTouchListener(new OnTouchListener() {

			// ���صײ��ؼ����رռ�������
			public boolean onTouch(View v, MotionEvent event) {
				MainActivity.HideKeyboard(v);
				MainActivity.showRadioButton();
				return false;
			}
		});

	}

	private void initView() {
		mListView = (ListView) getView().findViewById(R.id.lv_message);
		mInputMsg = (EditText) getView().findViewById(R.id.ed_send_message);
		mInputMsg.setOnClickListener(new OnClickListener() {

			// ���صײ��ؼ�
			public void onClick(View v) {
				MainActivity.hideRadioButton();
			}
		});
		mButton = (Button) getView().findViewById(R.id.btn_send);
		if (sendMesState) {
			mButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					final String msg = mInputMsg.getText().toString();
					if (TextUtils.isEmpty(msg)) {
						Toast.makeText(context, "��Ϣ����Ϊ�գ�����������",
								Toast.LENGTH_SHORT).show();
						return;
					}
					// ������Ϣ

					ChatMessage toMessage = new ChatMessage();
					toMessage.setDate(new Date());
					toMessage.setMsg(msg);
					toMessage.setType(Type.OUTCOMING);
					mChatMsg.add(toMessage);
					//��Ϣû�ظ�֮ǰ�����ܷ����µ���Ϣ
					sendMesState = false;
					// mAdapter.notifyDataSetChanged();

					// ��ListView��λ��ָ��Item��λ��
					mListView.setSelection(mChatMsg.size() - 1);
					// ��������
					mInputMsg.setText("");
					// �������뷨
					imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

					// ������Ϣ
					new Thread() {
						public void run() {
							ChatMessage fromMessage = HttpConectUtils
									.sendMessage(msg);
							Message message = new Message();
							message.obj = fromMessage;
							handler.sendMessage(message);

						};
					}.start();

				}
			});
		}
	}

	public void changeChatBackgroup(int imageID) {
		switch (imageID) {
		case 1:
			mListView.setBackgroundResource(R.drawable.chat_backgroud1);

			break;

		case 2:
			mListView.setBackgroundResource(R.drawable.chat_backgroud2);
			break;

		case 3:
			mListView.setBackgroundResource(R.drawable.chat_backgroud3);
			break;

		case 4:
			mListView.setBackgroundResource(R.drawable.chat_backgroud4);
			break;

		}
	}
}
