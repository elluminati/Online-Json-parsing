package com.example.online_jasonparsing.parsing;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.online_jasonparsing.MainActivity;
import com.example.online_jasonparsing.internet.HttpRequest;
import com.example.online_jasonparsing.model.ParseModel;



public class InformationParsing {

	// this class use for parse data which fetch from server
	//give base url of your where json code 
	private final static String BASE_URL = "http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";
	
	public final static int GET_DATA = 100;
	public final static int GET_ERROR = 200;

	public void getData(final Handler handler,
			final ArrayList<NameValuePair> nameValuePairs,
			final ArrayList<ParseModel> Models) {
	
		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpRequest httpRequest = new HttpRequest();

				try {
					
				/*	In response all data fetch from server using  BASE_URL */
				String response = httpRequest.postData(BASE_URL,
							nameValuePairs);
					Log.d("TAG demo", ""+response);
					Message msg = new Message();
					msg.what = GET_DATA;
					msg.obj = getParsedData(response, Models);
					handler.sendMessage(msg);

				} catch (Exception e) {
					e.printStackTrace();
					Message msg = new Message();
					msg.what = GET_ERROR;
					handler.sendMessage(msg);
				}

			}
		}).start();

	}

	public ArrayList<ParseModel> getParsedData(String response,
			ArrayList<ParseModel> Models) {
		try {
			
			// In String resonse we get like some information and we need to parse them.  
			// set in android layout
			/*{
  					"actors": [
    						{
      								"name": "Brad Pitt",
      								 "description": "William Bradley 'Brad' Pitt is an American actor and film producer. He has received a Golden Globe Award, a Screen Actors Guild Award, and three Academy Award nominations in acting categories",
     
      
                             },
                            {
                                     "name": "Tom Cruise",
                                     "description": "Tom Cruise, is an American film actor and producer. He has been nominated for three Academy Awards and has won three Golden Globe Awards. He started his career at age 19 in the 1981 film Endless Love.",
     
    			             },
    			             .
    			             .
    			             .
    			               ]
    		 }*/
			
			// create new object of JSONObject
			JSONObject jsono = new JSONObject(response);
			
			//where array of actor contain many object
			JSONArray jarray = jsono.getJSONArray("actors");

			//more than one object access using loop
			for (int i = 0; i < jarray.length(); i++) {
				JSONObject object = jarray.getJSONObject(i);

				// Each time generate new object of model
				// Model use for store data 
				 ParseModel model = new ParseModel();
				 
				 // name and description is key for access value
				 model.setFirstName(object.getString("name"));
				 model.setAddress(object.getString("description"));
				 Models.add(model);
			}
	
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return Models;

	}

}

