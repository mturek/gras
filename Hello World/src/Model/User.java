package Model;

public class User {
	
	private String name;
	private int uuid;
	
	public User(String name, int uuid){
		this.name = name;
		this.uuid = uuid;
		
	}
	
	public String getname(){
		return name;
	}
	
	public int getuuid(){
		return uuid;
	}
}
