

//all imports
//Pranav Belligundu
//Kim per 5
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;

//overrides JButton and creates a different button
public class MyButton extends JButton {
	//fields 
	private String text;
	private Color bgColor = new Color(255,255,255);
	
	//the constructor method where I initialize add components
	public MyButton(String text) {
		this.text = text;
		setRolloverEnabled(true);
	}
	//adds the affect of what happens when you scroll over the buttons
	public void paintComponent(Graphics g) {
		String label = text;
		if(getModel().isRollover()) bgColor = new Color(135,206,250);
		else bgColor = new Color(255,255,255);
		
		g.setColor(bgColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.BLACK);
		if(label.contentEquals("PLAY")) {//placement of the string inside the button for PLAY
			g.drawString(label,73,46);
		}
		else if(label.contentEquals("HOW TO PLAY")) {//placement of the string inside the button for HOW TO PLAY
			g.drawString(label,14,46);
		}
		else if(label.contentEquals("QUIT")) {//placement of the string inside the button for QUIT
			g.drawString(label,73,46);
		}
		else if(label.contentEquals("QUIT1")) {//placement of the string inside the button for QUIT
			g.drawString("QUIT",25,32);
		}
		else if(label.contentEquals("BACK")) {//placement of the string inside the button for BACK
			g.drawString(label,25,32);
		}
		else if(label.contentEquals("SOLO")) {//placement of the string inside the button for BACK
			g.drawString(label,45,45);
		}
		else if(label.contentEquals("2 PLAYER")) {//placement of the string inside the button for BACK
			g.drawString(label,15,45);
		}
		else if(label.contentEquals("EASY")) {//placement of the string inside the button for BACK
			g.drawString(label,37,45);
		}
		else if(label.contentEquals("HARD")) {//placement of the string inside the button for BACK
			g.drawString(label,32,46);
		}
		else if(label.contentEquals("MEDIUM")) {//placement of the string inside the button for BACK
			g.drawString(label,15,45);
		}
		//placement of the string inside the button for all level buttons
		else if(label.contentEquals("MAP 1") || label.contentEquals("MAP 2") || label.contentEquals("MAP 3")) {
			g.drawString(label,25,47);
		}
	}
	//paints the border of the buttons
	public void paintBorder(Graphics g) {
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(8));
		
		//paints all borders
		g2.drawLine(0, 0, getWidth()-1, 0);
		g2.drawLine(0, getHeight()-1, getWidth()-1, getHeight()-1);
		g2.drawLine(0, 0, 0, getHeight()-1);
		g2.drawLine(getWidth()-1, 0, getWidth()-1, getHeight()-1);
		
	}
}
