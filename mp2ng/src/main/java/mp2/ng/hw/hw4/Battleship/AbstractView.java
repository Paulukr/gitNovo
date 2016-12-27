package mp2.ng.hw.hw4.Battleship;

import java.util.function.BiConsumer;

import mp2.ng.hw.hw4.Battleship.Player.Point;

public abstract class AbstractView {
	BiConsumer<AbstractView, Point> controller;
	
	public AbstractView(BiConsumer<AbstractView, Point> controller) {
		this.controller = controller;
	}
	abstract void updateField(String frame);
	abstract void updateTracingField(String frame);
	abstract void enable(boolean enabled);
}
