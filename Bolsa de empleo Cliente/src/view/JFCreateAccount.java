package view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;


public class JFCreateAccount extends JFrame{

	private static final long serialVersionUID = 1L;
	private String type;
	private JPEmployeeAccount employeeAccount;
	
	//Employee
	public JFCreateAccount(String email, String password, String type) {
		this.type = type;
		initComponents();
		employeeAccount = new JPEmployeeAccount(email, password);
		JScrollPane jScrollPane = new JScrollPane(employeeAccount);
		add(jScrollPane, BorderLayout.CENTER);
		setVisible(true);
	}
	
	//Company
	public JFCreateAccount(String email, String password) {
		type = "Company";
		initComponents();
		setVisible(true);
	}
	
	public void initComponents() {
		this.getContentPane().setBackground(Constant.BACKGROUND);
		setTitle("Nuevo " + type);
		setSize(543, 650);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	public static void main(String[] args) {
		new JFCreateAccount("Julicamnet_012@hotmail.com", "fredonia012", "Employee");
	}
}
