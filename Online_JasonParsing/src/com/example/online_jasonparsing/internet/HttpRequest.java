package com.example.online_jasonparsing.internet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.util.Log;

/**
 * This class sends your data through GET and POST methods
 * 
 * */
public class HttpRequest {

	DefaultHttpClient httpClient;
	HttpContext localContext;
	HttpResponse response = null;
	HttpPost httpPost = null;
	HttpGet httpGet = null;

	public HttpRequest() {
		HttpParams myParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(myParams, 10000);
		HttpConnectionParams.setSoTimeout(myParams, 10000);
		httpClient = new DefaultHttpClient(myParams);
		localContext = new BasicHttpContext();
	}

	public void clearCookies() {
		httpClient.getCookieStore().clear();
	}

	public String postData(String url, List<NameValuePair> nameValuePairs)
			throws Exception {
		// Getting the response handler for handling the post response
		ResponseHandler<String> res = new BasicResponseHandler();
		HttpPost postMethod = new HttpPost(url);

		// Setting the data that is to be sent
		postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		// Execute HTTP Post Request
		String response = httpClient.execute(postMethod, res);
		return response;
	}
}