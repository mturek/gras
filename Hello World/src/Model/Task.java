package Model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;



public class Task {
	
	Integer utid;
	Integer protoid;
	Date date;
	Time time;
	User user1;
	User user2;
	User lead1;
	User lead2;
	Group group;
	
	String[] fields = {"utid", "protoid", "date", "time", "user1", "user2", "lead1", "lead2"};
	ArrayList<String>values = new ArrayList<String>();
	public Task(Integer utid, Integer protoid,String date, String time, String user1, String user2, String g1){
		this.values.add(utid.toString());
		this.values.add( protoid.toString());
		this.values.add( date);
		this.values.add( time);
		this.values.add( user1);
		this.values.add( user2);
		this.values.add( g1);

		this.utid= utid;
	/*	this.protoid = protoid;
		this.date = date;
		this.time = time;
		this.user1 = user1;
		this.user2 = user2;
		this.group = g1; */
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
}
