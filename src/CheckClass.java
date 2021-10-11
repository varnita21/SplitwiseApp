import java.util.HashMap;
import java.util.HashSet;

public class CheckClass {

	
	public boolean checkUserValid(HashMap<String, User> users, String s) {
		if(!users.containsKey(s)) {
			System.out.println("Member Doesnot exists");
			return false;
		}
		return true;
	}
	
	public boolean checkGroupValid(HashSet<String> groupList, String groupname) {
		if(!groupList.contains(groupname)) {
			System.out.println("Group Doesnot exists");
			return false;
		}
		return true;
	}
	
	public void printGroupWiseAmount(HashMap<String, Group> groups, String name,
			String groupname) {
		
		System.out.println(name+" Amount in "+groupname+" : "+groups.get(groupname).allUsers.get(name).balance);
	}
	
	public void printTotalAmount(HashMap<String, User> users, String name) {
		System.out.println(name+" Amount: "+users.get(name).balance);
	}
}
