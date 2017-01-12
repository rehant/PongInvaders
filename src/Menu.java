import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *Menu
 * <br>
 * <table><tr><td> 
 *  <table summary="">
 *   <tr>
 *      <td>Routine name|</td><td>In</td><td>|Out|</td><td>Exceptions</td>
 *      <tr>
 *      <td>Menu</td><td>Game,Handler</td><td></td><td></td>
 *      </tr>
 *      <tr>
 *      <td>MousePressed</td><td>MouseEvent</td><td>-</td><td></td>
 *      </tr>
 *      <tr>
 *      <td>mouseOver</td><td>int,int,int,int,int,int</td><td>boolean</td><td></td>
 *      </tr>
 *      <tr>
 *      <td>makeOption</td><td>int,Color,String,int,int,int,Graphics</td><td></td><td></td>
 *      </tr>
 *      <tr>
 *      <td>makeOption</td><td>int,Color,String,int,int,Graphics</td><td></td><td></td>
 *      </tr>
 *      <tr>
 *      <td>render</td><td>Graphics</td><td>-</td><td></td>
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
 *handler:Handler<br><br>
 *reference to Handler class<br><br>
 * game:Game<br><br>
 *reference to Game class<br><br>
 *ref : String<br><br>
 *no paddle sprite; however needs to be referenced as Pong is a child of Game Object <br><br>
 *e:MouseEvent<br><br>
 *mouse action component<br><br>
 *mx: int<br><br>
 *gives x-position of mouse location<br><br>
 *my: int<br><br>
 *gives y-position of mouse location<br><br>
 *STATE: enum<br><br>
 *all states of game<br><br>
 * add:LinkedList<br><br>
 * add GameObject to linked list<br><br>
 * remove: LinkedList<br><br>
 * removes GameObject from linked list<br><br>
 * ID: enumeration<br><br>
 * Is the identification of which class is being referred to<br><br>
 * HEIGHT:Game<br><br>
 * Give height of game window<br><br>
 * WIDTH:Game<br><br>
 * Give width of game window<br><br>
 * x: int<br><br>
 * Is the x-position of menu box<br><br>
 * y: int<br><br>
 * Is the y-position of the menu box<br><br>
 * height: int<br><br>
 * Is the height of menu box<br><br>
 * width: int<br><br>
 * Is the width of the menu box<br><br>
 * fnt: Font<br><br>
 * Type of font wanted to write for in menu<br><br>
 * fntSize: int<br><br>
 * Size of font wanted to write for in menu<br><br>
 * Color: AWT<br><br>
 * Sets a color of the menu option<br><br>
 * drawRect : Graphics<br><br>
 * Creates a box which will be menu options<br><br>
 * option:String<br><br>
 * the text to write in menu option<br><br>
 * posX: int<br><br>
 * Is the x-position of string to be placed in menu<br><br>
 * posY: int<br><br>
 * Is the y-position of string to be placed in menu<br><br>
 * dHeight: int<br><br>
 * change in height with regards to change in resolution of the game window<br><br>
 * g: Graphics<br><br>
 * allows graphics to be drawn on to screen<br><br>
 * title:String<br><br>
 * title of menu option box <br><br>
 * menuBoxHeight:int<br><br>
 * height of menu box<br><br>
 * menuBoxX:int<br><br>
 * x position of menu box<br><br>
 * menuBoxY:int<br><br>
 * y postion of menu box<br><br>
 * menuBoxWidth:int<br><br>
 * width of menu box<br>
 *<br>
 * 
 * Environment  Variables: <br>
 * Screen: Display Device<br><br>
 * 
 * Access Routine Semantics: <br>
 * Menu():<br>
 * transition: Initializes reference to Game and this instance of handler  <br>
 * mousePressed():<br>
 * transition: checks if parts of menu clicked <br>
 * mouseOver():<br>
 * transition: checks if mouse is currently hovering over menu options, returns boolean value<br>
 * makeOption():<br>
 * transition: creates box where menu option is drawn<br>
 * makeTitle():<br>
 * transition: creates the title displayed on menu option box<br>
 * render():<br>
 * transition: renders menu <br>
 */	

public class Menu extends MouseAdapter{

	private Game game; 
	private Handler handler;
	private int menuBoxHeight = 200;
	private int menuBoxX = Game.WIDTH/2-(menuBoxHeight/2); 
	private int menuBoxY = Game.HEIGHT/4; 
	private int menuBoxWidth = 64;

