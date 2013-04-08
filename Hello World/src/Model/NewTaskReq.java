package Model;

import java.util.ArrayList;


public class NewTaskReq implements ServerRequest {

	ArrayList<String> fields;
	ArrayList<String> values;
	
	public NewTaskReq(ArrayList<String> fields, ArrayList<String> values){
		this.fields = fields;
		this.values = values;
	}

	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onNewTask(this);
	}
	
	

}
