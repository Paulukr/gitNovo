package mp2.ng.hw.hw4.Battleship;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Arrays;
import java.util.function.BiConsumer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mp2.ng.hw.hw4.Battleship.Player.Point;

public class GUI extends AbstractView{
	public GUI(String name, BiConsumer<AbstractView, Point> controller) {
		super(controller);
		this.name = name;
		buildGUI();
		char[] temp = new char[100];
		Arrays.fill(temp, ' ');
		tracingLastFrame = new String(temp);
	}
	String name;
	int fieldSize = 10;
	private JFrame tracingFrame;
	private boolean mainFrameToRepaint;
	private JButton[][] tracingField = new JButton[fieldSize][fieldSize];
	private Thread getInputThread;
	String tracingLastFrame;
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		GUI g = new GUI();
//		g.buildGUI();
//
//	}
	class Timer extends Thread{

		@Override
		public void run() {
			System.out.println("try block");
			try {
				System.out.println("Waiting button");
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				System.out.println("getInput awaken");
			}
		}
		
	}
	public void getInput(){
		System.out.println("getInput");

		getInputThread = new Timer();
		try {
			System.out.println("start join");
			getInputThread.start();
			getInputThread.join();
			System.out.println("end join");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("interrupt join");
			e.printStackTrace();
		}
	}
	public void buildGUI() {

		JPanel buttonPanel = new JPanel();
		ComponentListener sqareCL = new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				Dimension d = tracingFrame.getSize();
				tracingFrame.setSize(new Dimension(Math.min(d.width, d.height), Math.min(d.width, d.height)));
				d = buttonPanel.getSize();
				buttonPanel.setSize(new Dimension(Math.min(d.width, d.height), Math.min(d.width, d.height)));
				if(!mainFrameToRepaint){
					repairGUI();
					mainFrameToRepaint = true;
				}
			}
			@Override
			public void componentMoved(ComponentEvent e) {			}
			@Override
			public void componentHidden(ComponentEvent e) {			}
		};
		
		{
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
			JButton button = new JButton("Play");
			buttonPanel.add(button);
			// button.addActionListener(new PlayListener());
		}
		// field
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(fieldSize + 1, fieldSize + 1));
		fieldPanel.addComponentListener(sqareCL);
		// column numbers
		for (int j = 0; j < fieldSize; j++)
			fieldPanel.add(new JLabel("" + j));
		fieldPanel.add(new JLabel(" "));

		for (int i = 0; i < fieldSize; i++) {
			for (int j = 0; j < fieldSize; j++) {
				tracingField[i][j] = new JButton();
				tracingField[i][j].addActionListener(new ButtonListener(i, j));
				fieldPanel.add(tracingField[i][j]);

			}
			fieldPanel.add(new JLabel("" + i));// row numbers
		}

		tracingFrame = new JFrame(name + "trace");
		tracingFrame.addComponentListener(sqareCL);
		tracingFrame.setSize(500, 500);
		tracingFrame.add(fieldPanel);
		tracingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tracingFrame.setVisible(true);
		
	}

	private void repairGUI(){
		new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {}
			tracingFrame.setState(JFrame.ICONIFIED);
			tracingFrame.setState(JFrame.NORMAL);
			mainFrameToRepaint = false;
		}).start();
	}

	private class ButtonListener implements ActionListener {
		int i;
		int j;

		private ButtonListener(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("ButtonPressed");
			controller.accept(GUI.this, new Point(j,i));
			getInputThread.interrupt();
		}

	}
	@Override
	public void updateTracingField(String frame) {
		System.out.println("\n" +frame);
		String cleanFrame = frame.replaceAll(", ", "").replaceAll("[\\[\\][\\n]]", "");;

		System.out.println("\n" +cleanFrame);
		if(cleanFrame.length() != (fieldSize)*fieldSize)
			throw new IllegalArgumentException("frame string size was " + cleanFrame.length());
		for (int i = 0; i < tracingField.length; i++) {
			for (int j = 0; j < tracingField[i].length; j++) {
				String cellState =  cleanFrame.charAt(i*fieldSize +j) + "";
//				System.out.println(i+" " + j + " :" + cellState);
				tracingField[i][j].setText(cellState);
				if(!cellState.equals(" "))
					tracingField[i][j].setEnabled(false);
			}
		}
		tracingLastFrame = frame;
	}

	@Override
	void enable(boolean enabled) {
		for (int i = 0; i < tracingField.length; i++) {
			for (int j = 0; j < tracingField[i].length; j++) {
				tracingField[i][j].setEnabled(enabled);
			}
		}
		if(enabled)
			updateTracingField(tracingLastFrame);
	}
	@Override
	void updateField(String frame) {
	}
}
