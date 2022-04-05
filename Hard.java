import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


//class for the hard level of the game. 
//hardest level available: the speed of the opponent is increased and it deals more damage. 
public class Hard extends JPanel implements KeyListener {
	// fields
	private Image bg = new ImageIcon("hardBG.gif").getImage();

	// gets fonts for each player
	private Image kenTitle = new ImageIcon("kenFont.png").getImage();
	private Image ryuTitle = new ImageIcon("ryuFont.png").getImage();
	Font buttonFont2 = new Font("ZapfDingbats", Font.PLAIN, 20);

	// images
	private Image hadukenImg = new ImageIcon("haduken.png").getImage();
	private Image KO = new ImageIcon("KO.png").getImage();

	// player/PU classes
	private Player1 player1;
	private CPU cpu;
	private HadukenRyu hadukenRyu;

	// timer classes
	private Timer cpuTimer;
	private Timer playerTimer1, hadukenTimerRyu;// timers for both players

	// position
	private double xP1, yP1, xP2, yP2, xHRyu, yHRyu;// x and y positions for both players
	private int width, height;// width and height of players

	// hitbounds
	private Rectangle player1HitBounds, player1AttackBounds, player2HitBounds, player2AttackBounds, ryuHadukenAB;

	// private JButton quitButtonRyu, backRyu, quitButtonKen, backKen;
	private JPanel panel;// panel after end of game
	private Texture tex = new Texture();

	// health/PU
	double healthRyu = 0;
	double healthKen = 225;
	private boolean ryuPU, powerUpEnabledRyu;
	private int ryuAmtFClicked = 0;
	private int hitKen = 0;
	ChooseDifficulty lvl;

	boolean hardPressed = false;

	//constructor that initializes variables and starts timers
	public Hard() {
		this.setBounds(0, 0, getWidth(), getHeight());

		setFocusable(true);
		requestFocus();// gives the panel focus to this panel
		addKeyListener(this);// adds keyListener to this panel
		width = 80;
		height = 150;

		// x and y positions of player 1(RYU)
		xP1 = 50;
		yP1 = 275;

		// x and y positions of player 2(CPU)
		xP2 = 600;
		yP2 = 275;

		// creates an instance of the player 1 class
		player1 = new Player1(width, height);
		playerTimer1 = new Timer(10, player1);// creates a timer to allow animation and movement
		playerTimer1.start();// starts timer

		hadukenRyu = new HadukenRyu(player1.getX() + 30, player1.getY() + 40);
		hadukenTimerRyu = new Timer(10, hadukenRyu);// creates a timer to allow animation and movement

		// creates an instance of the player 2 class
		cpu = new CPU(width, height,"hard");
		cpuTimer = new Timer(10, cpu);// creates a timer to allow animation and movement
		cpuTimer.start();// starts timer
	}

