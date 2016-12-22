package mp2.heritage;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class BeatBoxS24 {

	File bbxDir = new File("D:\\Libraries\\Google Drive\\workspace\\Hello\\src\\day3\\BeatBox\\");
	JFrame mF;
	JPanel mP;
	ArrayList <JCheckBox> cB;
	
	Sequencer player;
	Sequence seq;
	Track track;
	
	JLabel tempoLabel;
	
	String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Share", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "Hi Agogo", "Open Hi Conga"};
	int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
	final int nInstruments =16;
	final int nTicks = 24;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BeatBoxS24 bb24 = new BeatBoxS24();
		bb24.setupMIDI();
		bb24.buildGui();
		
	}
	public void buildGui()
	{
		mF = new JFrame("BeatBox");
		mF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout bl = new BorderLayout();
		JPanel background = new JPanel(bl); 
		background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		//menu bar
		JMenuBar menuBar =new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		mF.setJMenuBar(menuBar);
		
		//button box
		Box buttonBox = new Box(BoxLayout.Y_AXIS);
			
		JButton start =new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);
		
		JButton stop =new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);
		
		JButton upTempo =new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);
		
		JButton downTempo =new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);
		
		
		JLabel tempoLabelLabel = new JLabel("real tempo BPM");
		tempoLabel = new JLabel();
		buttonBox.add(tempoLabelLabel);
		buttonBox.add(tempoLabel);
		
		JButton qsave =new JButton("QSave");
		qsave.addActionListener(new MyQSaveListener());
		buttonBox.add(qsave);
		
		JButton saveAs =new JButton("Save as");
		saveAs.addActionListener(new MySaveAsListener());
		buttonBox.add(saveAs);
		
		JButton saveAsText =new JButton("Save as txt");
		saveAsText.addActionListener(new MySaveAsTextListener());
		buttonBox.add(saveAsText);	
		
		JButton qload =new JButton("QLoad");
		qload.addActionListener(new MyQLoadListener());
		buttonBox.add(qload);
		
		JButton loadLast =new JButton("Load last");
		loadLast.addActionListener(new MyLoadLastListener());
		buttonBox.add(loadLast);
		
		JButton load =new JButton("Load...");
		load.addActionListener(new MyLoadListener());
		buttonBox.add(load);
		
		JButton loadText =new JButton("Load Text");
		loadText.addActionListener(new MyLoadTextListener());
		buttonBox.add(loadText);
		
		background.add(BorderLayout.EAST, buttonBox);
		
		//name box
		Box nameBox = new Box(BoxLayout.Y_AXIS);
		Font bigFont = new Font("sanserif", Font.BOLD, 17);
		for(int i = 0; i<nInstruments ; i++)
		{
			JLabel l = new JLabel(instrumentNames[i]);
			l.setFont(bigFont);
			/*
			//bigFont = l.getFont();
			//System.out.println(bigFont.toString());
			//Dimension d =new Dimension(83, 16);
			//d=l.getPreferredSize();
			//System.out.println(d.toString());
			//l.setPreferredSize(d);
			 * 
			 */
			nameBox.add(l);
		}
		background.add(BorderLayout.WEST, nameBox);
		//numbers of ticks
		Box numberBox = new Box(BoxLayout.Y_AXIS);
		Font numberFont = new Font("sanserif", Font.BOLD, 8);
		String tickNumbers = "";//"                                                      ";
		for(int i = 1; i<=63 ; i++) tickNumbers += " ";
		String space = "";
		for(int i = 1; i<=8 ; i++) space += " ";
		for(int i = 1; i<=nTicks ; i++)
		{
			if(i<10) tickNumbers+="0";
			tickNumbers+=Integer.toString(i)+space;
		}
		JLabel l = new JLabel(tickNumbers);
		l.setFont(numberFont);
		numberBox.add(l);
		background.add(BorderLayout.NORTH, numberBox);
		//check box
		mF.getContentPane().add(background);
		
		GridLayout grid = new GridLayout( nInstruments, nTicks );
		grid.setVgap(1);
		grid.setHgap(2);
		mP=new JPanel(grid);
		background.add(BorderLayout.CENTER, mP);
		
		cB = new ArrayList<JCheckBox>();
		for(int i = 0; i < nInstruments * nTicks; i++)
		{
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			cB.add(c);
			mP.add(c);
		}
		
		mF.setBounds(50, 50, 300, 300);
		mF.pack();
		mF.setVisible(true);
	}//end buildGUI
	public void setupMIDI()
	{
		try{
			player = MidiSystem.getSequencer();
			player.open();
			seq = new Sequence(Sequence.PPQ,4);
			track = seq.createTrack();
			player.setTempoInBPM(220);
			
			if(seq==null) System.out.println("seq isnull setup");
			if(track==null) System.out.println("track isnull setup");
		} catch (MidiUnavailableException | InvalidMidiDataException e) {e.printStackTrace();}
	}
	
	public class MyStartListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			buildTrackAndStart();			
		}
	}
	public class MyStopListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			player.stop();
		}
	}
	public class MyUpTempoListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			float tempo = (float)player.getTempoFactor();
			player.setTempoFactor((float)(tempo*1.03));
			tempoLabel.setText(Float.toString(player.getTempoInBPM()*player.getTempoFactor()));
		}		
	}
	public class MyDownTempoListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			float tempo = (float)player.getTempoFactor();
			player.setTempoFactor((float)(tempo*0.97));
			tempoLabel.setText(Float.toString(player.getTempoInBPM()*player.getTempoFactor()));
		}		
	}

	public void buildTrackAndStart()
	{
		int[] trackList = null;
		if(seq==null) System.out.println("seq isnull");
		if(track==null) System.out.println("track null");
		System.out.println("    ");
		seq.deleteTrack(track);
		track = seq.createTrack();
		
		for(int i=0; i<nInstruments; i++)
		{
			trackList = new int[nTicks];
			int key = instruments[i];
			
			for(int j =0; j<nTicks; j++)
				
				
				
			{
				JCheckBox jc = (JCheckBox) cB.get(j+i*nInstruments );
				if(jc.isSelected()) { trackList[j] = key; }
				else{ trackList[j] = 0; }
			}//j
			makeTracks(trackList);
			track.add(makeEvent(176,1,127,0,24));//16 to 24 effort
		}//i
		track.add(makeEvent(192,9,9,1,15));
		try{
			player.setSequence(seq);
			player.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			player.start();
			player.setTempoInBPM(120);
			
			new MySaveAsListener().saveInFileObj(new File(bbxDir, "CheckBoxAutoSave.ser"));	
		}catch(Exception e){e.printStackTrace();}		
	}//end buildTrack...
	public void makeTracks(int[] list)
	{
		for(int i = 0; i <nTicks; i++)
		{
			int key = list[i];
			
			if(key!=0)
			{
				track.add(makeEvent(144,9,key,100,i));
				track.add(makeEvent(128,9,key,100,i+1));
			}
		}
	}//end makeTrack
	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick)
	{
		/*
	 	create and set message->create event with message->add
			event to track
		second argument is number of channel 
		third and occasionally forth stand for something like
		 argument for message
		
		first argument is type of message
			144 note on: 3th tone 0-127, 4th force and duration
			 performed  on note (key, button)
			192 change instrument: 3th instrument 
			128 note off
			176 to add event for: player.addControllerEventListener(dp2, eventsIWant);
				eventsIWant is int[]; You need add some int there and 3rd arg (data1) mast be equal to it
		MidiEvent 2th argument: moment in time, cycle at 
		 what it have to happen
		
		 */
		MidiEvent event= null;
		try{
			ShortMessage Message= new ShortMessage();
			Message.setMessage(comd, chan, one, two);
			event = new MidiEvent(Message,tick);
		}catch(Exception e){e.printStackTrace();}
		return event;
	}
	
	class MyQSaveListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{//becouseNonStatic (OMG)
			new MySaveAsListener().saveInFileObj(new File(bbxDir, "CheckBoxQSave.ser"));
			new MySaveAsListener().saveInFileText(new File(bbxDir, "CheckBoxQSave.bbx"));
		}// end function	
	}//end inner class

	class MyQLoadListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			new MyLoadListener().LoadFromBoolean ( new File(bbxDir,"CheckBoxQSave.ser") );//System.out.println("Save");
		}// end function	
	}//end inner class
	class MyLoadLastListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			new MyLoadListener().LoadFromBoolean ( new File(bbxDir,"CheckBoxAutoSave.ser") );//System.out.println("Save");//System.out.println("Save");
		}// end function	
	}//end inner class
	
	class MySaveAsListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JFileChooser fileChooser = new JFileChooser();			//System.out.println("Save");
			fileChooser.setCurrentDirectory(bbxDir);  
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Object tracks", "ser");
			fileChooser.setFileFilter(filter);
			
			if(JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(mF))
				saveInFileObj(fileChooser.getSelectedFile());
		}// end function	
		public void saveInFileObj(File file)
		{
			boolean[] cBstate = new boolean[nInstruments * nTicks];
			for(int i = 0; i < nInstruments * nTicks; i++) if(cB.get(i).isSelected()) cBstate[i] = true;
			try
			{
				FileOutputStream fs = new FileOutputStream(file);
				ObjectOutputStream os = new ObjectOutputStream(fs);
				os.writeObject(cBstate);
				os.close();
				fs.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end catch
		}
		public void saveInFileText(File file)
		{
			try
			{
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				for(int i = 0; i < nInstruments * nTicks; i++) if(cB.get(i).isSelected()) writer.write("1"); else writer.write("0"); 
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end catch	
		}
	}//end inner class	
	class MyLoadListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(bbxDir);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Object tracks", "ser");
			fileChooser.setFileFilter(filter);
			
			if(JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(mF))//showSaveDialog(mF);
				LoadFromBoolean ( fileChooser.getSelectedFile() );				//System.out.println("Save");	
		}// end function	
		public void LoadFromBoolean ( File file )
		{
			boolean[] cBstate = null;
			cBstate= getFromFileObj (file );
			for(int i = 0; i < nInstruments * nTicks; i++) cB.get(i).setSelected(cBstate[i]);//for(int i = 0; i < 256; i++) System.out.println(cBstate[i]);
		}// end function
		public boolean[] getFromFileObj (File file )
		{
			boolean[] state=new boolean[nInstruments * nTicks];;
			try
			{
				ObjectInputStream os = new ObjectInputStream(new FileInputStream(file));
				try {	state = (boolean[])os.readObject();	//for(int i = 0; i < 256; i++) System.out.println(state[i]);
				} catch (ClassNotFoundException e) {e.printStackTrace();	}
				os.close();
			} catch (FileNotFoundException e) {	e.printStackTrace();
			} catch (IOException e) {	e.printStackTrace();
			}//end catch	
			return state;
		}// end function
	}//end inner class
	
	class MySaveAsTextListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{	
			JFileChooser fileChooser = new JFileChooser();//System.out.println("Save");
			fileChooser.setCurrentDirectory(bbxDir); 
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Text tracks", "txt");
			fileChooser.setFileFilter(filter);
			
			if(JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(mF))
				new MySaveAsListener().saveInFileText(fileChooser.getSelectedFile());
		}// end function	

	}//end inner class	
	class MyLoadTextListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			//System.out.println("Save");
			boolean[] cBstate = new boolean[nInstruments * nTicks];
			
			JFileChooser fileChooser = new JFileChooser();//System.out.println("Save");
			fileChooser.setCurrentDirectory(bbxDir); 
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Text tracks", "txt");
			fileChooser.setFileFilter(filter);
			
			if(JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(mF))
			getFromFileTxt (cBstate, fileChooser.getSelectedFile() );
			for(int i = 0; i < nInstruments * nTicks; i++) cB.get(i).setSelected(cBstate[i]); //for(int i = 0; i < 256; i++) System.out.println(cBstate[i]);
		}// end function	
		public void getFromFileTxt (boolean[] state, File file )
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(file));
				
				String string = null;
				if((string = br.readLine())==null) 			System.out.println("File is empty");
				else
				{
					for(int i = 0; i < nInstruments * nTicks; i++)
					{
						if(string.charAt(i) == '1') state[i] = true;
		
					}
					//for(int i = 0; i < nInstruments * nTicks; i++) System.out.println(state[i]);

				}
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//end catch	
		}
	}//end inner class

}//end class
