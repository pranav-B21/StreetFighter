

//imports
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//class for the ken haduken
public class HadukenKen implements ActionListener {

	private double x,y;
	//grabs the x and y point and sets it as its own
	public HadukenKen(double x, double y) {
		this.x = x;
		this.y = y;
	}
	@Override
	//every 10 milisecs it adds the x value of the haduken by 3
	public void actionPerformed(ActionEvent e) {
		x = x-3;
	}
	//returns the x value 
	public double getX() {//returns the x value
		return x;
	}
	//returns the x value 
	public double getY() {//returns the y value
		return y;
	}
	//returns the attack bounds value 
	public Rectangle getAttackBounds() {
		return new Rectangle((int)x, (int)y-5, 80, 40);//returns a idle bound if not punching/kicking
	}
}
