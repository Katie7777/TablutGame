import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PositionTest {

	/**
	 * test case 1 : p1 and p2 have same row and same column number
	 */
	@Test
	void testSamePosition1() {
		
		Position p1 = new Position(3,4);

		Position p2 = new Position(3,4);		
		
		//p1.samePosition(p2) should be true if two positions have the same row and column number
		assertEquals(true, p1.samePosition(p2));
		
		
	}
	
	/**
	 * test case 2 : p1 and p3 have same row but different columns
	 */
	@Test
	void testSamePosition2() {
		
		Position p1 = new Position(3,4);
		
		Position p3 = new Position(3,5);		
	
		//p1.samePosition(p3) should be false if two positions have the same row but different columns
		assertEquals(false, p1.samePosition(p3));
		
	}
	
	
	/**
	 * test case 3 : p1 and p4 have different rows but same column number
	 */
	@Test
	void testSamePosition3() {
		
		Position p1 = new Position(3,4);
	
		Position p4 = new Position(5,4);		
		
		//p1.samePosition(p4) should be false if two positions have different rows but same column
		assertEquals(false, p1.samePosition(p4));
		
	}
	
	/**
	 * test case 4 : p1 and p5 have different rows and different columns
	 */
	@Test
	void testSamePosition4() {
		
		Position p1 = new Position(3,4);

		Position p5 = new Position(6,7);		
		
		//p1.samePosition(p5) should be false if two positions have different rows and different columns
		assertEquals(false, p1.samePosition(p5));
		
	}

}
