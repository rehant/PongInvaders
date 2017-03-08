

import java.awt.Graphics;
import java.util.LinkedList;
/**
 * Handler
 * <br>
 * <table><tr><td> 
 *  <table summary="">
 *   <tr>
 *      <td>Routine name|</td><td>In</td><td>|Out|</td><td>Exceptions</td>
 *      <tr>
 *      <td>tick</td><td></td><td>-</td><td>-</td>
 *      </tr>
 *      <tr>
 *      <td>render</td><td>Graphics</td><td>-</td><td>-</td>
 *      </tr>
 *      <tr>
 *      <td>addObject</td><td>GameObject</td><td>-</td><td>-</td>
 *      </tr>
 *      <tr>
 *      <td>removeObject</td><td>GameObject</td><td>-</td><td>Object is currently in list</td>
 *      </tr>
 *      <tr>
 *      <td>clearEnemy</td><td></td><td>-</td><td>-</td>
 *      </tr>
 *   </tr>
 * </table>
 * <br>
 * Assumptions: No Assumptions <br><br>
 * 
 * State Variables: 
 * <br>
 * object: LinkedList <br>
 * Linked List created<br>
 * g: Graphics<br>
 * allows graphics to be drawn on to screen<br>
 * tempObject : GameObject<br>
 * Is a temporary Object of GameObject which then allows traversal of Linked List in Handler<br>
 * get : LinkedList<br>
 * gets index of Object in Linked list<br>
 * size : LinkedList<br>
 * gets size of Linked list<br>
 * tick: GameObject<br>
 * uses tick method of GameObject for tempObject<br>
 * render: GameObject<br>
 * uses render method of GameObject for tempObject<br>
 * clear:LinkedList<br>
 * Clears linked list<br>
 * add:LinkedList<br>
 * add GameObject to linked list<br>
 * remove: LinkedList<br>
 * removes GameObject from linked list<br>
 * <br>
 * 
 * 
 * 
 * 
 * 
 * Environment  Variables: <br>
 * Screen: Display Device<br><br>
 * 
 * Access Routine Semantics:<br><br>
 * tick():<br>
 * transition: Uses game loop to dynamically add game objects in to a linked list.<br>
 * render(g):<br>
 * transition: Renders game objects on to screen.<br>
 * clearEnemy:<br>
 * transition: Clears the linked list<br>
 * addObject(object):<br>
 * transition: Adds this instance of game object to linked list.<br>
 * removeObject(object):<br>
 * transition: Removes this instance of game object to linked list.<br>
 * 
 * 
 */
public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>(); // List of game objects in the game

	/**
	 * Handles a single frame update by having each object handle it.
	 */
	public void tick(){
		for (int i = 0; i< object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}

	/**
	 * Draws the objects on the graphics object.
	 * @param g The graphics object on which to draw.
	 */
	public void render (Graphics g){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	/**
	 * Destroys the player when it comes into contact with an enemy.
	 */
	public void clearEnemy() {
		for (int i =0; i<object.size(); i++){
			GameObject tempObject = object.get(i);

			if (tempObject.getId()== ID.Player){
				object.clear();
			}
		}
	}

	/**
	 * Adds an object to the list of objects in the game.
	 * @param object The game object to add.
	 */
	public void addObject(GameObject object){
		this.object.add(object);

	}

	/**
	 * Removes a game object from the list of objects in the game.
	 * @param object The game object to remove.
	 */
	public void removeObject(GameObject object){
		this.object.remove(object);
	}

}
