package com.java.client;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.logging.Logger;

public class ClientTest {

	private static final Logger log = Logger.getLogger(ClientTest.class.getName());

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
		log.info("Running JUnit test cases from class: " + ClientTest.class);

	}

	@AfterClass
	public static void after() {
		log.info("Testing class " + ClientTest.class + " has completed.");

	}

	public void reset() {
		tearDown();
		setup();
	}

//	@Test
//	public void startClientTest() throws IOException {
//		// requires Mockito and Powermock
//	}
}