package Model;

import java.util.ArrayList;

import com.android.test.HttpInterface;


public class URLCreator implements ServerRequestVisitor {

	String rootURL = "http://nedmonds.scripts.mit.edu/parsereq.php?";

	
	@Override
	public String onNewTask(NewTaskReq newtaskreq) {
	
		String req =  rootURL + "command=newTask" + generateURLpart(newtaskreq.fields, newtaskreq.values);
		HttpInterface.sendreq(req);
		return req;
	}

	public static String generateURLpart(ArrayList<String> fields, ArrayList<String> values){
		String ret = "";
		for(int i = 0; i < fields.size(); i++){
			if(values.get(i).equals("")){
			}
			else{
				ret = ret + "&" + fields.get(i) + "=" + values.get(i);
			}
		}
		return ret;
	}

	@Override
	public String onUserTasksRequest(UserTasksRequest u) {
		String req = rootURL + "command=getUserTasks" + generateURLpart(u.getfields(), u.getvalues());
		HttpInterface.sendreq(req);
		return req;
	}

	@Override
	public String onNewUser(NewUserReq ur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onUserFromNewNameReq(UserFromUnameReq ufnn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onTaskFromIDReq(TaskFromIDRequest tfid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onUsersInGroupReq(UsersInGroupReq uig) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
