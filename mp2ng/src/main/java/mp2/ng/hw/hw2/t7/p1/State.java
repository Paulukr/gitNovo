package mp2.ng.hw.hw2.t7.p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class State extends Teritory{

	public State(String name, City capital) {
		super(name, capital, 0);
		teritories = new ArrayList<>();
	}
	public int addRegion(Region region){
		return super.addTeritory(region);
	}
	
	public void printCapital(){
		System.out.println(getCapital());
	}
	public void printQuantityOfRegions(){
		System.out.println(getQuantityOfRegions());
	}
	public int getQuantityOfRegions(){
		return teritoriesCount();
	}
	public void area(){
		System.out.println(getArea());
	}
	public void printRegions(){
		System.out.println(Arrays.toString(getTeritoriesNames()));
	}
	public void printRegionCenters(){
		System.out.println(Arrays.toString(getLocalCapitals()));
	}


}