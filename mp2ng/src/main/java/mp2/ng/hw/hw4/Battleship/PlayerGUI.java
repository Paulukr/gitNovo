package mp2.ng.hw.hw4.Battleship;

import mp2.ng.hw.hw4.Battleship.Field.AtackResult;

public class PlayerGUI extends Player {
	private GUI gui;
	Point target;
	
	public PlayerGUI(String name, AbstractView view, AbstractView enemyView) {
		super(name, view, enemyView);
		
		field.populate();
	}

	@Override
	public Point setResult(AtackResult atackResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateOwnView(String field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEnemyView(String field) {
		// TODO Auto-generated method stub
		
	}


}
