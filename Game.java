
//all imports
//Pranav Belligundu
//Kim per 5
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//main class where the game is played, a 2 player game
public class Game extends JPanel implements KeyListener{
	//fields
	
	//gets all bg's
	private Image c1bg = new ImageIcon("choice1bg.gif").getImage();
	private Image c2bg = new ImageIcon("choice2bg.gif").getImage();
	private Image c3bg = new ImageIcon("choice3bg.gif").getImage();
	
	//gets fonts for each player
	private Image kenTitle = new ImageIcon("kenFont.png").getImage();
	private Image ryuTitle = new ImageIcon("ryuFont.png").getImage();
	
	//images
	private Image hadukenImg = new ImageIcon("haduken.png").getImage();
	private Image KO = new ImageIcon("KO.png").getImage();
	
	private Player1 player1;
	private Player2 player2;
	private HadukenRyu hadukenRyu;
	private HadukenKen hadukenKen;
	private Timer playerTimer1, playerTimer2, hadukenTimerRyu, hadukenTimerKen;//timers for both players
	private double xP1,yP1, xP2, yP2, xHRyu, yHRyu, xHKen,yHKen;//x and y positions for both players
	private int width, height;//width and height of players
	private Rectangle player1HitBounds,player1AttackBounds,player2HitBounds,player2AttackBounds, ryuHadukenAB, kenHadukenAB;//attack bounds/hit bounds of players
	
	//private JButton quitButtonRyu, backRyu, quitButtonKen, backKen;
	private JPanel panel;//panel after end of game
	private String map;
	private Texture tex = new Texture();
	double healthRyu = 0;
	double healthKen = 225;
	private boolean kenPU,ryuPU, powerUpEnabledRyu, powerUpEnabledKen;
	Font buttonFont2 = new Font("ZapfDingbats", Font.PLAIN, 20);
	private int ryuAmtFClicked = 0;
	private int kenAmtBClicked = 0;
	private int hitRyu = 0;
	private int hitKen = 0;
	
	int damageDealtKen = 0;
	int damageDealtRyu = 0;
	
	//constructor that calls the start method, and the player method
	public Game() {	
		this.setBounds(0,0,getWidth(),getHeight());
		
		setFocusable(true);
	    requestFocus();//gives the panel focus to this panel
	    addKeyListener(this);//adds keyListener to this panel
		width = 80;
		height = 150;
		
		//x and y positions of player 1(RYU)
		xP1 = 50;
		yP1 = 275;
		
		//x and y positions of player 1(KEN)
		xP2 = 600;
		yP2 = 275;
		
		hadukenTimerRyu = new Timer(10,hadukenRyu);//creates a timer to allow animation and movement
		hadukenTimerKen = new Timer(10,hadukenKen);//creates a timer to allow animation and movement

	    //creates an instance of the player 1 class
	    player1 = new Player1(width,height);
	    playerTimer1 = new Timer(12,player1);//creates a timer to allow animation and movement
	    playerTimer1.start();//starts timer
		
	    //creates an instance of the player 2 class
		player2 = new Player2(width,height);
		playerTimer2 = new Timer(12,player2);//creates a timer to allow animation and movement
		playerTimer2.start();//starts timer		
		
		hadukenRyu = new HadukenRyu(player1.getX()+30,player1.getY()+40);
		hadukenTimerRyu = new Timer(15,hadukenRyu);//creates a timer to allow animation and movement
		//hadukenTimerRyu.start();//starts timer
		
		hadukenKen = new HadukenKen(player2.getX()+30,player2.getY()+40);
		hadukenTimerKen = new Timer(15,hadukenKen);//creates a timer to allow animation and movement
		//hadukenTimerKen.start();//starts timer

	}
	//paint method which paints the 2 players, and updates the x and y coordinates
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//shows the bg that user choose
		if(map.equals("map1")) g.drawImage(c1bg,0,0,getWidth(),getHeight(),this);
		if(map.equals("map2")) g.drawImage(c2bg,0,0,getWidth(),getHeight(),this);
		if(map.equals("map3")) g.drawImage(c3bg,0,0,getWidth(),getHeight(),this);
		 
		xP1 = player1.getX();//gets the x value of player 1
		yP1 = player1.getY();//gets the y value of player 1
		
