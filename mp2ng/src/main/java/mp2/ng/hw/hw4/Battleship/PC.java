// --------------------------------------------------------
// Code generated by Papyrus Java
// --------------------------------------------------------

package mp2.ng.hw.hw4.Battleship;

import java.util.Random;

import mp2.ng.hw.hw4.Battleship.Field.AtackResult;

/************************************************************/
/**
 * 
 */
public class PC extends Player{
	int [][] trackingField = new int[10][10];
	
	PC(String name){
		this.name = name;
		field = new Field();
		field.populate();
	}
	@Override
	public Point makeShot() {
		Random random = new Random();
		Point point = new Point();
		point.y = random.nextInt(10);
		point.x = random.nextInt(10);
		trackingField[point.x][point.y] = 1;
		return point;	
	}

	@Override
	public void updateOwnView(String field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEnemyView(String field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point setResult(Field.AtackResult atackResult) {
		// TODO Auto-generated method stub
		switch(atackResult){
		case HIT:
			break;
		case MISS:
			break;
		case SANK:
			break;
		case WIN:
			break;
		default:
			break;
		}
		return null;
	}
}