	//paint method which paints the 2 players, and updates the x and y coordinates
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg,0,0,(int)getWidth(), (int)getHeight(),null);

		xP1 = player1.getX();// gets the x value of player 1
		yP1 = player1.getY();// gets the y value of player 1

		xP2 = cpu.getX();// gets the x value of player 2
		yP2 = cpu.getY();// gets the y value of player 2

		// all RYU graphics and movement
		boolean idle = true;
		boolean crouch = true;
		if (player1.ifKick()) {
			idle = false;// sets idle to false, to make it so that only one image is playing at once
			crouch = false;
			g.drawImage(tex.ryu_kick[3], (int) xP1, (int) yP1, width + 50, height, this);// draws the kick image
		}
		if (player1.ifPunch()) {
			crouch = false;
			idle = false;// sets idle to false, to make it so that only one image is playing at once
			g.drawImage(tex.ryu_punch[2], (int) xP1, (int) yP1, width + 50, height, this);// draws the punching image
		}
		if (player1.ifCrouch() && crouch) {
			idle = false;// sets idle to false, to make it so that only one image is playing at once
			g.drawImage(tex.ryu_crouch[0], (int) xP1, (int) yP1 + 50, width, height - 50, this);// draws the kick image
		} else if (idle) {
			crouch = false;
			g.drawImage(tex.ryu_idle[0], (int) xP1, (int) yP1, width, height, this);// draws the idle image
		}

		// all KEN graphics and movement
		boolean idle1 = true;
		boolean crouch1 = true;
		if (cpu.ifKick()) {
			idle1 = false;// sets idle to false, to make it so that only one image is playing at once
			crouch1 = false;
			g.drawImage(tex.ken_kick[2], (int) xP2, (int) yP2, width - 210, height, this);// draws the kick image
		}
		if (cpu.ifPunch()) {
			idle1 = false;// sets idle to false, to make it so that only one image is playing at once
			crouch1 = false;
			g.drawImage(tex.ken_punch[2], (int) xP2, (int) yP2, width - 210, height, this);// draws the punching image
		}
		if (cpu.ifCrouch() && crouch1) {
			idle1 = false;// sets idle to false, to make it so that only one image is playing at once
			g.drawImage(tex.ken_crouch[0], (int) xP2, (int) yP2 + 50, width - 160, height - 50, this);// draws the
																										// crouch image
		}
		if (idle1) {
			crouch1 = false;
			g.drawImage(tex.ken_idle[0], (int) xP2, (int) yP2, width - 160, height, this);// draws the idle image
		}
		drawHitBounds(g);
		drawHealthBars(g);
		drawHaduken(g);
		repaint();
	}

	// draws the haduken and changes the health of the players when it deals damage
	private void drawHaduken(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.red);
		if (ryuPU && powerUpEnabledRyu) {
			hadukenTimerRyu.start();// starts timer
			xHRyu = hadukenRyu.getX();
			yHRyu = hadukenRyu.getY();

			ryuHadukenAB = new Rectangle(hadukenRyu.getAttackBounds().x + 20, hadukenRyu.getAttackBounds().y, hadukenRyu.getAttackBounds().width, hadukenRyu.getAttackBounds().height);

			if (player2HitBounds.intersects(ryuHadukenAB)) {
				healthKen = healthKen - 2.5;
				hitKen++;
			}
			if (hitKen >= 20)
				powerUpEnabledRyu = false;
			if (hitKen <= 20 && healthKen >= 0 && healthRyu <= 225) {
				g.drawImage(hadukenImg, (int) xHRyu + 20, (int) yHRyu, 70, 30, this);
				//g2d.draw(ryuHadukenAB);
			}
		}
		repaint();
	}

	// draws the health bars of each of the players
	private void drawHealthBars(Graphics g) {
		int damageDealtKen = 0;
		int damageDealtRyu = 0;
		if (player1HitBounds.intersects(player2AttackBounds)) {//if player ones hit bounds intersects player twos attack bounds(if player 1 got hit)
			damageDealtKen++;
		}
		if (player2HitBounds.intersects(player1AttackBounds)) {//if player twos hit bounds intersects player ones attack bounds(if player 2 got hit)
			damageDealtRyu++;
		}
		if (damageDealtKen == 1)
			healthRyu += 0.325;// change to 0.2
		if (damageDealtRyu == 1)
			healthKen -= 0.25;// change to 0.2
		g.drawImage(kenTitle, 515, 70, 70, 20, this);// draws the ken name image above the health bar
		g.drawImage(ryuTitle, 50, 70, 70, 20, this);// draws the ryu name image above the health bar
		g.drawImage(KO, 70, 10, 500, 40, this);// draws the ryu name image above the health bar

		g.setColor(Color.YELLOW);
		g.fillRect(70 + (int) healthRyu, 17, 225 - (int) healthRyu, 30);// ryu health bar
		g.fillRect(345, 17, (int) healthKen, 30);// ken health bar

		panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setBounds(0, 0, getWidth(), getHeight());
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
		if (healthRyu > 112.5) {
			ryuPU = true;
			if (healthRyu < 225 && healthKen > 0 && ryuAmtFClicked < 1)
				g.drawString("Power up available", 35, 120);
		}
		repaint();
	}

	// draws the hit/attack bounds of both players
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

	// currently not being used
	public void keyTyped(KeyEvent e) {}

	// passes in the keyPressed method into the player class
	public void keyPressed(KeyEvent e) {
		// passes in e.getKeyCode() with the parameter being the int of the key that you
		// pressed
		if (e.getKeyCode() == KeyEvent.VK_F && ryuPU == true && player1.getX() <= 50) {
			ryuAmtFClicked++;
			if (ryuAmtFClicked == 1) {
				powerUpEnabledRyu = true;
			}
		}
		player1.keyPressed(e.getKeyCode());
	}

	// passes in the keyReleased method into the player class
	public void keyReleased(KeyEvent e) {
		// passes in e.getKeyCode() with the parameter being the int of the key that you
		// released
		player1.keyReleased(e.getKeyCode());
	}
	//getters and setters
	public void hardPressed(boolean hardPressed) {//I pass in a boolen to the cpu class whether or not the button(if clicked hard) has been pressed
		this.hardPressed = hardPressed;
		cpu.hardPressed(hardPressed);
	}
}
