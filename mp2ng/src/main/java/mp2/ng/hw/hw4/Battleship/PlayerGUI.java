package mp2.ng.hw.hw4.Battleship;

import mp2.ng.hw.hw4.Battleship.Field.AtackResult;

public class PlayerGUI extends Player {
	private GUI gui;
	Point target;
	
	public PlayerGUI(String name) {
		this.name = name;
		field = new Field();
		field.populate();
		
		gui = new GUI();
		gui.setButtonPressed((x, y) -> {target.x = x; target.y = y; });
		gui.buildGUI();
		target = new Point();
	}

	@Override
	public Point makeShot() {
		gui.getInput();
		return target;
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
		gui.render(field);
	}

}