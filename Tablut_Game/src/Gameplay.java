


import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;

/**
 * This class manages GUI of our game in gameplay state.
 * @author 
 *
 */
public class Gameplay extends BasicGameState implements GameState{//inherits one of the slick classes

	private int stateID;
	
	private TiledMap map;
	private int x, y;
	private int mouseX, mouseY;
	public Board board;
	
	private Image blackwon;
	private Image whitewon;
	private Image pauseState;
	private Image pause;
	private Image pauseResume;
	private Image pauseRestart;
	private Image pauseMainMenu;
	private Image pauseQuit;
	private Image pauseCross;
	private Image turnGraphic;
	private Image turnBlack;
	private Image turnWhite;
	private Image whitepiece;
	private Image blackpiece;
	private Image king;
	private Image winscreen;
	private Image musicButtonOn;
	private Image musicButtonOff;
	private Image soundButtonOn;
	private Image soundButtonOff;
	
	private Music gameplayMusic;
	private Sound woodSlide;
	
	private int clickedR;
	private int clickedC;
	
	private int selectedR;
	private int selectedC;
	
	private float animationPositionR;
	private float animationPositionC;
	
	private double timer;
	private String turn;
	boolean animation;
	boolean pauseOn;
	boolean resume;
	boolean quit;
	boolean mainMenu;
	boolean restart;
	boolean gameEnds;
	boolean musicOn;
	boolean soundOn;
	
	public Gameplay (int state) {
		stateID = state;
	}
	
	
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		board = new Board();
		
		map = new TiledMap("res/TablutBoard1.tmx");
		whitepiece = new Image("res/whitepiece1.png");
		blackpiece = new Image("res/blackpiece1.png");
		king = new Image("res/king1.png");
		turnWhite = new Image("res/turnwhite.png");
		turnBlack = new Image("res/turnblack.png");
		pause = new Image("res/pausedefault.png");
		pauseResume = new Image("res/pauseresume.png");
		pauseRestart = new Image("res/pauserestart.png");
		pauseMainMenu = new Image("res/pausemainmenu.png");
		pauseQuit = new Image("res/pausequit.png");
		pauseCross = new Image("res/pausecross.png");
		whitewon = new Image("res/whitewon.png");
		blackwon = new Image("res/blackwon.png");
		musicButtonOn = new Image("res/musicbuttonon.png");
		musicButtonOff = new Image("res/musicbuttonoff.png");
		soundButtonOn = new Image("res/soundbuttonon.png");
		soundButtonOff = new Image("res/soundbuttonoff.png");
		
		woodSlide = new Sound("res/woodslide.ogg");
		gameplayMusic = new Music("res/gameplaymusic.ogg");
		
		pauseState = pause;
		
		x=75;
		y=75;
		
		
		clickedR = 100;
		clickedC = 100;
		
		selectedR = 100;
		selectedC = 100;
		
		animationPositionR = 0;
		animationPositionC = 0;
		
