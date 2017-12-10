package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import controller.ControllerManager;


public class JFCreateAccount extends JFrame{

	private static final long serialVersionUID = 1L;
	private String type;
	private JPEmployeeAccount employeeAccount;
	private JPCompanyAccount companyAccount;
	private JScrollPane jScrollPane;
	private ControllerManager controllerManager;
	
	//Employee
	public JFCreateAccount(String email, String password, String type, ControllerManager controllerManager, String[] departmentList) {
		this.controllerManager = controllerManager;
		this.type = type;
		initComponents();
		employeeAccount = new JPEmployeeAccount(email, password, controllerManager, departmentList);
		jScrollPane = new JScrollPane(employeeAccount);
		add(jScrollPane, BorderLayout.CENTER);
		setVisible(true);
	}
	
	//Company
	public JFCreateAccount(String email, String password, ControllerManager controllerManager, String[] departmentList) {
		this.controllerManager = controllerManager;
		type = "Empresa";
		initComponents();
		companyAccount = new JPCompanyAccount(email, password, controllerManager,departmentList);
		jScrollPane = new JScrollPane(companyAccount);
		add(jScrollPane, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void initComponents() {
		setIconImage(new ImageIcon("files/imagenes/icon.png").getImage());
		addWindowListener(controllerManager);
		this.getContentPane().setBackground(Constant.BACKGROUND);
		setTitle("Nuevo " + type);
		setResizable(false);
		setSize(543, 650);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
	}
	
	public String getDepartmentEmployee() {
		return employeeAccount.getDepartment();
	}
	
	public String getDepartmentCompany() {
		return companyAccount.getDepartment();
	}
	
	public void setCititesListEmployee(String[] values) {
		employeeAccount.setCitiesList(values);
	}
	
	public void setCititesListCompany(String[] values) {
		companyAccount.setCitiesList(values);
	}
}
