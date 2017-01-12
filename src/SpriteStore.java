
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
/**
 * SpriteStore
 * <br>
 * <table><tr><td> 
 *  <table summary="">
 *   <tr>
 *      <td>Routine name|</td><td>In</td><td>|Out|</td><td>Exceptions</td>
 *      <tr>
 *      <td>get</td><td></td>SpriteStore<td>-</td><td>-</td>
 *      </tr>
 *      <tr>
 *      <td>getSprite</td><td>String</td><td>Sprite</td><td>sprite is .gif,sprite is in folder</td>
 *      </tr>
 *      <tr>
 *      <td>fail</td><td>String</td><td></td><td>-</td>
 *      </tr>

 *   </tr>
 * </table>
 * <br>
 * Assumptions: No Assumptions <br><br>
 * 
 * State Variables: 
 * <br>
 * single: SpriteStore<br>
 * creates call to SpriteStore class<br>
 * sprites: HashMap<br>
 * The cached sprite map, from reference to sprite instance<br>
 * URL:net<br>
 * link to the image from 'res' folder<br>
 * sourceImage:BufferedImage<br>
 * describes an Image with an accessible buffer of image data<br>
 * <br>
 * 
 * 
 * Environment  Variables: <br>
 * Screen: Display Device<br><br>
 * 
 * Access Routine Semantics:<br><br>
 * get():<br>
 * transition:Get the single instance of this class<br>
 * getSprite():<br>
 * transition:retrieves a sprite from the 'res' folder to be loaded and used in game<br>
 * fail():<br>
 * transition: exits window and prints error message to console<br>
 * 
 */

public class SpriteStore {

	private static SpriteStore single = new SpriteStore();
	
	public static SpriteStore get() {
		return single;
	}
	
	
	private HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	

	public Sprite getSprite(String ref) {
		// if we've already got the sprite in the cache
		// then just return the existing version
		if (sprites.get(ref) != null) {
			return (Sprite) sprites.get(ref);
		}
		
		// otherwise, go away and grab the sprite from the resource
		// loader
		BufferedImage sourceImage = null;
		
		try {
			// The ClassLoader.getResource() ensures we get the sprite
			// from the appropriate place, this helps with deploying the game
			// with things like webstart. You could equally do a file look
			// up here.
			URL url = this.getClass().getClassLoader().getResource(ref);
			
			if (url == null) {
				fail("Can't find ref: "+ref);
			}
			
			// use ImageIO to read the image in
			sourceImage = ImageIO.read(url);
		} catch (IOException e) {
			fail("Failed to load: "+ref);
		}
		
		// create an accelerated image of the right size to store our sprite in
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(sourceImage.getWidth(),sourceImage.getHeight(),Transparency.BITMASK);
		
		// draw our source image into the accelerated image
		image.getGraphics().drawImage(sourceImage,0,0,null);
		
		// create a sprite, add it the cache then return it
		Sprite sprite = new Sprite(image);
		sprites.put(ref,sprite);
		
		return sprite;
	}
	

	private void fail(String message) {
		// we're pretty dramatic here, if a resource isn't available
		// we dump the message and exit the game
		System.err.println(message);
		System.exit(0);
	}
}