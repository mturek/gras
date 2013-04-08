package Model;

public class NewUserReq implements ServerRequest {

	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onNewUser(this);
	}

}
