

//package snehith;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import  sun.audio.*;    //import the sun.audio package
import  java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class zigzag extends JFrame implements ActionListener {
	
	static JFrame zigzag;
	JPanel mypanel;
	JButton mybutton;
	JLabel mylabel1;
	JLabel mylabel2;
	JLabel mylabel3;
	JLabel mylabel4;
	JTextField mytext1;
	JTextField mytext2;
	int change_x1 = 0;
	Graphics g;
	ArrayList<Point> mypoint = new ArrayList<Point>();
	String zigzag_length;
	String zigzag_lines;
	JLabel label;
	int length;
	int lines;
	JLabel air;
	int s,t,d;
	int distance;
	
	public zigzag() {
		//constructor creates panel and GUI components and adds	them to the frame and panel.
		//the following lines of code creates the required text fields in the GUI
		mypanel = new JPanel();
		mybutton = new JButton ("click me");
		mylabel1 = new JLabel("length of each zigzag line: ");
		mylabel2 = new JLabel("number of zigzag lines ");
		mylabel3=new JLabel("");
		mylabel4=new JLabel("");
		mytext1 = new JTextField(10);
		mytext2 = new JTextField(10);
		add(mypanel);
		mypanel.add(mylabel1);
		mypanel.add(mytext1);
		mypanel.add(mylabel2);
		mypanel.add(mytext2);
		mypanel.add(mybutton);
		mypanel.add(mylabel3);
		mypanel.add(mylabel4);
		//also initialises the event handling for the button
		mybutton.addActionListener(this);
	}

	public static void main(String[] args) {
		//creates Jframe object, sets title, size and location and	makes it appear onscreen. It also closes the frame when you press X
		zigzag = new zigzag();
		zigzag.setTitle("Draw shapes");
		zigzag.setSize(1400,1100);
		zigzag.setLocation(10,100);
		zigzag.setVisible(true);
		zigzag.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void draw_zigzag(Graphics g, int length1, int lines1) {
		super.paint(g);
				mypoint.clear();
				
				 s=10;
				 t= 30;
				 d=length1;
				int y_offset = 10;
				t=2*length1;
				for(int i=0;i<=lines1;i++) {
					mypoint.add(new Point(s+i*d, t + (i%2==0 ? 0 : d)));
				
				}
				
				int a=mypoint.get(0).x;
				
				int b=mypoint.get(0).y;
				int c=mypoint.get(mypoint.size()-1).x;
				int p=mypoint.get(mypoint.size()-1).y;
				
				int second_x=mypoint.get(1).x;
				int second_y=mypoint.get(1).y;
				//the following code calculates the air distance of the path
				
				 distance= (int)Math.sqrt((p-b)*(p-b)+(c-a)*(c-a));
				
				String air1 = Integer.toString(distance);
				
				mylabel3.setText("The air distance the zig-zag path is "+air1+",");
				//below formula calculate the travel distance of the zig zag path
				int travel_dist=(int)Math.sqrt((second_y-b)*(second_y-b)+(second_x-a)*(second_x-a))*lines1;
				String travel_str=Integer.toString(travel_dist);
				mylabel4.setText("the travel distance of zig zag path is "+travel_str);
				
			
				
					

				for(int i=1;i<mypoint.size();i++) {
					int x1=mypoint.get(i-1).x;
					int y1=mypoint.get(i-1).y;
					int x2=mypoint.get(i).x;
					int y2=mypoint.get(i).y;
					try {
						Thread.sleep(1000);
						if(i%2 == 0) {
							g.setColor(Color.green);
							Toolkit.getDefaultToolkit().beep();
							g.drawLine(x1, y1, x2, y2);
						}else {
							g.setColor(Color.pink);
							//Thread.sleep(250);
							Toolkit.getDefaultToolkit().beep();
							Thread.sleep(250);
							Toolkit.getDefaultToolkit().beep();
							g.drawLine(x1, y1, x2, y2);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					if(i == mypoint.size()-1) {
						
						for(int j=mypoint.size()-1;j>=0;j--) {
							
							int x11=mypoint.get(j-1).x;
							int y11=mypoint.get(j-1).y+50;
							int x22=mypoint.get(j).x;
							int y22=mypoint.get(j).y+50;
							
							try {
								Thread.sleep(1000);
								if(j%2 == 0) {
									// below line displays the color
									g.setColor(Color.pink);
									// below line gives the sound
									Toolkit.getDefaultToolkit().beep();
									g.drawLine(x11, y11, x22, y22);
								}else {
									g.setColor(Color.green);
									//Thread.sleep(250);
									Toolkit.getDefaultToolkit().beep();
									Thread.sleep(250);
									Toolkit.getDefaultToolkit().beep();
									g.drawLine(x11, y11, x22, y22);
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
					}
				}
				
			}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(mybutton) )
		{	
			String zigzag_length = mytext1.getText();
			String zigzag_lines = mytext2.getText();
			
			try {
				 length = Integer.parseInt(zigzag_length)*3;
				 lines = Integer.parseInt(zigzag_lines);
			}catch(Exception e1){
				System.out.println("wrong");
			}
			
			
			if((length>=40*3 && length<=70*3) && (lines>0 && lines<=8)) {
				draw_zigzag(getGraphics(), length, lines); 
				
				
				
			}else {
				zigzag.setSize(600,500);
				label = new JLabel("please enter valid integers ");
				mypanel.add(label);
			}	
			
			
				
			     		
		}
	}
	
	
	

}
 
