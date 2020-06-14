package com.java.repos;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.logging.Logger;

public class UserDaoTest {

    private static final Logger log = Logger.getLogger(UserDaoTest.class.getName());

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
        log.info("Running JUnit test cases from class: " + UserDaoTest.class);

    }

    @AfterClass
    public static void after() {
        log.info("Testing class " + UserDaoTest.class + " has completed.");

    }

    public void reset() {
        tearDown();
        setup();
    }

//    @Test
//    public void save() throws Exception {
//          requires Mockito and Powermock
//        reset();
//    }
//
//    @Test
//    public void checkIfUserExists() throws Exception {
//          requires Mockito and Powermock
//        reset();
//    }

//    @Test
//    public void findCredentialsByUsername() throws Exception {
//        requires Mockito and Powermock
//        reset();
//    }
}
