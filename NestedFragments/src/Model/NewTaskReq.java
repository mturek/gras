package Model;

import java.util.ArrayList;

import com.example.newspinproj.HttpInterface;


public class NewTaskReq implements ServerRequest {

	ArrayList<String> fields;
	ArrayList<String> values;
	
	public NewTaskReq(ArrayList<String> fields, ArrayList<String> values){
		this.fields = fields;
		this.values = values;
	}

	@Override
	public String accept(ServerRequestVisitor v) {
		return v.onNewTask(this);
	}

	@Override
	public String send() {
		URLCreator urler = new URLCreator();
		String req = this.accept(urler);
		return HttpInterface.sendreq(req);
	}
	
	

}