	public Menu(Game game, Handler handler){
		this.game = game; 
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();

		if(game.gameState == Game.STATE.Menu){

			if(mouseOver(mx, my, menuBoxX, menuBoxY, menuBoxHeight, menuBoxWidth)){
				game.gameState = Game.STATE.Game;
				handler.addObject(new Player("sprites/ship.gif",Game.WIDTH/2-32,Game.HEIGHT-70,ID.Player, handler));
				handler.addObject(new Pong("sprites/ship.gif",0, Game.HEIGHT/2-90, ID.Pong, handler));
				handler.addObject(new AI("sprites/ship.gif",Game.WIDTH-19, Game.HEIGHT/2-90, ID.AI, handler));
				handler.addObject(new Ball("sprites/ship.gif",Game.WIDTH/2-32, Game.HEIGHT-100, ID.Ball, handler));
				for(int i = 0; i<12; i++){
					for(int j = 0; j<3; j++) {
						handler.addObject(new Alien("sprites/alien.gif",70+(i*40),0+(j*40),ID.Alien, handler));
					}
				}
			}

			//Instructions menu option
			if(mouseOver(mx, my, menuBoxX, menuBoxY+100, menuBoxHeight, menuBoxWidth)){
				game.gameState = Game.STATE.Instructions;
			}

			//Quit menu option
			if(mouseOver(mx, my, menuBoxX,menuBoxY+200, menuBoxHeight, menuBoxWidth)){
				System.exit(1);
			}
		}
		//Back Button1
		if(game.gameState == Game.STATE.Instructions){
			if (mouseOver(mx, my, menuBoxX, menuBoxY+400, menuBoxHeight, menuBoxWidth)){
				game.gameState = Game.STATE.Menu;
				return;
			}
		}

		//Back Button2
		if(game.gameState == Game.STATE.GAMEOVER || game.gameState == Game.STATE.WIN){
			if (mouseOver(mx, my, menuBoxX, menuBoxY+200, menuBoxHeight, menuBoxWidth)){
				game.gameState = Game.STATE.Menu;
				return;
			}
		}
	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;  
		}else return false;
	}


	private void makeOption(int fntSize, Color colour, String option, int posX, int posY, int dHeight, Graphics g){
		Font fnt = new Font("arial", 1, fntSize);
		g.setFont(fnt);

		g.setColor(colour);
		g.drawRect(menuBoxX, menuBoxY+dHeight, menuBoxHeight, menuBoxWidth);
		g.drawString(option, posX, posY);
	}

	private void makeTitle(int fntSize, Color colour, String title, int posX, int posY, Graphics g){
		Font fnt = new Font("arial", 1, fntSize);
		g.setFont(fnt);

		g.setColor(colour);
		g.drawString(title, posX, posY);
	}

	public void render(Graphics g){
		if(game.gameState == Game.STATE.Menu){

			makeTitle(125, Color.white, "PONG INVADERS", Game.WIDTH/2-525, 150, g);
			makeOption(30, Color.white, "Start", Game.WIDTH/2-35, menuBoxY+menuBoxWidth/2+12, 0, g);
			makeOption(30, Color.white, "Instructions", Game.WIDTH/2-85, menuBoxY+menuBoxWidth/2+112, 100, g);
			makeOption(30, Color.red, "Quit", Game.WIDTH/2-30, menuBoxY+menuBoxWidth/2+212, 200, g);

		}else if(game.gameState == Game.STATE.Instructions){


			makeTitle(50, Color.white, "Instructions", Game.WIDTH/2-140, 100, g);

			makeTitle(30, Color.white, "Ship: A, D = Left, Right", Game.WIDTH/2-500, menuBoxY, g);
			makeTitle(30, Color.white, "Paddle: W, S = Up, Down", Game.WIDTH/2-500, menuBoxY+80, g);
			makeTitle(30, Color.white, "Spacebar = Shoot", Game.WIDTH/2-500, menuBoxY+160, g);
			makeTitle(27, Color.white, "Win Condition: Stop all aliens from landing and the	 ball passes enemy paddle", Game.WIDTH/2-500, menuBoxY+240, g);
			makeTitle(27, Color.white, "Aliens make a landing or the ball passes your paddle then GAME OVER", Game.WIDTH/2-500, menuBoxY+320, g);

			makeOption(30, Color.white, "Back", Game.WIDTH/2-33, menuBoxY+menuBoxWidth/2+412, 400, g);

		}
		else if(game.gameState == Game.STATE.GAMEOVER){

			makeTitle(200, Color.red, "GAME OVER", Game.WIDTH/2-625, 250, g);
			makeOption(30, Color.white, "Back", Game.WIDTH/2-33, menuBoxY+menuBoxWidth/2+212, 200, g);
		}
		else if(game.gameState == Game.STATE.WIN){

			makeTitle(200, Color.YELLOW, "VICTORY", Game.WIDTH/2-425, 250, g);
			makeOption(30, Color.white, "Back", Game.WIDTH/2-33, menuBoxY+menuBoxWidth/2+212, 200, g);

		}
	}
}