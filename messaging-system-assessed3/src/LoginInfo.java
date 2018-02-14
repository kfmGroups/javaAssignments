package assignment;
import java.util.HashMap;

public class LoginInfo {
	
	private HashMap<String, String> passwords = new HashMap<String, String>();
	
	public boolean registerUser(String userName, String password){
		if(passwords.containsKey(userName)){
			return false;
		}else{
			passwords.put(userName, password);
			return true;
		}
	} 
	
	public boolean isPasswordCorrect(String userName, String passwordEntered){
		String passwordExist = passwords.get(userName);
		return passwordExist != null && passwordEntered.equals(passwordExist);
	}
	
}
