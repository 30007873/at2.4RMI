package com.java.repos;

import com.java.domian.Connect;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This is the class of ServerDao.
 *
 * @Description: DAO refers to Data Access Object. Object of Connect.class is the DAO in this case.
 * This class would make use of object of class Connect to map its fields to respective fields in
 * the database.
 */
public class UserDao implements UserRepository {

	private static final Logger log = Logger.getLogger(UserDao.class.getName());

	// user db with table for class Connect
	public Map<String, String> map = new HashMap<String, String>();

	@Override
	public boolean save(Connect connect) throws Exception {
		if (connect != null) {
			if (!connect.getUsername().isEmpty() && !connect.getPassword().isEmpty()) {
				map.put(connect.getUsername(), connect.getPassword());
				log.info("Saved:" + connect.toString() );
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean checkIfUserExists(String username) throws Exception {
		boolean result = false;
		if (map.keySet().contains(username) && !username.isEmpty()) {
			result = true;
		} else {
			result = false;
		}

		log.info("User " + username + " exists? " + result);
		return result;
	}

	@Override
	public Connect findCredentialsByUsername(String username) throws Exception {
		Connect connect = null;
		if (checkIfUserExists(username)) {
			connect = new Connect();
			String password = map.get(username);
			connect.setUsername(username);
			connect.setPassword(password);
		}

		return connect;
	}
}