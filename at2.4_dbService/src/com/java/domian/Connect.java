package com.java.domian;

import java.io.Serializable;

public class Connect implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2968525936727984251L;

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Connect{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
