package Model;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;



public class Task {
	
	Integer protoid;
	Date date;
	Time time;
	User user1;
	User user2;
	User lead1;
	User lead2;
	Group group;
	
	String[] fields = {"protoid", "date", "time", "user1", "user2", "lead1", "lead2"};
	ArrayList<String>values = new ArrayList<String>();
	public Task(Integer protoid,Date date,Time time,User user1,User user2,Group g1){
		this.values.add( protoid.toString());
		this.values.add( date.toString());
		this.values.add( time.toString());
		this.values.add( user1.toString());
		this.values.add( user2.toString());
		this.values.add( g1.toString());


		this.protoid = protoid;
		this.date = date;
		this.time = time;
		this.user1 = user1;
		this.user2 = user2;
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
	
	public void SendToServer(){
		URLCreator urler = new URLCreator();
		NewTaskReq taskreq = new NewTaskReq(this.getfields(), this.getvalues());
		taskreq.accept(urler);
	}
}
