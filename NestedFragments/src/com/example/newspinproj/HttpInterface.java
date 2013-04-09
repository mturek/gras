package com.example.newspinproj;



public class HttpInterface {
	
	public static String sendreq(String req){
	
		bgNetworkingThread bg = new bgNetworkingThread();
		bg.execute(req);
		return "success";
	}
	
	
		
	
}
	

