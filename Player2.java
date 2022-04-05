//all imports
//Pranav Belligundu
//Kim per 5
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

//Player class which includes the movement and KeyListners
public class Player2 implements ActionListener {
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

	// fall speed
	private double fallspd = 2.2;
	private double currentFallSpd = fallspd;

	// constructor which receives all passed in variables
	public Player2(int width, int height) {
		x = 600;
		y = 275;
		this.width = width;
		this.height = height;
	}

	// keyPressed method which checks if you pressed a key(arrows keys)
	public void keyPressed(int i) {
		if (i == KeyEvent.VK_RIGHT)
			rightPressed = true;
		if (i == KeyEvent.VK_LEFT)
			leftPressed = true;
		if (i == KeyEvent.VK_UP)
			jumping = true;
		if (i == KeyEvent.VK_M) {
			if ((!(i == KeyEvent.VK_N))) {// logic that doesnt allow you to kick/punch/crouch at once
				kick = false;
				punch = true;
				crouch = false;
			}
		}
		if (i == KeyEvent.VK_N) {
			if ((!(i == KeyEvent.VK_M))) {
				punch = false;
				kick = true;
				crouch = false;
			}
		}
		if (i == KeyEvent.VK_DOWN) {
			if ((!(i == KeyEvent.VK_N) || (i == KeyEvent.VK_M)))
				crouch = true;
		}

	}

	// keyReleased method which checks if you released a key
	// needed if you want to hold down keys for long
	public void keyReleased(int i) {
		if (i == KeyEvent.VK_RIGHT)
			rightPressed = false;
		if (i == KeyEvent.VK_LEFT)
			leftPressed = false;
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (i == KeyEvent.VK_M)
					punch = false;
				if (i == KeyEvent.VK_N)
					kick = false;
			}
		}, 150);
		if (i == KeyEvent.VK_DOWN)
			crouch = false;

	}

	// action performed method to change the x-values/jumping of the player to suit
	// the player inputs
	public void actionPerformed(ActionEvent e) {
		if (rightPressed) {
			x = x + 2;// moves the rect to the right
		}
		if (leftPressed) {
			x = x - 2;// moves the rect to the left
		}
		// calculates the jumping speed
		if (jumping) {
			y -= currentJumpSpd;
			currentJumpSpd -= 0.095;// slows the y value ascend as it goes up

			if (currentJumpSpd <= 0) {
				currentJumpSpd = jumpSpd;// when the jump speed = 0, the player is not falling anymor
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
				falling = false;// when the player y-value is greater than 275, it stops falling and "hits" the
								// ground
		}
		// sets bounds so that the player cannot leave the JFrame
		if (x < 130)
			x = 130;
		if (x > 640)
			x = 640;
		if (y > 275)
			y = 275;

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

	// getters and setters
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

}
