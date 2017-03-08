

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
 *      <td>Player</td><td>String,float,float,enumeration</td><td></td><td>ID has to be in ID class, Reference to sprite must be in right directory</td>
 *      </tr>
 *      <tr>
 *      <td>tick</td><td>-</td><td>-</td><td></td>
 *      </tr>
 *       <tr>
 *      <td>collision</td><td>-</td><td>-</td><td></td>
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
 *Is the reference to which directory the sprite of ship is located<br>
 * x: float<br>
 * Is the x-position of player on window<br>
 * y: float<br>
 * Is the y-position of player on window<br>
 * id: enumeration<br>
 * Is the identification of which class is being referred to<br>
 * velX: float<br>
 * Is the velocity of ship in the x-direction<br>
 * velY: float<br>
 * Is the velocity of ship in the y-direction<br>
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
 *  <br>
 * 
 * Environment  Variables: <br>
 * None<br><br>
 * 
 * Access Routine Semantics: <br>
 * Player():<br>
 * transition: Initializes reference to Sprite, x position y position and ID of Player, and this instance of handler  <br>
 * tick():<br>
 * transition: Allows game objects to be placed in game loop <br>
 * collision():<br>
 * transition: Checks if alien has hit the ship<br>
 * render():<br>
 * transition: Will render object to screen <br>
 * getBounds():<br>
 * transition: Gets bounds of the ship to set up for collision <br>
 */	
public class Player extends GameObject {

	Handler handler; // Object handler

	/**
	 * Construct a player object.
	 * @param ref String containing path to player sprite image.
	 * @param x Initial x position.
	 * @param y Initial y position.
	 * @param id Game object ID.
	 * @param handler Game object handler.
	 */
	public Player(String ref,float x, float y, ID id, Handler handler) {
		super(ref,x, y, id);
		this.handler = handler;
	}

	/**
	 * Handles a single frame update by moving the player forward while keeping it within the screen. Handles collision detection as well.
	 */
	public void tick() {
		x += velX;
		x = Game.Clamp((int)x, 3, Game.WIDTH-39);
		collision();
	}

	/**
	 * Checks for collision with any other object. If the player hits an alien, they lose health.
	 */
	private void collision(){
		for(int i= 0; i<handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Alien){//tempObject is BasicEnemy
				//collision code
				if(getBounds().intersects(tempObject.getBounds())){
					//collision code
					HUD.HEALTH -=50;
					}
				}

		}
	}

	/**
	 * Draws the player sprite on the screen.
	 * @param g The graphics interface on which to draw.
	 */
	public void render(Graphics g) {
		sprite.draw(g,(int) x,(int) y);
	}

	/**
	 * Fetches a Rectangle object describing the player's collision bounds.
	 * @return A Rectangle object describing the area which the player occupies on the screen.
	 */
	public Rectangle getBounds() {
		return new Rectangle(0, Game.HEIGHT-75 ,Game.WIDTH, 32);
}


}
