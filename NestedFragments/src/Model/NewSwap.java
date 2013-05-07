package Model;

import com.example.newspinproj.HttpInterface;

public class NewSwap implements ServerRequest {

	String uname1;
	String uname2;
	int utid1;
	int utid2;
	
	public NewSwap(String uname1, String uname2, int utid1, int utid2){
		this.uname1 = uname1;
		this.uname2 = uname2;
		this.utid1 = utid1;
		this.utid2 = utid2;
	}
	
	
	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onNewSwap(this);
	}

	@Override
	public String send() {
		URLCreator urler = new URLCreator();
		String req = this.accept(urler);
		return HttpInterface.sendreq(req);
	}

}
