package com.java.repos;

import com.java.domian.Connect;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerDaoTest {

	private static final Logger log = Logger.getLogger(ServerDaoTest.class.getName());

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
		log.info("Running JUnit test cases from class: " + ServerDaoTest.class);

	}

	@AfterClass
	public static void after() {
		log.info("Testing class " + ServerDaoTest.class + " has completed.");

	}

	public void reset() {
		tearDown();
		setup();
	}

	@Test
	public void save() throws Exception {
		UserDao serverDao = new UserDao();
		Connect connect = new Connect();
		connect.setUsername("test1");
		connect.setPassword("password1");
		boolean isSaved = serverDao.save(connect);
		assertEquals(true, isSaved);
		Map<String, String> map = serverDao.map;
		assertEquals(new ArrayList<String>(map.keySet()).get(0), "test1");
		assertEquals(new ArrayList<String>(map.values()).get(0), "password1");

		expectedException.expect(NullPointerException.class);
		connect = new Connect();
		connect.setUsername("test1");
		isSaved = serverDao.save(connect);
		assertEquals(false, isSaved);

		reset();
	}

	@Test
	public void checkIfUserExists() throws Exception {
		UserDao serverDao = new UserDao();
		assertTrue(!serverDao.checkIfUserExists("sample1"));
		assertTrue(!serverDao.checkIfUserExists(null));
		serverDao.map.put("sample1", "samplePassword1");
		assertTrue(serverDao.checkIfUserExists("sample1"));

		reset();
	}

	@Test
	public void findCredentialsByUsername() throws Exception {
		UserDao serverDao = new UserDao();
		serverDao.map.put("sample1", "samplePassword1");
		Connect connect = serverDao.findCredentialsByUsername("sample1");
		assertEquals(connect.getUsername(), "sample1");
		assertEquals(connect.getPassword(), "samplePassword1");

		connect = serverDao.findCredentialsByUsername(null);
		assertTrue(connect == null);

		reset();
	}
}