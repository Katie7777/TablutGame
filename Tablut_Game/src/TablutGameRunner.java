
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * run the game
 *
 *
 */
public class TablutGameRunner {

	public static void main(String[] args) {
			//create our main window
		try {
			AppGameContainer agc = new AppGameContainer(new Game("Tablut")); 
			//window contains our game
			agc.setDisplayMode(800, 675, false);
			agc.setShowFPS(false);
			agc.start(); //start our window
			
		}catch(SlickException e) {
			e.printStackTrace();
		}

	}

}

