package Model;

import com.example.newspinproj.HttpInterface;

public class GroupTasksReq implements ServerRequest{
	
	private String gname;
	public GroupTasksReq(String gname){
		this.gname = gname;
	}

	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onGroupTasksReq(this);
	}

	@Override
	public String send() {
		URLCreator urler = new URLCreator();
		String req = this.accept(urler);
		return HttpInterface.sendreq(req);
	}

	public String getGname() {
		return gname;
	}

}
