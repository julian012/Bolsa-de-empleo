package controller;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import models.Server;
import persistence.FileManager;

public class ControllerManager {
	
	private Server server;
	private FileManager fileManager;
	
	public ControllerManager() throws IOException, ParseException {
		fileManager = new FileManager();
		server = new Server(3011);
		server.startServer();
		server.loadInformationServerDepartment(fileManager.readDepartmentJson());
	}
	
	public static void main(String[] args) {
		try {
			new ControllerManager();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
