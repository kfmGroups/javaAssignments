
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class LoginInfo {

	private static final String SAVE_PATH_SERVER = "RegisterdUsersInServer.txt";

	private HashMap<String, String> passwords = new HashMap<String, String>();

	public boolean registerUser(String userName, String password) {
		if (passwords.containsKey(userName)) {
			return false;
		} else {
			passwords.put(userName, password);
			return true;
		}
	}

	public boolean isPasswordCorrect(String userName, String passwordEntered) {
		String passwordExist = passwords.get(userName);
		return passwordExist != null && passwordEntered.equals(passwordExist);
	}

	public void saveInServer() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(SAVE_PATH_SERVER, "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return;
		}
		for (String user : passwords.keySet()) {
			writer.println(user);
			writer.println(passwords.get(user));
		}

		writer.close();
	}

	public void loadInServer() {
		try {
			File f = new File(SAVE_PATH_SERVER);
			if (!f.exists() || f.isDirectory()) {
				return;
			}
			passwords.clear();
			BufferedReader br = new BufferedReader(new FileReader(SAVE_PATH_SERVER));

			while (true) {
				String user = br.readLine();
				String password = br.readLine();
				if (user == null || password == null) {
					break;
				}
				passwords.put(user, password);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
