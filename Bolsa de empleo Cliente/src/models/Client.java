package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class Client extends Thread{
	
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private boolean connectionUp;
	private String host;
	private int port;
	private String resultConnection;
	
	public Client(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	public void signIn(String email, String password, String type) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeUTF(type);
		outputStream.writeUTF(email);
		outputStream.writeUTF(password);
		resultConnection = inputStream.readUTF();
		
	}
	
	public String getResultConnection() {
		return resultConnection;
	}
	
	public void initConnection() {
		connectionUp = true;
		start();
	}
	

	public String[] getDeparmentList() throws IOException {
		socket = new Socket(host, port);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeUTF(Request.DEPARTMENT_LIST.toString());
		String[] values = new String[inputStream.readInt()];
		for (int i = 0; i < values.length; i++) {
			values[i] = inputStream.readUTF();
		}
		return values;
	}
	
	public String[] getCitiesList(String department) throws IOException {
		socket = new Socket(host, port);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeUTF(Request.CITY_LIST.toString());
		outputStream.writeUTF(department);
		String[] values = new String[inputStream.readInt()];
		for (int i = 0; i < values.length; i++) {
			values[i] = inputStream.readUTF();
		}
		return values;
	}
	
	public void createAccountEmployee(String email, String photoPath, String password, String numberPhone,
			String address, String city, String department, int id, String firstName, String lastName,
			Date birthDate, String jobTitle, String professionalPorfile) throws IOException {
		socket = new Socket(host, port);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeUTF(Request.SEND_INFO_EMPLOYEE_ACCOUNT.toString());
		outputStream.writeUTF(email);
	}
	
	public void createAccountCompany(String email, String photoPath, String password, String numberPhone,
			String address, String city, String department, int id, String name, String description) {
		
	}
	
	@Override
	public void run() {
		while(connectionUp) {
			try {
				String option = inputStream.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
