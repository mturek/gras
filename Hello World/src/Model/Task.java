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
	Leader lead1;
	Leader lead2;
	
	String[] fields = {"protoid", "date", "time", "user1", "user2", "lead1", "lead2"};
	ArrayList<String>values;
	public Task(Integer protoid,Date date,Time time,User user1,User user2,Leader lead1,Leader lead2){
		this.values.add( protoid.toString());
		this.values.add( date.toString());
		this.values.add( time.toString());
		this.values.add( user1.toString());
		this.values.add( user2.toString());
		this.values.add( lead1.toString());
		this.values.add( lead2.toString());


		this.protoid = protoid;
		this.date = date;
		this.time = time;
		this.user1 = user1;
		this.user2 = user2;
		this.lead1 = lead1;
		this.lead2 = lead2;
	}
	
	public Task(Integer protoid, User user1, Leader lead1) {
		this.values.add( protoid.toString());
		this.values.add( null);
		this.values.add( null);
		this.values.add( user1.toString());
		this.values.add(null);
		this.values.add( lead1.toString());
		this.values.add( null);
		this.protoid = protoid;
		this.lead1 = lead1;
		this.user1 = user1;
	}

	public ArrayList<String> getfields(){
		ArrayList<String> fields = null;
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
