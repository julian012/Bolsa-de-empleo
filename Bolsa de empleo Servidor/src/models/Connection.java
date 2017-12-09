package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread{
	
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private boolean connectionUp;
	private Server server;
	private Employee employee;
	private Company company;
	
	public Connection(Socket socket, Server server, Employee employee) throws IOException {
		this.socket = socket;
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		connectionUp = true;
		this.server = server;
		this.employee = employee;
		start();
	}
	
	public Connection(Socket socket, Server server, Company company) throws IOException {
		this.socket = socket;
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		connectionUp = true;
		this.server = server;
		this.company = company;
		start();
	}
	
	public boolean isConnectionUp() {
		return connectionUp;
	}
	
	@Override
	public void run() {
		while(connectionUp) {
			
		}
	}
	
}
