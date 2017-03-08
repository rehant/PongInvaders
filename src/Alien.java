import java.awt.*; // Graphics classes

/**
 * Child of GameObject
 * <br>
 * <table><tr><td> 
 *  <table summary="">
 *   <tr>
 *      <td>Routine name|</td><td>In</td><td>|Out|</td><td>Exceptions</td>
 *      <tr>
 *      <td>Player</td><td>String,float,float,enumeration</td><td></td><td>ID has to be in ID class, Reference to sprite must be in right directory</td>
 *      </tr>
 *      <tr>
 *      <tr>
 *      <td>collisionR</td><td>-</td><td>-</td><td></td>
 *      </tr>
 *      <tr>
 *      <td>collisionL</td><td>-</td><td>-</td><td></td>
 *      </tr>
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
 * State Variables: 
 * <br>
 *handler:Handler<br>
 *reference to Handler class<br>
 *ref : String<br>
 *Is the reference to which directory the sprite of Alien is located<br>
 * x: float<br>
 * Is the x-position of player on window<br>
 * y: float<br>
 * Is the y-position of player on window<br>
 * id: enumeration<br>
 * Is the identification of which class is being referred to<br>
 * velX: float<br>
 * Is the velocity of Alien in the x-direction<br>
 * velY: float<br>
 * Is the velocity of Alien in the y-direction<br>
 * Clamp: Game<br>
 * Sets the bounds to which the Player can travel to on the window<br>
 * tempObject : GameObject<br>
 * Is a temporary Object of GameObject which then allows traversal of Linked List in Handler<br>
 * intersects: Rectangle<br>
 * Checks if hit boxes of two objects have intersected<br> 
 * Health:HUD<br>
 * Is the health of the player<br>
 * g: Graphics<br>
 * allows graphics to be drawn on to screen<br>
 * draw:Sprite<br>
 * renders the sprite on to screen<br>
 * HEIGHT:Game<br>
 * Give height of game window<br>
 * WIDTH:Game<br>
 * Give width of game window<br>
 * get : Handler<br>
 * gets index of Object in Linked list<br>
 * size : Handler<br>
 * gets size of Linked list<br>
 * <br>
 * 
 * Environment  Variables: <br>
 * None<br><br>
 * 
 * Access Routine Semantics: <br>
 * Alien():<br>
 * transition: Initializes reference to Sprite, x position y position and ID of Player, and this instance of handler  <br>
 * tick():<br>
 * transition: Allows game objects to be placed in game loop <br>
 * collisionR():<br>
 * transition: Checks if alien has hit the right wall<br>
 * collisionL():<br>
 * transition: Checks if alien has hit the right wall<br>
 * render():<br>
 * transition: Will render object to screen <br>
 * getBounds():<br>
 * transition: Gets bounds of the Alien to set up for collision <br>
 */	
public class Alien extends GameObject {

	Handler handler; // Object which controls all game objects
	private int alienDrop = -29; // Amount to drop the other alien by in the event of a collision with one

	/**
	 * Constructor which creates an Alien object.
	 * @param ref String containing path to sprite file.
	 * @param x Alien's initial x position.
	 * @param y Alien's initial y position.
	 * @param id ID for game handler.
	 * @param handler The game handler which will handle this alien.
	 */
	public Alien(String ref, float x, float y, ID id, Handler handler) {

		/**
		 * Save variables, re-using parent's code in doing so.
		 */
		super(ref,x, y, id);
		this.handler = handler;
		velX = 5;
	}


	/**
	 * Handle a frame update
	 */
	public void tick() {
		/**
		 * Update positions according to our velocities.
		 */
		x += velX;
		y += velY;

		// If an alien reaches the sides of the window, then invert velX and shift y down
		if(x >= Game.WIDTH-60) {
			collisionR();
		}

		if(x <= 10) {
			collisionL();
		}

		// Game State Change
		if(y >= Game.HEIGHT-100){
			velX = 0;
			x = 32;
		}
	}

	/**
	 * Handles a collision with the right side of the sprite.
	 */
	private void collisionR() {
		/**
		 * Check if this object collided with an alien. If so, reverse it's direction on the X axis and drop it down the screen.
		 */
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId() == ID.Alien) {
					tempObject.velX = -velX;
					tempObject.y -= alienDrop;
				}
			}
		}

	/**
	 * Handles a collision with the left side of the sprite.
	 */
	private void collisionL() {
		/**
		 * Check if this object collided with an alien. If so, reverse it's direction on the X axis and drop it down the screen.
		 */
		for(int i = handler.object.size()-1; i > 0; --i) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Alien) {
				tempObject.velX = -velX;
				tempObject.y -= alienDrop;
			}
		}
		// Band-aid fix for first alien object not updating properly on collision, no obvious way to fix as of now.
		GameObject tempObject = handler.object.get(4);
		tempObject.x += 10;
	}

	/**
	 * Renders the alien sprite on the Java Graphics object.
	 * @param g The Java Graphics object to draw on.
	 */
	public void render(Graphics g) {
		sprite.draw(g,(int) x,(int) y);
	}

	/**
	 * Fetches this object's collision bounds.
	 * @return A rectangle representing the 2D area on the screen which this alien object occupies.
	 */
	public Rectangle getBounds() {
		return new Rectangle((int)x + 4,(int)y ,34, 28);
	}

}
