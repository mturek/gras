package Model;

import java.util.ArrayList;
import java.util.Collections;

public class DataContainer {

	public static String username;
	static ArrayList<User> users;
	static ArrayList<TaskProto> taskprotos;
	static ArrayList<Task> tasks;
	static ArrayList<Group> groups;
	static ArrayList<Swap> swaps;
	
	public DataContainer() {
		users = new ArrayList<User>();
		taskprotos = new ArrayList<TaskProto>();
		tasks = new ArrayList<Task>();
		swaps = new ArrayList<Swap>();

		groups = new ArrayList<Group>();
		String[] groupNames = { "No6", "SH", "21W789" };

		for (String groupName : groupNames)
			(new UsersInGroupReq(groupName)).send();
	}

	public static void recieveMessage(String returnstring) {
		String[] breakcommand = returnstring.split("COMMANDEND");
		if (breakcommand[0].equals("tasklist")) {
			if (breakcommand.length == 1) {

			} else {
				DataContainer.recieveTaskList(breakcommand[1]);
			}
		} else if (breakcommand[0].equals("groupinfo")) {
			System.out.println("Break command[1]: " + breakcommand[1]);
			String[] breakname = breakcommand[1].split("GNAME");
			DataContainer.recieveGroupInfo(breakname[1], breakname[0]);
		}
		else{
			DataContainer.recieveSwaps(breakcommand[0]);
		}

	}

	private static void recieveSwaps(String string) {
		String[] first = string.split("|");
		for( String swap : first){
			String[] second = swap.split(",");
			Swap newswap = new Swap(second[0], second[1], Integer.parseInt(second[2]), second[3]);
			swaps.add(newswap);
		}
		
	}
	

	public static void recieveTaskList(String tl) {
		String[] first = tl.split("\\|");
		for (int i = 0; i < first.length; i++) {
			String thing = first[i];
			String[] stuffs = thing.split("\\.");
			// System.out.println(stuffs.length);

			if (stuffs.length == 5) {

				boolean notthere = true;
				for (int j = 0; j < tasks.size(); j++) {

					if (stuffs[0].equals(tasks.get(j).getutid())) {
						notthere = false;

					}
				}
				if (notthere) {
					Task res = new Task(Integer.parseInt(stuffs[0]), stuffs[1],
							stuffs[2], stuffs[3], stuffs[4]);
					// System.out.println("got a task");
					tasks.add(res);
				}

			} else {
			}
		}
		DataContainer.sortTasks();
	}

	public static Task taskbyutid(int utid) {
		for (Task t : tasks) {
			if (t.utid == utid)
				return t;
		}
		return null;

	}

	public static boolean userExists(String username) {
		for (int i = 0; i < DataContainer.users.size(); i++) {
			if (DataContainer.users.get(i).getname().equals(username))
				return true;
		}
		return false;
	}

	public static String getFullnameForUser(String username) {
		for (int i = 0; i < DataContainer.users.size(); i++) {
			if (DataContainer.users.get(i).getname().equals(username))
				return DataContainer.users.get(i).getFullname();
		}
		return null;
	}

	public static void recieveGroupInfo(String ginfo, String gname) {
		System.out.println("groupInfo: " + ginfo);
		String[] parts1 = ginfo.split("LEADERS:");
		String[] parts2 = parts1[0].substring(1).split("\\|");
		String[] parts3 = parts1[1].substring(1).split("\\|");

		ArrayList<User> groupusers = new ArrayList<User>();
		ArrayList<User> groupleaders = new ArrayList<User>();

		for (int i = 0; i < parts2.length; i++) {
			String[] userparts = parts2[i].split(",");
			if (userparts.length == 4) {
				User newuser = new User(userparts[0], userparts[1],
						userparts[2], Integer.parseInt(userparts[3]));
				DataContainer.addUser(newuser);
				groupleaders.add(newuser);
			} else {
				System.out.print("ERROR");
			}
		}
		for (int i = 0; i < parts3.length; i++) {
			String[] userparts = parts3[i].split(",");
			if (userparts.length == 4) {
				User newuser = new User(userparts[0], userparts[1],
						userparts[2], Integer.parseInt(userparts[3]));
				DataContainer.addUser(newuser);
				groupusers.add(newuser);
			} else {
				System.out.print("ERROR");
			}
		}

		Group newgroup = new Group(gname, groupleaders, groupusers);
		DataContainer.groups.add(newgroup);

	}

	public static void removeTask(int utid) {
		for (int i = 0; i < tasks.size(); i++)
			if (tasks.get(i).utid == utid) {
				tasks.remove(i);
				break;
			}
	}

	public static ArrayList<Task> getTasksByUname(String uname) {
		ArrayList<Task> ret = new ArrayList<Task>();
		for (Task t : tasks) {
			if (t.getuser().equals(uname)) {
				ret.add(t);
			}
		}
		return ret;
	}

	public static void recieveUserList(String ul) {

	}

	public static ArrayList<Task> getTasks() {
		return tasks;
	}

	public static void addUser(User u) {
		users.add(u);
	}

	public static void addTaskProto(TaskProto tp) {
		taskprotos.add(tp);
	}

	public static void addTask(Task t) {
		tasks.add(t);
	}

	public static ArrayList<Task> getListByDay(String day) {
		ArrayList<Task> stuffs = new ArrayList<Task>();
		for (Task t : tasks) {
			if (t.getTime().contains(day)) {
				stuffs.add(t);
			}
		}
		return stuffs;
	}
	
	public static void removeSwap(int utid){
		for(Swap swap : swaps){
			if(swap.utid == utid){
				swaps.remove(swap);
			}
		}
	}

	public static ArrayList<Task> getTasksByGroup(String group) {
		ArrayList<Task> ret = new ArrayList<Task>();
		if (group.equals("All groups")) {
			System.out.println("We are in allgroups");
			Collections.sort(DataContainer.tasks);
			return tasks;
		}
		for (Task t : tasks) {
			if (t.getGroup().equals(group)) {
				ret.add(t);
			}
		}
		return ret;
	}

	public static ArrayList<Task> getTasksbyGroupandDay(String group, String day) {
		ArrayList<Task> ret = new ArrayList<Task>();
		for (Task t : tasks) {
			if (t.getTime().contains(day)
					&& (t.getGroup().equals(group) || group
							.equals("All groups"))) {
				ret.add(t);
			}
		}
		return ret;
	}

	public static ArrayList<String> getFullnames(String groupName) {
		ArrayList<String> fullnames = new ArrayList<String>();

		for (Group group : groups) {
			if (groupName.equals("All groups")
					|| group.getName().equals(groupName))
				fullnames.addAll(group.getFullnames());
		}

		return fullnames;
	}

	public static void sortTasks() {
		Collections.sort(DataContainer.tasks);
	}

	public static ArrayList<String> getFullLeaderNames(String groupName) {
		ArrayList<String> fullnames = new ArrayList<String>();

		for (Group group : groups) {
			if (groupName.equals("All groups")
					|| group.getName().equals(groupName))
				fullnames.addAll(group.getFullLeaderNames());
		}

		return fullnames;
	}

	public static ArrayList<String> getLedGroups(String uname) {
		ArrayList<String> gnames = new ArrayList<String>();
		for (Group group : groups) {
			ArrayList<User> leads = group.getLeaders();
			for (User leader : leads) {
				if (leader.getname().equals(uname)) {
					gnames.add(leader.getname());
				}
			}
		}
		return gnames;
	}
}
