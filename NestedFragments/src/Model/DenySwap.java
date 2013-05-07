package Model;

import com.example.newspinproj.HttpInterface;

public class DenySwap implements ServerRequest {

	int utid;
	
	public DenySwap(int utid){
		this.utid = utid;
	}
	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onDenySwap(this);
	}

	@Override
	public String send() {
		URLCreator urler = new URLCreator();
		String req = this.accept(urler);
		return HttpInterface.sendreq(req);
	}

}
