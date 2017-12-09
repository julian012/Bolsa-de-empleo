package models;

import java.util.Stack;

public class Company extends Account{

	private int idCompany;
	private String nameCompany;
	private String description;
	private Stack<NotificationCompany> notificationList;
	public Company(String email, String password, String namePhoto, String numberPhone, String address, City city,
			int idCompany, String nameCompany, String description, Stack<NotificationCompany> notificationList) {
		super(email, password, namePhoto, numberPhone, address, city);
		setIdCompany(idCompany);
		setNameCompany(nameCompany);
		setDescription(description);
		setNotificationList(notificationList);
	}
	
	public int getIdCompany() {
		return idCompany;
	}
	
	private void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}
	
	public String getNameCompany() {
		return nameCompany;
	}
	
	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Stack<NotificationCompany> getNotificationList() {
		return notificationList;
	}
	
	public void setNotificationList(Stack<NotificationCompany> notificationList) {
		this.notificationList = notificationList;
	}
}
