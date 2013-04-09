package Model;

import java.util.ArrayList;

import com.android.test.HttpInterface;

public class UserTasksRequest implements ServerRequest{

	ArrayList<String> fields = new ArrayList<String>();
	ArrayList<String> values = new ArrayList<String>();
	
	public UserTasksRequest(String uname ){
		fields.add("uname");
		values.add(uname);
	}

	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onUserTasksRequest(this);
	}
	
	public ArrayList<String> getfields(){
		return fields;
	}
	
	public ArrayList<String> getvalues(){
		return values;
	}

	@Override
	public String send() {
		URLCreator urler = new URLCreator();
		return this.accept(urler);
		
	}

}
