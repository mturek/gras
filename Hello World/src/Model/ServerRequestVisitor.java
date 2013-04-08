package Model;


public interface ServerRequestVisitor {
	String onNewTask(NewTaskReq newtaskreq);
	String onUserTasksRequest(UserTasksRequest u);
}
