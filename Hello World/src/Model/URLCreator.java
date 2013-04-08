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
			ret = ret + "&" + fields.get(i) + values.get(i);
		}
		return ret;
	}
	

	
}
