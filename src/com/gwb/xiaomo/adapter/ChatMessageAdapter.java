package com.gwb.xiaomo.adapter;

import java.text.SimpleDateFormat;
import java.util.List;

import com.gwb.xiaomo.R;
import com.gwb.xiaomo.data.ChatMessage;
import com.gwb.xiaomo.data.ChatMessage.Type;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChatMessageAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<ChatMessage> mChatMsg;


	public ChatMessageAdapter(Context context, List<ChatMessage> mChatMessage) {
		super();
		this.mInflater = LayoutInflater.from(context);
		this.mChatMsg = mChatMessage;
	}

	@Override
	public int getItemViewType(int position) {
		ChatMessage chatMessage = mChatMsg.get(position);
		if (chatMessage.getType() == Type.INCOMING) {
			return 0;
		}
		return 1;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public int getCount() {
		return mChatMsg.size();
	}

	@Override
	public Object getItem(int position) {
		return mChatMsg.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ChatMessage chatMessage = mChatMsg.get(position);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			// 通过ItemType设置不同的布局
			if (getItemViewType(position) == 0) {
				convertView = mInflater.inflate(R.layout.item_from_msg, parent,
						false);
				viewHolder = new ViewHolder();
				viewHolder.mDate = (TextView) convertView
						.findViewById(R.id.form_msg_date);
				viewHolder.mMsg = (TextView) convertView
						.findViewById(R.id.from_msg_info);
			} else {
				convertView = mInflater.inflate(R.layout.item_to_msg, parent,
						false);
				viewHolder = new ViewHolder();
				viewHolder.mDate = (TextView) convertView
						.findViewById(R.id.to_msg_date);
				viewHolder.mMsg = (TextView) convertView
						.findViewById(R.id.to_msg_info);
			}
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// 设置数据
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		viewHolder.mDate.setText(sdf.format(chatMessage.getDate()));
		viewHolder.mMsg.setText(chatMessage.getMsg());
		return convertView;
	}

	private final class ViewHolder {
		TextView mDate;
		TextView mMsg;
	}

}
