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

//this class contains the game/allows user to choose the map
public class ChooseMap extends JPanel implements MouseListener, ActionListener{
	//fields
	private Image bg = new ImageIcon("instBG.png").getImage();
	private Image c1bg = new ImageIcon("choice1bg.gif").getImage();
	private Image c2bg = new ImageIcon("choice2bg.gif").getImage();
	private Image c3bg = new ImageIcon("choice3bg.gif").getImage();
	//all components
	private JPanel namePanelGame, startGamePanel, bgSelected;
	private JLabel title;
	private JButton lvl1Button, lvl2Button, lvl3Button;
	private CardLayout cl = new CardLayout();
	private Game game;
	boolean overLevel1, overLevel2, overLevel3;

	
	Font titleFont = new Font("ZapfDingbats", Font.BOLD, 51);
	Font buttonFont = new Font("ZapfDingbats", Font.PLAIN, 30);
	
	//the constructor method where I initialize add components that are part of the map choosing panel
	public ChooseMap() {
		this.setBounds(0,0,getWidth(),getHeight());
		this.setLayout(cl);//sets the layout to cardLayout
		
		//JPanel for the game
		game = new Game();
		
		//main JPanel where all other components are added. 
		startGamePanel = new JPanel();
		startGamePanel.setLayout(null);
		startGamePanel.setOpaque(false);
		startGamePanel.setBounds(0,0,getWidth(),getHeight());
		
		namePanelGame = new JPanel();//creates JPanel for the title of the JPanel
		namePanelGame.setBounds(95,20,450,100);
		namePanelGame.setOpaque(false);
		startGamePanel.add(namePanelGame);
		
		title = new JLabel("MAPS");//title of the JPanel
		title.setForeground(Color.WHITE);
		title.setFont(titleFont);
		namePanelGame.add(title);
	
		lvl1Button = new MyButton("MAP 1");//map 1 button
		lvl1Button.setFont(buttonFont);
		lvl1Button.setBounds(50,130,150,70);
		lvl1Button.addActionListener(this);
		lvl1Button.addMouseListener(this);
		startGamePanel.add(lvl1Button);
		
		lvl2Button = new MyButton("MAP 2");//map 2 button
		lvl2Button.setFont(buttonFont);
		lvl2Button.setBounds(50,230,150,70);
		lvl2Button.addActionListener(this);
		lvl2Button.addMouseListener(this);
		startGamePanel.add(lvl2Button);
		
		lvl3Button = new MyButton("MAP 3");//map 3 button
		lvl3Button.setFont(buttonFont);
		lvl3Button.setBounds(50,330,150,70);
		lvl3Button.addActionListener(this);
		lvl3Button.addMouseListener(this);
		startGamePanel.add(lvl3Button);
		
		bgSelected = new JPanel();//JPanel where the map is going to show
		bgSelected.setBounds(240,120,370,280);
		bgSelected.setOpaque(false);
		startGamePanel.add(bgSelected);
		
		//adds all panels that have been created in this class
		this.add(startGamePanel, "CHOOSE_MAP");
		this.add(game, "GAME");
		
		cl.show(this,"CHOOSE_MAP");//shows the current Panel
		
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
	//this is needed for the cardLayout to work, detects which button is being pressed
	public void actionPerformed(ActionEvent e)
    {    
        if (e.getSource() == lvl1Button){    
        	makeSound();//makes a clicking sound when you click one of the buttons
        	cl.show(this,"GAME");//shows the game panel
        	game.getMap("map1");//setter
        }
        else if (e.getSource() == lvl2Button){ 
        	makeSound();//makes a clicking sound when you click one of the buttons
        	cl.show(this,"GAME");//shows the game panel
        	game.getMap("map2");//setter
        	
        }
        else if (e.getSource() == lvl3Button){  
        	makeSound();//makes a clicking sound when you click one of the buttons
        	cl.show(this,"GAME");//shows the game panel
        	game.getMap("map3");//setter
        	
        }
    }
	@Override
	//checks if the mouse has entered the button
	//when the user rolls over the different maps, it will set the boolean to true
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == lvl1Button) {
			overLevel1 = true;
		}
		if(e.getSource() == lvl2Button) {
			overLevel2 = true;
		}
		if(e.getSource() == lvl3Button) {
			overLevel3 = true;
		}
	}
	@Override
	//checks if the mouse exited the button
	//when the user rolls out the different levels, it will set the boolean to false
	public void mouseExited(MouseEvent e) {
		if((e.getSource() == lvl1Button)) {
			overLevel1 = false;
		}
		if((e.getSource() == lvl2Button)) {
			overLevel2 = false;
		}
		if((e.getSource() == lvl3Button)) {
			overLevel3 = false;
		}
	}
	
	/*
	 * paints this with graphics
	 * Includes the line below the title and background
	*/
	public void paintComponent(Graphics g) {
		g.drawImage(bg,0,0,getWidth(),getHeight(),this);//draws bg
		g.setColor(Color.WHITE);
		g.drawLine(155, 90, 485, 90);//draws line around the map showing area
		g.drawRect(240,120,370,280);//draws rect around the map showing area
		g.fillRect(240,120,370,280);//fills rect around the map showing area
	
		if(overLevel1) g.drawImage(c1bg,240,120,370,280,bgSelected);
		if(overLevel2) g.drawImage(c2bg,240,120,370,280,bgSelected);
		if(overLevel3) g.drawImage(c3bg,240,120,370,280,bgSelected);
		repaint();
	}
	//unused MouseListener methods(might need later)
	@Override
	public void mousePressed(MouseEvent e) {}//checks if the mouse has been pressed
	@Override
	public void mouseReleased(MouseEvent e) {}//checks if the mouse has been released
	@Override
	public void mouseClicked(MouseEvent e) {}//checks if the mouse has been clicked
	
	
}


