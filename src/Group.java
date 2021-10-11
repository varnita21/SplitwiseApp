import java.util.ArrayList;
import java.util.HashMap;

public class Group {

	String gName;
	HashMap<String,User> allUsers;
	ArrayList<Bill> allBills = new ArrayList<>();
	
	public Group(String gName,HashMap<String,User> allUsers) {
		this.gName = gName;
		this.allUsers = allUsers;
	}
	
	public void addBills(ArrayList<Bill> allBills) {
		this.allBills = allBills;
	}
}