		timer = 0.0;
		turn = "Black";
		turnGraphic = turnBlack;
		animation = false;
		pauseOn = false;
		resume = false;
		quit = false;
		mainMenu = false;
		restart = false;
		gameEnds = false;
		musicOn = true;
		soundOn = true;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		
		//draw our map (board) and side bar.
		g.drawImage(turnGraphic, 673, 0);
		map.render(0, 0);
		
		
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				//this part of the code is our animation - it draws a new image at slightly different position 
				//until the image reaches the desired position - then animation is set to false, and board 
				//graphical interface is generated based on the board object's state
				if(animation == true && ((i == clickedR && j == clickedC) || (i == selectedR && j == selectedC))) {
					if(board.getBoard()[clickedR][clickedC].getType() == "White") {
						g.drawImage(whitepiece, x*animationPositionC + 2, y*animationPositionR + 4);
					}
					if(board.getBoard()[clickedR][clickedC].getType() == "Black") {
						g.drawImage(blackpiece, x*animationPositionC + 2, y*animationPositionR + 4);
					}
					if(board.getBoard()[clickedR][clickedC].getType() == "King") {
						g.drawImage(king, x*animationPositionC + 2, y*animationPositionR + 4);
					}
					
				}
				//this part of the code checks the state of the board object, and draws our pieces onto our interface
				else {
					if(board.getBoard()[i][j].getType() == "White") {
						g.drawImage(whitepiece, x*j + 2, y*i + 4);
					}
					if(board.getBoard()[i][j].getType() == "Black") {
						g.drawImage(blackpiece, x*j + 2, y*i + 4);
					}
					if(board.getBoard()[i][j].getType() == "King") {
						g.drawImage(king, x*j + 2, y*i + 4);
					}
					if(i == selectedR && j == selectedC) {
						g.drawOval(x*j + 1, y*i + 3, 73, 73);
					}
				}
			}
		}
		
		//if pause is on, display pause screen
		if(pauseOn == true) {
			g.drawImage(pauseState, 250, 150);
		}
		//if one of the sides wins, show end game screen 
		//(make sure the animation has ended - because it looks better)
		if(gameEnds == true && animation == false) {
			g.drawImage(winscreen, 50, 37);
		}
		//display and change the state of music and sound buttons
		if(musicOn == true) {g.drawImage(musicButtonOn, 711, 297);}
		if(musicOn == false) {g.drawImage(musicButtonOff, 711, 297);}
		if(soundOn == true) {g.drawImage(soundButtonOn, 711, 347);}
		if(soundOn == false) {g.drawImage(soundButtonOff, 711, 347);}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		//if escape is pressed - go into pause mode
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			pauseOn = true;
		}
		//if pause screen or game over screen is visible, pauseScreen() method handles button clicks etc. 
		if (pauseOn == true) {
			pauseScreen(gc,sbg,delta);	
		}
		//If game is over, conditional statement handles 'OK' button that sends us to main menu
		if(gameEnds == true) {
			if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				if(Mouse.getX() >= 211 && Mouse.getX() <= 486 && Mouse.getY() >= 99 && Mouse.getY() <= 159) {
					sbg.getState(0).init(gc, sbg);
		            sbg.enterState(0);
				}
			}
		}
		//if pause mode is off, moveTurn() and performAnimation() handle the game normally
		if (pauseOn == false) {
			// make sure the animation is over - can't move while the animation is running
			if(animation == false) {
				if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
					//get mouse coordinates and row and column number of the clicked field
					mouseX = Mouse.getX();
					mouseY = Mouse.getY();
					clickedC = TablutGameUtility.getMousePositionColumn();
					clickedR = TablutGameUtility.getMousePositionRow();
					if (clickedR > 8 || clickedR < 0 || clickedC > 8 || clickedC < 0) {
						//turn music on and off (it is on the side bar, so clickedC and clickedR are outside the board)
						if(mouseX >= 711 && mouseX <= 760 && mouseY >= 329 && mouseY <= 380) {
							if(musicOn == true) {
								musicOn = false;
								gameplayMusic.pause();
							}
							else {
								musicOn = true;
								gameplayMusic.resume();
							}
						}
						//turn sound on and off
						if(mouseX >= 711 && mouseX <= 760 && mouseY >= 279 && mouseY <= 330) {
							if(soundOn == true) {
								soundOn = false;
							}
							else {
								soundOn = true;
							}
						}
					} else {
						moveTurn(turn);
						//check if someone has won
						win(board, gc, sbg);
					}
				}
			}
			performAnimation(delta);
		}
	}

	/**
	 * The most important method in this class - responsible for making a move. 
	 * It has 1 parameter - a String with the name of one of the sides.
	 * 
	 * @param whoseTurn
	 */
	public void moveTurn(String whoseTurn) {
		
		// if black/white piece is clicked - select it
		if (board.getBoard()[clickedR][clickedC].getType() == whoseTurn) {
			selectedR = clickedR;
			selectedC = clickedC;
		}
		// if king piece is clicked - select it (king is on white side, so select only
		// if it is white's turn)
		if (board.getBoard()[clickedR][clickedC].getType() == "King" && whoseTurn == "White") {
			selectedR = clickedR;
			selectedC = clickedC;
		}
		// selected field is not 100/100 so there is some piece that is selected - so··
		// now check if clicked field is empty (to move the selected piece to an empty
		// field, because you cannot move it to a field that is already occupied)
		// also we need to make sure that clicked row or column is equal to selected 
		// row or column because the movement can be only horizontal or vertical
		if (board.getBoard()[clickedR][clickedC].getType() == "Empty" && selectedR != 100 && selectedC != 100 ) {
			//check if the move is valid
			if((board.getBoard()[selectedR][selectedC].getType() != "King" && BoardUtility.isMoveValid(BoardUtility.validPositionsForNonKingToMove(board, new Position(selectedR, selectedC)), new Position(clickedR, clickedC))) || (board.getBoard()[selectedR][selectedC].getType() == "King" && BoardUtility.isMoveValid(BoardUtility.kingValidPositionsToMove(board, new Position(selectedR, selectedC)), new Position(clickedR, clickedC)))) {
				// update the board
				board.updateBoard(new Position(selectedR, selectedC), new Position(clickedR, clickedC));
				// set animation attributes and turn the animation on,
				// by setting boolean to true
				animationPositionR = selectedR;
				animationPositionC = selectedC;
				animation = true;
				if (soundOn == true) {
					woodSlide.play(1f, 0.05f);
				}
				// after the turn is over - set the turn to the opposite side's turn
				if (whoseTurn == "Black") {
					turn = "White";
				}
				if (whoseTurn == "White") {
					turn = "Black";
				}
				// change the interface to show that it is a black's turn
				if (whoseTurn == "Black") {
					turnGraphic = turnWhite;
				}
				if (whoseTurn == "White") {
					turnGraphic = turnBlack;
				}
				// reset selected position to 100 (so no field is selected)
				selectedR = 100;
				selectedC = 100;
			}
		}
	}
	/**
	 * This method handles the pause screen - button positions and their tasks.
	 * @param gc
	 * @param sbg
	 * @param delta
	 * @throws SlickException
	 */
	public void pauseScreen(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//delta measures the time, timer is initialized here to make sure our "button pressed" graphics are visible for a slight moment.
		timer = timer + delta * .01f;
		//this conditional statement manages functionality of our buttons
		if (timer > 1.5) {
			if(resume == true) {
				pauseOn = false;
				resume = false;
				pauseState = pause;
			}
			if(quit == true) {
				quit = false;
				AL.destroy();// shutting our sound stream down (I think it's like closing scanner, it is not necessary as we do exit, but we don't want to get some error)
				System.exit(0);
			}
			if(mainMenu == true) {
				gameplayMusic.stop();
				sbg.getState(0).init(gc, sbg);
	            sbg.enterState(0);
				mainMenu = false;
				pauseOn = false;
				pauseState = pause;
			}
			if(restart == true) {
				restart = false;
				sbg.getState(1).init(gc, sbg);
	            sbg.enterState(1);
			}
		}
		//when button is pressed, timer is reset to 0, and our "button pressed" graphics are visible
		if(gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if(Mouse.getX() >= 280 && Mouse.getX() <= 520 && Mouse.getY() >= 395 && Mouse.getY() <= 448) {
				timer = 0.0;
				pauseState = pauseResume;
				resume = true;
			}
			if(Mouse.getX() >= 280 && Mouse.getX() <= 520 && Mouse.getY() >= 325 && Mouse.getY() <= 377) {
				timer = 0.0;
				pauseState = pauseRestart;
				restart = true;
			}
			if(Mouse.getX() >= 280 && Mouse.getX() <= 520 && Mouse.getY() >= 256 && Mouse.getY() <= 309) {
				timer = 0.0;
				pauseState = pauseMainMenu;
				mainMenu = true;
			}
			if(Mouse.getX() >= 280 && Mouse.getX() <= 520 && Mouse.getY() >= 155 && Mouse.getY() <= 207) {
				timer = 0.0;
				pauseState = pauseQuit;
				quit = true;
			}
			if(Mouse.getX() >= 505 && Mouse.getX() <= 541 && Mouse.getY() >= 482 && Mouse.getY() <= 517) {
				timer = 0.0;
				pauseState = pauseCross;
				resume = true;
			}
		}
		//pressing escape is one way of resuming the game
		if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
				pauseOn = false;
		}
	}
	/**
	 * Method responsible for changing location of pieces in a unit of time - creating an illusion of animation.
	 * Render method handles the actual display.
	 * @param delta
	 */
	public void performAnimation(int delta) {
		//animationPosition represents current position of the pawn. It is constantly bigger or smaller - therefore the pawn moves
		if (animation == true) {
			if (animationPositionR < clickedR) {
				animationPositionR = animationPositionR + delta * .005f;
			}
			if (animationPositionR > clickedR) {
				animationPositionR = animationPositionR - delta * .005f;
			}
			if (animationPositionC < clickedC) {
				animationPositionC = animationPositionC + delta * .005f;
			}
			if (animationPositionC > clickedC) {
				animationPositionC = animationPositionC - delta * .005f;
			}
			//if animationPosition reaches desired position on the board, end animation
			if (Math.abs(animationPositionR - clickedR * 1.00) < 0.1
					&& Math.abs(animationPositionC - clickedC * 1.00) < 0.1) {
				animation = false;//set the animation to false - ending the current animation.
				woodSlide.stop();
			}
		}
	}
	/**
	 * Method to check if any of the sides wins.
	 */
	public void win(Board board, GameContainer gc, StateBasedGame sbg) {

		if (TablutGameUtility.ifKingOnEdgeOfTheBoard(board) == true || TablutGameUtility.noBlackPiece(board) == true) {
			winscreen = whitewon;
			gameEnds = true;
		} else if (TablutGameUtility.isKingCaptured(board) == true
				|| TablutGameUtility.kingHasNoLegalMove(board) == true) {
			winscreen = blackwon;
			gameEnds = true;
		}
	}
	
	
	
	//returns the id of this state (gameplay state)
	public int getID() {
		return stateID; 
	}


}