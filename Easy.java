//all imports
//Pranav Belligundu
//Kim per 5
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//class for the easy level of the game. 
//easiest level where the opponent deals the same damage and the same speed as you
public class Easy extends JPanel implements KeyListener{
	//fields
	
	//gets all bg's
	private Image bg = new ImageIcon("easyBG.gif").getImage();
	
	//gets fonts for each player
	private Image kenTitle = new ImageIcon("kenFont.png").getImage();
	private Image ryuTitle = new ImageIcon("ryuFont.png").getImage();
	
	//images
	private Image hadukenImg = new ImageIcon("haduken.png").getImage();
	private Image KO = new ImageIcon("KO.png").getImage();

	private Timer playerTimer1, cpuTimer;
	private double xP1,yP1, xP2, yP2;//x and y positions for both players
	public JButton quitButtonEnd, backEnd;
	private int width, height;//width and height of players
	private Rectangle player1HitBounds,player1AttackBounds,player2HitBounds,player2AttackBounds;//attack bounds/hit bounds of players
	
	//private JButton quitButtonRyu, backRyu, quitButtonKen, backKen;
	private JPanel panel;//panel after end of game
	double healthRyu = 0;
	double healthKen = 225;
	Font buttonFont2 = new Font("ZapfDingbats", Font.PLAIN, 20);
	ChooseDifficulty lvl;
	boolean easyPressed = false;
	
	//instances of classes
	private String map;
	private Texture tex;
	private Player1 player1;
	private CPU cpu;
	
