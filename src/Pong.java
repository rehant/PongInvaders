import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/**
 *Child of GameObject
 * <br>
 * <table><tr><td> 
 *  <table summary="">
 *   <tr>
 *      <td>Routine name|</td><td>In</td><td>|Out|</td><td>Exceptions</td>
 *      <tr>
 *      <td>Pong </td><td>String,float,float,enumeration</td><td></td><td>ID has to be in ID class, Reference to sprite must be in right directory</td>
 *      </tr>
 *      <tr>
 *      <td>tick</td><td>-</td><td>-</td><td></td>
 *      </tr>
 *      <tr>
 *      <td>render</td><td>Graphics</td><td></td><td></td>
 *      </tr>
 *      <tr>
 *      <td>getBounds</td><td>-</td><td>Rectangle</td><td></td>
 *      </tr>
 *      <tr>
 *   </tr>
 * </table>
 * <br>
 * Assumptions: No Assumptions <br><br>
 * 
 *
 *State Variables:
 * <br>
 *handler:Handler<br>
 *reference to Handler class<br>
 *ref : String<br>
 *no paddle sprite; however needs to be referenced as Pong is a child of Game Object <br>
 * x: float<br>
 * Is the x-position of pong paddle on window<br>
 * y: float<br>
 * Is the y-position of pong paddle on window<br>
 * id: enumeration<br>
 * Is the identification of which class is being referred to<br>
 * velX: float<br>
 * Is the velocity of Pong paddle in the x-direction<br>
 * velY: float<br>
 * Is the velocity of Pong paddle in the y-direction<br>
 * Clamp: Game<br>
 * Sets the bounds to which the pong paddle can travel to on the window<br>
 * intersects: Rectangle<br>
 * Checks if hit boxes of two objects have intersected<br> 
 * g: Graphics<br>
 * allows graphics to be drawn on to screen<br>
 * setColor: Graphics<br>
 * Sets a color of the paddle<br>
 * fillRect : Graphics<br>
 * Creates a box which will be paddle<br>
 *  <br>
 * 
 * Environment  Variables: <br>
 * None<br><br>
 * 
 * Access Routine Semantics: <br>
 * Pong():<br>
 * transition: Initializes reference to Sprite, x position y position and ID of Pong, and this instance of handler  <br>
 * tick():<br>
 * transition: Allows game objects to be placed in game loop <br>
 * render():<br>
 * transition: Will render object to screen <br>
 * getBounds():<br>
 * transition: Gets bounds of the Pong paddle to set up for collision <br>
 */	
public class Pong extends GameObject {

	Handler handler; // Reference to object which handles all game objects.

	/**
	 * Creates a Pong paddle object.
	 * @param ref String containing filesystem path to paddle sprite.
	 * @param x Initial x co-ordinate.
	 * @param y Initial y co-ordinate.
	 * @param id Object ID.
	 * @param handler Reference to master game object handler.
	 */
	public Pong(String ref,float x, float y, ID id, Handler handler) {
		super(ref,x, y, id); // Construct the game object
		this.handler = handler; // Save a reference to our handler
	}

	/**
	 * Handles a single frame update by dropping the paddle down, ensuring that it doesn't go below the viewport.
	 */
	public void tick() {
		y -= velY; // Negative velY because the top of the screen is 0
		y = Game.Clamp((int)y, 0, Game.HEIGHT-169);
	}

	/**
	 * Draws the paddle on the screen.
	 * @param g The graphics interface on which to draw.
	 */
	public void render(Graphics g) {		
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 12, 90);
	}

	/**
	 * Calculates and fetches paddle bounds for collision detection.
	 * @return A Rectangle object describing the area occupied on screen by the paddle.
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y+7 , 12,74 );
	}

}