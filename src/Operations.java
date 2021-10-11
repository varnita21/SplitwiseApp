import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Operations {

	HashMap<String, User> users = new HashMap<>();
	HashMap<String, Group> groups = new HashMap<>();
	HashSet<String> groupList = new HashSet<>();
	
	public void RegisterUser(String name) {
		users.put(name, new User(name,0));
		System.out.println("Added");
	}
	
	public void createGroup(String gname) {
		groupList.add(gname);
		System.out.println("Group Added");
	}
	
	public void addUsersToGroup(String[] userList, String groupname) {
		CheckClass check = new CheckClass();
		if(!check.checkGroupValid(groupList, groupname))
			return;
		
		for(String s:userList) {
			if(!check.checkUserValid(users,s)) {
				return;
			}
			
		}
		HashMap<String,User> addUsers = new HashMap<String,User>();
		for(String s:userList) {
			addUsers.put(s,new User(s,0));
		}
		groups.put(groupname, new Group(groupname,addUsers));
		System.out.println(groups);
	}
	
	public void addBill(int partyAmount, HashMap<String, Double> hm, String gname, 
			String paidPerson) {
		Double total=0.0;
		CheckClass check = new CheckClass();
		for(Double f:hm.values()) {
			total+=f;
		}
		if(partyAmount<=0) {
			System.out.println("Wrong amount");
			return;
		}
		if(total != 100.0) {
			System.out.println("Wrong dividing");
			return;
		}
		for(String s:hm.keySet()) {
			if(!check.checkUserValid(users, s))
				return;
		}
		if(!check.checkUserValid(users, paidPerson))
			return;
		Bill b = new Bill(paidPerson,partyAmount,hm);
		for(String s:hm.keySet()) {
			double val = hm.get(s);
			double amount = (val/100)*partyAmount;
			users.put(s, new User(s,users.get(s).balance-amount));
			Group g = groups.get(gname);
			User us = g.allUsers.get(s);
			us.balance = us.balance-amount;
			g.allUsers.put(s, us);
			g.allBills.add(b);
			groups.put(gname, g);
		}
//		System.out.println("Amount : "+users.get("Mudit").balance);
		users.put(paidPerson, new User(paidPerson,users.get(paidPerson).balance+partyAmount));
		Group g = groups.get(gname);
		User us = g.allUsers.get(paidPerson);
		us.balance = us.balance+partyAmount;
		g.allUsers.put(paidPerson, us);
		g.allBills.add(b);
		groups.put(gname, g);
	}
	
	
	public static void main(String[] args) {
		Operations op = new Operations();
		op.RegisterUser("Mudit");
		op.RegisterUser("Shouvik");
		op.RegisterUser("Sourav");
		op.RegisterUser("Saloni");
		op.createGroup("Buddies");
		op.createGroup("Colleagues");
		System.out.println("map: "+op.users);
		op.addUsersToGroup(new String[]{"Shouvik","Mudit","Sourav"}, "Buddies");
		op.addUsersToGroup(new String[]{"Saloni","Mudit"}, "Colleagues");
		HashMap<String, Double> hm = new HashMap<>();
		hm.put("Mudit",83.3333);
		hm.put("Shouvik", 16.6667);
		hm.put("Sourav", (double) 0);
		op.addBill(300, hm, "Buddies", "Shouvik");
		HashMap<String, Double> hm1 = new HashMap<>();
		hm1.put("Mudit", 50.0);
		hm1.put("Saloni", 50.0);
		op.addBill(100, hm1, "Colleagues", "Mudit");
		CheckClass c = new CheckClass();
		c.printGroupWiseAmount(op.groups, "Mudit", "Buddies");
		c.printGroupWiseAmount(op.groups, "Sourav", "Buddies");
		c.printGroupWiseAmount(op.groups, "Shouvik", "Buddies");
		c.printGroupWiseAmount(op.groups, "Mudit", "Colleagues");
		c.printGroupWiseAmount(op.groups, "Saloni", "Colleagues");
		c.printTotalAmount(op.users, "Mudit");
		c.printTotalAmount(op.users, "Saloni");
	}
}
