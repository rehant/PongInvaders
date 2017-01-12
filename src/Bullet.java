import java.awt.*;
/**
 *Child of GameObject
 * <br>
 * <table><tr><td> 
 *  <table summary="">
 *   <tr>
 *      <td>Routine name|</td><td>In</td><td>|Out|</td><td>Exceptions</td>
 *      <tr>
 *      <td>Bullet</td><td>String,float,float,enumeration</td><td></td><td>ID has to be in ID class, Reference to sprite must be in right directory</td>
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
 *alienKillCount: int<br>
 *Gets the count of aliens that have died <br>
 *ref : String<br>
 *Is the reference to which directory the sprite of bullet is located<br>
 * x: float<br>
 * Is the x-position of Bullet on window<br>
 * y: float<br>
 * Is the y-position of Bullet on window<br>
 * id: enumeration<br>
 * Is the identification of which class is being referred to<br>
 * velX: float<br>
 * Is the velocity of bullet in the x-direction<br>
 * velY: float<br>
 * Is the velocity of bullet in the y-direction<br>
 * Clamp: Game<br>
 * Sets the bounds to which the Bullet can travel to on the window<br>
 * tempObject : GameObject<br>
 * Is a temporary Object of GameObject which then allows traversal of Linked List in Handler<br>
 * intersects: Rectangle<br>
 * Checks if hit boxes of two objects have intersected<br> 
 * Health:HUD<br>
 * Is the health of the Bullet<br>
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
 * Bullet():<br>
 * transition: Initializes reference to Sprite, x position y position and ID of Bullet, and this instance of handler  <br>
 * tick():<br>
 * transition: Allows game objects to be placed in game loop <br>
 * collision():<br>
 * transition: Checks if alien has hit the bullet, if so deletes bullet and alien<br>
 * render():<br>
 * transition: Will render object to screen <br>
 * getBounds():<br>
 * transition: Gets bounds of the bullet to set up for collision <br>
 */	
public class Bullet extends GameObject {

	Handler handler;

	public static int alienKillCount = 0;
	
	public Bullet(String ref,float x, float y, ID id, Handler handler) {
		super(ref,x, y, id);
		this.handler = handler;
	}

	public void tick() {
		//x += velX;
		y += -5;
		if(y <= 20) {
			handler.removeObject(this);
		}
		collision();
	}

	public void render(Graphics g) {
		sprite.draw(g,(int)x,(int)y);
	}

	private void collision() {
		for(int i= 0; i<handler.object.size(); i++) {

			// If tempObject is of ID.Alien, and if the alien intersects the bullet, it is deleted
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Alien) {

				if(getBounds().intersects(tempObject.getBounds())) {

					handler.removeObject(tempObject);
					handler.removeObject(this);
					alienKillCount++;
				}
			}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x +1, (int) y ,10, 17);
	}
}