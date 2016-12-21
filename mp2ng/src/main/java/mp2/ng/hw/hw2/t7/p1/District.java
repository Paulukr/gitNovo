package mp2.ng.hw.hw2.t7.p1;

import java.util.ArrayList;
import java.util.List;

public class District extends Teritory{
	private List<City> cities;
	public District(String name, City capital, double area) {
		super(name, capital, area);
		cities = new ArrayList<>();
	}
	public int addCity(City city){
		cities.add(city);
		return cities.size();
	}
}