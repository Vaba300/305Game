package wheresashley.rockstew;

import javax.swing.JFrame;
//import java.awt.*;
//import java.awt.image.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import wheresashley.rockstew.graphics.*;
import wheresashley.rockstew.input.Keyboard;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L; //Gets rid of certain annoying yellow errors
	
	public static int width = 300; //All adjustments in Dimensions will be done in increments of 300 in 16:9 resolution
	public static int height = width/16 * 9; //Sets game to 16:9 aspect ratio.
	public static int scale = 3;
	public static String title = "Rock Stew 0.0.1a";
	
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private boolean running = false;

	private Render screen;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //Holds image data before it's drawn to the screen
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	//Creates an image to draw onto, and allows writing data to it
	//by storing data for every single pixel in the game (in integer form)
	
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		
		screen = new Render(width,height);
		frame = new JFrame();
		
		key = new Keyboard();
		frame.addKeyListener(key);
	}
	
	public synchronized void start() { 								//Creates new display, sets game to running mode, starts game thread.
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public synchronized void stop() { 								//Stops game threads, ending the game.
		running = false;
		
		try{
			thread.join();
		} catch (InterruptedException e) { 							//If the game freezes, it will throw this exception
			e.printStackTrace();
		}
	}
	
	public void run() { 											//Keeps game running forever unless stop()ped. Because Game implements runnable, when the thread() is started, the run()method starts too
		long lastTime = System.nanoTime();							//Lets us use Java's internal precise time
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 12.0;						//Sets a cap (in nanoseconds) for how many times the game can update. Right now it can update 6 times a second. 
		double delta = 0;
		int frameCounter = 0;
		int tickCounter = 0;
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;											//Updates lastTime to the "real" current system time.
			while (delta >= 1){										//Wait until 1/60 of a second passes and then update the game. This is where we can make it turn based.
				tick();												//Updates logic of game once every second.
				tickCounter++;
				delta--;
			}
			render(); 												//Updates game graphics
			frameCounter++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(tickCounter + "ups," + frameCounter + "fps");
				frame.setTitle(title + "  |  " + tickCounter + " ticks/sec, " + frameCounter + " fps"); //Puts a tick and fps counter in the title bar
				frameCounter = 0;
				tickCounter = 0;
			}
		}
		stop();
	}
	int x=0, y=0;
	public void tick() { 											//Updates entities behavior after a time. 
		key.update();

		if(key.up) y-=1;											//Moves your view up, down, left or right by 1 tile
		if(key.down) y+=1;											
		if(key.left)x-=1;
		if(key.right)x+=1;
	}
	
	
	public void render() { 											//Draws elements in the game on a loop
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {											//Only creates a buffer strategy if it's not present in the Canvas object
 			createBufferStrategy(3); 								//Enables triple buffering to speed up drawing 
			return;
		}
		
		screen.clear();												//Clears the screen, and then
		screen.render(x, y);											//Displays the image on top of the black background
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
			
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());		
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.dispose(); 												//Gets rid of current graphics
		bs.show();   												//Shows the contents of the next available buffer
		
	}
	
	public static void main(String[] args){ //Program starts here
		Game game = new Game();
		game.frame.setTitle(Game.title); 					//Sets window title
		game.frame.setResizable(false); 							//Disables resizing to avoid graphical errors when set to false
		game.frame.add(game); 										//Fills the window with Game object (this class)
		game.frame.pack(); 											//Sets size of frame to same size as "size" on line 21
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //When we hit the close button, it will close
		game.frame.setVisible(true); 								//Makes the window visible
		
		//ADD FULLSCREEN FUNCTIONALITY HERE, keyboardlistener required to make F11 function.
		//game.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//game.frame.setUndecorated(true);
		//game.frame.setLocation(null); 							//Centers window on screen.
		
		game.start();												//Starts the game!
	}
	
}
