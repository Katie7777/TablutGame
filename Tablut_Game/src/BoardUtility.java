import java.util.ArrayList;

/**
 * helper functions related to board
 * 
 * 
 *
 */
public class BoardUtility {

	/**
	 * helper function for initiateBoard(),
	 * 
	 * @return an ArrayList with all initial white pieces' positions
	 */
	public static ArrayList<Position> whitePiecesInitialPositions() {
		ArrayList<Position> whitePositions = new ArrayList<Position>();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if ((i == 2 || i == 3 || i == 5 || i == 6) && j == 4) {
					Position white = new Position(i, j);
					whitePositions.add(white);
				} else if (i == 4 && (j == 2 || j == 3 || j == 5 || j == 6)) {
					Position white = new Position(i, j);
					whitePositions.add(white);

				}
			}
		}

		return whitePositions;
	}

	/**
	 * helper function for initiateBoard(),
	 * 
	 * @return an ArrayList with all initial black pieces' positions
	 */
	public static ArrayList<Position> blackPiecesInitialPositions() {
		ArrayList<Position> blackPositions = new ArrayList<Position>();

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if ((i == 0 || i == 8) && (j == 3 || j == 4 || j == 5)) {
					Position black = new Position(i, j);
					blackPositions.add(black);
				} else if ((j == 0 || j == 8) && (i == 3 || i == 4 || i == 5)) {
					Position black = new Position(i, j);
					blackPositions.add(black);
				} else if ((i == 1 || i == 7) && j == 4) {
					Position black = new Position(i, j);
					blackPositions.add(black);
				} else if ((j == 1 || j == 7) && i == 4) {
					Position black = new Position(i, j);
					blackPositions.add(black);
				}

			}

		}

		return blackPositions;
	}

	/**
	 * helper function for initiateBoard(),
	 * 
	 * @return a Position that is initial king's position
	 */
	public static Position thronePosition() {
		Position throne = new Position(4, 4);

		return throne;
	}

	/**
	 * moveKing(...) is the helper function for updateBoard() 
	 * If the player moves the King piece: 1. if the king has only one available position to move, use
	 * addOneToNumOfKingRepetitiveMove(); 2. if the king has more than one available
	 * positions, use kingHasNoRepetitiveMove 3. move the king to one valid position
	 * and update the king's position
	 * 
	 * @param board
	 * @param endPosition [may need additional helper functions]
	 */
	public static void moveKing(Board board, Position endPosition) {
		Position startPosition = board.getKingPosition();

		if (kingValidPositionsToMove(board, startPosition).size() == 1) {
			board.addOneToNumOfKingRepetitiveMove();
		} else {
			board.kingHasNoRepetitiveMove();
		}

		if (BoardUtility.isMoveValid(kingValidPositionsToMove(board, startPosition), endPosition)) {

			board.getBoard()[startPosition.getRow()][startPosition.getColumn()].setType("Empty");
			board.getBoard()[endPosition.getRow()][endPosition.getColumn()].setType("King");
			board.setKingPosition(endPosition);
		}
	}

	
	/**
	 * helper function for moveKing(...) 
	 * @param board
	 * @param piecePosition
	 * @return valid positions for king to move
	 */
	public static ArrayList<Position> kingValidPositionsToMove(Board board, Position piecePosition) {
		ArrayList<Position> kingValidPositions = new ArrayList<Position>();

		if (board.getBoard()[piecePosition.getRow()][piecePosition.getColumn()].getType() != "Empty") {

			for (int j = piecePosition.getColumn() - 1; j >= 0; j--) {
				if (board.getBoard()[piecePosition.getRow()][j].getType() == "Empty") {
					Position p = new Position(piecePosition.getRow(), j);
					kingValidPositions.add(p);
				} else {
					break;
				}

			}
			for (int j = piecePosition.getColumn() + 1; j < 9; j++) {
				if (board.getBoard()[piecePosition.getRow()][j].getType() == "Empty") {
					Position p = new Position(piecePosition.getRow(), j);
					kingValidPositions.add(p);
				} else {
					break;
				}

			}
			for (int i = piecePosition.getRow() - 1; i >= 0; i--) {
				if (board.getBoard()[i][piecePosition.getColumn()].getType() == "Empty") {
					Position p = new Position(i, piecePosition.getColumn());
					kingValidPositions.add(p);
				} else {
					break;
				}
			}
			for (int i = piecePosition.getRow() + 1; i < 9; i++) {
				if (board.getBoard()[i][piecePosition.getColumn()].getType() == "Empty") {
					Position p = new Position(i, piecePosition.getColumn());
					kingValidPositions.add(p);
				} else {
					break;
				}
			}

		}

		return kingValidPositions;
	}

	/**
	 * helper function for moveKing(..) & moveOnePieceOtherThanKing(...)
	 * check if the move is valid
	 * @param validPositions
	 * @param endPosition
	 * @return true if the move is valid or false if the move is not valid
	 */
	public static boolean isMoveValid(ArrayList<Position> validPositions, Position endPosition) {

		for (Position position : validPositions) {

			if (position.samePosition(endPosition)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * helper function for updateBoard() 
	 * if the player moves the black or white piece, move the piece to the one valid position
	 * 
	 * @param board
	 * @param startPosition
	 * @param endPosition
	 */
	public static void moveOnePieceOtherThanKing(Board board, Position startPosition, Position endPosition) {

		if (BoardUtility.isMoveValid(validPositionsForNonKingToMove(board, startPosition), endPosition)) {
			Piece pieceToMove = board.getBoard()[startPosition.getRow()][startPosition.getColumn()];
			String pieceType = pieceToMove.getType();
			board.getBoard()[endPosition.getRow()][endPosition.getColumn()].setType(pieceType);
			board.getBoard()[startPosition.getRow()][startPosition.getColumn()].setType("Empty");
		}
	}

	/**
	 * helper function for moveOnePieceOtherThanKing(...) 
	 * return valid positions for non-king (white/King) piece to move
	 * 
	 * @param board
	 * @param piecePosition
	 * @return valid positions for black or white piece to move
	 */

	public static ArrayList<Position> validPositionsForNonKingToMove(Board board, Position piecePosition) {

		ArrayList<Position> blackAndWhiteValidPositionsToMove = new ArrayList<Position>();
		ArrayList<Position> kingValidPositions = kingValidPositionsToMove(board, piecePosition);

		for (Position position : kingValidPositions) {
			if (position.getRow() != 4 || position.getColumn() != 4) {
				blackAndWhiteValidPositionsToMove.add(position);
			}
		}
		return blackAndWhiteValidPositionsToMove;
	}

	/**
	 * helper function for updateBoard() 
	 * Prior to using this method, we assume some piece has moved to the end position. 
	 * then, we need to check whether this move will result in capturing the nearby opposite pieces. 
	 * Thinking of different situations where any nearby piece will be removed: 
	 * 1. if the piece on the end position is black, check if any nearby white piece will be removed.
	 * If yes, remove it (if nearby piece is white, remove it directly; if King,
	 * remove king and set its position in Board to (-1, -1)) 
	 * 2. if the piece on the end position is white/king, check if any nearby black piece(s) will be
	 * removed. if yes, remove it.
	 * 
	 * @param board
	 * @param pos   
	 */
	public static void checkAndRemoveNearbyPiecesIfNeeded(Board board, Position endPosition) {
		if (BoardUtility.typeOfPieceOnPosition(board, endPosition) == 1) {
			BoardUtility.moveBlack(board, endPosition);
		} else if (BoardUtility.typeOfPieceOnPosition(board, endPosition) == 2
				|| BoardUtility.typeOfPieceOnPosition(board, endPosition) == 3) {
			BoardUtility.moveWhiteOrKing(board, endPosition);
		}
	}

	/**
	 * helper function for checkAndRemoveNearbyPiecesIfNeeded(...) 
	 * check what type of piece on board
	 * 
	 * @param board
	 * @param p
	 * @return the corresponding number based on the type of the piece
	 */

	public static int typeOfPieceOnPosition(Board board, Position p) {
		int i = p.getRow();
		int j = p.getColumn();

		if (board.getBoard()[i][j].getType().equals("Black")) {
			return 1;
		} else if (board.getBoard()[i][j].getType().equals("White")) {
			return 2;
		} else if (board.getBoard()[i][j].getType().equals("King")) {
			return 3;
		} else if (board.getBoard()[i][j].getType().equals("Empty") && p.samePosition(BoardUtility.thronePosition())) {
			return 4;
		}

		else {
			return 5;
		}

	}

	/**
	 * helper function for checkAndRemoveNearbyPiecesIfNeeded(...) 
	 * if the piece on the end position is black, check if any white/king piece around it will be removed if
	 * king is removed then update its position to (-1, -1 )
	 * 
	 * @param board
	 * @param endPosition
	 */

	public static void moveBlack(Board board, Position endPosition) {
		int[][] changeOfRowAndColoum = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

		for (int k = 0; k < 4; k++) {
			int i = endPosition.getRow() + changeOfRowAndColoum[k][0];
			int j = endPosition.getColumn() + changeOfRowAndColoum[k][1];

			if (i >= 0 && i <= 8 && j >= 0 && j <= 8) {
				if (BoardUtility.typeOfPieceOnPosition(board, new Position(i, j)) == 2) {
					int m = i + changeOfRowAndColoum[k][0];
					int n = j + changeOfRowAndColoum[k][1];

					if (m >= 0 && m <= 8 && n >= 0 && n <= 8) {
						if ((BoardUtility.typeOfPieceOnPosition(board, new Position(m, n)) == 1)
							|| BoardUtility.typeOfPieceOnPosition(board, new Position(m, n)) == 4) {

							board.getBoard()[i][j].setType("Empty");
						}
					}
				} else if (BoardUtility.typeOfPieceOnPosition(board, new Position(i, j)) == 3) {
					int m = i + changeOfRowAndColoum[k][0];
					int n = j + changeOfRowAndColoum[k][1];

					if (m >= 0 && m <= 8 && n >= 0 && n <= 8) {
						if (BoardUtility.typeOfPieceOnPosition(board, new Position(m, n)) == 1) {
							board.getBoard()[i][j].setType("Empty");
							;
							board.setKingPosition(new Position(-1, -1));
						}
					}
				}
			}
		}

	}

	/**
	 * helper function for checkAndRemoveNearbyPiecesIfNeeded(...) 
	 * if the piece on the end position is white/king, check if any black piece around it will be removed
	 * 
	 * @param board
	 * @param endPosition
	 */

	public static void moveWhiteOrKing(Board board, Position endPosition) {
		int[][] changeOfRowAndColoum = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

		for (int k = 0; k < 4; k++) {
			int i = endPosition.getRow() + changeOfRowAndColoum[k][0];
			int j = endPosition.getColumn() + changeOfRowAndColoum[k][1];

			
			if (i >= 0 && i <= 8 && j >= 0 && j <= 8) {
				if (BoardUtility.typeOfPieceOnPosition(board, new Position(i, j)) == 1) {
					int m = i + changeOfRowAndColoum[k][0];
					int n = j + changeOfRowAndColoum[k][1];

					if (m >= 0 && m <= 8 && n >= 0 && n <= 8) {
						if (BoardUtility.typeOfPieceOnPosition(board, new Position(m, n)) == 2
							|| BoardUtility.typeOfPieceOnPosition(board, new Position(m, n)) == 3
							|| BoardUtility.typeOfPieceOnPosition(board, new Position(m, n)) == 4) {
							board.getBoard()[i][j].setType("Empty");
						}
					}
				}
			}
		}
	}

	/**
	 * check if King reaches the edge of the board this method will be used to check
	 * if the game is win or not
	 * 
	 * @param board
	 * @return true if King is on the edge of the board
	 */
	public static boolean checkKingOnEdge(Board board) {

		Position king = board.getKingPosition();

		if (king.getRow() == 0 || king.getRow() == 8 || king.getColumn() == 0 || king.getColumn() == 8) {
			return true;
		}

		return false;
	}

}