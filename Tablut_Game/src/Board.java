/**
 * board of the game
 */

public class Board {

	/**
	 * board is the 9*9 grid
	 * 
	 */
	private Piece[][] board = new Piece[9][9];

	/**
	 * construct a Board with 9*9 grid and initial pieces
	 */
	public Board() {

		board_grid();
		initiateBoard();
	}

	/**
	 * put one piece with default type "Empty" on each position
	 */
	public void board_grid() {
		for (int x = 0; x < 9; x++)
			for (int y = 0; y < 9; y++)
				board[x][y] = new Piece();
	}

	/**
	 * king's position will be updated once the king is moved and removed 
	 * if the king is removed, its position will be set as (-1, -1), and then the white side
	 * loses the game
	 */
	private Position kingPosition = new Position(4, 4);

	/**
	 * if king is cornered and only has one possible positions to move
	 * if the king moves back and forth between two squares more than 5 times, white side loses.
	 * This field records the total number of repetitive moves
	 */
	private int numOfKingRepetitiveMove = 0;

	/**
	 * if the king has only one available position to move, add 1 to
	 * numOfKingRepetitiveMove
	 */
	public void addOneToNumOfKingRepetitiveMove() {
		this.numOfKingRepetitiveMove++;
	}

	/**
	 * if the king has more than one available positions to move, set the
	 * numOfKingRepetitiveMove BACK to 0
	 */
	public void kingHasNoRepetitiveMove() {
		this.numOfKingRepetitiveMove = 0;
	}


	public int getNumOfKingRepetitiveMove() {
		return numOfKingRepetitiveMove;
	}


	public Position getKingPosition() {
		return kingPosition;
	}

	/**
	 * update king's position when the king is moved and removed
	 * 
	 * @param kingPosition
	 */
	public void setKingPosition(Position kingPosition) {
		this.kingPosition = kingPosition;
	}

	/**
	 * in other classes, when we need to access board positions, we will use
	 * ClassName.getBoard[i][j]
	 * 
	 * @return board
	 */
	public Piece[][] getBoard() {
		return board;
	}

	/**
	 * if the board position hold a piece "Empty", display "-"; if the board
	 * position holds one piece other than "Empty", display piece.getType.charAt(0)
	 * @return boardState
	 */
	public String displayBoard() {
		
		String boardState = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (this.board[i][j].getType() == "Empty") {
					boardState = boardState + String.format("%5s", "-");
				} else {
					boardState = boardState + String.format("%5s", this.board[i][j].getType().charAt(0));
				}

			}
			boardState = boardState + "\r\n";
		}
		boardState = boardState + "**************************************************";
		return boardState;
	}

	/**
	 * initiate the board with 16 Black pieces, 8 White pieces and 1 King piece 
	 * The position information is given by whitePiecesInitialPositions() and
	 * blackPiecesInitialPositions(). 
	 * Initialization means placing pieces according to two Array lists from above two functions.
	 */
	public void initiateBoard() {
		this.board[BoardUtility.thronePosition().getRow()][BoardUtility.thronePosition().getColumn()].setType("King");

		for (Position p : BoardUtility.whitePiecesInitialPositions()) {
			this.board[p.getRow()][p.getColumn()].setType("White");
		}

		for (Position p : BoardUtility.blackPiecesInitialPositions()) {
			this.board[p.getRow()][p.getColumn()].setType("Black");
		}
	}

	/**
	 * 1. get the piece via start position 
	 * 2. if the piece is not the King, use moveOnePieceOtherThanKing(), otherwise, use moveKing() 
	 * 3. After the move, consider removing captured opposite piece if needed via
	 * checkAndRemoveNearbyPiecesIfNeeded()
	 * 
	 * @param startPosition
	 * @param endPosition
	 */
	public void updateBoard(Position startPosition, Position endPosition) {

		Piece pieceOnStartPosition = this.board[startPosition.getRow()][startPosition.getColumn()];
		if (pieceOnStartPosition != null && !pieceOnStartPosition.getType().equals("King")) {
			BoardUtility.moveOnePieceOtherThanKing(this, startPosition, endPosition);
		} else if (pieceOnStartPosition != null && pieceOnStartPosition.getType().equals("King")) {
			BoardUtility.moveKing(this, endPosition);
		}
		BoardUtility.checkAndRemoveNearbyPiecesIfNeeded(this, endPosition);
		System.out.println("");
	}


}
