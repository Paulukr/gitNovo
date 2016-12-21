package mp2.ng.hw.hw2.t7.p1;

import java.util.List;

abstract class Teritory{
	protected String name;
	protected City capital;
	protected double area;
	protected List<Teritory> teritories;
	protected Teritory(String name, City capital, double area) {
		this.name = name;
		this.capital = capital;
		this.area = area;
	}
	protected 	int addTeritory(Teritory teritory){
		teritories.add(teritory);
		area += teritory.area;
		return teritories.size();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public City getCapital() {
		return capital;
	}
	public String getCapitalName() {
		return capital.toString();
	}
	public double getArea() {
		return area;
	}
	public int teritoriesCount() {
		return teritories.size();
	}
	public String[] getTeritoriesNames() {
		if(teritories == null)
			return new String[0];
		String[] teritoriesList = new String[teritories.size()];
		int i  = 0;
		for (Teritory teritory : teritories) {
			teritoriesList[i++] = teritory.toString();
		}
		return teritoriesList;
	}
	public String[] getLocalCapitals() {
		if(teritories == null)
			return new String[0];
		String[] capitalsList = new String[teritories.size()];
		int i  = 0;
		for (Teritory teritory : teritories) {
			capitalsList[i++] = teritory.capital.toString();
		}
		return capitalsList;
	}

	public int getLocalsCount(){
		return teritories.size();
	}
	@Override
	public String toString(){
		return name;
	}
}