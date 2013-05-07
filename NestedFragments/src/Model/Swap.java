package Model;

public class Swap {

	String uname1;
	String uname2;
	int utid;
	String message;
	
	public  Swap(String uname1, String uname2, int utid, String message){
		this.message = message;
		this.uname1 = uname1;
		this.uname2 = uname2;
		this.utid = utid;  
	}
	
	public String getmessage(){
		return message;
	}
	
	public String getuname2(){
		return uname2;
	}
	
	public int getutid(){
		return utid;
	}
}
