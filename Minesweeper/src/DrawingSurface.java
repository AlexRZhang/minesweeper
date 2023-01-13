


import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import processing.core.PApplet;



public class DrawingSurface extends PApplet {

	private Board board;
	
	
	
	public DrawingSurface() {
		// Consider using the file reading code for testing purposes. Sometimes, it's nice to run the program and have some grid contents already present that you can
		// mess with to see if things are working. 
		board = new Board();    
		
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		
	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		background(255);   // Clear the screen with a white background
		fill(0);
		textAlign(LEFT);
		textSize(12);
		
		text("Give basic instructions: ", height+20, 30);

		board.draw(this, 0, 0, height, height);

	}
	
	
	public void mousePressed() {
		
		Cell[][] grid = board.getGrid();
		Point click = new Point(mouseX,mouseY);
		float dimension = height;
		Point cellCoord = board.clickToIndex(click,0,0,dimension,dimension);
		if (mouseButton == LEFT) {
			
			if (cellCoord != null) {
				if (grid[cellCoord.x][cellCoord.y].getVal() != -1) {
					grid[cellCoord.x][cellCoord.y].flip();
				}
				else {
					board.endGame();
				}
				
			}
			
		}
		
		else if (mouseButton == RIGHT) {
			
			if (cellCoord != null) {
				grid[cellCoord.x][cellCoord.y].mark();
				
			}
		}
	}
	
	
	// In general, it's better to use mouse interactions for interacting with the grid (like placing a piece).
	// Key interactions can be used if it really makes sense, such as for uncommon tasks (like resetting the game).
	// If you'd like to do basic gameplay interaction via keyboard, consider asking Mr Shelby about it.
	public void keyPressed() {
		if (keyCode == KeyEvent.VK_SPACE) {
			
		} else if (keyCode == KeyEvent.VK_DOWN) {
			
		} else if (keyCode == KeyEvent.VK_UP) {
			
		} else if (keyCode == KeyEvent.VK_ENTER) {
			
		}
	}

	
}










