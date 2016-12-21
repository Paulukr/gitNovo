package mp2.ng.hw.hw2.t6;

@FunctionalInterface
public interface Shape{
	double area();
	default double sum(Shape other){
		return area() + other.area();
	}
}

