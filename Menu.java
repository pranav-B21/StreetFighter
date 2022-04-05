//all imports
//Pranav Belligundu
//Kim per 5
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;

//this class creates the menu and uses cardLayout to move between Panels, using 3 buttons
//START, QUIT, and HOW TO PLAY
public class Menu extends JPanel implements ActionListener
{
	//fields
	private ChooseMap map;
	private Game game;
	private JPanel namePanel, buttonPanel, menuPanel, titlePanel, panel, instPanel, instructions;
	private JLabel name, title;
	private Image bg = new ImageIcon("menuBG.gif").getImage();
	private ChooseGameMode gameMode;
	public JButton quitButtonEnd, backEnd;
	public JPanel panel1, endpanel;
	
	private JButton startButton,instButton, quitButton, exitButton;
	private CardLayout cl;
	//private boolean clickedExit;
	
	Font titleFont = new Font("ZapfDingbats", Font.BOLD, 51);
	static Font buttonFont = new Font("ZapfDingbats", Font.PLAIN, 30);
	static Font buttonFont2 = new Font("ZapfDingbats", Font.PLAIN, 20);
	
	private boolean clickedInst = false;
	
	//the constructor method where I initialize add components
	public Menu()
	{
		this.setBounds(0,0,getWidth(),getHeight());
		cl = new CardLayout();
		this.setLayout(cl);//sets the layout of the Menu panel to cardLayout
		
		//Panels
		setUpInstructions();//sets the Instructions panel	
		setUpMenu();//sets the Menu panel
		addButtonsEnd();
		map = new ChooseMap();
		gameMode = new ChooseGameMode();
		this.add(menuPanel, "MENU");
		this.add(gameMode, "GAME MODE");
		this.add(instructions, "INSTRUCTIONS");
		
		cl.show(this, "MENU");//shows the current panel
		
	}
	//initializes the buttons 
	public void addButtonsEnd() {		
		quitButtonEnd = new MyButton("QUIT1");//Quit button
		quitButtonEnd.setFont(buttonFont2);
		quitButtonEnd.setBounds(270,200,100,50);
		quitButtonEnd.addActionListener(this);	
		
		backEnd = new MyButton("BACK");//Quit button
		backEnd.setFont(buttonFont2);
		backEnd.setBounds(270,250,100,50);
		backEnd.addActionListener(this);	
	}
	//Initializes/Declares all components needed in the menuPanel and puts them on the JFrame
	public void setUpMenu() {
		//MENU
		menuPanel = new JPanel();
		menuPanel.setLayout(null);
		menuPanel.setOpaque(false);
		menuPanel.setBounds(0,0,getWidth(),getHeight());
		
		//Everything inside menuPanel
		namePanel = new JPanel();
		namePanel.setBounds(95,20,450,100);
		namePanel.setOpaque(false);
		menuPanel.add(namePanel);
		
		name = new JLabel("STREET FIGHTER");//title
		name.setForeground(Color.WHITE);
		name.setFont(titleFont);
		namePanel.add(name);
		
		buttonPanel = new JPanel();//panel for the buttons
		buttonPanel.setBounds(400,165,260,300);
		buttonPanel.setLayout(null);
		buttonPanel.setOpaque(false);
		menuPanel.add(buttonPanel);
	
		//buttons
		startButton = new MyButton("PLAY");//Play button
		startButton.setFont(buttonFont);
		startButton.setBounds(0,0,230,70);
		buttonPanel.add(startButton);
		startButton.addActionListener(this);
		
		instButton = new MyButton("HOW TO PLAY");//How to play button
		instButton.setFont(buttonFont);
		instButton.setBounds(0,80,230,70);
		buttonPanel.add(instButton);
		instButton.addActionListener(this);
		
		quitButton = new MyButton("QUIT");//Quit button
		quitButton.setFont(buttonFont);
		quitButton.setBounds(0,160,230,70);
		buttonPanel.add(quitButton);
		quitButton.addActionListener(this);		
	}
	//Initializes/Declares all components needed in the instructions panel and puts them on the JFrame
	public void setUpInstructions() {
		//instructions panel where everything is added on to
		instructions = new JPanel();
		instructions.setLayout(cl);
		instructions.setOpaque(false);
		instructions.setBounds(0,0,getWidth(),getHeight());
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setBounds(0,0,getWidth(),getHeight());
		
		//title for the instructions panel
		titlePanel = new JPanel();
		titlePanel.setBounds(95,20,450,200);
		titlePanel.setOpaque(false);
		panel.add(titlePanel);
		
		//JLabel for the title
		title = new JLabel("HOW TO PLAY");
		title.setForeground(Color.white);
		title.setFont(titleFont);
		titlePanel.add(title);
		
		//----------------------------instructions------------------------------
		
		instPanel = new JPanel();
		instPanel.setLayout(null);
		instPanel.setOpaque(false);
		instPanel.setBounds(10, 100, 600, 400);
		
		//all JPanels for the JLabels
		JPanel wP = new JPanel();//panel for W key
		wP.setOpaque(false);
		wP.setBounds(0,0,200,40);
		instPanel.add(wP);
		
		JPanel aP = new JPanel();//panel for A key
		aP.setOpaque(false);
		aP.setBounds(0,40,260,40);
		instPanel.add(aP);
		
		JPanel sP = new JPanel();//panel for S key
		sP.setOpaque(false);
		sP.setBounds(0,80,235,40);
		instPanel.add(sP);
		
		JPanel dP = new JPanel();//panel for D key
		dP.setOpaque(false);
		dP.setBounds(0,120,280,40);
		instPanel.add(dP);
		
		JPanel gP = new JPanel();//panel for G key
		gP.setOpaque(false);
		gP.setBounds(0,160,200,40);
		instPanel.add(gP);
		
		JPanel hP = new JPanel();//panel for H key
		hP.setOpaque(false);
		hP.setBounds(0,200,230,40);
		instPanel.add(hP);
		
		JPanel fP = new JPanel();//panel for F key
		fP.setOpaque(false);
		fP.setBounds(0,240,300,40);
		instPanel.add(fP);
		  
		JLabel w = new JLabel("W - JUMP");//JLabel for W key
		w.setForeground(Color.red);
		w.setFont(buttonFont2);
		wP.add(w);
		
		JLabel a = new JLabel("A - MOVE LEFT");//JLabel for A key
		a.setForeground(Color.red);
		a.setFont(buttonFont2);
		aP.add(a);
		
		JLabel s = new JLabel("S - CROUCH");//JLabel for S key
		s.setForeground(Color.red);
		s.setFont(buttonFont2);
		sP.add(s);
		
		JLabel d = new JLabel("D - MOVE RIGHT");//JLabel for D key
		d.setForeground(Color.red);
		d.setFont(buttonFont2);
		dP.add(d);
		
		JLabel g = new JLabel("G - KICK");//JLabel for G key
		g.setForeground(Color.red);
		g.setFont(buttonFont2);
		gP.add(g);
		
		JLabel h = new JLabel("H - PUNCH");//JLabel for H key
		h.setForeground(Color.red);
		h.setFont(buttonFont2);
		hP.add(h);
		
		JLabel f = new JLabel("F - SPECIAL MOVE");//JLabel for F key
		f.setForeground(Color.red);
		f.setFont(buttonFont2);
		fP.add(f);
		
		//----------------second set of inst ----------------------/
		JPanel upP = new JPanel();//panel for Up arrow key
		upP.setOpaque(false);
		upP.setBounds(300,0,250,40);
		instPanel.add(upP);
		
		JPanel leftP = new JPanel();//panel for left arrow key
		leftP.setOpaque(false);
		leftP.setBounds(310,40,300,40);
		instPanel.add(leftP);
		
		JPanel downP = new JPanel();//panel for down arrow key
		downP.setOpaque(false);
		downP.setBounds(305,80,300,40);
		instPanel.add(downP);
		
		JPanel rightP = new JPanel();//panel for right arrow key
		rightP.setOpaque(false);
		rightP.setBounds(343,120,260,40);
		instPanel.add(rightP);
		
		JPanel mP = new JPanel();//panel for M key
		mP.setOpaque(false);
		mP.setBounds(277,160,250,40);
		instPanel.add(mP);
		
		JPanel nP = new JPanel();//panel for N key
		nP.setOpaque(false);
		nP.setBounds(257,200,270,40);
		instPanel.add(nP);
		
		JPanel bP = new JPanel();//panel for B key
		bP.setOpaque(false);
		bP.setBounds(305,240,270,40);
		instPanel.add(bP);
		  
		JLabel up = new JLabel("Up Arrow - JUMP");//JLabel for Up arrow key
		up.setForeground(Color.red);
		up.setFont(buttonFont2);
		upP.add(up);
		
		JLabel left = new JLabel("Left Arrow - MOVE LEFT");//JLabel for A key
		left.setForeground(Color.red);
		left.setFont(buttonFont2);
		leftP.add(left);
		
		JLabel down = new JLabel("Down Arrow - CROUCH");//JLabel for S key
		down.setForeground(Color.red);
		down.setFont(buttonFont2);
		downP.add(down);
		
		JLabel right = new JLabel("Right Arrow - MOVE RIGHT");//JLabel for D key
		right.setForeground(Color.red);
		right.setFont(buttonFont2);
		rightP.add(right);
		
		JLabel m = new JLabel("M - PUNCH");//JLabel for G key
		m.setForeground(Color.red);
		m.setFont(buttonFont2);
		mP.add(m);
		
		JLabel n = new JLabel("N - KICK");//JLabel for H key
		n.setForeground(Color.red);
		n.setFont(buttonFont2);
		nP.add(n);
		
		JLabel b = new JLabel("B - SPECIAL MOVE");//JLabel for H key
		b.setForeground(Color.red);
		b.setFont(buttonFont2);
		bP.add(b);
		panel.add(instPanel);
		
		//exit button
		exitButton = new MyButton("BACK");
		exitButton.setFont(buttonFont2);
		exitButton.setBounds(250,270,100,50);
		exitButton.addActionListener(this);
		instPanel.add(exitButton);

		instructions.add(panel);
	}
	//method that grabs the .wav file and plays the audio
	public void makeSound(){
	    File audio = new File("click.wav");
	    try{
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(audio));
	        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        	gainControl.setValue(-27.5f); // Reduce volume by 27.5 decibels.
	        clip.start();
	    } catch (Exception e){
	        e.printStackTrace();
	    }
	}
	//this is needed for the cardLayout to work, detects which button is being pressed
	public void actionPerformed(ActionEvent e)
    {    
        if (e.getSource() == startButton){  
        	showCard("GAME MODE");
        	makeSound();
        }
        else if (e.getSource() == instButton){ 
        	clickedInst = true;
        	showCard("INSTRUCTIONS");
        	makeSound();
        }
        else if (e.getSource() == quitButton){   
        	System.exit(1);
        	makeSound();
        }
        else if (e.getSource() == backEnd){   
        	clickedInst = false;
        	cl.show(this,"MENU");
        	makeSound();
        }
        else if (e.getSource() == quitButtonEnd){ 
        	System.exit(1);
        	makeSound();
        }
        else if (e.getSource() == exitButton){   
        	showCard("MENU");
        	makeSound();
        }
    }
	//shows the JPanel by using a method
	public void showCard(String text) {
		cl.show(this, text);
	}
	/*
	 * paints this with graphics
	 * Includes the line below the title and background
	*/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg,0,0,getWidth(),getHeight(),this);
		g.setColor(Color.WHITE);
		g.drawLine(95, 90, 545, 90);
		
		Graphics2D g2d = (Graphics2D) g;
		if(clickedInst) drawRotate(g2d, 50, 300, -90, "PLAYER 1");
		if(clickedInst) drawRotate(g2d, 330, 300, -90, "PLAYER 2");
		
	}
	//rotates the text sideways to show in the instructions panel
	public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text) 
	{    
		g2d.setFont(buttonFont);
		g2d.setColor(Color.YELLOW);
	    g2d.translate((float)x,(float)y);
	    g2d.rotate(Math.toRadians(angle));
	    g2d.drawString(text,0,0);
	    g2d.rotate(-Math.toRadians(angle));
	    g2d.translate(-(float)x,-(float)y);
	} 
	//getters to get the back button
	public JButton backEnd() {
		return backEnd;
	}
	//getters to get the quit button
	public JButton quitEnd() {
		return quitButtonEnd;
	}
}
