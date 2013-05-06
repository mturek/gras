package Model;

import java.util.ArrayList;

import com.example.newspinproj.HttpInterface;

public class UsersInGroupReq implements ServerRequest {
	
	private String gname;
	
	public UsersInGroupReq(String gname){
		this.gname = gname;
	}

	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onUsersInGroupReq(this);
	}

	@Override
	public String send() {
		URLCreator urler = new URLCreator();
		String req = this.accept(urler);
		return HttpInterface.sendreq(req);
	}
	
	public String getGname(){
		return this.gname;
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
