import java.awt.*;
/**
 *Child of GameObject
 * <br>
 * <table><tr><td> 
 *  <table summary="">
 *   <tr>
 *      <td>Routine name|</td><td>In</td><td>|Out|</td><td>Exceptions</td>
 *      <tr>
 *      <td>Ball</td><td>String,float,float,enumeration</td><td></td><td>ID has to be in ID class, Reference to sprite must be in right directory</td>
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
 *no ball sprite; however needs to be referenced as Pong is a child of Game Object <br>
 * x: float<br>
 * Is the x-position of ball on window<br>
 * y: float<br>
 * Is the y-position of ball on window<br>
 * id: enumeration<br>
 * Is the identification of which class is being referred to<br>
 * velX: float<br>
 * Is the velocity of ball in the x-direction<br>
 * velY: float<br>
 * Is the velocity of ball in the y-direction<br>
 * Clamp: Game<br>
 * Sets the bounds to which the ball can travel to on the window<br>
 * tempObject : GameObject<br>
 * Is a temporary Object of GameObject which then allows traversal of Linked List in Handler<br>
 * intersects: Rectangle<br>
 * Checks if hit boxes of two objects have intersected<br> 
 * g: Graphics<br>
 * allows graphics to be drawn on to screen<br>
 * HEIGHT:Game<br>
 * Give height of game window<br>
 * WIDTH:Game<br>
 * Give width of game window<br>
 * get : Handler<br>
 * gets index of Object in Linked list<br>
 * size : Handler<br>
 * gets size of Linked list<br>
 * setColor: Graphics<br>
 * Sets a color of the ball<br>
 * fillRect : Graphics<br>
 * Creates a box which will be ball<br>
 * alienKillCount: Bullet<br>
 * Gets the count of aliens that have died<br>
 * SetY:GameObject<br>
 * Sets the y-position of ball on the window<br>
 * dy: float<br>
* change in y-direction used to calculate which velocity ball will have<br>
* AIDefeat: boolean<br>
* check if AI has lost<br>
* PlayerDefeat :boolean
* checks if player has lost<br>
* g: Graphics<br>
* allows graphics to be drawn on to screen<br>
 *  <br>
 * 
 * Environment  Variables: <br>
 * None<br><br>
 * 
 * Access Routine Semantics: <br>
 * Ball():<br>
 * transition: Initializes reference to Sprite, x position y position and ID of Ball, and this instance of handler  <br>
 * tick():<br>
 * transition: Allows game objects to be placed in game loop <br>
 * collision():<br>
 * transition: Checks if ball hits alien,or ball controlled paddle or, AI controlled paddle<br>
 * render():<br>
 * transition: Will render object to screen <br>
 * getBounds():<br>
 * transition: Gets bounds of the ball to set up for collision <br>
 */	



public class Ball extends GameObject {

	Handler handler;

	public static boolean AIDefeat = false;
	public static boolean PlayerDefeat = false;

	public Ball(String ref,float x, float y, ID id, Handler handler) {
		super(ref,x, y, id);
		this.handler = handler;
		setY(Game.HEIGHT-100);
		velX = -4;
		velY =2;
		Game.Clamp((int)velY, -10, 10);
	}

	public void tick() {
		x += velX;
		y -= velY; // Negative velY because the top of the screen is 0
		if(y < 0||y > Game.HEIGHT -75){
			velY = -velY;
		}

		// If the ball passes the player pong paddle, enter game loss
		if(x < -20) {
			//handler.removeObject(this);
			PlayerDefeat = true;
		}

		// If the ball passes the AI pong paddle, enable potential to win
		if(x > Game.WIDTH) {
			//handler.removeObject(this);
			AIDefeat = true;
		}

		collision();
	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int)x, (int)y, 15, 15);
	}

	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			// If tempObject is of ID.Alien, and if the alien intersects the ball, it is deleted
			if(tempObject.getId() == ID.Alien) {

				if(getBounds().intersects(tempObject.getBounds())) {

					handler.removeObject(tempObject);
					Bullet.alienKillCount++;
					
					// Change x-velocity of the ball to go in the opposite direction
					velY = -velY;
				}
			}

			// If tempObject is of ID.Pong, and if the pong paddle intersects the ball, the ball is deflected
			if(tempObject.getId() == ID.Pong) {

				if(x<=0){
					if(y > tempObject.y && y < (tempObject.y+90)){
						velX = -velX;
						float dy = y - (tempObject.y + 45);
						velY = (float) (dy *0.2);
					}
					
					
				}
				
				

			}
			if(tempObject.getId() == ID.AI) {

				if(x > Game.WIDTH-19){
					if(y > tempObject.y && y < (tempObject.y+90)){
						velX = -velX;
						float dy = y - (tempObject.y + 45);
						velY = (float) (dy *0.2);
					}
			}
		}
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 15, 15);
	}
}