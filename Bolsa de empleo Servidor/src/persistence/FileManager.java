package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import models.City;
import models.Department;

public class FileManager {
	
	public static final String PATH_DEPARTMENT_LIST = "files/ColombiaCities.json";

	@SuppressWarnings("unchecked")
	public ArrayList<Department> readDepartmentJson() throws FileNotFoundException, IOException, ParseException{
		ArrayList<Department> departmentList = new ArrayList<>();
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(PATH_DEPARTMENT_LIST));
		JSONArray list = (JSONArray) jsonObject.get("Lista de despartamentos");
		Iterator<JSONObject> iterator = list.iterator();
		while(iterator.hasNext()) {
			ArrayList<City> arrayCity = new ArrayList<>();
			JSONObject info = iterator.next();
			int id = Integer.parseInt(String.valueOf(info.get("id")));
			String name = String.valueOf(info.get("departamento"));
			JSONArray cities = (JSONArray) info.get("ciudades");
			Iterator<String> citiesList = cities.iterator();
			while (citiesList.hasNext()) {
				String city = citiesList.next();
				arrayCity.add(new City(city, name));
			}
			departmentList.add(new Department(id, name, arrayCity));
		}
		return departmentList;
	}
	
	public static void main(String[] args) {
		FileManager f = new FileManager();
		try {
			f.readDepartmentJson();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
