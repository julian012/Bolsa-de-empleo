package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
	
	private ServerSocket serverSocket;
	private ArrayList<Department> departmentList;
	private boolean serverUp;
	private ArrayList<Company> companyList;
	private ArrayList<Employee> employeeList;
	private ArrayList<JobOffer> jobOfferList;
	private ArrayList<Connection> connectionList;
	
	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		companyList = new ArrayList<>();
		employeeList = new ArrayList<>();
		jobOfferList = new ArrayList<>();
		connectionList = new ArrayList<>();
	}
	
	public void startServer() {
		serverUp = true;
		start();
	}
	
	public void loadInformationServerDepartment(ArrayList<Department> departmentList) {
		this.departmentList = departmentList;
	}
	
	public void loadInformationServerCompany(ArrayList<Company> companyList) {
		this.companyList = companyList;
	}
	
	public void loadInformationServerEmployee(ArrayList<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	public void loadInformationServerJobOffer(ArrayList<JobOffer> jobOfferList) {
		this.jobOfferList = jobOfferList;
	}
	
	private void initServices(Socket socket) throws IOException {
		DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		String type = inputStream.readUTF();
		String email = inputStream.readUTF();
		String password = inputStream.readUTF();
		signIn(type, email, password, socket);
		inputStream.close();
	}
	
	private void signIn(String type, String email, String password,Socket socket) {
		switch (type) {
		case "Employee":
			signInEmployee(email, password, socket);
			break;
		case "Company" :
			singInCompany(email, password, socket);
			break;
		default:
			break;
		}
	}
	
	private void singInCompany(String email, String password, Socket socket) {
		for (Company company : companyList) {
			if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
				try {
					connectionList.add(new Connection(socket, this, company));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		wrongPassword(socket);
	}
	
	private void signInEmployee(String email, String password, Socket socket) {
		for (Employee e : employeeList) {
			if (e.getEmail().equals(email) && e.getPassword().equals(password)) {
				try {
					connectionList.add(new Connection(socket, this, e));
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}
			}
		}
		wrongPassword(socket);
	} 
	
	private void wrongPassword(Socket socket) {
		DataOutputStream outputStream;
		try {
			outputStream = new DataOutputStream(socket.getOutputStream());
			outputStream.writeUTF(Request.WRONG_INFO.toString());
			outputStream.close();
			socket.close();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	private void removeClosedConnection() {
		for (int i = 0; i < connectionList.size(); i++) {
			if (!connectionList.get(i).isConnectionUp()) {
				companyList.remove(i);
				i--;
			}
		}
	}
	
	@Override
	public void run() {
		while(serverUp) {
			try {
				Socket socket = serverSocket.accept();
				removeClosedConnection();
				initServices(socket);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
