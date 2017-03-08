
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
/**
 * Game
 * <br>
 * <table><tr><td> 
 *  <table summary="">
 *   <tr>
 *      <td>Routine name|</td><td>In</td><td>|Out|</td><td>Exceptions</td>
 *      <tr>
 *      <td>Game</td><td></td><td></td><td></td>
 *      </tr>
 *      <tr>
 *      <td>start</td><td></td><td></td><td></td>
 *      </tr>
 *      <tr>
 *      <td>stop</td><td></td><td></td><td></td>
 *      </tr>
 *      <tr>
 *      <td>tick</td><td></td><td></td><td></td>
 *      </tr>
 *      <tr>
 *      <td>render</td><td></td><td></td><td></td>
 *      </tr>
 *      <tr>
 *      <td>Clamp</td><td>int,int,int</td><td></td><td></td>
 *      </tr>
 *      <td>main</td><td>String[]</td><td></td><td></td>
 *      </tr>
 *   </tr>
 * </table>
 * <br>
 * Assumptions: No Assumptions <br><br>
 * 
 * State Variables: 
 * <br>
 * WIDTH: int <br>
 * width of game window<br>
 * HEIGHT: int<br>
 * height of game window<br>
 * serialVersionUID: final int<br>
 * Gives memory identification of this class<br>
 * handler: Handler<br>
 * calls reference to Handler class
 * hud: HUD<br>
 * calls reference to HUD class
 * menu: Menus<br>
 * calls reference to Menu class
 * STATE: enum<br>
 * All states of the game <br>
 * addKeyListener :Component<br>
 * calls KeyInput class allows keys defined there to have action on screen<br>
 * addMouseListener :Component<br>
 * calls Menu class allows mouse defined there to have action on screen<br>
 * Window:Window
 * creates window on screen with a title<br>
 * thread: Thread<br>
 * thread of Game created<br>
 * running: boolean<br>
 * checks if thread is running<br>
 * lastTime: long<br>
 * gives the syatem time in nanoseconds<br>
 * amountofTicks: double<br>
 * refers to a single instance of a repeated action 
 * delta: double<br>
 * change from last frame to current<br>
 * timer: long<br>
 * current system time in milliseconds<br>
 * frames: int<br>
 * how many frames per second are shown on screen<br>
 * now: long<br>
 * this current frame<br>
 * bs: BufferStartegy<br>
 * organize complex memory on a particular Canvas or Window<br>
 * g: Graphics<br>
 * allows graphics to be drawn on to screen<br>
 * var:int<br>
 * variable to be controlled<br>
 * min:int<br>
 * minimum value variable can be<br>
 * max:int<br>
 * maximum value variable can be<br>
 * <br>
 * 
 * Environment  Variables: <br>
 * Screen: Display Device<br>
 * Keyboard: Input Device<br><br>
 * 
 * Access Routine Semantics: <br><br>
 * Game():<br>
 * transition: Initializes keyboard listener and calls Window class. In the window draws ship and alien <br>
 * start():<br>
 * transition: Initializes thread and starts it. <br>
 * stop():<br>
 * transition: Stops the thread from running <br>
 * run():<br>
 * transition: Game loop <br>
 * render():<br>
 * transition: Manages the allotment of memory by using Buffer Strategy. Renders black background on to window. <br>
 * Clamp(): <br>
 * transition: sets bound for a variable to not go below a minimum value or maximum value
 * 
 * 
 */
public class Game extends Canvas implements Runnable  {
	private static final long serialVersionUID = -1930825029999864569L;

	public static final int WIDTH = 1280, HEIGHT = WIDTH / 16*9;
	private Thread thread; // The main game thread
	private boolean running = false; // Controls game loop

	/*
	* Meta-game components.
	 */
	private Handler handler;
	private HUD hud;
	private Menu menu;

	// Possible game states
	public enum STATE {
		Menu,
		Game,
		Instructions, 
		GAMEOVER,
		WIN

	};

