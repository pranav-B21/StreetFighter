//all imports
//Pranav Belligundu
//Kim per 5

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//Player class which includes the movement and KeyListners
//this is the CPU version where the user doenst control/play it, the computer does
public class CPU implements ActionListener {
	// fields
	public static BufferedImage idle;
	// player Movement
	private boolean rightPressed = false, leftPressed = false, jumping = false, falling = false;
	// fighting moves
	private boolean punch, kick, crouch;
	private double x, y;
	private int width, height;

	// jump speed
	private double jumpSpd = 4.5;
	private double currentJumpSpd = jumpSpd;
	Timer timer = new Timer();
	private int randNum;
	Random rand = new Random();

	// fall speed
	private double fallspd = 2.2;
	private double currentFallSpd = fallspd;

	boolean easyPressed, medPressed,hardPressed = false;
	
	private String difficulty;

	// constructor which receives all passed in variables
	public CPU(int width, int height, String difficulty) {
		x = 600;
		y = 275;
		this.difficulty = difficulty; 
		this.width = width;
		this.height = height;
	}
	//runs the game and 
	public void runGame() {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {	
				randNum = getRandNum();//every 800ms it calls the getRandNum method and gets a number between 1-6 
				if (randNum == 0)//0 == move right
					rightPressed = true;
				else
					rightPressed = false;

				if (randNum == 1)//1 == move left
					leftPressed = true;
				else
					leftPressed = false;

				if (randNum == 2)//2 == jump
					jumping = true;

				if (randNum == 5) {//5 == crouch
					if ((!(randNum == 4) || (randNum == 5)))
						crouch = true;
				} else
					crouch = false;
			}
		}, 1000, 800);// wait 1000 ms before doing the action and do it every 800ms
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				randNum = getRandNum();//every 380ms it calls the getRandNum method and gets a number between 1-6 
				if (randNum == 3) {//3 ==  punch
					if ((!(randNum == 4))) {// logic that doesnt allow you to kick/punch/crouch at once
						kick = false;
						punch = true;
						crouch = false;
					}
				} else
					punch = false;

				if (randNum == 4) {//4 == kick
					if ((!(randNum == 3))) {// logic that doesnt allow you to kick/punch/crouch at once
						punch = false;
						kick = true;
						crouch = false;
					}
				} else
					kick = false;
			}
		}, 1000, 380);// wait 1000 ms before doing the action and do it every 380ms
	}
	// returns a random number from o-6
	private int getRandNum() {
		return rand.nextInt(6);
	}
	// action performed method to change the x-values/jumping of the player to suit the player inputs
	public void actionPerformed(ActionEvent e) {
		if(difficulty.equals("easy")||difficulty.equals("medium")) {
			if (rightPressed) {
				x = x + 2;// moves the x value to the right
			}
			if (leftPressed) {
				x = x - 2;// moves the x value to the left
			}
			// calculates the jumping speed
			if (jumping) {
				y -= currentJumpSpd;
				currentJumpSpd -= 0.095;// slows the y value ascend as it goes up

				if (currentJumpSpd <= 0) {
					currentJumpSpd = jumpSpd;// when the jump speed = 0, the player is not falling anymore
					jumping = false;
					falling = true;
				}

			}
			// calculates the falling speed
			if (falling) {
				y += currentFallSpd;
				if (currentFallSpd < fallspd) {
					currentFallSpd += 1;// increments the player falling
				}
				if (y >= 275)
					falling = false;// when the player y-value is greater than 275, it stops falling and "hits" the ground
			}
			// sets bounds so that the player cannot leave the JFrame
			if (x < 130)
				x = 130;
			if (x > 640)
				x = 640;
			if (y > 275)
				y = 275;

		}
		else if(difficulty.equals("hard")) {
			if (rightPressed) {
				x = x + 3;// moves the rect to the right
			}
			if (leftPressed) {
				x = x - 3;// moves the rect to the left
			}
			// calculates the jumping speed
			if (jumping) {
				y -= currentJumpSpd;
				currentJumpSpd -= 0.095;// slows the y value ascend as it goes up

				if (currentJumpSpd <= 0) {
					currentJumpSpd = jumpSpd;// when the jump speed = 0, the player is not falling anymore
					jumping = false;
					falling = true;
				}

			}
			// calculates the falling speed
			if (falling) {
				y += currentFallSpd;
				if (currentFallSpd < fallspd) {
					currentFallSpd += 1;// increments the player falling
				}
				if (y >= 275)
					falling = false;// when the player y-value is greater than 275, it stops falling and "hits" the ground
			}
			// sets bounds so that the player cannot leave the JFrame
			if (x < 130)
				x = 130;
			if (x > 640)
				x = 640;
			if (y > 275)
				y = 275;

		}
	}
	// gets the hit bounds of player 2
	public Rectangle getHitBounds() {
		if (crouch == true)
			return new Rectangle((int) x, (int) y + 50, 80, 100);// returns crouch bounds
		return new Rectangle((int) x, (int) y, 80, 150);// returns idle bounds
	}

	// gets the attack bounds of player 2
	public Rectangle getAttackBounds() {
		if (kick)
			return new Rectangle((int) x - 50, (int) y + 45, 20, 20);// attack bounds for punching
		if (punch)
			return new Rectangle((int) x - 50, (int) y + 10, 30, 25);// attack bounds for kicking
		return new Rectangle((int) x, (int) y, 0, 0);// returns idle bounds if not punching/kicking
	}
	//getters and setters
	public double getX() {// returns the x value
		return x;
	}

	public double getY() {// returns the y value
		return y;
	}

	public boolean ifPunch() {// returns if user pressed punch
		return punch;
	}

	public boolean ifKick() {// returns if the user kicked
		return kick;
	}

	public boolean ifRight() {// returns if the user pressed the D button
		return rightPressed;
	}

	public boolean ifLeft() {// returns if the user pressed the A button
		return leftPressed;
	}

	public boolean ifUp() {// returns if the player is jumping
		return jumping;
	}

	public boolean ifCrouch() {// returns if the player is jumping
		return crouch;
	}
	public void easyPressed(boolean easyPressed) {//receives a boolean that checks if the "easy" button is pressed
		this.easyPressed = easyPressed;
		if (easyPressed)
			runGame();//only if true call runGame();
	}

	public void medPressed(boolean medPressed) {//receives a boolean that checks if the "medium" button is pressed
		this.medPressed = medPressed;
		if (medPressed)
			runGame();//only if true runGame();
	}
	public void hardPressed(boolean hardPressed) {//receives a boolean that checks if the "hard" button is pressed
		this.hardPressed = hardPressed;
		if (hardPressed)
			runGame();//only if true runGame();
	}
}
