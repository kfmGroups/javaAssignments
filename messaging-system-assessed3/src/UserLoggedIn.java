
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class UserLoggedIn {

	ArrayList<String> usersLoggedIn = new ArrayList<String>();

	public boolean addUser(String user) {
		if (!usersLoggedIn.contains(user)) {
			usersLoggedIn.add(user);
			return true;
		} else {
			return false;
		}
	}

	public boolean contains(String user) {
		return usersLoggedIn.contains(user);
	}

	public boolean removeUser(String user) {
		return usersLoggedIn.remove(user);
	}

}
