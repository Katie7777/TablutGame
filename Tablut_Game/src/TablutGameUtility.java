import org.lwjgl.input.Mouse;

/**
 * functions related to TablutGame class
 *
 */

public class TablutGameUtility {

	/**check if the king is on the edge of the board
	 * use the checkKingOnEdge(Board board) function in BoardUtility class 
	 * @param board
	 * @return true if the king is on the edge of the board
	 */
	public static boolean ifKingOnEdgeOfTheBoard(Board board) {
						
		if (BoardUtility.checkKingOnEdge(board) == true) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * check if the king's position is (-1, -1), 
	 * which means king has been captured and removed from the board
	 * use getKingPosition() function
	 * @param board
	 * @return true if the King is captured
	 */
	public static boolean isKingCaptured(Board board) {
		
		if (board.getKingPosition().getRow() == -1 && board.getKingPosition().getColumn() == -1) {
			return true;
		}
		return false;
	}
	

	
		
	/** check if getNumOfKingRepetitiveMove() > 5,
	 *  which means the king has already moved back and forth more than 5 times
	 *  use getNumOfKingRepetitiveMove() function
	 * @param board
	 * @return true if king has no legal move
	 */
	public static boolean kingHasNoLegalMove(Board board) {
		
		if (board.getNumOfKingRepetitiveMove() > 5) {
			return true;
		}
		return false;
	}
	
	
	
	
	/**
	 * check the board to see if there is any black piece left
	 * @param board
	 * @return true if there's no black piece left on Board
	 */
	public static boolean noBlackPiece(Board board) {

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board.getBoard()[i][j].getType().equals("Black")) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Converts the mouse position Y to be the number of the row that the mouse is on.
	 * @return
	 */
	public static int getMousePositionRow() {

		int mousey = Mouse.getY();

		int clickedR = 100;

		switch (mousey / 75) {
		case 0:
			clickedR = 8;
			break;
		case 1:
			clickedR = 7;
			break;
		case 2:
			clickedR = 6;
			break;
		case 3:
			clickedR = 5;
			break;
		case 4:
			clickedR = 4;
			break;
		case 5:
			clickedR = 3;
			break;
		case 6:
			clickedR = 2;
			break;
		case 7:
			clickedR = 1;
			break;
		case 8:
			clickedR = 0;
			break;
		}
		
		return clickedR;

	}
	/**
	 * Converts the mouse position X to be the number of the Column that the mouse is on.
	 * @return
	 */
	public static int getMousePositionColumn() {

		int mousex = Mouse.getX();

		int clickedC = mousex / 75;
		
		return clickedC;


	}
	
	
}

