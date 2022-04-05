//all imports
//Pranav Belligundu
//Kim per 5
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

//grabs the images and uses BufferedImage and stores the array of images in a public variable
public class Texture {
	//fields

	//for ryu
	private BufferedImage Ryu_idle_sheet, Ryu_punch_sheet, Ryu_kick_sheet, Ryu_crouch_sheet = null;
	public BufferedImage[] ryu_idle = new BufferedImage[6];
	public BufferedImage[] ryu_kick = new BufferedImage[9];
	public BufferedImage[] ryu_punch = new BufferedImage[6];
	public BufferedImage[] ryu_crouch = new BufferedImage[1];
	
	//for ken
	private BufferedImage Ken_idle_sheet, Ken_punch_sheet, Ken_kick_sheet, Ken_crouch_sheet= null;
	public BufferedImage[] ken_idle = new BufferedImage[6];
	public BufferedImage[] ken_kick = new BufferedImage[9];
	public BufferedImage[] ken_punch = new BufferedImage[6];
	public BufferedImage[] ken_crouch = new BufferedImage[1];

	SpriteSheet ryu_is,ryu_ps,ryu_ks,ryu_cs;
	SpriteSheet ken_is,ken_ps,ken_ks,ken_cs;
	//gets the sprite sheets
	public Texture() {
		try {
			Ryu_idle_sheet = ImageIO.read(new File("Ryu_idle.png"));
			Ryu_kick_sheet = ImageIO.read(new File("Ryu_upper_kick.png"));
			Ryu_punch_sheet = ImageIO.read(new File("Ryu_punch.png"));
			Ryu_crouch_sheet = ImageIO.read(new File("Ryu_crouch.png"));
			
			Ken_idle_sheet = ImageIO.read(new File("Ken_idle.png"));
			Ken_kick_sheet = ImageIO.read(new File("Ken_kick.png"));
			Ken_punch_sheet = ImageIO.read(new File("Ken_punch.png"));
			Ken_crouch_sheet = ImageIO.read(new File("Ken_crouch.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		ryu_is = new SpriteSheet(Ryu_idle_sheet);
		ryu_ps = new SpriteSheet(Ryu_punch_sheet);
		ryu_ks = new SpriteSheet(Ryu_kick_sheet);
		ryu_cs = new SpriteSheet(Ryu_crouch_sheet);
		
		ken_is = new SpriteSheet(Ken_idle_sheet);
		ken_ps = new SpriteSheet(Ken_punch_sheet);
		ken_ks = new SpriteSheet(Ken_kick_sheet);
		ken_cs = new SpriteSheet(Ken_crouch_sheet);
		getTextures();
	}
	//crops the images into what I want
	private void getTextures() {
		//---------- all RYU stuff --------------//
		for (int i = 0; i < 6; i++) 
			ryu_idle[i] = ryu_is.crop(57, 106, 57 * i, 0);//crops images for the idle 
		for (int i = 0; i < 6; i++) 
			ryu_punch[i] = ryu_ps.crop(101, 102, 101*i, 0);//crops images for the punch 
		for (int i = 0; i < 9; i++) 
			ryu_kick[i] = ryu_ks.crop(110, 111, 110 * i, 0);//crops images for the kick 
		ryu_crouch[0] = ryu_cs.crop(54, 73, 0, 0);
		
		//---------- all KEN stuff --------------//
		
		for (int i = 0; i < 6; i++) 
			ken_idle[i] = ken_is.crop(57, 106, 57 * i, 0);//crops images for the idle 
		for (int i = 0; i < 4; i++) 
			ken_punch[i] = ken_ps.crop(95, 102, 95 * i, 0);//crops images for the punch 
		for (int i = 0; i < 5; i++) 
			ken_kick[i] = ken_ks.crop(118, 105, 118 * i, 0);//crops images for the kick 
		ken_crouch[0] = ken_cs.crop(54, 73, 0, 0);	
	}
}
