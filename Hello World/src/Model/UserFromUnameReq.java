package Model;

public class UserFromUnameReq implements ServerRequest{

	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onUserFromNewNameReq(this);
	}

}
