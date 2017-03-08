import static org.junit.Assert.*;

import org.junit.Test;

/**
 * JUnit test for the Menu class.
 */
public class MenuTest {

	@Test
	//test check if mouse has proper hit detection with mouse
	public void test() {
		Game game = new Game(); // Create model
		Handler handler = new Handler(); // Create game-object handler
		Menu menu = new Menu(game, handler); // Create test menu  object
		boolean result = menu.mouseOver(50, 30, 20, 20, 200, 200); // Simulate a mouse over action and check if it worked.
		System.out.println(result);
		assertEquals(true,result); // The mouse should be over a button
	}

}
