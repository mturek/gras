package Model;

import java.util.ArrayList;

public  class DataContainer {

	static ArrayList<User> users;
	static ArrayList<TaskProto> taskprotos;
	static ArrayList<Task> tasks;
	public DataContainer(){
		 users = new ArrayList<User>();
		 taskprotos = new ArrayList<TaskProto>();
		 tasks = new ArrayList<Task>();
	}
	
	public static void recieveTaskList(String tl){
		System.out.println("i'm here");
		String[] first = tl.split("\\|");
		for( int i =0; i< first.length ; i++){
			String thing = first[i];
			String[] stuffs = thing.split("\\.");
		
			if(stuffs.length == 7){

				boolean notthere = true;
				for(int j = 0; j < tasks.size(); j++){

					if (stuffs[0].equals(tasks.get(j).getutid())){
						System.out.println("i'm here");
						notthere = false;
				
					}
				}
				if(notthere){
						Task res = new Task(Integer.parseInt(stuffs[0]) ,4, stuffs[2], stuffs[3], stuffs[4], stuffs[5], stuffs[6]);
						tasks.add(res);
				}
				
				
			}
			else{
				System.out.println("didid");
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
