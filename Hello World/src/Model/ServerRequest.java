package Model;


public interface ServerRequest {

	public String accept(ServerRequestVisitor v);
}
