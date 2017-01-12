import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.Test;

public class KeyInputTest {
	//checks if key not presses
	@Test
	public void test() {
		Handler handler = new Handler();
		KeyInput keyInput = new KeyInput(handler);
		int e = KeyEvent.VK_A;
		boolean result = keyInput.keyDown[1];
		System.out.println(result);
		
		
		assertEquals(false,result);
	}

}
