package com.android.test;


public class HttpInterface {
	
	public static void sendreq(String req){
	
		bgNetworkingThread bg = new bgNetworkingThread();
		bg.execute(req);
	}
	
	
		
	
}
	

