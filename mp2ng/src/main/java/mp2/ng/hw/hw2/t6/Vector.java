package mp2.ng.hw.hw2.t6;

import javax.management.RuntimeErrorException;

public class Vector{
	Vector() {}
	Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	Vector(int[] vector) {
		set(vector);
	}
	int x;
	int y;
	public void set(int[] vector) {
		if(vector.length != 2)
			throw new RuntimeErrorException(null, "Invalid argument cout. 2 needed");
		this.x = vector[0];
		this.y = vector[1];
	}
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int cross(Vector other){
		return x*other.y - y*other.x;//a d-b c
}
}

