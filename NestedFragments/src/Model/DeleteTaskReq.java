package Model;

import java.util.ArrayList;

import com.example.newspinproj.HttpInterface;


public class DeleteTaskReq implements ServerRequest {

	int utid;
	
	public DeleteTaskReq(int utid){
		this.utid = utid;
	}

	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onDeleteTask(this);
	}

	@Override
	public String send() {
		URLCreator urler = new URLCreator();
		String req = this.accept(urler);
		return HttpInterface.sendreq(req);
	}
	
	public int getutid(){
		return utid;
	}
	

}
