package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controller.Events;

public class JPEmployeeAccount extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField jTid;
	private JTextField jTFistName;
	private JTextField jTLastName;
	private JDateChooser birthDate;
	private JTextField jTJobTile;
	private JTextArea jTAProfessionalPorfile;
	private JTextField jTEmail;
	private JTextField jTAddress;
	private JTextField jTNumberPhone;
	private JComboBox<String> jCDepartmentList;
	private JComboBox<String> jCCitiesList;
	private JTextField jTPath;
	private JPasswordField jTPassword;
	private JPasswordField jTPasswordConfirm;
	private GridBagConstraints constraints;
	private JButton jSave;
	private String email;
	private String password;
	private JLabel jlTitle;
	
	public JPEmployeeAccount(String email, String password) {
		this.email = email;
		this.password = password;
		initComponents();
		employeeForm();
		setVisible(true);
	}
	
	public void initComponents() {
		setBackground(Constant.BACKGROUND);
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
	}
	
	public void employeeForm() {
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridheight = 1;
		constraints.gridwidth = 4;
		constraints.weightx = 1;
		constraints.ipady = 50;
		
		constraints.gridy = 1;
		jlTitle = new JLabel("Nuevo candidato", JLabel.CENTER);
		jlTitle.setOpaque(true);
		jlTitle.setBackground(Constant.COLORBLUE);
		jlTitle.setBorder(BorderFactory.createLineBorder(Constant.COLORBLUE));
		jlTitle.setFont(new Font("Arial", Font.BOLD, 30));
		jlTitle.setForeground(Color.WHITE);
		add(jlTitle, constraints);
		
		constraints.ipady = 10;
		constraints.gridy = 2;
		constraints.insets = new Insets(30, 139, 5, 139);
		jTid = new JTextField();
		jTid.setBorder(BorderFactory.createTitledBorder("Documento"));
		add(jTid, constraints);
		constraints.insets = new Insets(0, 139, 5, 139);
		constraints.gridy = 3;
		jTFistName = new JTextField();
		jTFistName.setBorder(BorderFactory.createTitledBorder("Nombres"));
		add(jTFistName, constraints);
		constraints.insets = new Insets(0, 139, 5, 139);
		constraints.gridy = 4;
		jTLastName = new JTextField();
		jTLastName.setBorder(BorderFactory.createTitledBorder("Apellidos"));
		add(jTLastName, constraints);
		constraints.insets = new Insets(0, 139, 5, 139);
		constraints.gridy = 5;
		birthDate = new JDateChooser();
		birthDate.setBorder(BorderFactory.createTitledBorder("Fecha de nacimiento"));
		add(birthDate, constraints);
		
		constraints.gridy = 6;
		jTEmail = new JTextField(email);
		jTEmail.setEnabled(false);
		jTEmail.setBorder(BorderFactory.createTitledBorder("Email"));
		add(jTEmail, constraints);
		
		constraints.gridy = 7;
		jTAddress = new JTextField();
		jTAddress.setBorder(BorderFactory.createTitledBorder("Direccion"));
		add(jTAddress, constraints);
		
		constraints.gridy = 8;
		jTNumberPhone = new JTextField();
		jTNumberPhone.setBorder(BorderFactory.createTitledBorder("Numero de telefono"));
		add(jTNumberPhone, constraints);
		
		constraints.gridy = 9;
		jCDepartmentList = new JComboBox<>();
		jCDepartmentList.setBorder(BorderFactory.createTitledBorder("Departamento"));
		add(jCDepartmentList, constraints);
		
		constraints.gridy = 10;
		jCCitiesList = new JComboBox<>();
		jCCitiesList.setBorder(BorderFactory.createTitledBorder("Ciudad"));
		add(jCCitiesList, constraints);
		
		constraints.gridy = 11;
		jTJobTile = new JTextField();
		jTJobTile.setBorder(BorderFactory.createTitledBorder("Titulo profesional"));
		add(jTJobTile, constraints);
		
		constraints.ipady = 40;
		constraints.gridy = 12;
		jTAProfessionalPorfile = new JTextArea();
		jTAProfessionalPorfile.setBorder(BorderFactory.createTitledBorder("perfil profesional"));
		JScrollPane jScrollPane = new JScrollPane(jTAProfessionalPorfile);
		add(jScrollPane, constraints);
		
		constraints.ipady = 10;
		constraints.gridy = 13;
		jTPath = new JTextField();
		jTPath.setBorder(BorderFactory.createTitledBorder("Seleccionar imagen"));
		add(jTPath, constraints);
		
		constraints.gridy = 14;
		jTPassword = new JPasswordField(password);
		jTPassword.setBorder(BorderFactory.createTitledBorder("Contraseña"));
		jTPassword.setEditable(false);
		add(jTPassword, constraints);
		
		constraints.gridy = 15;
		jTPasswordConfirm = new JPasswordField();
		jTPasswordConfirm.setBorder(BorderFactory.createTitledBorder("Confirmar contraseña"));
		add(jTPasswordConfirm, constraints);
		
		constraints.gridy = 16;
		jSave = new JButton(Constant.BUTTOM_CREATE_ACCOUNT);
		jSave.setBackground(Constant.COLOR_ORANGE);
		jSave.setForeground(Color.WHITE);
		jSave.setFont(Constant.JBUTTOM_SIGNIN);
		jSave.setBorder(BorderFactory.createLineBorder(Constant.COLOR_ORANGE));
		jSave.setActionCommand(Events.CREATE_ACCOUNT.toString());
		add(jSave, constraints);
		
	}

}
