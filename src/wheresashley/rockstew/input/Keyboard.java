package wheresashley.rockstew.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

	private boolean[] keys = new boolean [120];
	public boolean up, down, left, right;
	
	//This is where we can add more keyboard functionality
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];

		for (int i = 0; i < keys.length; i++){
			if(keys[i]) {
				System.out.println("KEY: " + i);
			}
		}
	}
	
	//Flips a boolean on when a key is pressed
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}

	//Flips a boolean off when a key is released
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	public void keyTyped(KeyEvent e) {

		
	}

}
