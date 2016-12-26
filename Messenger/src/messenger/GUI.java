package messenger;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

public class GUI {

	JFrame frame;
	JPanel mainPanel; 
	JTextArea incoming;
	JTextField outgoing;
	JLabel ipAddressLabel;
	String defaulIPAddress = "localhost";
	int defaultPort = 20000;
	
	ChatClientC client;
	
	public void go(String ip, int port)
	{
		setUpInterface();
		client = new ChatClientC();
		client.go(ip, port, new SocketListener());
	}
	public class SocketListener implements StringListener{

		@Override
		public void stringReceived(String string) {//filtering out system messages used to keep connection alive
			if(!string.contentEquals("/ka")) incoming.append(string+"\n");
		}	
	}
	public void setUpInterface()
	{
		frame = new JFrame("Chat Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		incoming = new JTextArea(15, 50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);
		DefaultCaret caret = (DefaultCaret)incoming.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		outgoing = new JTextField(20);		
		outgoing.addKeyListener(new EnterButtonListener());
		ipAddressLabel = new JLabel(defaulIPAddress);
		ipAddressLabel.setText(defaulIPAddress);
		ipAddressLabel.addMouseListener(new ipLabelMouseListener());
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitButtonListener());
		mainPanel.setLayout(new FlowLayout());
		//mainPanel.add(exitButton, BorderLayout.CENTER);
//		mainPanel.add(qScroller, BorderLayout.NORTH);
//		mainPanel.add(outgoing, BorderLayout.SOUTH);
//		mainPanel.add(sendButton, BorderLayout.EAST);
//		mainPanel.add(exitButton, BorderLayout.EAST);
//		mainPanel.add(ipAddressLabel, BorderLayout.WEST);
		mainPanel.add(qScroller, BorderLayout.NORTH);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		mainPanel.add(exitButton);
		mainPanel.add(ipAddressLabel);
		

//		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.getContentPane().add(mainPanel);
		frame.setSize(600, 500);
		frame.setVisible(true);
	}
	public class SendButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ev) {
			client.send(outgoing.getText());
			outgoing.setText("");
			outgoing.requestFocus();
		}
		
	}
//	public class EnterButtonListener extends SendButtonListener{
//	}
	public class EnterButtonListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				client.send(outgoing.getText());
				outgoing.setText("");
				outgoing.requestFocus();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	public class ExitButtonListener implements ActionListener{
//new Runnable() {public void run(){}}
		@Override
		public void actionPerformed(ActionEvent ev) {
			// TODO Remove test staff after working client.close
			new Thread(() ->  {//wachdog
				try {Thread.sleep(1000);} catch (InterruptedException e) {}
				System.out.println("Thread for exit :)");
				System.exit(0);
			}).start();
			System.out.println("Thread for exit :1)");
			if(client.socket != null)
			client.send("/close");
			System.out.println("Thread for exit :2)");
			client.close();
			System.out.println("Thread for exit :3)");
			frame.dispose();
			System.out.println("Thread for exit :4)");
			//;
			client = null;
			System.out.println("Thread");
		}
		
	}
	public class ipLabelMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
//			JOptionPane.showMessageDialog(frame,
//				    "Eggs are not supposed to be green.");
			//Object[] possibilities = {"ham", "spam", "yam"};
			defaulIPAddress = (String)JOptionPane.showInputDialog(
			                    frame,
			                    "Type new ip",
			                    "Change ip",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    null,
			                    defaulIPAddress);
			ipAddressLabel.setText(defaulIPAddress);
			client.ip = defaulIPAddress;
			//client.close();
			//client.go(defaulIPAddress, defaultPort, new SocketListener());

			//If a string was returned, say so.
//			if ((s != null) && (s.length() > 0)) {
//			    //setLabel("Green eggs and... " + s + "!");
//				label.setText("Green eggs and... " + s + "!");
//			    return;
//			}
//
//			//If you're here, the return value was null/empty.
//			setLabel("Come on, finish the sentence!");
			
//		    /** Sets the text displayed at the bottom of the frame. */
//		    void setLabel(String newText) {
//		        label.setText(newText);
//		    }
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI gui = new GUI();
		gui.go(gui.defaulIPAddress, gui.defaultPort);
	}
}
