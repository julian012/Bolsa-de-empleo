package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPMenuEmployee extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton jBHome;
	private JButton jBApplication;
	private JButton jBSearchJob;
	private JButton jBNotification;
	private JButton JBCurriculum;
	private String nameUser;
	private GridBagConstraints constraints;
	private JPHeader jpHeader;
	
	public JPMenuEmployee(String nameUser) {
		this.nameUser = nameUser;
		constraints = new GridBagConstraints();
		setLayout(new GridBagLayout());
		initHeader();
	}
	
	public void initHeader() {
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridheight = 1;
		constraints.gridwidth = 5;
		constraints.weightx = 1;
		constraints.ipady = 80;
		constraints.gridy = 1;
		constraints.gridx = 1;
		jpHeader = new JPHeader(nameUser, null);
		add(jpHeader, constraints);
		
	}
	
}
