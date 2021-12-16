import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TablutGameUtilityTest {

	/**
	 * test the king is not on the edge of the board
	 */
	@Test
	void testIfKingOnEdgeOfTheBoard1() {

		Board b = new Board();

		// moving King to the edge of the board
		b.updateBoard(new Position(4, 7), new Position(5, 7));
		b.updateBoard(new Position(4, 6), new Position(5, 6));
		b.updateBoard(new Position(4, 5), new Position(5, 5));
		b.updateBoard(new Position(3, 8), new Position(2, 8));

		// when the King is not on the edge, the ifKingOnEdgeOfTheBoard(b) method
		// should return false
		assertEquals(false, TablutGameUtility.ifKingOnEdgeOfTheBoard(b));

	}

	/**
	 * test the king is on the edge of the board
	 */
	@Test
	void testIfKingOnEdgeOfTheBoard2() {

		Board b = new Board();

		// moving King to the edge of the board
		b.updateBoard(new Position(4, 7), new Position(5, 7));
		b.updateBoard(new Position(4, 6), new Position(5, 6));
		b.updateBoard(new Position(4, 5), new Position(5, 5));
		b.updateBoard(new Position(3, 8), new Position(2, 8));

		// keep moving king until it's on the edge
		b.updateBoard(new Position(4, 8), new Position(3, 8));
		b.updateBoard(new Position(4, 4), new Position(4, 8));

		// when the king is on the edge, the ifKingOnEdgeOfTheBoard(b) method
		// should return true
		assertEquals(true, TablutGameUtility.ifKingOnEdgeOfTheBoard(b));

	}

	/**
	 * test the king is not captured
	 */
	@Test
	void testIsKingCaptured1() {
		Board b = new Board();

		// moving pieces to let black piece captures the king
		b.updateBoard(new Position(4, 7), new Position(3, 7));
		b.updateBoard(new Position(4, 6), new Position(3, 6));

		// before the king is captured, isKingCaptured(b) should return false
		assertEquals(false, TablutGameUtility.isKingCaptured(b));

	}

	/**
	 * test the king is captured
	 */
	@Test
	void testIsKingCaptured2() {
		Board b = new Board();

		// moving pieces to let black piece captures the king
		b.updateBoard(new Position(4, 7), new Position(3, 7));
		b.updateBoard(new Position(4, 6), new Position(3, 6));

		// keep moving pieces until the King is captured
		b.updateBoard(new Position(4, 5), new Position(3, 5));
		b.updateBoard(new Position(4, 4), new Position(4, 7));
		b.updateBoard(new Position(5, 8), new Position(5, 7));

		// when the King is captured, isKingCaptured(b) should return true
		assertEquals(true, TablutGameUtility.isKingCaptured(b));

	}

	/**
	 * test the king has legal moves
	 */
	@Test
	void testKingHasNoLegalMove1() {
		Board b = new Board();

		// moving pieces to surround the King
		b.updateBoard(new Position(0, 3), new Position(0, 1));
		b.updateBoard(new Position(1, 4), new Position(1, 2));
		b.updateBoard(new Position(3, 0), new Position(1, 0));
		b.updateBoard(new Position(4, 0), new Position(2, 0));
		b.updateBoard(new Position(2, 0), new Position(2, 2));
		b.updateBoard(new Position(5, 0), new Position(2, 0));
		b.updateBoard(new Position(3, 4), new Position(3, 5));
		b.updateBoard(new Position(4, 4), new Position(3, 4));
		b.updateBoard(new Position(3, 4), new Position(3, 1));
		b.updateBoard(new Position(3, 1), new Position(1, 1));
		b.updateBoard(new Position(4, 1), new Position(3, 1));

		// King is moving from Position(1,1) to Position(2,1) first time
		b.updateBoard(new Position(1, 1), new Position(2, 1));

		// King moves back forth 2nd time
		b.updateBoard(new Position(2, 1), new Position(1, 1));

		// King moves back forth 3rd time
		b.updateBoard(new Position(1, 1), new Position(2, 1));

		// if the king moves back and forth between two squares less than or equal to 5
		// times
		// kingHasNoLeagalMove(b) should return false
		assertEquals(false, TablutGameUtility.kingHasNoLegalMove(b));

	}

	/**
	 * test the king has no legal moves
	 */
	@Test
	void testKingHasNoLegalMove2() {
		Board b = new Board();

		// moving pieces to surround the King
		b.updateBoard(new Position(0, 3), new Position(0, 1));
		b.updateBoard(new Position(1, 4), new Position(1, 2));
		b.updateBoard(new Position(3, 0), new Position(1, 0));
		b.updateBoard(new Position(4, 0), new Position(2, 0));
		b.updateBoard(new Position(2, 0), new Position(2, 2));
		b.updateBoard(new Position(5, 0), new Position(2, 0));
		b.updateBoard(new Position(3, 4), new Position(3, 5));
		b.updateBoard(new Position(4, 4), new Position(3, 4));
		b.updateBoard(new Position(3, 4), new Position(3, 1));
		b.updateBoard(new Position(3, 1), new Position(1, 1));
		b.updateBoard(new Position(4, 1), new Position(3, 1));

		// King is moving from Position(1,1) to Position(2,1) first time
		b.updateBoard(new Position(1, 1), new Position(2, 1));

		// King moves back forth 2nd time
		b.updateBoard(new Position(2, 1), new Position(1, 1));

		// King moves back forth 3rd time
		b.updateBoard(new Position(1, 1), new Position(2, 1));

		// King moves back forth 4th time
		b.updateBoard(new Position(2, 1), new Position(1, 1));

		// King moves back forth 5th time
		b.updateBoard(new Position(1, 1), new Position(2, 1));

		// King moves back forth 6th time
		b.updateBoard(new Position(2, 1), new Position(1, 1));

		/// if the king moves back and forth between two squares more than 5 times
		// kingHasNoLeagalMove(b) should return true
		assertEquals(true, TablutGameUtility.kingHasNoLegalMove(b));

	}

	/**
	 * test when there are black pieces on board
	 */
	@Test
	void testNoBlackPiece1() {
		Board b = new Board();

		// The game board should have black pieces before the game starts
		// so noBlackPiece(b) should be false
		assertEquals(false, TablutGameUtility.noBlackPiece(b));

	}

	/**
	 * test when there's no black piece on board
	 */
	@Test
	void testNoBlackPiece2() {
		Board b = new Board();

		// loop all positions and if there is any position has "Black" piece
		// change it type to "Empty"
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (b.getBoard()[i][j].getType().equals("Black")) {
					b.getBoard()[i][j].setType("Empty");
				}
			}
		}

		// after the loop, there's no black piece left and
		// noBlackPiece(b) should be true
		assertEquals(true, TablutGameUtility.noBlackPiece(b));

	}

}
