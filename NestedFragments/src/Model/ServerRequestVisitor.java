package Model;


public interface ServerRequestVisitor {
	String onNewTask(NewTaskReq newtaskreq);
	String onUserTasksRequest(UserTasksRequest u);
	String onNewUser(NewUserReq ur);
	String onUserFromNewNameReq(UserFromUnameReq ufnn);
	String onTaskFromIDReq(TaskFromIDRequest tfid);
	String onUsersInGroupReq(UsersInGroupReq uig);
	String onDeleteTask(DeleteTaskReq deleteTaskReq);
	String onGroupTasksReq(GroupTasksReq grouptasksreq);
	String onSwapRequest(SwapReq sr);
}
