
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

	private static final String SAVE_PATH_SERVER = "userStatusInServer.txt";

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
		for (String user : usersLoggedIn) {
			writer.println(user);
		}

		writer.close();
	}

	public void loadInServer() {
		try {
			File f = new File(SAVE_PATH_SERVER);
			if (!f.exists() || f.isDirectory()) {
				return;
			}
			usersLoggedIn.clear();
			BufferedReader br = new BufferedReader(new FileReader(SAVE_PATH_SERVER));

			while (true) {
				String user = br.readLine();

				if (user == null) {
					break;
				}
				usersLoggedIn.add(user);
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
