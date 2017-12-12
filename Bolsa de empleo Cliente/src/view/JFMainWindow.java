package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import models.Request;

public class JFMainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPMenuEmployee menuEmployee;
	private JPMenuCompany menuCompany;
	
	public JFMainWindow(String typeAccount, String name) {
		switch (Request.valueOf(typeAccount)) {
		case START_PROGRAM_COMPANY:
			
			break;
		case START_PROGRAM_EMPLOYEE:
			initEmployee(name);
			break;

		default:
			break;
		}
	}
	
	public void initEmployee(String name) {
		setIconImage(new ImageIcon("files/imagenes/icon.png").getImage());
		setTitle("Pagina principal Candidatos");
		setSize(1054, 535);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		menuEmployee = new JPMenuEmployee(name);
		add(menuEmployee, BorderLayout.NORTH);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new JFMainWindow(Request.START_PROGRAM_EMPLOYEE.toString(), "Julián Camilo");
	}
	

}
