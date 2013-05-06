package Model;

import java.util.ArrayList;

public class Group {
	private String name;
	private ArrayList<User> users;
	private ArrayList<User> leaders;
	
	public Group(){
		name = "";
		users = new ArrayList<User>();
		leaders = new ArrayList<User>();
	}
	
	public Group(String name, ArrayList<User> use, ArrayList<User> lead){
		this.name = name;
		users = use;
		leaders = lead;
	}
	

	public ArrayList<User> getUsers(){
		return users;
	}
	
	public ArrayList<User> getLeaders(){
		return leaders;
	}
	
}
