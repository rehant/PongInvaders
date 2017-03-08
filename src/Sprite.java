import java.awt.Graphics;
import java.awt.Image;
/**
 * Sprite
 * <br>
 * <table><tr><td> 
 *  <table summary="">
 *   <tr>
 *      <td>Routine name|</td><td>In</td><td>|Out|</td><td>Exceptions</td>
 *      <tr>
 *      <td>Sprite</td>Image<td></td><td>-</td><td>-</td>
 *      </tr>
 *      <tr>
 *      <td>getWidth</td><td></td><td>int</td><td>-</td>
 *      </tr>
 *      <tr>
 *      <td>getHeight</td><td></td><td>int</td><td>-</td>
 *      </tr>
 *      <tr>
 *      <td>draw</td><td>Graphics,int,int</td><td>-</td><td></td>
 *      </tr>
 *   </tr>
 * </table>
 * <br>
 * Assumptions: No Assumptions <br><br>
 * 
 * State Variables: 
 * <br>
 * image: Image<br>
 * creates call to Java's Image class<br>
 * getWidth:Image<br>
 * gets the width of image<br>
 * getHeight:Image<br>
 * gets the height of the image<br>
 * drawImage:Image<br>
 * renders the image on to the screen<br>
 * <br>
 * 
 * 
 * Environment  Variables: <br>
 * Screen: Display Device<br><br>
 * 
 * Access Routine Semantics:<br><br>
 * Sprite():<br>
 * transition:Intializes image<br>
 * getWidth():<br>
 * transition:returns width of image<br>
 * getHeight():<br>
 * transition:returns height of image<br>
 * draw():<br>
 * transition:drwas the image<br>
 * 
 * 
 */
public class Sprite {
	private Image image; // Image object for sprite

	/**
	 * Constructor. Creates a Sprite object by storing a reference to the sprite image.
	 * @param image A reference to the sprite image.
	 */
	public Sprite(Image image) {
		this.image = image;
	}

	/**
	 * Fetch sprite width.
	 * @return The sprite's width in pixels.
	 */
	public int getWidth() {
		return image.getWidth(null);
	}

	/**
	 * Fetches sprite height.
	 * @return The sprite's height in pixels.
	 */
	public int getHeight() {
		return image.getHeight(null);
	}

	/**
	 * Draws the sprite on the screen.
	 * @param g The graphics context on which to draw.
	 * @param x X co-ordinate to start drawing top-left corner at.
	 * @param y Y co-ordinate to start drawing top-left corner at.
	 */
	public void draw(Graphics g,int x,int y) {
		g.drawImage(image,x,y,null);
	}
}
