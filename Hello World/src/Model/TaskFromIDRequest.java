package Model;

import java.util.ArrayList;

import com.android.test.HttpInterface;

public class TaskFromIDRequest implements ServerRequest {
	
	ArrayList<String> fields;
	ArrayList<String> values;
	public TaskFromIDRequest(Integer id){
		fields = new ArrayList<String>();
		fields.add("utid");
		values = new ArrayList<String>();
		values.add(id.toString());
		
	}

	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onTaskFromIDReq(this);
	}

	@Override
	public String send() {
		URLCreator urler = new URLCreator();
		String req = this.accept(urler);
		return HttpInterface.sendreq(req);
	}

	public ArrayList<String> getfields() {
		return values;
	}

	public ArrayList<String> getValues() {
		return fields;
	}

}
