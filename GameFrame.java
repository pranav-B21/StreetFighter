
//Kim per 5
//Pranav Belligundu
//all imports
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

//creates the instance of the main class
public class GameFrame extends JFrame{
	public static final int WIDTH = 650;
	public static final int HEIGHT = 450;//487.5
	public static final String TITLE = "STREET FIGHTER";
	
	public boolean running = false;
	
	//instructor method, where the JFrame is created
	public GameFrame()
	{
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		setResizable( false );
		setLayout(null);
		setLocationRelativeTo(null);

		Menu menu = new Menu();
		setContentPane(menu);//makes menu the contentPane
		setVisible(true);
		
	}
	//method that grabs the .wav file and plays the audio
	public void makeSound(String str){
	    File audio = new File("music_BG.wav");//plays the background music till it stops
	    try{
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(audio));
	        FloatControl gainControl = 
	        	    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	        	gainControl.setValue(-27.5f); // Reduce volume by 27.5 decibels.
	        if(str.contentEquals("start"))clip.start();
	        else if(str.contentEquals("start")) {
	        	clip.stop();
	        	clip.close();
	        }
	    } catch (Exception e){
	        e.printStackTrace();
	    }
	}
	//this is the main method, where the thread starts
	public static void main(String[] args) {
		GameFrame pranav = new GameFrame();	
		pranav.makeSound("start");
	}
}
