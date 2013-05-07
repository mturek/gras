package Model;

import com.example.newspinproj.HttpInterface;

public class GetSwaps implements ServerRequest {

	
	String uname;
	public GetSwaps( String uname){
		this.uname = uname;
	}
	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onGetSwaps(this);
	}

	@Override
	public String send() {
		URLCreator urler = new URLCreator();
		String req = this.accept(urler);
		return HttpInterface.sendreq(req);
	}

}
