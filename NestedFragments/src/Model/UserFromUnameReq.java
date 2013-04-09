package Model;

import java.util.ArrayList;

public class UserFromUnameReq implements ServerRequest{

	ArrayList<String> fields;
	ArrayList<String> values;
	
	public UserFromUnameReq(String uname){
		fields = new ArrayList<String>();
		fields.add("uname");
		values = new ArrayList<String>();
		values.add(uname);
	}
	
	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onUserFromNewNameReq(this);
	}

	@Override
	public String send() {
		URLCreator urler = new URLCreator();
		return this.accept(urler);
	}

	public ArrayList<String> getfields() {
		return fields;
	}

	public ArrayList<String> getValues() {
		return values;
	}

}
