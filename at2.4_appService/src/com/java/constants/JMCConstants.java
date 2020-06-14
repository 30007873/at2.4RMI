package com.java.constants;

public class JMCConstants {
	public static final String JMC_HOST_A = "localhost";

	// This host is exposed by DB Service's method exportObject(Remote obj, int port) and is copied to JMC_HOST_B
	public static final String JMC_HOST_B = "192.168.1.2";
	
	public static final int JMC_PORT_A = 8080;
	public static final int JMC_PORT_B = 1099;
	
	public static final String JMC_ENCRYPTION_SALTED_KEY = "doq02bc[3bd[w2c[2bcowocb";
	public static final String JMC_ENCRYPTION_TRANSFORMATION_ALGORITHM = "DESede";
	public static final String ENCODING_UTF = "UTF8";
	
	public static final String ADMIN_USERNAME = "admin";
	public static final String ADMIN_PASSWORD = "admin";
}
