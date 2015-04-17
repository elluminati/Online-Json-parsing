package com.example.online_jasonparsing;

import java.util.ArrayList;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import com.example.online_jasonparsing.adapter.CustomAdapter;
import com.example.online_jasonparsing.model.ParseModel;
import com.example.online_jasonparsing.parsing.InformationParsing;

public class MainActivity extends Activity {

	private ArrayList<ParseModel> detailsArray;
	private CustomAdapter adapter;
	private ListView list_emp;
	
	// Handler for use handle parsing data which fetch from server
	private Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			//  see any data fetch or not
			if (msg.what == InformationParsing.GET_DATA) {
				
			// if get respone than set in arraylist
			detailsArray = (ArrayList<ParseModel>) msg.obj;
			} else if (msg.what == InformationParsing.GET_ERROR) {
				Toast.makeText(getApplicationContext(), "Error in Data fetch",
						Toast.LENGTH_LONG).show();

			}

		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		InformationParsing parsing = new InformationParsing();
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		detailsArray = new ArrayList<>();
		list_emp = (ListView) findViewById(R.id.lv_walker);
       	parsing.getData(handler, nameValuePairs, detailsArray);
		adapter = new CustomAdapter(this, detailsArray);
		list_emp.setAdapter(adapter);

	
	}

}
