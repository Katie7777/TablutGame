

import org.newdawn.slick.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.*;
import org.lwjgl.*;


/**
 * This is the class that connects all the states of our game.
 * The GUI classes like Gameplay.java and Menu.java constantly
 * execute render() and update() methods for every frame of the 
 * game. 
 * @author 
 *
 */
public class Game extends StateBasedGame{
	
	private static final String GAMENAME = "Tablut"; // the name for our window
	private static final int MENU = 0; // id of menu screen
	private static final int GAMEPLAY = 1; // id of game screen
	
	public Game(String GAMENAME) {
		//set game name to be the "window" name
		super(GAMENAME); 
		 //add the states to our game
		this.addState(new Menu(MENU));
		this.addState(new Gameplay(GAMEPLAY));
		
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		
		this.getState(GAMEPLAY).init(gc, this);//we are telling the program about the states our game has (initializing them)
		this.getState(MENU).init(gc, this); 
		this.enterState(MENU); //set menu screen as the first state (when the game starts, we see our main menu)
		
	}
}

