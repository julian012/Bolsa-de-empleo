package controller;

import java.io.IOException;

import models.Server;

public class ControllerManager {
	
	private Server server;
	
	public ControllerManager() throws IOException {
		server = new Server(3011);
		server.startServer();
	}
	
	public static void main(String[] args) {
		try {
			new ControllerManager();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
