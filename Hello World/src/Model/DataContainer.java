package Model;

import java.util.ArrayList;

public  class DataContainer {

	static ArrayList<User> users = new ArrayList<User>();
	static ArrayList<TaskProto> taskprotos = new ArrayList<TaskProto>();
	static ArrayList<Task> tasks = new ArrayList<Task>();
	
	public DataContainer(){
		
	}
	
	public static void recieveTaskList(String tl){
		System.out.println("i'm here");
		String[] first = tl.split("\\|");
		for( int i =0; i< first.length ; i++){
			String thing = first[i];
			String[] stuffs = thing.split("\\.");
		
			if(stuffs.length == 7){
				Task res = new Task(2,4, stuffs[2], stuffs[3], stuffs[4], stuffs[5], stuffs[6]);
				tasks.add(res);
			}
			else{
				
			}
		}
		
		System.out.println(tasks.size());
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
