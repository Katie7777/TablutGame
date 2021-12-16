


import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * This class manages GUI of our game in menu state.
 * 
 *
 */
public class Menu extends BasicGameState{//inherits one of the slick classes

	private int stateID;
	
	private Image menu;
	private Image menuDefault;
	private Image menuPlayButton;
	private Image menuRulesButton;
	private Image menuCreditsButton;
	private Image menuQuitButton;
	private Image menuRulesScreen;
	private Image menuRulesScreenExitButton;
	private Image menuCreditsScreen;
	private Image menuCreditsScreenExitButton;
	private Image menuMusicButtonOn;
	private Image menuMusicButtonOff;
	
	private Music menuMusic;
	private Music gameplayMusic;
	
	private boolean musicOn;
	private boolean rulesScreen;
	private boolean creditsScreen;
	private boolean quit;
	private boolean play;
	
	private int mousex;
	private int mousey;
	
	private double timer;
	
	public Menu(int state) {
		stateID = state;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{

		menuDefault = new Image("res/menu.png");
		menuPlayButton = new Image("res/menuplaybutton.png");
		menuRulesButton = new Image("res/menurulesbutton.png");
		menuCreditsButton = new Image("res/menucreditsbutton.png");
		menuQuitButton = new Image("res/menuquitbutton.png");
		menuRulesScreen = new Image("res/menurulesscreen.png");
		menuCreditsScreen = new Image("res/menucreditsscreen.png");
		menuCreditsScreenExitButton = new Image("res/menucreditsscreenexit.png");
		menuRulesScreenExitButton = new Image("res/menurulesscreenexit.png");
		menuMusicButtonOn = new Image("res/musicbuttonon.png");
		menuMusicButtonOff = new Image("res/musicbuttonoff.png");

		menuMusic = new Music("res/menumusic1.ogg");
		gameplayMusic = new Music("res/gameplaymusic.ogg");
		menuMusic.loop();
		
		menu = menuDefault;

		timer = 0.0;

		musicOn = true;
		rulesScreen = false;
		creditsScreen = false;
		quit = false;
		play = false;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//draws our menu and music button
		g.drawImage(menu, 0, 0);
		if (musicOn == true) {
			g.drawImage(menuMusicButtonOn, 700, 600);
		} else {
			g.drawImage(menuMusicButtonOff, 700, 600);
		}

	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		//get mouse location and save it in a variable:
		mousex = Mouse.getX();
		mousey = Mouse.getY();
		//timer is going to measure time and refresh - to change our graphics for split second
		//we use it to change the appearance of our buttons when they are clicked
		timer = timer + delta * .01f;
		
		//if rulesScreen boolean is true - the rules button was clicked so the rules screen is displayed
		if (rulesScreen == true) {
			rulesScreen(gc);
		}
		//same thing for credits screen
		if (creditsScreen == true) {
			creditsScreen(gc);
		}
		//if rulesScreen and creditsScreen are false, we display the default state of our menu graphic
		if (rulesScreen == false && creditsScreen == false) {
			if (timer > 1.5) {
				menu = menuDefault;
			}
			if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				defaultScreen();
			}
		}
		//when the "clicked animation" time has passed - check if the user has clicked quit or play, 
		//that is why we check play and quit values here, instead of performing action on button click
		if (timer > 1.5) {
			if (quit == true) {
				AL.destroy(); //shutting our sound stream down (I think it's like closing scanner, it is not necessary as we do exit, but we don't want to get some error)
				System.exit(0);//quit the game
			}
			if (play == true) {
				menuMusic.stop();
				gameplayMusic.loop(1.0f, 0.25f);
				sbg.getState(1).init(gc, sbg);
				sbg.enterState(1);//enter gameplay state
			}
		}
		
		

	}

	/**
	 * method that is responsible for default screen performance
	 */
	public void defaultScreen() {
		//depending on the location of the mouse when left mouse button was pressed, change the menu screen to 
		//according "button pressed" graphic and change different boolean values to tell the program how to continue
		//also, refresh timer after the click - so our "animation graphic" is visible for a split second
		if(mousex >= 66 && mousex <= 316 && mousey >= 340 && mousey <= 392) {
			timer = 0.0;
			menu = menuPlayButton;
			play = true;
		}
		if(mousex >= 66 && mousex <= 316 && mousey >= 274 && mousey <= 329) {
			timer = 0.0;
			menu = menuRulesButton;
			rulesScreen = true;
		}
		if(mousex >= 66 && mousex <= 316 && mousey >= 210 && mousey <= 264) {
			timer = 0.0;
			menu = menuCreditsButton;
			creditsScreen = true;
		}
		if(mousex >= 66 && mousex <= 316 && mousey >= 122 && mousey <= 178) {
			timer = 0.0;
			menu = menuQuitButton;
			quit = true;
		}
		if(mousex >= 700 && mousex <= 750 && mousey >= 25 && mousey <= 75) {
			timer = 0.0;
			if(musicOn == true) {
				menuMusic.pause();
				musicOn = false;
			}else {
				menuMusic.resume();
				musicOn = true;
			}
		}
		
		
	}
	/**
	 * method that is responsible for rules screen performance
	 */
	public void rulesScreen(GameContainer gc) {
		if (timer > 1.5) {
			menu = menuRulesScreen;
			if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (mousex >= 585 && mousex <= 632 && mousey >= 622 && mousey <= 672) {
					timer = 0.0;
					menu = menuRulesScreenExitButton;
					rulesScreen = false;
				}
			}
		}
	}
	/**
	 * method that is responsible for credits screen performance
	 */
	public void creditsScreen(GameContainer gc) {
		if (timer > 1.5) {
			menu = menuCreditsScreen;
			if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if (mousex >= 625 && mousex <= 673 && mousey >= 518 && mousey <= 566) {
					timer = 0.0;
					menu = menuCreditsScreenExitButton;
					creditsScreen = false;
				}
			}
		}
	}
	
	
	
	//returns the id of this state (menu state)
	public int getID() {
		return stateID; 
	}
	
}

