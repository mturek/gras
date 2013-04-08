package Model;

public class TaskFromIDRequest implements ServerRequest {

	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onTaskFromIDReq(this);
	}

}
