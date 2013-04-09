package Model;

import java.util.ArrayList;

public class NewUserReq implements ServerRequest {

	private ArrayList<String> fields;
	private ArrayList<String> values;
	
	public NewUserReq(User user){
		fields = user.getFields();
		values = user.marshallValues();
	}
	
	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onNewUser(this);
	}
	
	public ArrayList<String> getfields(){
		return fields;
	}

	public ArrayList<String> getValues(){
		return values;
	}

	@Override
	public String send() {
		// TODO Auto-generated method stub
		return null;
	}
}
