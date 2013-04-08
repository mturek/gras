package com.android.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class bgNetworkingThread extends AsyncTask<String, Integer, String>{

	@Override
	protected String doInBackground(String... params) {
		System.out.println(params[0]);
		HttpResponse response = null;
		String req =params[0];
		try {     
			
	        HttpClient client = new DefaultHttpClient();
	        HttpGet request = new HttpGet();
	        request.setURI(new URI(req));
	        response = client.execute(request);
	        InputStream inp= response.getEntity().getContent();
	        InputStreamReader inputStreamReader = new InputStreamReader(inp);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String bufferedStrChunk = null;
            while((bufferedStrChunk = bufferedReader.readLine()) != null){
                stringBuilder.append(bufferedStrChunk);
            }
            
	        System.out.println(stringBuilder.toString());
	        return stringBuilder.toString();
	        
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    } catch (ClientProtocolException e) {
	        
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }   
		return "failure";
	}
	
}