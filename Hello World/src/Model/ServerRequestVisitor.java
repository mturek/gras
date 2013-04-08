package Model;


public interface ServerRequestVisitor {
	String onNewTask(NewTaskReq newtaskreq);
}
