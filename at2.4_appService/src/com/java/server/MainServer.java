package com.java.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import com.java.constants.JMCConstants;
import com.java.domian.Connect;
import com.java.repos.UserDao;
import com.java.utils.JMCUtils;

public class MainServer {

    private static final Logger log = Logger.getLogger(MainServer.class.getName());

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader clientReader;
    private PrintWriter clientWriter;

    private static UserDao userDao = new UserDao();

    public void startServer() throws Exception {
        log.info("Initializing connection on port:" + JMCConstants.JMC_PORT_A);
        log.info("Listening to any incoming client connection...");
        // Creating new Server Socket
        serverSocket = new ServerSocket(JMCConstants.JMC_PORT_A);

        // Handshake
        // Execution waits here for incoming connection from the client server
        // Ensure that the client is running
        clientSocket = serverSocket.accept();

        // creating admin
        Connect connect = new Connect();
        connect.setUsername(JMCConstants.ADMIN_USERNAME);
        try {
            connect.setPassword(JMCUtils.getInstance().encode(JMCConstants.ADMIN_PASSWORD));
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean adminStatus = userDao.save(connect);
        log.info("Admin availability status: " + adminStatus);

        log.info("Processing clientSocket...");
        // call login
        boolean isLoggedIn = login();
        log.info("User login status: " + isLoggedIn);
    }

    public boolean login() throws Exception {
        boolean loggedIn = false;
        clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String username = clientReader.readLine();
        String password = clientReader.readLine();

        boolean isUserExists = userDao.checkIfUserExists(username);
        clientWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        if (isUserExists) {
            Connect connect = userDao.findCredentialsByUsername(username);
            if (username.equals(connect.getUsername())
                    && password.equals(JMCUtils.getInstance().decode(connect.getPassword()))) {
                clientWriter.println("Hi, " + username);
                loggedIn = true;
            } else {
                clientWriter.println("Logon failed.");
            }
        } else if (!username.isEmpty() || !password.isEmpty()) {
            clientWriter.println("You do not have an account with us. Creating a new account...");
            Connect connect = new Connect();
            connect.setUsername(username);
            connect.setPassword(JMCUtils.getInstance().encode(password));
            boolean isNewUser = userDao.save(connect);
            log.info(username + " save status: " + isNewUser);
            clientWriter.println(username + " save status: " + isNewUser);
            loggedIn = false;
        } else {
            clientWriter.println("Invalid entries.");
        }
        clientWriter.flush();
        return loggedIn;
    }

    // Main method of DB Service must be started first before starting server's main method
    public static void main(String[] args) throws Exception {
        MainServer mainServer = new MainServer();
        mainServer.startServer();
        log.info("Server started on port: " + JMCConstants.JMC_PORT_A);
//		server.clientWriter.close();
    }
}