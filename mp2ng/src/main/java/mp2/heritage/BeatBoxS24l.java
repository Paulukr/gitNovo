package mp2.heritage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BeatBoxS24l {
	
	
	final int bitCount = 24;
	final int instrumentCount = 16;
	String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Share", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "Hi Agogo", "Open Hi Conga"};
	int[] instrumentNumbers = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
	
//	MyPlayer player;
	Sequencer sequencer;
	JFrame mainFrame;
	//MyNetFace net;
	
//	class MyPlayer{
//		Sequencer sequencer;
//	}
	
	
	Boolean checkBox[][] = new Boolean[instrumentCount][bitCount];
	JCheckBox jCheckBox[][] = new JCheckBox[instrumentCount][bitCount];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BeatBoxS24l bbl = new BeatBoxS24l();
		bbl.go();
	}
	public void go(){
		buildGUI();
		mainFrame.setVisible(true);
		//play();
		setUpMidi();
//		play();
	}
	private void setUpMidi() {
		// TODO Auto-generated method stub
		try {
				sequencer = MidiSystem.getSequencer();
				sequencer.open();
				sequencer.setTempoInBPM(120);
				
				
				//sequencer.close();
				

		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void play()
	{
		
		
		
		
		Sequence seq;
		try {
			seq = new Sequence(Sequence.PPQ, 4);
		
			Track track = seq.createTrack();
			
			for(int i = 0; i < instrumentCount; i++)
			{
				for(int j = 0; j < bitCount; j++)
				{
					checkBox[i][j] = jCheckBox[i][j].isSelected();	
					if(checkBox[i][j])
					{
						System.out.println("Beat"+i+j);
						addEvent(ShortMessage.NOTE_ON, 9, instrumentNumbers[i], 93, j,track);
						addEvent(ShortMessage.NOTE_OFF, 9, instrumentNumbers[i], 93, j+4,track);
					}
					addEvent(ShortMessage.PROGRAM_CHANGE, 9, 1, 0, bitCount-1,track);
				}
			}
			
			
//			for(int i = 0; i < bitCount; i++)
//			{
//				addEvent(ShortMessage.NOTE_ON, 0, 60+i, 93, i,track);
//				addEvent(ShortMessage.NOTE_OFF, 0, 60+i, 93, i+4,track);
//
//			}
			//addEvent(ShortMessage.PROGRAM_CHANGE, 9, 1, 0, 1,track);
			
//			for(int i = 0; i < bitCount; i++)	// a kind of magic, to get drums we need not change instrument (program),
//			{	//but use 1st instrument on channel 9
//				addEvent(ShortMessage.NOTE_ON, 9, 60+i, 93, i,track);
//				addEvent(ShortMessage.NOTE_OFF, 9, 60+i, 93, i+4,track);
//			}
//			addEvent(ShortMessage.PROGRAM_CHANGE, 9, 1, 0, bitCount-1,track);
			
//			Track track = seq.createTrack();
//			addEvent(ShortMessage.NOTE_ON, 0, 60, 93, 1,track);
//			addEvent(ShortMessage.NOTE_OFF, 0, 60, 93, 16,track);

			sequencer.setSequence(seq);
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
		} catch (InvalidMidiDataException e) {e.printStackTrace();}
	}
	MidiEvent qEvent(int command, int channel, int data1, int data2, long time)
	{
		try {
			return new MidiEvent(new ShortMessage( command, channel, data1, data2), time);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
			return null;
		}
	}
	void addEvent(int command, int channel, int data1, int data2, long time, Track tr)
	{
		tr.add(qEvent(command, channel, data1, data2, time));
	}
	public  void buildGUI(){
		mainFrame = new JFrame("BeatBoxS24l");
		JPanel mainPanel = new JPanel();
			
		//buttons
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
			myButtonColumn buttons = new myButtonColumn(8);
			buttons.add("Play");
			buttons.add("Pause");
			buttons.add("Stop");
			buttons.table[0].button.addActionListener(new PlayListener());
			buttons.table[2].button.addActionListener(new StopListener());
			buttons.deploy(buttonPanel);
			
			//bits
			JPanel checkBoxPanel = new JPanel();
			checkBoxPanel.setLayout(new GridLayout(0, bitCount));
			//buttonPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.LINE_AXIS));
			for(int j = 1; j <= bitCount; j++)
			{
				JLabel bitNo = new JLabel(""+j);
				checkBoxPanel.add(bitNo);
			}
			for(int i = 0; i < instrumentCount; i++)
				for(int j = 0; j < bitCount; j++)
				{
					jCheckBox[i][j] = new JCheckBox();
					jCheckBox[i][j].setSelected(false);
					checkBoxPanel.add(jCheckBox[i][j]);
//					if (jCheckBox[i][j] == null) System.out.println("null"); else System.out.println("No null");
				}
			JPanel InstrumentPanel = new JPanel();
			InstrumentPanel.setLayout(new GridLayout(0, 1));
			InstrumentPanel.add(new JLabel(""));
			for(int i = 0; i < instrumentCount; i++) 
				InstrumentPanel.add(new JLabel(instrumentNames[i]));

			
			
			
		mainPanel.setLayout(new BorderLayout());
		mainPanel. add(BorderLayout.CENTER, checkBoxPanel);
		mainPanel. add(BorderLayout.WEST, InstrumentPanel);
		mainPanel. add(BorderLayout.EAST, buttonPanel);
		
		mainFrame.add(mainPanel);
		
		mainFrame.setSize(640, 280);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	//button listeners 
	class PlayListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			play();
			
		}
		
	}
	class StopListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sequencer.stop();
			
		}
		
	}
		public class myButtonColumn{
			int maxCount;
			myButton[] table;
			int count = 0;
			public myButtonColumn(int height)
			{
				maxCount = height;
				table = new myButton[maxCount];
			}
			public void add(String name)
			{
				if(count < maxCount)
				{
					table[count] = new myButton( name);
					count ++;
				}else{System.out.println("ButtomPanelMaxCount!");}
			}
			void deploy(JPanel buttonPanel)
			{
				buttonPanel.setLayout(new GridLayout(count, 1));
				for(int i = 0; i < count; i ++) buttonPanel.add(table[i].button);
			}
			class myButton{
				JButton button;
				myButton(String name)
				{
					button = new JButton(name);
				}
		}
	}

}

/*			Sequencer player = MidiSystem.getSequencer();
player.open();


Sequence seq = new Sequence(Sequence.PPQ, 4);
Track tr = seq.createTrack();


MidiEvent eventON = qEvent(ShortMessage.NOTE_ON, 0, 60, 93, 1);
MidiEvent eventOFF = qEvent(ShortMessage.NOTE_OFF, 0, 60, 93, 16);
//Receiver rvr = MidiSystem.getReceiver();
tr.add(eventON);
tr.add(eventOFF);




player.setSequence(seq);
//player.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
player.start();
//player.setTempoInBPM(120);
//player.close();

//Synthesizer synth = MidiSystem.getSynthesizer();
//MidiMessage messageON = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 93);
//MidiMessage messageOFF = new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 93);
////System.out.println("Instsnse of " +  ((player instanceof Synthesizer)?"yes":"no"));
//if ( player == null) System.out.println("Sequencer null");
//if ( synth == null) System.out.println("Sequencer null");
//synth.open();
//Receiver srvr = synth.getReceiver(); 



//srvr.send(messageON, -1);
//srvr.send(messageOFF, 1000);

//for (int i = 0; i < 20; i++)			srvr.send(message, -1);			
//synth.close();
*/