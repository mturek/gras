package Model;

import java.util.ArrayList;



public class Task implements Comparable<Task>{
	
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

	public ArrayList<String> getuser() {
		ArrayList<String> names = new ArrayList<String>();
		String[] users = this.user1.split(",");
		for( String uname: users){
			names.add(uname);
		}
		return names;
	}
	
	public String getname(){
		return protoid;
	}

	public String getTime() {
		return date;
		
	}
	
	public void delete(){
		DeleteTaskReq req = new DeleteTaskReq(this.utid);
		req.send();
	}

	public String getunfilteredunames(){
		return user1;
	}
	public String getGroup() {
		return group;
	}

	@Override
	public int compareTo(Task another) {
		String[] datetime1 = this.date.split(" ");
		String[] date1 = datetime1[0].split("\\/");
		String[] time1 = datetime1[1].split(":");
		
		String[] datetime2 = another.getTime().split(" ");
		String[] date2 = datetime2[0].split("\\/");
		String[] time2 = datetime2[1].split(":");

		
		
		if(date1[2].equals(date2[2])){

			if(date1[1].equals(date2[1])){

				if(date1[0].equals(date2[0])){
						if(time1[0].equals(time2[0])){
							System.out.println(date1[0]);
							System.out.println(date2[0]);

							if(time1[1].equals(time2[1])){
								System.out.println("no diff!!");
								return 0;
							}
							System.out.println("diff min");

							return (Integer.parseInt(time1[1]) < Integer.parseInt(time2[1]) ? -1 : 1);
						}
						System.out.println("diff hour");

						return (Integer.parseInt(time1[0]) < Integer.parseInt(time2[0]) ? -1 : 1);
					
				}
				System.out.println("diff day");

				return (Integer.parseInt(date1[0]) < Integer.parseInt(date2[0]) ? -1 : 1);
			}
			System.out.println("diff month");

			return (Integer.parseInt(date1[1]) < Integer.parseInt(date2[1]) ? -1 : 1);
		}
		System.out.println("diff year");

		return (Integer.parseInt(date1[2]) < Integer.parseInt(date2[2]) ? -1 : 1);

		
	}
}
