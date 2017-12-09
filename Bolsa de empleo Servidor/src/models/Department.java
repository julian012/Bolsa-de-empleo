package models;

import java.util.ArrayList;

public class Department {
	
	private int id;
	private String name;
	private ArrayList<City> cityList;
	
	public Department(int id, String name, ArrayList<City> cityList) {
		this.id = id;
		this.name = name;
		this.cityList = cityList;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<City> getCityList() {
		return cityList;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", cityList=" + cityList + "]";
	}
	
	
}