	//constructor that initializes variables and starts timers
	public Easy() {	
		this.setBounds(0,0,getWidth(),getHeight());
		setFocusable(true);
	    requestFocus();//gives the panel focus to this panel
	    addKeyListener(this);//adds keyListener to this panel
		
	    width = 80;
		height = 150;
		
		//x and y positions of player 1(RYU)
		xP1 = 50;
		yP1 = 275;
		
		//x and y positions of player 2(CPU)
		xP2 = 600;
		yP2 = 275;

		tex = new Texture();//instance of texture class for the images of the player
		
	    //creates an instance of the player 1 class
	    player1 = new Player1(width,height);//passes in width and height of player
	    playerTimer1 = new Timer(10,player1);//creates a timer to allow animation and movement
	    playerTimer1.start();//starts timer
		
	    //creates an instance of CPU class
	    cpu = new CPU(width,height, "easy");//passes in a string "easy" 
		cpuTimer = new Timer(10,cpu);//creates a timer to allow animation and movement
		cpuTimer.start();//starts timer	
		
	}
	//paint method which paints the 2 players, and updates the x and y coordinates
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg,0,0,(int)getWidth(), (int)getHeight(),null);//draws bg

		xP1 = player1.getX();//gets the x value of player 1
		yP1 = player1.getY();//gets the y value of player 1
		
		xP2 = cpu.getX();//gets the x value of player 2
		yP2 = cpu.getY();//gets the y value of player 2
	
		//all RYU graphics and movement
		boolean idle = true;
		boolean crouch = true;
		if(player1.ifKick()) {
			idle = false;//sets idle to false, to make it so that only one image is playing at once
			crouch = false;
			g.drawImage(tex.ryu_kick[3],(int)xP1, (int)yP1,width+50,height,this);//draws the kick image
		}
		if(player1.ifPunch()) {
			crouch = false;
			idle = false;//sets idle to false, to make it so that only one image is playing at once
			g.drawImage(tex.ryu_punch[2],(int)xP1, (int)yP1,width+50,height,this);//draws the punching image
		}
		if(player1.ifCrouch() && crouch) {
			idle = false;//sets idle to false, to make it so that only one image is playing at once
			//punch = false;
			g.drawImage(tex.ryu_crouch[0],(int)xP1, (int)yP1+50,width,height-50,this);//draws the kick image
		}
		else if(idle) {
			crouch = false;
			g.drawImage(tex.ryu_idle[0],(int)xP1, (int)yP1,width,height,this);//draws the idle image
		}

		//all KEN(cpu) graphics and movement
		boolean idle1 = true;
		boolean crouch1 = true;
		if(cpu.ifKick()) {
			idle1 = false;//sets idle to false, to make it so that only one image is playing at once
			crouch1 = false;
			g.drawImage(tex.ken_kick[2],(int)xP2, (int)yP2,width-210,height,this);//draws the kick image
		}
		if(cpu.ifPunch()) {
			idle1 = false;//sets idle to false, to make it so that only one image is playing at once
			crouch1 = false;
			g.drawImage(tex.ken_punch[2],(int)xP2, (int)yP2,width-210,height,this);//draws the punching image
		}
		if(cpu.ifCrouch() && crouch1)
		{
			idle1 = false;//sets idle to false, to make it so that only one image is playing at once
			g.drawImage(tex.ken_crouch[0],(int)xP2, (int)yP2+50,width-160,height-50,this);//draws the crouch image
		}
		if(idle1) {
			crouch1 = false;
			g.drawImage(tex.ken_idle[0],(int)xP2, (int)yP2,width-160,height,this);//draws the idle image
		}
		drawHitBounds(g);
		drawHealthBars(g);	
		repaint();
	}
	//draws the health bars of both players
	private void drawHealthBars(Graphics g) {
		int damageDealtKen = 0;
		int damageDealtRyu = 0;
		if(player1HitBounds.intersects(player2AttackBounds)) {//if player ones hit bounds intersects player twos attack bounds(if player 1 got hit)
			damageDealtKen++;//increment the variable
		}
		if(player2HitBounds.intersects(player1AttackBounds)) {//if player twos hit bounds intersects player ones attack bounds(if player 2 got hit)
			damageDealtRyu++;//increment the variable
		}
		if(damageDealtKen == 1)healthRyu +=0.25;//increments health since health bar is flipped
		if(damageDealtRyu == 1)healthKen -=0.25;//decreases the health
		
		g.drawImage(kenTitle,515, 70,70,20,this);//draws the ken name image above the health bar
		g.drawImage(ryuTitle,50, 70,70,20,this);//draws the ryu name image above the health bar
		g.drawImage(KO,70, 10,500,40,this);//draws the ryu name image above the health bar
		
		g.setColor(Color.YELLOW);
		g.fillRect(70+(int)healthRyu, 17, 225-(int)healthRyu, 30);//ryu health bar
		g.fillRect(345, 17,(int)healthKen, 30);//ken health bar
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setBounds(0,0,getWidth(),getHeight());
		this.add(panel);
		
		if(healthRyu>=225) {//if ken wins
			g.setFont(buttonFont2);
			String ryuWin = "YOU LOST THE GAME :(";
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 650, 450);
			g.setColor(Color.WHITE);
			g.drawString(ryuWin,220,180);//draws the string

			Menu menu = new Menu();
			panel.add(menu.quitEnd());//adds the quit button initialized in the Menu class
		}
		if(healthKen<=0) {//if ryu wins
			g.setFont(buttonFont2);
			String ryuWin = "YOU WON THE GAME!";
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 650, 450);
			g.setColor(Color.WHITE);
			g.drawString(ryuWin,220,180);//draws the string
			
			Menu menu = new Menu();
			panel.add(menu.quitEnd());//adds the quit button initialized in the Menu class
		}
		repaint();
	}
	//draws the hit/attack bounds of both players
	private void drawHitBounds(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		// RYU hit bounds
		g.setColor(Color.WHITE);
		player1HitBounds = new Rectangle(player1.getHitBounds().x, player1.getHitBounds().y, player1.getHitBounds().width, player1.getHitBounds().height);
		// g2d.draw(player1HitBounds); draws hit bounds
		
		// RYU attack bounds
		g.setColor(Color.RED);
		player1AttackBounds = new Rectangle(player1.getAttackBounds().x, player1.getAttackBounds().y, player1.getAttackBounds().width, player1.getAttackBounds().height);
		// g2d.draw(player1AttackBounds); draws attack bounds

		// KEN hit bounds
		g.setColor(Color.WHITE);
		player2HitBounds = new Rectangle(cpu.getHitBounds().x - 80, cpu.getHitBounds().y, cpu.getHitBounds().width, cpu.getHitBounds().height);
		// g2d.draw(player2HitBounds); draws hit bounds
		
		// KEN attack bounds
		g.setColor(Color.RED);
		player2AttackBounds = new Rectangle(cpu.getAttackBounds().x - 80, cpu.getAttackBounds().y, cpu.getAttackBounds().width, cpu.getAttackBounds().height);
		// g2d.draw(player2AttackBounds); draws attack bounds
	
		repaint();
	}
	//currently not being used
	public void keyTyped(KeyEvent e) {}
	//passes in the keyPressed method into the player class
	public void keyPressed(KeyEvent e) {
		//passes in e.getKeyCode() with the parameter being the int of the key that you pressed
		player1.keyPressed(e.getKeyCode());
	}
	//passes in the keyReleased method into the player class
	public void keyReleased(KeyEvent e) { 
		//passes in e.getKeyCode() with the parameter being the int of the key that you released
		player1.keyReleased(e.getKeyCode());
	}
	//getters and setters
	public void easyPressed(boolean easyPressed) {//I pass in a boolean to the cpu class whether or not the button(if clicked easy) has been pressed
	    this.easyPressed = easyPressed;
	   	cpu.easyPressed(easyPressed);
	}
}
