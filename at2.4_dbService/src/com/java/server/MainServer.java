package com.java.server;

import com.java.constants.JMCConstants;
import com.java.repos.UserDao;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class MainServer {

    private static final Logger log = Logger.getLogger(MainServer.class.getName());

    // This main method must be started first before other main methods
    public static void main(String[] args) throws UnknownHostException, IOException, AlreadyBoundException {
        UserDao userDao = new UserDao();
        Remote stub = UnicastRemoteObject.exportObject(userDao, JMCConstants.JMC_PORT_B);
        Registry registry = LocateRegistry.createRegistry(JMCConstants.JMC_PORT_B);
        registry.bind("UserRepository", stub);
        log.info("DB MainServer started on port: " + JMCConstants.JMC_PORT_B);
    }
}
