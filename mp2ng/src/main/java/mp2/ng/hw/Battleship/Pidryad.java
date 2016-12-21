package mp2.ng.hw.Battleship;

import java.util.Arrays;
import java.util.Random;

public class Pidryad {

	public static void main(String[] args) {
		int prob = 100000;
//		totalResult(prob);
		
		for (int i = 0; i < 12; i++) {
			System.out.println(minResult(prob));
		}

	}
	
	public static double totalResult(int	prob) {
		Random r = new Random();

		int units = 100;
		int clusters = 100;
		int[] clusterResults = new int[clusters];
		for (int clusterNo = 0; clusterNo < clusters; clusterNo++) {
			int attempts = 0;
			for (int unitNo = 0; unitNo < units; unitNo++) {
				while(0 != r.nextInt(prob))
					attempts++;
			}
			
			clusterResults[clusterNo] = attempts/units;
		}
		System.out.println(Arrays.toString(clusterResults));
		double clusterAverage = Arrays.stream(clusterResults).average().getAsDouble();
		return clusterAverage;
	}
	public static double minResult(int	prob) {
		Random r = new Random();
		int units = 100;
		int clusters = 10;
		int[] clusterResults = new int[clusters];
		for (int clusterNo = 0; clusterNo < clusters; clusterNo++) {
			int minAttempts = Integer.MAX_VALUE;
			for (int unitNo = 0; unitNo < units; unitNo++) {
				int attempts = 0;
				while(0 != r.nextInt(prob))
					attempts++;
				if(minAttempts > attempts) minAttempts = attempts;
			}
			
			clusterResults[clusterNo] = minAttempts;
		}
//		System.out.println(Arrays.toString(clusterResults));
		double clusterAverage = Arrays.stream(clusterResults).average().getAsDouble();
		return clusterAverage;
	}
}
