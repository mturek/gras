package Model;

import java.util.ArrayList;



public class Task {
	
	Integer utid;
	String protoid;
	String date;
	String user1;
	String group;
	
	String[] fields = {"utid", "protoid", "date", "user1",  "group"};
	ArrayList<String>values = new ArrayList<String>();
	public Task(Integer utid, String protoid,String date, String user1,  String g1){
		this.values.add(utid.toString());
		this.values.add( protoid.toString());
		this.values.add( date);
		this.values.add( user1);
		this.values.add( g1);

		this.utid= utid;
		this.protoid = protoid;
		this.date = date;
		this.user1 = user1;
		//this.user2 = user2;
		this.group = g1; 
	}
	
	public Task(String allInfoFromServer){
		
		//TODO
	}
	
	

	public ArrayList<String> getfields(){
		ArrayList<String> fields = new ArrayList<String>();
		for( String f : this.fields){
			fields.add(f);
		}
		return fields;
		
	}
	
	public ArrayList<String> getvalues(){
		return this.values;
	}
	
	public String getutid(){
		return utid.toString();
	}
	public void SendToServer(){
		URLCreator urler = new URLCreator();
		NewTaskReq taskreq = new NewTaskReq(this.getfields(), this.getvalues());
		taskreq.accept(urler);
	}

	public String getuser() {
		return user1;
	}
	
	public String getname(){
		return protoid;
	}

	public String getTime() {
		return date;
		
	}

	public String getGroup() {
		return group;
	}
}
