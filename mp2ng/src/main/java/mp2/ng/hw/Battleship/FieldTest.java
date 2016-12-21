package mp2.ng.hw.Battleship;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

import javax.management.RuntimeErrorException;

import org.junit.Before;
import org.junit.Test;

public class FieldTest {
	Field field = new Field();

	@Before
	public void setUp() throws Exception {


	}

	@Test
	public void testPopulate() {
		int maxTrials = 50;
		long[] totalFails = new long[maxTrials];
		int[] totalTrials = new int[maxTrials];
		for( int k = 0; k < 12; k++){		
			for( int j = 0; j < maxTrials; j++){
				Field.setTrialsAllowed(j);
				for(int i = 0; i < 1000; i++){
					field = new Field();
					try{
						field.populate();
					}catch(Exception e){	
						totalFails[j] ++;
					}
				}
				totalTrials[j] += Field.getTotalTrialsOccured();
				Field.resetTotalTrialsOccured();
			}
		}
//		System.out.println("\n totalFails");
//		Arrays.stream(totalFails).forEach(n -> System.out.print(n / (12)+" "));
//		
//		System.out.println("\n totalTrials");
//		Arrays.stream(totalTrials).forEach(n -> System.out.print(n / (12)+" "));
//		
//		System.out.println("\n max add trials " + Field.getTrialsAddBrickCount()
//		+ " " + (double)Field.getTrialsAddBrickSum()/Field.getTrialsAddBrickCount());
		
		double stepSum = 0;
		for (int i = 1; i < totalFails.length; i++) {
			if(totalFails[i+1] == 0){
//				System.out.println(i + " " + stepSum/i);
				assertEquals(0.54, stepSum/i, 0.1);//1.8^32 ~ 2e9  //0.54^32 ~ 2e-9 
				break; 
			}
			stepSum += (double)totalFails[i+1]/totalFails[i];
		}
		
//		System.out.println(Arrays.toString(totalSucceed));
//		System.out.println(field);
//		System.out.println("max trials " + Field.getTrialsOccured());
		//IntStream.range(0, maxTrials).forEach(i -> System.out.print(totalTrials[i] / totalSucceed[i]));
	}

	@Test
	public void testPlaceShip() {
		//System.out.println("testPlaceShip");
		field.placeShip(1);
		//System.out.println("the 1st ship");
		System.out.println("field.placeShip(1) " + field);
		assertEquals(1, field.countShipedCells());
		//System.out.println(field);
		for (int i = 0; i < 5; i++) {
			field.placeShip(1);
		}
		assertEquals(6, field.countShipedCells());
		//System.out.println("5 more ships");
		//System.out.println(field);
		
	
		for (int i = 0; i < 1; i++) {
			field.placeShip(4);
		}
		//System.out.println("another one");
		//System.out.println(field);
		assertEquals(10, field.countShipedCells());
		assertEquals(true, field.checkNeighbourhood());
		
//		//System.out.println(field);
	}

	@Test
	public void testTryAddBrick() {
//		//System.out.println("testTryAddBrick");
//		//System.out.println(field);
		int shipSize = 4;
		Deque<Cell> probableShip = new ArrayDeque<>();
		Ship ship = new Ship(shipSize);

		NextPosition:
			for(int trials = 0; trials < 15; trials++){
				//System.out.println("brick add\n   trial No " + trials);
				//recover after failed trial
				probableShip.forEach((e) -> e.removeShip());
				probableShip.clear();

				for(int bricksPlaced = 0; bricksPlaced < shipSize; ){
					if (field.tryAddBrick(ship)){
						bricksPlaced++;
//					//System.out.println("brick added");
//					//System.out.println(field);
					}
					else 
						continue NextPosition;
				}// if all bricks have been placed then enough trials
				break NextPosition; 
			}						
		if(probableShip.size() < shipSize){
			probableShip.forEach((e) -> e.removeShip());
			return;
			//throw new RuntimeErrorException(new Error("Ship wasn't placed"));
		}
		assertEquals(shipSize, probableShip.size());
		probableShip.forEach((e) -> e.setShip(new Ship(4)));

		assertEquals(shipSize, ship.getSize());
	}

	@Test
	public void testSurroundShip() {
//		field = new Field();
//		field.placeShip(1);
//		//System.out.println("Suroound 1\n" + field);
//		assertEquals(8, field.countSurroundCells());
//
//		
//		field = new Field();
//		field.placeShip(2);
//		//System.out.println("Suroound 1\n" + field);
//		assertEquals(10, field.countSurroundCells());
//
//		
//		field = new Field();
//		field.placeShip(3);
//		//System.out.println("Suroound 1\n" + field);
//		assertEquals(12, field.countSurroundCells());
//
//		
//		field = new Field();
//		field.placeShip(4);
//		//System.out.println("Suroound 1\n" + field);
//		assertEquals(14, field.countSurroundCells());
	}

	@Test
	public void testToString()  {

	}

}
