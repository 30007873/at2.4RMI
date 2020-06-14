package com.java.repos;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.java.constants.JMCConstants;
import com.java.domian.Connect;

public class UserDao implements UserRepository {

	private static final Logger log = Logger.getLogger(UserDao.class.getName());

	@Override
	public boolean save(Connect connect) throws Exception {
		try {
			Registry registry = LocateRegistry.getRegistry(JMCConstants.JMC_HOST_B);
			UserRepository stub = (UserRepository) registry.lookup("UserRepository");
			return stub.save(connect);
		} catch (RemoteException | NotBoundException e) {
			log.info("Client exception: " + e.getMessage() + " :: " + Level.SEVERE );
		}
		return false;
	}
	
	@Override
	public boolean checkIfUserExists(String username) throws Exception {
		try {
			Registry registry = LocateRegistry.getRegistry(JMCConstants.JMC_HOST_B);
			UserRepository stub = (UserRepository) registry.lookup("UserRepository");
			return stub.checkIfUserExists(username);
		} catch (RemoteException | NotBoundException e) {
			log.info("Client exception: " + e.getMessage() + " :: " + Level.SEVERE );
		}
		return false;
	}
	
	@Override
	public Connect findCredentialsByUsername(String username) throws Exception {
		try {
			Registry registry = LocateRegistry.getRegistry(JMCConstants.JMC_HOST_B);
			UserRepository stub = (UserRepository) registry.lookup("UserRepository");
			return (Connect) stub.findCredentialsByUsername(username);
		} catch (RemoteException | NotBoundException e) {
			log.info("Client exception: " + e.getMessage() + " :: " + Level.SEVERE );
		}
		return null;
	}
}
