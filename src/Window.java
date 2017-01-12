

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{


	private static final long serialVersionUID = -650585914685456797L;
/**
 * Window
 * <br>
 * <table><tr><td> 
 *  <table summary="">
 *   <tr>
 *      <td>Routine name|</td><td>In|</td><td>Out|</td><td>Exceptions</td>
 *      <tr>
 *      <td>Window</td><td>int,int,String,Game</td><td>-</td><td>Width and Height will not be negative value</td>
 *      </tr>
 *   </tr>
 * </table>
 * <br>
 * Assumptions: No Assumptions <br><br>
 * 
 * 
 * 
 * 
 * State Variables: <br>
 * width: int <br>
 * Width of the window<br>
 * height: int<br>
 * Height of the Window<br>
 * title: String<br>
 * Title placed on top of Window<br>
 * game: Game<br>
 * Reference to Game Class, Game class is where window is drawn<br><br> 
 * 
 * Environment  Variables: <br>
 * Screen: Display Device<br><br>
 * 
 * Access Routine Semantics: <br><br>
 * Window(width,height,title,game):<br>
 * transition: A window is created in middle of screen with exit button by resolution and title defined in Game class.
 * 
 * 
 * 
 */
	public Window (int width, int height, String title, Game game){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		//allows to close game
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//makes window not sizeable
		frame.setResizable(false);
		//draws window in middle of screen
		frame.setLocationRelativeTo(null);
		//adds game class in to frame
		frame.add(game);
		//frame can be seen
		frame.setVisible(true);
		game.start();
		
	}
	
}