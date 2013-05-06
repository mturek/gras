package Model;

import java.util.ArrayList;

public class User {
	
	private ArrayList<String> fields;
	private ArrayList<String> values;
	private String uname;
	private String fname;
	private String lname;
	private Integer phone;
	
	public User(String uname, String fname, String lname, int phone){
		this.uname = uname;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.fields = new ArrayList<String>();
		fields.add("uname");
		fields.add("fname");
		fields.add("lname");
		fields.add("phone");
		
	}
	
	public String getname(){
		return uname;
	}
	
	
	public String getFname(){
		return fname;
	}
	
	@Override
	public String toString(){
		return this.fname + this.lname;
	}
	
	public int getPhone(){
		return phone;
	}
	
	public ArrayList<String> getFields(){
		return fields;
	}
	
	public ArrayList<String> marshallValues(){
		values = new ArrayList<String>();
		values.add(uname);
		values.add(fname);
		values.add(lname);
		values.add(phone.toString());
		return values;
	}
	
	public String sendToServer(){
		NewUserReq nur = new NewUserReq(this);
		return nur.send();
	}
	
	public String getTasks(){
		UserTasksRequest utr = new UserTasksRequest(this.uname);
		return utr.send();
	}
	
	public String getFullname(){
		return fname+" "+lname;
	}
}
