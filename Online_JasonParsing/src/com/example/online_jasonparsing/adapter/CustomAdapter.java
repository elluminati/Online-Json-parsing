package com.example.online_jasonparsing.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.online_jasonparsing.R;
import com.example.online_jasonparsing.model.ParseModel;


	/* 	Adapter use for set each data in layout */
public class CustomAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private ArrayList<ParseModel> list;
	Context context;
	
	public CustomAdapter(Context context,
			ArrayList<ParseModel> list) {
		inflater = LayoutInflater.from(context);
		this.list = list;
		this.context = context;

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressWarnings("unused")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;
		if (convertView == null) {
			
			//if view is empty then  create new object for all controls.
			convertView = inflater.inflate(R.layout.custom_list, null);
			holder = new Holder();

			holder.txtFirstName = (TextView) convertView
					.findViewById(R.id.txt_Fname);
			holder.txtAddress = (TextView) convertView
					.findViewById(R.id.txt_Lname);

			convertView.setTag(holder);

		} else {
			// 
			holder = (Holder) convertView.getTag();
		}

		ParseModel ParseModel = list.get(position);

		holder.txtFirstName
				.setText("First Name "
						+ String.valueOf(list.get(position)
								.getFirstName()));
		holder.txtAddress.setText("Address "
				+ list.get(position).getAddress());

		return convertView;
	}
	
	
	class Holder {
		public TextView txtAddress;
		public TextView txtFirstName;
		

	}

	
}
