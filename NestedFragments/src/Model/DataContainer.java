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
		String[] first = tl.split("\\|");
		for( int i =0; i< first.length ; i++){
			String thing = first[i];
			String[] stuffs = thing.split("\\.");
			//System.out.println(stuffs.length);
		
			if(stuffs.length == 5){

				boolean notthere = true;
				for(int j = 0; j < tasks.size(); j++){

					if (stuffs[0].equals(tasks.get(j).getutid())){
						notthere = false;
				
					}
				}
				if(notthere){
						Task res = new Task(Integer.parseInt(stuffs[0]) , stuffs[1], stuffs[2], stuffs[3], stuffs[4]);
						//System.out.println("got a task");
						tasks.add(res);
				}
				
				
			}
			else{
			}
		}
		
		System.out.println(tasks.size());
	}
	
	public static ArrayList<Task> getTasksByUname(String uname){
		ArrayList<Task> ret = new ArrayList<Task>();
		for(Task t: tasks){
			if(t.getuser().equals(uname)){
				ret.add(t);
			}
		}
		return ret;
	}
	
	public static void recieveUserList(String ul){
		
	}
	
	public static ArrayList<Task> getTasks() {
		return tasks;
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
