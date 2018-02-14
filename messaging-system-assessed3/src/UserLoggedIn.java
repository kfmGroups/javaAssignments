package assignment;

import java.util.ArrayList;

public class UserLoggedIn {
	
	ArrayList<String> usersLoggedIn = new ArrayList<String>();
	
	public boolean addUser(String user){
		if(!usersLoggedIn.contains(user)){
			usersLoggedIn.add(user);
			return true;
		}else{
			return false;
		}
	}
	
	public boolean contains(String user){
		return usersLoggedIn.contains(user);
	}

}
