package com.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.java.constants.JMCConstants;

public class MainClient {

	private static final Logger log = Logger.getLogger(MainClient.class.getName());

	private Socket clientSocket;
	private BufferedReader serverReader;
	private PrintWriter serverWriter;

	public void startClient() throws UnknownHostException, IOException, SocketException {
		clientSocket = new Socket(JMCConstants.JMC_HOST_A, JMCConstants.JMC_PORT_A);

		serverWriter = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

		String username = JOptionPane.showInputDialog(null, "Enter username:");
		serverWriter.println(username);
		String password = JOptionPane.showInputDialog(null, "Enter password");
		serverWriter.println(password);
		serverWriter.flush();
	}

	public void listen() throws IOException {
		// client will have to keep running instead of shutting off after sending
		// the username and password (credentials)
		// because the server would send response regarding the credentials passed to it
		// and hence, this method is a server listener on the client's side
		while(true){
			serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String responseFromServer = serverReader.readLine();
			JOptionPane.showMessageDialog(null, responseFromServer);
		}
	}

	// Main method in MainServer.java must be started first before starting Client's main method
	public static void main(String[] args) throws UnknownHostException, IOException {
		MainClient mainClient = new MainClient();
		mainClient.startClient();
		log.info("Client started on port: " + JMCConstants.JMC_PORT_A);

		try {
			mainClient.listen();
		} catch (Exception e) {
			log.info(e.getMessage() + " :: " + Level.SEVERE);
			mainClient.serverWriter.close();
			mainClient.serverReader.close();
		}
	}
}