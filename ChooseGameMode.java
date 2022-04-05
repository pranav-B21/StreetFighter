//imports
import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.io.*;

//class where you can choose between 2 game modes that are available
public class ChooseGameMode extends JPanel implements ActionListener{

	//fields
	private Image bg = new ImageIcon("instBG.png").getImage();
	private JLabel title;
	private JButton cpuButton, twoPlyrButton;
	private JPanel chooseGamePanel, titlePanel;
	private ChooseMap map;
	private ChooseDifficulty level;
	
	Font titleFont = new Font("ZapfDingbats", Font.BOLD, 51);
	Font buttonFont = new Font("ZapfDingbats", Font.PLAIN, 30);
	private CardLayout cl = new CardLayout();
	
	//constructor method where the JPanel is initialised and CardLayout used to move between panels
	public ChooseGameMode() {
		this.setBounds(0,0,getWidth(),getHeight());
		this.setLayout(cl);//sets the layout to cardLayout
		
		map = new ChooseMap();
		level = new ChooseDifficulty();
		chooseGamePanel = new JPanel();
		chooseGamePanel.setLayout(null);
		chooseGamePanel.setOpaque(false);
		chooseGamePanel.setBounds(0,0,getWidth(),getHeight());
		
		titlePanel = new JPanel();//creates JPanel for the title of the JPanel
		titlePanel.setBounds(45,20,550,100);
		titlePanel.setOpaque(false);
		chooseGamePanel.add(titlePanel);
		
		title = new JLabel("CHOOSE GAMEMODE");//title of the JPanel
		title.setForeground(Color.WHITE);
		title.setFont(titleFont);
		titlePanel.add(title);
		
		cpuButton = new MyButton("SOLO");//cpu
		cpuButton.setFont(buttonFont);
		cpuButton.setBounds(50,160,170,70);
		cpuButton.addActionListener(this);
		chooseGamePanel.add(cpuButton);
		
		twoPlyrButton = new MyButton("2 PLAYER");//cpu
		twoPlyrButton.setFont(buttonFont);
		twoPlyrButton.setBounds(50,300,170,70);
		twoPlyrButton.addActionListener(this);
		chooseGamePanel.add(twoPlyrButton);
		
		this.add(chooseGamePanel,"CHOOSE GAMEMODE");
		this.add(map, "MAP");
		this.add(level, "Levels");
		
		cl.show(this, "CHOOSE GAMEMODE");
	}
	//method that grabs the .wav file and plays the audio
	public void makeSound(){
	    File audio = new File("click.wav");
	    try{
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(audio));
	        FloatControl gainControl = 
	        	    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        	gainControl.setValue(-27.5f); // Reduce volume by 27.5 decibels.
	        clip.start();
	    } catch (Exception e){
	        e.printStackTrace();
	    }
	}
	//draws the instructions for the 2 game modes
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg,0,0,getWidth(),getHeight(),this);
		g.setColor(Color.WHITE);
		g.drawLine(45, 90, 600, 90);
		
		g.drawString("Play against the comptuer", 250, 200);
		g.drawString("Multiplayer and play against another person", 250, 340);
	}
	//shows the 2 different Panels which contain the 2 game modes
	@Override
	public void actionPerformed(ActionEvent e)
    {    
        if (e.getSource() == cpuButton){    
        	makeSound();//makes a clicking sound when you click one of the buttons
        	cl.show(this,"Levels");//shows the game panel
        }
        else if (e.getSource() == twoPlyrButton){  
        	makeSound();//makes a clicking sound when you click one of the buttons
        	cl.show(this,"MAP");//shows the game panel
        }
    }
}
