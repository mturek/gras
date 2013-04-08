package Model;

public class UsersInGroupReq implements ServerRequest {

	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onUsersInGroupReq(this);
	}

}
