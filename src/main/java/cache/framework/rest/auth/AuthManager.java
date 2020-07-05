package cache.framework.rest.auth;

import java.util.HashMap;
import java.util.Map;

public class AuthManager {
	
	private static Map<Integer, String> customerMap;
	static {
		customerMap = new HashMap<>();
		customerMap.put(1001, "abc24b");
		customerMap.put(1002, "abc25d");
		customerMap.put(1003, "abc26e");
	} 
	
	public String auth(Integer customerID) {
		String authToken = null;
		if(customerMap.containsKey(customerID)) {
			authToken = customerMap.get(customerID);
			System.out.println("token generated for customerID: " + customerID);
		}
		return authToken;
	}
}
