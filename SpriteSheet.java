//all imports
//Pranav Belligundu
//Kim per 5
import java.awt.image.BufferedImage;

//receives the image and crops it
public class SpriteSheet {
	//fields
	private BufferedImage sheet;
	//sheet - sends an image to be cropped, the constructor method receives it and initializes its own
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	//gets the subimages from the parameters and returns the cropped image
	public BufferedImage crop(int width, int height, int x, int y) {
		return sheet.getSubimage(x, y, width, height);
	}

}
