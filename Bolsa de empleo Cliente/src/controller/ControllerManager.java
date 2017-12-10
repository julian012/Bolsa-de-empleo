package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import models.Client;
import view.JDSignIn;

public class ControllerManager implements ActionListener{
	
	private JDSignIn login;
	private Client client;
	
	public ControllerManager() {
		login = new JDSignIn(this);
	}
	
	public void initClient() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream("config.init"));
		String host = properties.getProperty("host");
		int port = Integer.parseInt((properties.getProperty("port")));
		client = new Client(host, port);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		login.setSelectedButtom(e.getActionCommand());
	}
	
	public static void main(String[] args) {
		new ControllerManager();
	}
}
