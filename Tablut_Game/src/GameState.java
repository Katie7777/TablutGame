
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
/**
 * All the classes that contain a GUI: so all the "game screens" need
 * to have these four methods, so we added an interface, to make sure they do.
 * 
 *
 */
public interface GameState {
	/**init(...) is called when the game starts. We can put code here to set things up for our game, 
	 * such as loading resources like images and sounds.
	 * @param gc
	 * @param sbg
	 * @throws SlickException
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException;
	
	/**
	 * render(...) is called constantly. This is where all our graphics is done
	 * @param gc
	 * @param sbg
	 * @param g
	 * @throws SlickException
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException;
	
	/**
	 * update(...) is called periodically, usually every 20 milliseconds, 
	 * but the time will vary depending on how much processing you’ll be putting in. 
	 * This is where the game logic is done.
	 * @param gc
	 * @param sbg
	 * @param delta
	 * @throws SlickException
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException;
	
	/**
	 * @return the id of different state
	 */
	public int getID();
	
}