	public STATE gameState = STATE.Menu; // Current game state. Starts with menu.

	/**
	 * Constructor. Creates the game.
	 */
	public Game(){
		/* Create necessary objects and add them to the window. */
		handler = new Handler();
		menu = new Menu(this, handler);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		new Window(WIDTH,HEIGHT,"Pong Invaders",this);

		hud = new HUD();

	}

	/**
	 * Initialisation. Starts thread and game loop.
	 */
	public synchronized void start(){

		thread = new Thread(this);
		thread.start();
		running = true;
	}

	/**
	 * Handles thread cleanup and ending the game loop.
	 */
	public synchronized void stop(){
		try{
			thread.join();
			running = false;


		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Runs the game. Takes time differences between frames into account to ensure smooth graphics.
	 */
	public void run(){
		this.requestFocus(); // Ensure window focus stays no game

		/* Variables used to calculate time elapsed between frames */
		long lastTime = System.nanoTime();
		double amountofTicks = 60.0;
		double ns = 1000000000 / amountofTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames =0;

		while(running){ // Game loop - handles input, updating game model, and rendering.

			/* Advance game model by fixed time units as many times as necessary to take into account all elapsed time since last
			frame update */
			long now = System.nanoTime();
			delta += (now - lastTime)/ ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}

			/* Render the game objects if the game is running */
			if(running)
				render();
			frames++;

			/*
				Calculate and display FPS every second.
			 */
			if(System.currentTimeMillis() - timer> 1000){
				timer+= 1000;
				System.out.println ("FPS: " +frames);
				frames = 0;
			}
		}
		stop();

	}

	/**
	 * Handles a single "tick" - a single time step in the game world.
	 */
	private void tick(){
		handler.tick(); // Update all game objects

		if(gameState == STATE.Game){ // Only need to update objects if game is running
			hud.tick(); // Update display

			/* Handle player death by enemy or player victory by killing all aliens */
			if(gameState == Game.STATE.Game){
				if(HUD.HEALTH != 100 || Ball.PlayerDefeat == true){
					handler.clearEnemy();
					gameState = Game.STATE.GAMEOVER;
					Ball.PlayerDefeat = false;
					HUD.HEALTH = 100;
					Bullet.alienKillCount = 0;
				}

				if ((Bullet.alienKillCount == 36) && (Ball.AIDefeat == true)){
					handler.clearEnemy();
					gameState = Game.STATE.WIN;
					Ball.AIDefeat = false;
					Bullet.alienKillCount = 0;
				}
			}
			}
	}

	/**
	 * Renders objects to the screen using buffers.
	 */
	private void render(){
		/* Set up buffers to be swapped. */
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}

		Graphics g  = bs.getDrawGraphics(); // Fetch graphics object to draw ob

		/* Set up graphics object for drawing by clearing to black */
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);

		handler.render(g); // Render all game objects using the handler

		// Render the menu if the game isn't in progress
		if(gameState == STATE.Menu || gameState == STATE.Instructions || gameState == STATE.GAMEOVER || gameState == STATE.WIN){
			menu.render(g);
		}

		/* Release graphics object and show most recently-drawn data */
		g.dispose();
		bs.show();
	}

	/**
	 * Clamps a value to a range.
	 * @param var The value to clamp.
	 * @param min The minimum value to clamp this value to.
	 * @param max The maximum value to clamp this value to.
	 * @return Max if the value was >= to it, min if the value was <= to it, or the value otherwise.
	 */
	public static int Clamp(int var, int min, int max){
		if(var >= max){ // Value above max, clamp it to max
			return var = max;
		}
		else if(var <= min){ // Value below min, clamp it to min
			return var = min;
		}

		else
			return var; // Value in range, return it
	}

	/**
	 * Main method of Java program. Starts the system.
	 * @param args Command-line arguments (unused).
	 */
	public static void main (String args[]){
		new Game();
	}
}

