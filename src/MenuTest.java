import static org.junit.Assert.*;

import org.junit.Test;

public class MenuTest {

	@Test
	//test check if mouse has proper hit detection with mouse
	public void test() {
		Game game = new Game();
		Handler handler = new Handler();
		Menu menu = new Menu(game, handler);
		boolean result = menu.mouseOver(50, 30, 20, 20, 200, 200);
		System.out.println(result);
		assertEquals(true,result);
	}

}
