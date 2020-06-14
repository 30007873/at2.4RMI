package com.java.server;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ServerTest {

	private static final Logger log = Logger.getLogger(ServerTest.class.getName());

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private BufferedReader clientReader;
	private PrintWriter clientWriter;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setup() {
		log.info("Setting up...");

	}

	@After
	public void tearDown() {
		log.info("Tearing down...");

	}

	@BeforeClass
	public static void before() {
		log.info("Running JUnit test cases from class: " + ServerTest.class);

	}

	@AfterClass
	public static void after() {
		log.info("Testing class " + ServerTest.class + " has completed.");

	}

	public void reset() {
		tearDown();
		setup();
	}

//	@Test
//	public void listen() {
//		//        requires Mockito and Powermock
//	}
//
//	@Test
//	public void startServer() throws IOException {
//		//        requires Mockito and Powermock
//	}
//
//	@Test
//	public boolean login() throws Exception {
//		//        requires Mockito and Powermock
//	}
}