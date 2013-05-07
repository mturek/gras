package Model;

import java.util.ArrayList;

import com.example.newspinproj.HttpInterface;


public class URLCreator implements ServerRequestVisitor {

	String rootURL = "http://nedmonds.scripts.mit.edu/parsereq.php?";

	

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
	public String onNewTask(NewTaskReq newtaskreq) {
	
		String req =  rootURL + "command=newTask" + generateURLpart(newtaskreq.fields, newtaskreq.values);
		System.out.println(req);
		HttpInterface.sendreq(req);
		return req;
	}
	
	@Override
	public String onUserTasksRequest(UserTasksRequest u) {
		String req = rootURL + "command=getUserTasks" + generateURLpart(u.getfields(), u.getvalues());
		HttpInterface.sendreq(req);
		return req;
	}

	@Override
	public String onNewUser(NewUserReq ur) {
		String req = rootURL + "command=newUser" + generateURLpart(ur.getfields(), ur.getValues());
		HttpInterface.sendreq(req);
		return req;
	}

	@Override
	public String onUserFromNewNameReq(UserFromUnameReq ufnn) {
		String req = rootURL + "command=userfromuname" + generateURLpart(ufnn.getfields(), ufnn.getValues());
		HttpInterface.sendreq(req);
		return req;
	}

	@Override
	public String onTaskFromIDReq(TaskFromIDRequest tfid) {
		String req = rootURL + "command=taskfromID" + generateURLpart(tfid.getfields(), tfid.getValues());
		HttpInterface.sendreq(req);
		return req;
	}

	@Override
	public String onUsersInGroupReq(UsersInGroupReq uig) {
		String req = rootURL + "command=getgroupinfo" + "&gname=" + uig.getGname();
		HttpInterface.sendreq(req);
		return req;
	}



	@Override
	public String onDeleteTask(DeleteTaskReq dtr) {
		String req = rootURL + "command=deletetask" + "&utid=" +  dtr.utid;
		HttpInterface.sendreq(req);
		return req;
	}



	@Override
	public String onGroupTasksReq(GroupTasksReq grouptasksreq) {
		String req = rootURL + "command=grouptasksreq" + "&gname=" + grouptasksreq.getGname();
		HttpInterface.sendreq(req);
		return req;
	}
	

	

	
}
