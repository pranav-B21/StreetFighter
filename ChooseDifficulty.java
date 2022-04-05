//imports
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//class where you can choose the level of difficulty in the game
public class ChooseDifficulty extends JPanel implements ActionListener {
	//fields
	private Image bg = new ImageIcon("instBG.png").getImage();
	private JLabel title;
	private JButton lvl1Button, lvl2Button, lvl3Button;
	public static boolean easyPressed, medPressed, hardPressed;
	private JPanel chooseLevels, titlePanel;
	private ChooseMap map;
	private Easy easy;
	private Medium med;
	private Hard hard;

	Font titleFont = new Font("ZapfDingbats", Font.BOLD, 51);
	Font buttonFont = new Font("ZapfDingbats", Font.PLAIN, 30);
	private CardLayout cl = new CardLayout();
	
	//constructor method where 
	public ChooseDifficulty() {
		this.setBounds(0, 0, getWidth(), getHeight());
		this.setLayout(cl);// sets the layout to cardLayout

		map = new ChooseMap();
		easy = new Easy();
		med = new Medium();
		hard = new Hard();
		chooseLevels = new JPanel();
		chooseLevels.setLayout(null);
		chooseLevels.setOpaque(false);
		chooseLevels.setBounds(0, 0, getWidth(), getHeight());

		titlePanel = new JPanel();// creates JPanel for the title of the JPanel
		titlePanel.setBounds(45, 20, 550, 100);
		titlePanel.setOpaque(false);
		chooseLevels.add(titlePanel);

		title = new JLabel("DIFFICULTY");// title of the JPanel
		title.setForeground(Color.WHITE);
		title.setFont(titleFont);
		titlePanel.add(title);

		lvl1Button = new MyButton("EASY");// level 1
		lvl1Button.setFont(buttonFont);
		lvl1Button.setBounds(50, 200, 150, 70);
		lvl1Button.addActionListener(this);
		chooseLevels.add(lvl1Button);

		lvl2Button = new MyButton("MEDIUM");// level 2
		lvl2Button.setFont(buttonFont);
		lvl2Button.setBounds(250, 200, 150, 70);
		lvl2Button.addActionListener(this);
		chooseLevels.add(lvl2Button);

		lvl3Button = new MyButton("HARD");// level 3
		lvl3Button.setFont(buttonFont);
		lvl3Button.setBounds(450, 200, 150, 70);
		lvl3Button.addActionListener(this);
		chooseLevels.add(lvl3Button);

		this.add(chooseLevels, "CHOOSE LEVELS");
		this.add(easy, "EASY");
		this.add(med, "MEDIUM");
		this.add(hard, "HARD");

		cl.show(this, "CHOOSE LEVELS");
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
	//paints the background
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		g.setColor(Color.WHITE);
		g.drawLine(45, 90, 600, 90);
	}
	//shows the 3 different Panels based on which button the user clicked, easy medium or hard
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == lvl1Button) {
			makeSound();//makes a clicking sound when you click one of the buttons
			cl.show(this, "EASY");// shows level1
			easyPressed = true;
			easy.easyPressed(easyPressed);	
		}
		else if (e.getSource() == lvl2Button) {
			makeSound();//makes a clicking sound when you click one of the buttons
			cl.show(this, "MEDIUM");// shows level2
			medPressed = true;
			med.medPressed(medPressed);
			
		}
		else if (e.getSource() == lvl3Button) {
			makeSound();//makes a clicking sound when you click one of the buttons
			cl.show(this, "HARD");// shows level3
			hardPressed = true;
			hard.hardPressed(hardPressed);
		}
	}
}
