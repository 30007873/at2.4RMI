package com.java.repos;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.java.domian.Connect;

public interface UserRepository extends Remote {

	boolean save(Connect connect) throws RemoteException, Exception;

	boolean checkIfUserExists(String username) throws RemoteException, Exception;

	Connect findCredentialsByUsername(String username) throws Exception;
}
