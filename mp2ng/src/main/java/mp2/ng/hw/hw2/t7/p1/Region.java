package mp2.ng.hw.hw2.t7.p1;

import java.util.ArrayList;

public class Region extends Teritory{
	public Region(String name, District district) {
		super(name, district.capital, 0);
		teritories = new ArrayList<>();
		addTeritory(district);
	}
	public int addDistrict(District district){
		return super.addTeritory(district);
	}
}