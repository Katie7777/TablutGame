import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

	public Board b = new Board();
	/**
	 * tests the initial board setting
	 */
	@Test
	void SimulatedGameTest() {
		
		String boardState = "    -    -    -    B    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    B    -    -    -    W    -    -    -    B\r\n" + 
				"    B    B    W    W    K    W    W    B    B\r\n" + 
				"    B    -    -    -    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    B    B    B    -    -    -\r\n" + 
				"**************************************************";
		assertEquals(b.displayBoard(), boardState);
	

	}
	
	
	/**
	 * Test case 1: black , white, black  -> black captures white with another black
	 */
	
	@Test
    void SimulatedGameTest1() {
		Position p1 = new Position(0, 3);
		Position p2 = new Position(3, 3);
		b.updateBoard(p1, p2);
		b.updateBoard(new Position(8, 3), new Position(5, 3));
		
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    B    -    -    B    W    -    -    -    B\r\n" + 
				"    B    B    W    -    K    W    W    B    B\r\n" + 
				"    B    -    -    B    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");
	
	}
	
	/**
     * Test case 1: black , white, black  -> black captures white with another black (second move)
     */
	@Test
    void SimulatedGameTest2() {
	    Position p1 = new Position(0, 3);
        Position p2 = new Position(3, 3);
        b.updateBoard(p1, p2);
        b.updateBoard(new Position(8, 3), new Position(5, 3));
		b.updateBoard(new Position(5,3), new Position(4,3));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    B    -    -    B    W    -    -    -    B\r\n" + 
				"    B    B    -    B    K    W    W    B    B\r\n" + 
				"    B    -    -    -    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");
    }
	
	/**Test case 2: white, black, white ->  white captures black with another white (first move)
	 * 
	 */
	@Test
    void SimulatedGameTest3() {
	    Position p1 = new Position(0, 3);
        Position p2 = new Position(3, 3);
        b.updateBoard(p1, p2);
        b.updateBoard(new Position(8, 3), new Position(5, 3));
        b.updateBoard(new Position(5,3), new Position(4,3));
		b.updateBoard(new Position(2,4), new Position(2,2));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    W    -    -    -    -    -    -\r\n" + 
				"    B    -    -    B    W    -    -    -    B\r\n" + 
				"    B    B    -    B    K    W    W    B    B\r\n" + 
				"    B    -    -    -    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");
	}
	/**Test case 2: white, black, white ->  white captures black with another white (second move)
     * 
     */	
	@Test
    void SimulatedGameTest4() {
	    Position p1 = new Position(0, 3);
        Position p2 = new Position(3, 3);
        b.updateBoard(p1, p2);
        b.updateBoard(new Position(8, 3), new Position(5, 3));
        b.updateBoard(new Position(5,3), new Position(4,3));
        b.updateBoard(new Position(2,4), new Position(2,2));
		b.updateBoard(new Position(2,2), new Position(3,2));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    -    -    -    -    -\r\n" + 
				"    B    -    W    -    W    -    -    -    B\r\n" + 
				"    B    B    -    B    K    W    W    B    B\r\n" + 
				"    B    -    -    -    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");
	}
	
	
	/**Test case 3: white, black, king -> white captures black with the king
	 * 
	 */
	
	@Test
    void SimulatedGameTest5() {
		Position p1 = new Position(0, 3);
	    Position p2 = new Position(3, 3);
	    b.updateBoard(p1, p2);
	    b.updateBoard(new Position(8, 3), new Position(5, 3));
	    b.updateBoard(new Position(5,3), new Position(4,3));
	    b.updateBoard(new Position(2,4), new Position(2,2));
	    b.updateBoard(new Position(2,2), new Position(3,2));
	 	b.updateBoard(new Position(3,2), new Position(4,2));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    -    -    -    -    -\r\n" + 
				"    B    -    -    -    W    -    -    -    B\r\n" + 
				"    B    B    W    -    K    W    W    B    B\r\n" + 
				"    B    -    -    -    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");
	
	}
	
	
	/**Test case 4: white, black, throne -> white captures black with the empty throne
	 * 
	 */
	
	@Test
    void SimulatedGameTest6() {
		Position p1 = new Position(0, 3);
	    Position p2 = new Position(3, 3);
	    b.updateBoard(p1, p2);
	    b.updateBoard(new Position(8, 3), new Position(5, 3));
	    b.updateBoard(new Position(5,3), new Position(4,3));
	    b.updateBoard(new Position(2,4), new Position(2,2));
	    b.updateBoard(new Position(2,2), new Position(3,2));
	 	b.updateBoard(new Position(3,2), new Position(4,2));
	 	//moving 3 steps
	 	b.updateBoard(new Position(4,4), new Position(4,3));
		b.updateBoard(new Position(4,2), new Position(3,2));
		b.updateBoard(new Position(4,3), new Position(5,3));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    -    -    -    -    -\r\n" + 
				"    B    -    W    -    W    -    -    -    B\r\n" + 
				"    B    B    -    -    -    W    W    B    B\r\n" + 
				"    B    -    -    K    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");


	}
	
	/**Test case 4: white, black, throne -> white captures black with the empty throne
	 * 
	 */
	
	@Test
    void SimulatedGameTest7() {
		Position p1 = new Position(0, 3);
	    Position p2 = new Position(3, 3);
	    b.updateBoard(p1, p2);
	    b.updateBoard(new Position(8, 3), new Position(5, 3));
	    b.updateBoard(new Position(5,3), new Position(4,3));
	    b.updateBoard(new Position(2,4), new Position(2,2));
	    b.updateBoard(new Position(2,2), new Position(3,2));
	 	b.updateBoard(new Position(3,2), new Position(4,2));
	 	//moving 3 steps
	 	b.updateBoard(new Position(4,4), new Position(4,3));
		b.updateBoard(new Position(4,2), new Position(3,2));
		b.updateBoard(new Position(4,3), new Position(5,3));
		//moving 1 step
		b.updateBoard(new Position(4,1), new Position(4,3));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    -    -    -    -    -\r\n" + 
				"    B    -    W    -    W    -    -    -    B\r\n" + 
				"    B    -    -    B    -    W    W    B    B\r\n" + 
				"    B    -    -    K    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");

	}
	
	/**Test case 4: white, black, throne -> white captures black with the empty throne
	 * 
	 */
	
	@Test
    void SimulatedGameTest8() {
		Position p1 = new Position(0, 3);
	    Position p2 = new Position(3, 3);
	    b.updateBoard(p1, p2);
	    b.updateBoard(new Position(8, 3), new Position(5, 3));
	    b.updateBoard(new Position(5,3), new Position(4,3));
	    b.updateBoard(new Position(2,4), new Position(2,2));
	    b.updateBoard(new Position(2,2), new Position(3,2));
	 	b.updateBoard(new Position(3,2), new Position(4,2));
	 	//moving 3 steps
	 	b.updateBoard(new Position(4,4), new Position(4,3));
		b.updateBoard(new Position(4,2), new Position(3,2));
		b.updateBoard(new Position(4,3), new Position(5,3));
		//moving 1 step
		b.updateBoard(new Position(4,1), new Position(4,3));
		//moving 1 step
		b.updateBoard(new Position(3,2), new Position(4,2));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    -    -    -    -    -\r\n" + 
				"    B    -    -    -    W    -    -    -    B\r\n" + 
				"    B    -    W    -    -    W    W    B    B\r\n" + 
				"    B    -    -    K    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");

	}
	
	/**Test Case 5: king, black, throne -> king captures black with the empty throne
	 * 
	 */
	
	@Test
    void SimulatedGameTest9() {
		Position p1 = new Position(0, 3);
	    Position p2 = new Position(3, 3);
	    b.updateBoard(p1, p2);
	    b.updateBoard(new Position(8, 3), new Position(5, 3));
	    b.updateBoard(new Position(5,3), new Position(4,3));
	    b.updateBoard(new Position(2,4), new Position(2,2));
	    b.updateBoard(new Position(2,2), new Position(3,2));
	 	b.updateBoard(new Position(3,2), new Position(4,2));
	 	b.updateBoard(new Position(4,4), new Position(4,3));
		b.updateBoard(new Position(4,2), new Position(3,2));
		b.updateBoard(new Position(4,3), new Position(5,3));
		b.updateBoard(new Position(4,1), new Position(4,3));
		//revert the last step
		//b.updateBoard(new Position(3,2), new Position(4,2));
		b.updateBoard(new Position(5,3), new Position(5,2));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    -    -    -    -    -\r\n" + 
				"    B    -    W    -    W    -    -    -    B\r\n" + 
				"    B    -    -    B    -    W    W    B    B\r\n" + 
				"    B    -    K    -    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");
		
		
	

	}
	
	/**Test Case 5: king, black, throne -> king captures black with the empty throne
	 * 
	 */
	
	@Test
    void SimulatedGameTest10() {
		Position p1 = new Position(0, 3);
	    Position p2 = new Position(3, 3);
	    b.updateBoard(p1, p2);
	    b.updateBoard(new Position(8, 3), new Position(5, 3));
	    b.updateBoard(new Position(5,3), new Position(4,3));
	    b.updateBoard(new Position(2,4), new Position(2,2));
	    b.updateBoard(new Position(2,2), new Position(3,2));
	 	b.updateBoard(new Position(3,2), new Position(4,2));
	 	b.updateBoard(new Position(4,4), new Position(4,3));
		b.updateBoard(new Position(4,2), new Position(3,2));
		b.updateBoard(new Position(4,3), new Position(5,3));
		b.updateBoard(new Position(4,1), new Position(4,3));
		//revert the last step
		//b.updateBoard(new Position(3,2), new Position(4,2));
		b.updateBoard(new Position(5,3), new Position(5,2));		
		b.updateBoard(new Position(5,2), new Position(4,2));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    -    -    -    -    -\r\n" + 
				"    B    -    W    -    W    -    -    -    B\r\n" + 
				"    B    -    K    -    -    W    W    B    B\r\n" + 
				"    B    -    -    -    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");
		
		
	

	}
	
	/**Test Case 6: black, king, black -> black captures king with black
	 * 
	 */
	@Test
    void SimulatedGameTest11() {
		Position p1 = new Position(0, 3);
	    Position p2 = new Position(3, 3);
	    b.updateBoard(p1, p2);
	    b.updateBoard(new Position(8, 3), new Position(5, 3));
	    b.updateBoard(new Position(5,3), new Position(4,3));
	    b.updateBoard(new Position(2,4), new Position(2,2));
	    b.updateBoard(new Position(2,2), new Position(3,2));
	 	b.updateBoard(new Position(3,2), new Position(4,2));
	 	b.updateBoard(new Position(4,4), new Position(4,3));
		b.updateBoard(new Position(4,2), new Position(3,2));
		b.updateBoard(new Position(4,3), new Position(5,3));
		b.updateBoard(new Position(4,1), new Position(4,3));
		//revert the last step
		//b.updateBoard(new Position(3,2), new Position(4,2));
		b.updateBoard(new Position(5,3), new Position(5,2));		
		b.updateBoard(new Position(5,2), new Position(4,2));
		//moving 2 steps
		b.updateBoard(new Position(4,0), new Position(4,1));
		b.updateBoard(new Position(5,0), new Position(5,3));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    -    -    -    -    -\r\n" + 
				"    B    -    W    -    W    -    -    -    B\r\n" + 
				"    -    B    K    -    -    W    W    B    B\r\n" + 
				"    -    -    -    B    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");		
	

	}
	
	/**Test Case 6: black, king, black -> black captures king with black
	 * 
	 */
	@Test
    void SimulatedGameTest12() {
		Position p1 = new Position(0, 3);
	    Position p2 = new Position(3, 3);
	    b.updateBoard(p1, p2);
	    b.updateBoard(new Position(8, 3), new Position(5, 3));
	    b.updateBoard(new Position(5,3), new Position(4,3));
	    b.updateBoard(new Position(2,4), new Position(2,2));
	    b.updateBoard(new Position(2,2), new Position(3,2));
	 	b.updateBoard(new Position(3,2), new Position(4,2));
	 	b.updateBoard(new Position(4,4), new Position(4,3));
		b.updateBoard(new Position(4,2), new Position(3,2));
		b.updateBoard(new Position(4,3), new Position(5,3));
		b.updateBoard(new Position(4,1), new Position(4,3));
		//revert the last step
		//b.updateBoard(new Position(3,2), new Position(4,2));
		b.updateBoard(new Position(5,3), new Position(5,2));		
		b.updateBoard(new Position(5,2), new Position(4,2));
		//moving 2 steps
		b.updateBoard(new Position(4,0), new Position(4,1));
		b.updateBoard(new Position(5,0), new Position(5,3));
		// moving 1 step
		b.updateBoard(new Position(5,3), new Position(4,3));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    -    -    -    -    -\r\n" + 
				"    B    -    W    -    W    -    -    -    B\r\n" + 
				"    -    B    -    B    -    W    W    B    B\r\n" + 
				"    -    -    -    -    W    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");
	

	}
	
	
	/**Test Case 7: black, white, throne -> black captures white with the empty throne
	 * 
	 */
	@Test
    void SimulatedGameTest13() {
		Position p1 = new Position(0, 3);
	    Position p2 = new Position(3, 3);
	    b.updateBoard(p1, p2);
	    b.updateBoard(new Position(8, 3), new Position(5, 3));
	    b.updateBoard(new Position(5,3), new Position(4,3));
	    b.updateBoard(new Position(2,4), new Position(2,2));
	    b.updateBoard(new Position(2,2), new Position(3,2));
	 	b.updateBoard(new Position(3,2), new Position(4,2));
	 	b.updateBoard(new Position(4,4), new Position(4,3));
		b.updateBoard(new Position(4,2), new Position(3,2));
		b.updateBoard(new Position(4,3), new Position(5,3));
		b.updateBoard(new Position(4,1), new Position(4,3));
		b.updateBoard(new Position(5,3), new Position(5,2));		
		b.updateBoard(new Position(5,2), new Position(4,2));		
		b.updateBoard(new Position(4,0), new Position(4,1));
		b.updateBoard(new Position(5,0), new Position(5,3));		
		b.updateBoard(new Position(5,3), new Position(4,3));
		//moving 4 steps
		b.updateBoard(new Position(4,3), new Position(4,2));
		b.updateBoard(new Position(4,2), new Position(6,2));
		b.updateBoard(new Position(5,4), new Position(5,3));
		b.updateBoard(new Position(5,3), new Position(4,3));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    -    -    -    -    -\r\n" + 
				"    B    -    W    -    W    -    -    -    B\r\n" + 
				"    -    B    -    W    -    W    W    B    B\r\n" + 
				"    -    -    -    -    -    -    -    -    B\r\n" + 
				"    -    -    B    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");

	

	}
	
	
	/**Test Case 7: black, white, throne -> black captures white with the empty throne
	 * 
	 */
	@Test
    void SimulatedGameTest14() {
		Position p1 = new Position(0, 3);
	    Position p2 = new Position(3, 3);
	    b.updateBoard(p1, p2);
	    b.updateBoard(new Position(8, 3), new Position(5, 3));
	    b.updateBoard(new Position(5,3), new Position(4,3));
	    b.updateBoard(new Position(2,4), new Position(2,2));
	    b.updateBoard(new Position(2,2), new Position(3,2));
	 	b.updateBoard(new Position(3,2), new Position(4,2));
	 	b.updateBoard(new Position(4,4), new Position(4,3));
		b.updateBoard(new Position(4,2), new Position(3,2));
		b.updateBoard(new Position(4,3), new Position(5,3));
		b.updateBoard(new Position(4,1), new Position(4,3));
		b.updateBoard(new Position(5,3), new Position(5,2));		
		b.updateBoard(new Position(5,2), new Position(4,2));		
		b.updateBoard(new Position(4,0), new Position(4,1));
		b.updateBoard(new Position(5,0), new Position(5,3));		
		b.updateBoard(new Position(5,3), new Position(4,3));
		//moving 4 steps
		b.updateBoard(new Position(4,3), new Position(4,2));
		b.updateBoard(new Position(4,2), new Position(6,2));
		b.updateBoard(new Position(5,4), new Position(5,3));
		b.updateBoard(new Position(5,3), new Position(4,3));
		//moving 1 step
		b.updateBoard(new Position(6,2), new Position(4,2));
		assertEquals(b.displayBoard(), "    -    -    -    -    B    B    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    -    -    -    -    -\r\n" + 
				"    B    -    W    -    W    -    -    -    B\r\n" + 
				"    -    B    B    -    -    W    W    B    B\r\n" + 
				"    -    -    -    -    -    -    -    -    B\r\n" + 
				"    -    -    -    -    W    -    -    -    -\r\n" + 
				"    -    -    -    -    B    -    -    -    -\r\n" + 
				"    -    -    -    -    B    B    -    -    -\r\n" + 
				"**************************************************");

	

	}
	

}
