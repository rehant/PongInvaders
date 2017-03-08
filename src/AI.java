/* Graphics classes */
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
 *      <td>AI</td><td>String,float,float,enumeration</td><td></td><td>ID has to be in ID class, Reference to sprite must be in right directory</td>
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
 *ball: Ball<br>
 *reference to Ball class<br>
 *ref : String<br>
 *no paddle sprite; However needs to be referenced as AI is a child of Game Object <br>
 * x: float<br>
 * Is the x-position of AI paddle on window<br>
 * y: float<br>
 * Is the y-position of AI paddle on window<br>
 * id: enumeration<br>
 * Is the identification of which class is being referred to<br>
 * velX: float<br>
 * Is the velocity of AI paddle in the x-direction<br>
 * velY: float<br>
 * Is the velocity of AI paddle in the y-direction<br>
 * Clamp: Game<br>
 * Sets the bounds to which the AI paddle can travel to on the window<br>
 * intersects: Rectangle<br>
 * Checks if hit boxes of two objects have intersected<br> 
 * g: Graphics<br>
 * allows graphics to be drawn on to screen<br>
 * setColor: Graphics<br>
 * Sets a color of the paddle<br>
 * fillRect : Graphics<br>
 * Creates a box which will be paddle<br>
 * paddle2Ycenter: float
 * gets value of middle of paddle
 * tempObject : GameObject<br>
 * Is a temporary Object of GameObject which then allows traversal of Linked List in Handler<br>
 *  <br>
 * 
 * Environment  Variables: <br>
 * None<br><br>
 * 
 * Access Routine Semantics: <br>
 * AI():<br>
 * transition: Initializes reference to Sprite, x position y position and ID of AI, and this instance of handler  <br>
 * tick():<br>
 * transition: Allows game objects to be placed in game loop, and decides how to track ball to hit back to player controlled paddle <br>
 * render():<br>
 * transition: Will render object to screen <br>
 * getBounds():<br>
 * transition: Gets bounds of the AI paddle to set up for collision <br>
 */
public class AI extends GameObject {

	/**
	 * References to game handler and pong ball.
	 */
	Handler handler;
	Ball ball;

	/**
	 * Constructor. Creates a new AI opponent object.
	 * @param ref A reference to the file where the AI sprite is located.
	 * @param x Starting x position.
	 * @param y Starting y position.
	 * @param id Object ID for game controller.
	 * @param handler A reference to the main handler for the game.
	 */
	public AI(String ref, float x, float y, ID id, Handler handler) {
		/* Store variables, re-using parent code where possible */
		super(ref, x, y, id);
		this.handler = handler;
		velY=3;
	}

	/**
	 * Handles a frame update.
	 */
	public void tick() {
		/**
		 * Fetch a reference to the AI's paddle and calculate the y co-ordinate of its center.
		 */
		GameObject tempObject = handler.object.get(3);
		float paddle2Ycenter = y + 45;

		/**
		 * Change our position depending on whether we are too far above or below the ball.
		 */
		if(paddle2Ycenter < tempObject.y ){
			y+=velY;
		}
		else if(paddle2Ycenter > tempObject.y){
			y-=velY;
		}
		y = Game.Clamp((int)y, 0, Game.HEIGHT-169); // Ensure that our y position remains within acceptable bounds
	}

	/**
	 * Render the AI object.
	 * @param g The graphics object on which to draw the AI sprite.
	 */
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, 12, 90);
		
	}

	/**
	 * Fetches bounds for collision checking.
	 * @return A Rectangle object representing the 2D area occupied by the AI sprite on screen.
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x-1, (int)y +7 , 12,74 );
	}


}
