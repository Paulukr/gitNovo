package mp2.ng.hw.Battleship;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.function.BiConsumer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {
	int fieldSize = 10;
	BiConsumer<Integer, Integer> buttonPressed;
	private JFrame mainFrame;
	private boolean mainFrameToRepaint;
	private JButton[][] field = new JButton[fieldSize + 1][fieldSize + 1];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GUI g = new GUI();
		g.buildGUI();

	}

	public void buildGUI() {

		JPanel buttonPanel = new JPanel();
		ComponentListener sqareCL = new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {			}
			
			@Override
			public void componentResized(ComponentEvent arg0) {
				Dimension d = mainFrame.getSize();
				mainFrame.setSize(new Dimension(Math.min(d.width, d.height), Math.min(d.width, d.height)));
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
				field[i][j] = new JButton();
				field[i][j].addActionListener(new ButtonListener(i, j));
				fieldPanel.add(field[i][j]);
			}
			fieldPanel.add(new JLabel("" + i));// row numbers
		}

		// mainPanel.setLayout(new BorderLayout());
		// mainPanel. add(BorderLayout.NORTH, checkBoxPanel);
		mainFrame = new JFrame("BeatBoxS24l");
		mainFrame.addComponentListener(sqareCL);
		mainFrame.setSize(400, 400);
		mainFrame.add(fieldPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);

	}
	void repairGUI(){
		new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {}
			mainFrame.setState(JFrame.ICONIFIED);
			mainFrame.setState(JFrame.NORMAL);
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
			buttonPressed.accept(i, j);
		}

	}
}
