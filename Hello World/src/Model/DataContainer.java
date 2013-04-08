package Model;

import java.util.ArrayList;

public class DataContainer {

	ArrayList<User> users = new ArrayList<User>();
	ArrayList<TaskProto> taskprotos = new ArrayList<TaskProto>();
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	public DataContainer(){
		
	}
	
	
	public void addUser(User u){
		users.add(u);
	}
	
	public void addTaskProto(TaskProto tp){
		taskprotos.add(tp);
	}
	
	public void addTask(Task t){
		tasks.add(t);
	}
}
