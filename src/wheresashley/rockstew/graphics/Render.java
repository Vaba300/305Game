package wheresashley.rockstew.graphics;

import java.util.Random;

public class Render {
	
	private int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	public int[] tiles = new int[MAP_SIZE*MAP_SIZE];
	
	private Random random = new Random();
	

	public Render(int width, int height) 
	{
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; 					//Creates an int for every single pixel onscreen (50,400)
		
		for(int i = 0; i < MAP_SIZE*MAP_SIZE; i++){
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void render(int xOffset, int yOffset)
	{
																	//This code is modified to enable tile-by-tile scrolling instead of smooth scrolling
		for (int y = 0; y < height; y++) {							//Loops until every single pixel in both x and y
			int yy = y;	//+yOffset									//temporary y value to allow movement without changing y
			//if(yy >= height || yy < 0) break;						//If object is out of bounds in y direction, stops rendering
			for (int x = 0; x < width; x++) {						//directions on screen are colored
				int xx = x +xOffset;								//Temporary x value in order to allow movement without changing x
				//if(xx >= width || xx < 0) break; 					//If object goes out of bounds in x direction, it stops rendering
				int tileIndex = ((xx >> 4) +xOffset & MAP_SIZE_MASK) + ((yy >> 4) +yOffset & MAP_SIZE_MASK) * MAP_SIZE; //Makes every multiple of 16 represent 1 tile. This is where we can change the scale of tiles. x >> 4 means x/ 16 but is bitwise instead of standard math
				pixels[x + y * width] = tiles[tileIndex];			// &63 is there to prevent arrayIndexOutOfBounds errors. It loops back to 0 when it reaches 64 in the array
			}
		}
	}
	
	public void clear() {							//Sets every single pixel to black,
		for (int i = 0; i < pixels.length; i++){	//clearing the screen
			pixels[i] = 0;
		}
	}
	
}