		xP2 = player2.getX();//gets the x value of player 2
		yP2 = player2.getY();//gets the y value of player 2
		
		System.out.println(xP1);
	
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
			g.drawImage(tex.ryu_crouch[0],(int)xP1, (int)yP1+50,width,height-50,this);//draws the kick image
		}
		else if(idle) {
			crouch = false;
			g.drawImage(tex.ryu_idle[0],(int)xP1, (int)yP1,width,height,this);//draws the idle image
		}

		//all KEN graphics and movement
		boolean idle1 = true;
		boolean crouch1 = true;
		if(player2.ifKick()) {
			idle1 = false;//sets idle to false, to make it so that only one image is playing at once
			crouch1 = false;
			g.drawImage(tex.ken_kick[2],(int)xP2, (int)yP2,width-210,height,this);//draws the kick image
		}
		if(player2.ifPunch()) {
			idle1 = false;//sets idle to false, to make it so that only one image is playing at once
			crouch1 = false;
			g.drawImage(tex.ken_punch[2],(int)xP2, (int)yP2,width-210,height,this);//draws the punching image
		}
		if(player2.ifCrouch() && crouch1)
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
		drawHaduken(g);	
		repaint();
	}
	//draws the haduken and changes the health of the players when it deals damage
	private void drawHaduken(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		if(ryuPU && powerUpEnabledRyu) {
			hadukenTimerRyu.start();//starts timer
			
			//gets the x and y position
			xHRyu = hadukenRyu.getX();
			yHRyu = hadukenRyu.getY();
		
			ryuHadukenAB = new Rectangle(hadukenRyu.getAttackBounds().x+20, hadukenRyu.getAttackBounds().y, hadukenRyu.getAttackBounds().width, hadukenRyu.getAttackBounds().height);//the hadukem hitbounds
			
			if(player2HitBounds.intersects(ryuHadukenAB)) {
				healthKen = healthKen - 2.5;//decreases health if the haduken hits them
				hitKen++;
			}
			if(hitKen >= 20) powerUpEnabledRyu = false;
			if(hitKen <= 20 && healthKen >= 0 && healthRyu <= 225) {
				g.drawImage(hadukenImg,(int)xHRyu+20,(int)yHRyu,70,30,this);//draws the haduken
				//g2d.draw(ryuHadukenAB);
			}
		}
		
		if(kenPU && powerUpEnabledKen) {
			hadukenTimerKen.start();//starts timer
			
			//gets the x and y position
			xHKen = hadukenKen.getX();
			yHKen = hadukenKen.getY();
			
			kenHadukenAB = new Rectangle(hadukenKen.getAttackBounds().x-180, hadukenKen.getAttackBounds().y, hadukenKen.getAttackBounds().width, hadukenKen.getAttackBounds().height);//the hadukem hitbounds
			
			if(player1HitBounds.intersects(kenHadukenAB)) {
				healthRyu = healthRyu + 2.5;//decreases health if the haduken hits them
				hitRyu++;				
			}
			if(hitRyu >= 20) powerUpEnabledKen = false;
			if(hitRyu <= 20 && healthRyu <= 225 && healthKen >= 0) {
				g.drawImage(hadukenImg,(int)xHKen-100,(int)yHKen,-70,30,this);//draws the haduken
				//g2d.draw(kenHadukenAB);
			}
		}
		repaint();
	}
	//draws the health bars of each of the players
	private void drawHealthBars(Graphics g) {
		if(player1HitBounds.intersects(player2AttackBounds)) {//if player ones hit bounds intersects player twos attack bounds(if player 1 got hit)
			damageDealtKen++;//increment the variable
		}
		if(player2HitBounds.intersects(player1AttackBounds)) {//if player twos hit bounds intersects player ones attack bounds(if player 2 got hit)
			damageDealtRyu++;//increment the variable
		}
		if(damageDealtKen == 1) {
			healthRyu +=0.25;
		}
		if(damageDealtRyu == 1) {
			healthKen -=0.25;
		}
		damageDealtKen = 0;//resets counter back to 0
		damageDealtRyu = 0;//resets counter back to 0
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
			String ryuWin = "KEN WINS THE GAME!";
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 650, 450);
			g.setColor(Color.WHITE);
			g.drawString(ryuWin,220,180);
			
			Menu menu = new Menu();
			panel.add(menu.quitEnd());//adds the quit button initialzied in the Menu class
		}
		if(healthKen<=0) {//if ryu wins
		
			g.setFont(buttonFont2);
			String ryuWin = "RYU WINS THE GAME!";
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 650, 450);
			g.setColor(Color.WHITE);
			g.drawString(ryuWin,220,180);
			
			Menu menu = new Menu();
			panel.add(menu.quitEnd());//adds the quit button initialzied in the Menu class
		}
		if(healthKen<112.5) {
			kenPU = true;
			if(map.equals("map1")) g.setColor(Color.YELLOW);
			else if(map.equals("map2")) g.setColor(Color.YELLOW);
			else if(map.equals("map3")) g.setColor(Color.MAGENTA);
			
			
			if(healthKen > 0 && healthRyu < 225 && kenAmtBClicked < 1)g.drawString("Power up available",490,120);//string shows up on screen based on condition which allows the player to use a power up
		}
		//System.out.println(kenPU);
		if(healthRyu>112.5){
			ryuPU = true;;
			if(map.equals("map1")) g.setColor(Color.YELLOW);
			else if(map.equals("map2")) g.setColor(Color.YELLOW);
			else if(map.equals("map3")) g.setColor(Color.MAGENTA);
			
			if(healthRyu < 225 && healthKen > 0 && ryuAmtFClicked < 1)g.drawString("Power up available",35,120);//string shows up on screen based on condition which allows the player to use a power up
		}
		repaint();
	}
	//draws the hit/attack bounds of both players and intitializes them
	private void drawHitBounds(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		//RYU hit bounds
		g.setColor(Color.WHITE);
		player1HitBounds = new Rectangle(player1.getHitBounds().x, player1.getHitBounds().y, player1.getHitBounds().width, player1.getHitBounds().height);
		//g2d.draw(player1HitBounds);
		
		//RYU attack bounds
		g.setColor(Color.RED);
		player1AttackBounds = new Rectangle(player1.getAttackBounds().x, player1.getAttackBounds().y, player1.getAttackBounds().width, player1.getAttackBounds().height);
		//g2d.draw(player1AttackBounds);
		
		//KEN hit bounds
		g.setColor(Color.WHITE);
		player2HitBounds = new Rectangle(player2.getHitBounds().x-80, player2.getHitBounds().y, player2.getHitBounds().width, player2.getHitBounds().height);
		///g2d.draw(player2HitBounds);
		
		//KEN attack bounds
		g.setColor(Color.RED);
		player2AttackBounds = new Rectangle(player2.getAttackBounds().x-80, player2.getAttackBounds().y, player2.getAttackBounds().width, player2.getAttackBounds().height);
		//g2d.draw(player2AttackBounds);
	
		repaint();
	}
	//currently not being used
	public void keyTyped(KeyEvent e) {}
	//passes in the keyPressed method into the player class
	public void keyPressed(KeyEvent e) {
		//passes in e.getKeyCode() with the parameter being the int of the key that you pressed
		if(e.getKeyCode() == KeyEvent.VK_F && ryuPU == true && player1.getX() <= 50) {//parameters that only allows user to use the powerup once and based on the players x position
			ryuAmtFClicked++;
			if(ryuAmtFClicked == 1) {
				powerUpEnabledRyu = true;//ryu can use the power up
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_B && kenPU == true &&  player2.getX() >= 600) {//parameters that only allows user to use the powerup once and based on the players x position
			kenAmtBClicked++;
			if(kenAmtBClicked == 1){
				powerUpEnabledKen = true;//ken can use the power up
			}
		}
		player1.keyPressed(e.getKeyCode());
		player2.keyPressed(e.getKeyCode());
	}
	//passes in the keyReleased method into the player class
	public void keyReleased(KeyEvent e) { 
		//passes in e.getKeyCode() with the parameter being the int of the key that you released
		player1.keyReleased(e.getKeyCode());
		player2.keyReleased(e.getKeyCode());
	}
	//getters and setters
	//gets the map/bg that was choosen by the user
	public void getMap(String map) {
		this.map = map;
	}
}
