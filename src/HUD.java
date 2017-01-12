import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	/**
	 * HUD
	 * <br>
	 * <table><tr><td> 
	 *  <table summary="">
	 *   <tr>
	 *      <td>Routine name|</td><td>In|</td><td>Out|</td><td>Exceptions</td>
	 *      <tr>
	 *      <td>tick</td><td>-</td><td>-</td><td>-</td>
	 *      </tr>
	 *   </tr>
	 * </table>
	 * <br>
	 * Assumptions: No Assumptions <br><br>
	 * 
	 * 
	 * State Variables: <br>
	 * Health: static int <br>
	 * Health made to make end game condition to happen<br><br> 
	 * 
	 * Environment  Variables: <br>
	 * Screen: Display Device<br><br>
	 * 
	 * Access Routine Semantics: <br><br>
	 * tick:<br>
	 * Function calls clamp method in Game class, and basically it makes sure that Health never goes below 0.
	 * 
	 * 
	 * 
	 */
	
	public static int HEALTH = 100;
	
	public void tick(){
		
		HEALTH = Game.Clamp(HEALTH, 0, 100);
	}
	
	
}
