package models;

import java.util.Date;

public class NotificationCompany {
	
	private int jobOffer;
	private int idEmployee;
	private Date date;
	private StatusJobOffer offer;
	
	public NotificationCompany(int jobOffer, int idEmployee, Date date, StatusJobOffer offer) {
		this.jobOffer = jobOffer;
		this.idEmployee = idEmployee;
		this.date = date;
		this.offer = offer;
	}

	public int getJobOffer() {
		return jobOffer;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public Date getDate() {
		return date;
	}

	public StatusJobOffer getOffer() {
		return offer;
	}
	
}
