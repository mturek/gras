package Model;

public class TaskProto {

	private String name;
	private String descrip;
	private String photoloc;
	private String time;
	
	public TaskProto(String name, String des, String phot, String tim){
		this.name = name;
		this.descrip = des;
		this.photoloc = phot;
		this.time = tim;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getDescrip(){
		return this.descrip;
	}
	
	public String getphotoloc(){
		return this.photoloc;
	}
	
	public String getTime(){
		return this.time;
	}
}
