package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import models.Client;
import models.Request;
import view.JDSignIn;

public class ControllerManager implements ActionListener{
	
	private JDSignIn login;
	private Client client;
	
	public ControllerManager() throws FileNotFoundException, IOException {
		login = new JDSignIn(this);
		initClient();
	}
	
	public void initClient() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream("config.init"));
		String host = properties.getProperty("host");
		int port = Integer.parseInt((properties.getProperty("port")));
		client = new Client(host, port);
	}
	
	public void singIn() {
		
		String type = login.getSelectedButtom();
		String email = login.getEmail();
		String password = new String(login.getPassword());
		if (!type.equals(Events.SELECT_CREATEACCOUNT.toString())) {
			try {
				client.signIn(email, password, type);
				Thread.sleep(2000);
				validateResponse();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			newAccount();
		}
	}
	
	public void newAccount() {
		String[] options = {"Candidato","Compañia"};
		Image image = new ImageIcon("files/imagenes/crear_cuenta.png").getImage();
		int option = JOptionPane.showOptionDialog(login, "Seleccione el tipo de cuenta que desea crear", "Crear cuenta", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_DEFAULT)), options, options[0]);
		if (option >= 0) {
			System.out.println(options[option]);
		}
	}
	
	public void validateResponse() {
		if (client.getResultConnection().equals(Request.WRONG_INFO.toString())) {
			JOptionPane.showMessageDialog(login, "Email o contraeña incorrecta");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Events.valueOf(e.getActionCommand())) {
		case SIGN_IN:
			singIn();
			break;

		default:
			break;
		}
	}
	
	public static void main(String[] args) {
		try {
			new ControllerManager();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
