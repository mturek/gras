package Model;

import java.util.ArrayList;

public class Group {

	private ArrayList<User> users;
	private ArrayList<User> leaders;
	
	public Group(){
		users = new ArrayList<User>();
		leaders = new ArrayList<User>();
	}
}
