package chatting.application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;
import java.net.Socket;

import javax.swing.*;

public class Server extends JFrame implements ActionListener{

	JPanel p1;
	JTextField t1;
	JButton b1;
	static JTextArea a1;  //we will write in the text field and then append it to the text area
	
	static ServerSocket skt;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	Server(){
		 
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,450,70);
		add(p1);
		
		//
		//"C:\\Users\\sahup\\eclipse-workspace\\ChattingApplication\\src\\chatting\\application\\icons\\3.png"
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3.png"));
		Image i2 = i1.getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1 = new JLabel(i3);
		l1.setBounds(5,20,30,30);  
		p1.add(l1);  //helps to add the image over the panel.
		
		l1.addMouseListener(new MouseAdapter() { //helps to close the window when click on the back button
			public void mouseClicked(MouseEvent ae) {
				System.exit(0);
			}
		});
		
		
		//"C:\\Users\\sahup\\eclipse-workspace\\ChattingApplication\\src\\chatting\\application\\icons\\111.png"
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/111.png"));
		Image i5 = i4.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel l2 = new JLabel(i6);
		l2.setBounds(40,5,60,60);  
		p1.add(l2);  //helps to add the image over the panel.
		
		 //"C:\\Users\\sahup\\eclipse-workspace\\ChattingApplication\\src\\chatting\\application\\icons\\video.png"
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/video.png"));
		Image i8 = i7.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel l5 = new JLabel(i9);
		l5.setBounds(300,15,40,40);  
		p1.add(l5);  //helps to add the image over the panel.
		
		//"C:\\Users\\sahup\\eclipse-workspace\\ChattingApplication\\src\\chatting\\application\\icons\\phone.png"
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/phone.png"));
		Image i11 = i10.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel l6 = new JLabel(i12);
		l6.setBounds(350,15,40,40);  
		p1.add(l6);  //helps to add the image over the panel.
		
		ImageIcon i13 = new ImageIcon("C:\\Users\\sahup\\eclipse-workspace\\ChattingApplication\\src\\chatting\\application\\icons\\3icon.png");
		Image i14 = i13.getImage().getScaledInstance(13, 25,Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel l7 = new JLabel(i15);
		l7.setBounds(400,20,13,25);  
		p1.add(l7);  //helps to add the image over the panel.
		
		
		JLabel l3 = new JLabel("Luffy");
		l3.setFont(new Font("SAN_SERIF",Font.BOLD,18));
		l3.setForeground(Color.white);
		l3.setBounds(110, 15, 100, 18);
		p1.add(l3);
		
		JLabel l4 = new JLabel("Active Now");
		l4.setFont(new Font("SAN_SERIF",Font.BOLD,12));
		l4.setForeground(Color.white);
		l4.setBounds(110, 35, 100, 20);
		p1.add(l4);
		
		a1 = new JTextArea();
		a1.setBounds(5,75,440,570);
		a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		a1.setEditable(false); //text area will not be editable
		a1.setLineWrap(true);
		a1.setWrapStyleWord(true);
		add(a1);
		
		
		
		
		t1 = new JTextField();
		t1.setBounds(5,655,310,40);
		t1.setFont(new Font("SAN_SHERIF",Font.PLAIN,14 ));
		add(t1);
		
		b1 = new JButton("Send");
		b1.setBounds(320,655,125,40);
		b1.setBackground(new Color(7,94,84));
		b1.setForeground(Color.white);
		b1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		b1.addActionListener(this); //used to add action on send button fuction written below
		add(b1);
		
		
		setLocation(250,100);
		setLayout(null); //use to avoid overlapping of labels.
		setSize(450, 700); //used to resize the box or working area
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//used to close the program when we click cross
		//generally program remain open when we click on close, so to fix that we use this.	
		setUndecorated(true); //helps to remove top taskbar.
		setVisible(true); //used to visible the frame
	 
	 }
	 @Override
	public void actionPerformed(ActionEvent ae) {
		try {
			String out = t1.getText(); //get messages from text area
			a1.setText(a1.getText()+"\n\t\t\t"+out);	//append to the message of text field in text area
			dout.writeUTF(out);
			t1.setText(""); //text field will be empty after every send press
				
		} catch (Exception e) {
			
		}		
	}
	
	public static void main(String[] args) {
		
		///SAMVAAD
		Server obj = new Server();
		
		String msginput = "";
		
		try {
			skt = new ServerSocket(6001);
			s = skt.accept();
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			
			msginput = din.readUTF();
			a1.setText(a1.getText()+"\n"+msginput);
			
			skt.close();
			s.close();
			
		}
		catch (Exception e) {
			
		}
	}
}
