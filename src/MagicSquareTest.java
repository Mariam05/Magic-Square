import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MagicSquareTest {

	MagicSquare ms;

	@Before
	public void setUp() throws Exception {
		ms = new MagicSquare();
	}

	/**
	 * Test for illegal input 
	 */
	@Test
	public void testIllegalInput() {
		ms.addNumber(0, 0, -1); // try to input -1
		assertEquals(0, ms.getValueOfSquare(0, 0)); // check if it processed the requests (if it didn't value will still
													// be 0)

		ms.addNumber(0, 0, ms.LIMIT + 1); // try to input 1 more than the limit
		assertEquals(0, ms.getValueOfSquare(0, 0));
	}

	/**
	 * Test for whether the program correctly checks if the grid is in winnign state 
	 */
	@Test
	public void testSolved() {
		// The correct solution to the 3x3
		int[] correctVals = { 2, 7, 6, 9, 5, 1, 4, 3, 8 };

		int k = 0;
		for (int i = 0; i < ms.SIZE; i++) {
			for (int j = 0; j < ms.SIZE; j++) {
				ms.addNumber(i, j, correctVals[k]); //add the correct values
				k++;
			}
		}

		assertTrue(ms.checkWon());
		
		ms.resetGame(); //clean the slate for the incorrect values

		// an incorrect solution to the 3x3
		int[] incorrectVals = { 2, 8, 3, 9, 5, 1, 4, 3, 7 };

		k = 0;
		for (int i = 0; i < ms.SIZE; i++) {
			for (int j = 0; j < ms.SIZE; j++) {
				ms.addNumber(i, j, incorrectVals[k]);
				k++;
			}
		}

		assertFalse(ms.checkWon());

	}

}
