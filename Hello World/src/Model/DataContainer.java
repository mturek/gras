package Model;

import java.util.ArrayList;

public  class DataContainer {

	static ArrayList<User> users = new ArrayList<User>();
	static ArrayList<TaskProto> taskprotos = new ArrayList<TaskProto>();
	static ArrayList<Task> tasks = new ArrayList<Task>();
	
	public DataContainer(){
		
	}
	
	public static void recieveTaskList(String tl){
		String[] parts = tl.split("newtask");
		System.out.println(parts[0]);
	}
	
	public static void recieveUserList(String ul){
		
	}
	
	public static void addUser(User u){
		users.add(u);
	}
	
	public static void addTaskProto(TaskProto tp){
		taskprotos.add(tp);
	}
	
	public static void addTask(Task t){
		tasks.add(t);
	}
}
