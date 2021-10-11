import java.util.HashMap;

public class Bill {

	String personPaid;
	int amount;
	HashMap<String, Double> split = new HashMap<>();
	
	//should I create a function to add all the members in hashmap one by one or 
	//should do it in operations and add full map in constructor
	public Bill(String p, int a, HashMap<String,Double> hm) {
		personPaid = p;
		amount = a;
		split = hm;
	}
}
