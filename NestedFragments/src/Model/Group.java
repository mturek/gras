package Model;

import java.util.ArrayList;

public class Group {
	private String name;
	private ArrayList<User> users;
	private ArrayList<User> leaders;
	private ArrayList<Task> tasks;
	
	public Group(){
		name = "";
		users = new ArrayList<User>();
		leaders = new ArrayList<User>();
		tasks = new ArrayList<Task>();
	}
	
	public Group(ArrayList<User> use, ArrayList<User> lead, ArrayList<Task> task){
		users = use;
		leaders = lead;
		tasks = task;
	}
	
	public ArrayList<User> getUsers(){
		return users;
	}
	
	public ArrayList<User> getLeaders(){
		return leaders;
	}
		
	public ArrayList<Task> getTasks(){
		return tasks;
	}
}
