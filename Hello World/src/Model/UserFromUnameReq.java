package Model;

import java.util.ArrayList;

public class UserFromUnameReq implements ServerRequest{

	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onUserFromNewNameReq(this);
	}

	@Override
	public String send() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getfields() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
