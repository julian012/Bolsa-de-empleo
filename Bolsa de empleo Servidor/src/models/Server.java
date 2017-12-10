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
		departmentList = new ArrayList<>();
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
		if (inputStream.readUTF().equals(Request.DEPARTMENT_LIST.toString())) {
			sendDepartmentList(socket);
		}else if(inputStream.readUTF().equals(Request.CITY_LIST.toString())){
			String name = inputStream.readUTF();
			sendCityList(socket, name);
		}else {
			String type = inputStream.readUTF();
			String email = inputStream.readUTF();
			String password = inputStream.readUTF();
			signIn(type, email, password, socket);
		}
		inputStream.close();
	}
	
	private void sendCityList(Socket socket, String name ) throws IOException {
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		for (Department department : departmentList) {
			if(department.getName().equals(name)) {
				outputStream.writeInt(department.getCityList().size());
				for (int i = 0; i < department.getCityList().size(); i++) {
					outputStream.writeUTF(department.getCityList().get(i).getName());
				}
			}
		}
		outputStream.close();
	}
	
	private void sendDepartmentList(Socket socket) throws IOException {
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeInt(departmentList.size());
		for (int i = 0; i < departmentList.size(); i++) {
			outputStream.writeUTF(departmentList.get(i).getName());
		}
		outputStream.close();
	}

	private void signIn(String type, String email, String password,Socket socket) {
		switch (type) {
		case "SELECT_JEMPLOYEE":
			signInEmployee(email, password, socket);
			break;
		case "SELECT_JCOMPANY" :
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
	
	public ArrayList<City> getCitiesList(String name) {
		for (Department d : departmentList) {
			if (name.equals(d.getName())) {
				return d.getCityList();
			}
		}
		
		return null;
	}
	
	@Override
	public void run() {
		while(serverUp) {
			try {
				System.out.println("Esperando coneccion");
				Socket socket = serverSocket.accept();
				System.out.println("Connectado: " + socket);
				removeClosedConnection();
				initServices(socket);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
